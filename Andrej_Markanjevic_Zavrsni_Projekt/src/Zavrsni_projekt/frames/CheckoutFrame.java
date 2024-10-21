package Zavrsni_projekt.frames;

import Zavrsni_projekt.app.FileHandler;
import Zavrsni_projekt.classes.Car;
import Zavrsni_projekt.classes.Receipt;
import Zavrsni_projekt.classes.User;
import Zavrsni_projekt.enums.PaymentEnum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** Last frame in the dealership where we confirm customers purchase.
 * @author Andrej Markanjević
 */
public class CheckoutFrame extends JFrame {

    private Car car;
    private User loggedUser;
    private JLabel paymentOptionLabel;
    private JButton returnButton;
    private JButton proceedButton;
    private JComboBox paymentBox;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JPanel contentPane;
    private JButton homeButton;

    /** Creates a CheckoutFrame object.
     * @param loggedUser One that's currently logged in.
     * @param car One that is being bought.
     */
    public CheckoutFrame(User loggedUser,Car car){
        this.loggedUser = loggedUser;
        this.car = car;
        initCheckoutFrame();
        layoutCheckoutFrame();
        activateCheckoutFrame();
    }

    /** Creates the components for my frame.
     */
    public void initCheckoutFrame(){
        paymentOptionLabel = new JLabel("Payment Options: ");
        returnButton = new JButton("Return");
        proceedButton = new JButton("Proceed");
        paymentBox = new JComboBox(PaymentEnum.values());
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contentPane = new JPanel();
        homeButton = new JButton(new ImageIcon("src/homeButton.png"));
    }

    /** Aside from making sure everything is placed on the frame properly, this method is also used to edit the looks of the compartments.
     */
    public void layoutCheckoutFrame(){
        setTitle("Checkout");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        paymentOptionLabel.setBounds(100, 280, 120, 25);
        returnButton.setBounds(230, 320, 120, 25);
        proceedButton.setBounds(360, 320, 120, 25);
        paymentBox.setBounds(230, 280, 120, 25);
        scrollPane.setBounds(30, 20, 430, 240);
        homeButton.setBounds(30,320,20,20);

        textArea.setEditable(false);
        setupPanelInfo(car);

        contentPane.add(paymentOptionLabel);
        contentPane.add(returnButton);
        contentPane.add(proceedButton);
        contentPane.add(paymentBox);
        contentPane.add(scrollPane);
        contentPane.add(homeButton);
    }

    /** Filled with ActionListeners, the purpose of this method is to give functionalities to various parts of the frame.
     */
    public void activateCheckoutFrame(){
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarDealershipFrame cdf = new CarDealershipFrame(loggedUser,car);
                dispose();
            }
        });
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"You bought the car!");
                ArrayList<User> users = FileHandler.loadUsersFromFile("./src/Users.bin");

                for (int i = 0; i < users.size();i++){
                    if (loggedUser.compareTo(users.get(i)) == 1){
                        users.get(i).addCar(car);
                        loggedUser = users.get(i);
                        break;
                    }
                }
                Receipt receipt = new Receipt(loggedUser, car, paymentBox.getSelectedItem().toString(),Integer.parseInt(car.getPrice()));
                ArrayList<Receipt> receipts = FileHandler.loadReceiptsFromFile("./src/Receipts.bin");
                receipts.add(receipt);

                FileHandler.writeReceiptsToFile("./src/Receipts.bin",receipts);
                FileHandler.writeAllUsersToFile("./src/Users.bin",users);

                returnButton.setEnabled(false);
                proceedButton.setEnabled(false);
                paymentBox.setEnabled(false);
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                dispose();
            }
        });
    }

    /** This method is used to determine what is going to be written on the JTextArea.
     */
    private void setupPanelInfo(Car car){
        textArea.setText("");
        textArea.setText("~~~~ Your receipt : ~~~~~\n");
        textArea.append("\n");
        textArea.append("   * Car Brand: '" + car.getBrand() + "'\n");
        textArea.append("   * Car Model: '"+ car.getModel() + "'\n");
        textArea.append("   * Car Color: '"+ car.getCarColour() + "'\n");
        textArea.append("   * Seat Color: '"+ car.getSeatsColour() + "'\n");
        textArea.append("   * Car Engine: '"+ car.getMotorName() + "'\n");
        textArea.append("   * Car Tires: '"+ car.getTireType() + "'\n");
        textArea.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        textArea.append("   * Car Price: '"+ car.getPrice() + "' $\n");
    }
}
