package Zavrsni_projekt.frames;

import Zavrsni_projekt.app.FileHandler;
import Zavrsni_projekt.classes.Car;
import Zavrsni_projekt.classes.User;
import Zavrsni_projekt.enums.CarBrands;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** In this frame you choose the brand and model you want your car to be.
 * @author Andrej Markanjević
 */
public class CarDealershipFrame extends JFrame{

    private Car car;
    private User user;
    private JLabel carBrandLabel;
    private JLabel carModelLabel;

    /** A JComboBox filled with brands from the enum.
     */
    private JComboBox<CarBrands> carBrandBox;

    /** A JComboBox filled with models from the enum.
     */
    private JComboBox<String> carModelBox;
    private JButton viewButton;
    private JButton customButton;
    private JButton checkoutButton;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JPanel contentPane;
    private JButton homeButton;

    /** Creates a CarDealershipFrame objects,starts the frame.
     * @param user One user is logged in.
     * @param car One car is being customized currently.
     */
    public CarDealershipFrame(User user,Car car){
        this.user = user;
        this.car = car;
        initHomeFrame();
        layoutHomeFrame();
        activateHomeFrame();
    }

    /** Creates the components for my frame.
     */
    public void initHomeFrame() {
        carBrandLabel = new JLabel("Car Brand: ");
        carModelLabel = new JLabel("Car Model: ");
        carBrandBox = new JComboBox(CarBrands.values());
        carModelBox = new JComboBox();
        viewButton = new JButton("Preview");
        customButton = new JButton("Customize");
        checkoutButton = new JButton("Checkout");
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contentPane = new JPanel();
        homeButton = new JButton(new ImageIcon("src/homeButton.png"));
    }

    /** Aside from making sure everything is placed on the frame properly, this method is also used to edit the looks of the compartments.
     */
    public void layoutHomeFrame() {
        setTitle("Home");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        carBrandLabel.setBounds(40, 80, 100, 25);
        carModelLabel.setBounds(40, 130, 100, 25);
        carBrandBox.setBounds(150, 80, 120, 25);
        carModelBox.setBounds(150, 130, 120, 25);
        viewButton.setBounds(100, 180, 100, 25);
        customButton.setBounds(260, 310, 100, 25);
        checkoutButton.setBounds(370, 310, 100, 25);
        scrollPane.setBounds(280, 40, 200, 260);
        homeButton.setBounds(20,20,20,20);

        textArea.setEditable(false);
        viewButton.setEnabled(false);
        customButton.setEnabled(false);
        checkoutButton.setEnabled(false);
        carModelBox.setEnabled(false);
        carBrandBox.setSelectedIndex(-1);

        contentPane.add(carBrandLabel);
        contentPane.add(carModelLabel);
        contentPane.add(carBrandBox);
        contentPane.add(carModelBox);
        contentPane.add(viewButton);
        contentPane.add(customButton);
        contentPane.add(checkoutButton);
        contentPane.add(scrollPane);
        contentPane.add(homeButton);
    }

    /** Filled with ActionListeners, the purpose of this method is to give functionalities to various parts of the frame.
     */
    public void activateHomeFrame() {
        carBrandBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carBrandBox.getSelectedItem() != null){
                    buttonEnabler();
                    setBoxesAfterChoice(carBrandBox.getSelectedItem().toString());
                }
            }
        });
        carModelBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEnabler();
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEnabler();
                ArrayList<Car> factoryCars = FileHandler.loadCarsFromFile("./src/FactoryCars.bin");
                for(Car car : factoryCars){
                    if(car.getBrand().equals(carBrandBox.getSelectedItem().toString()) && car.getModel().equals(carModelBox.getSelectedItem().toString())){
                        writeOnArea(car);
                    }
                }
            }
        });
        customButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car.setBrand(carBrandBox.getSelectedItem().toString());
                car.setModel(carModelBox.getSelectedItem().toString());
                CustomFrame customFrame = new CustomFrame(user,car);
                dispose();
            }
        });
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Car> factoryCars = FileHandler.loadCarsFromFile("./src/FactoryCars.bin");
                for(Car carT : factoryCars){
                    if(carT.getBrand().equals(carBrandBox.getSelectedItem().toString()) && carT.getModel().equals(carModelBox.getSelectedItem().toString())){
                        car = carT;
                    }
                }
                CheckoutFrame checkoutFrame = new CheckoutFrame(user,car);
                dispose();
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

    /** Changes the models available,depending on which brand is selected currently.
     */
    private void setBoxesAfterChoice(String choice){
        ArrayList<String[]> models = FileHandler.getAllModels();

        carModelBox.removeAllItems();

        for(String[] model : models){
            if(model[0].equals(choice)){
                for(int i  = 1 ; i < model.length ; i++){
                    carModelBox.addItem(model[i]);
                }
            }
        }
    }

    /** Unlocks disabled buttons .
     */
    private void buttonEnabler(){
        carModelBox.setEnabled(true);
        if(carBrandBox.getSelectedItem() != null && carModelBox.getSelectedItem() != null){
            customButton.setEnabled(true);
            checkoutButton.setEnabled(true);
            viewButton.setEnabled(true);
        } else {
            viewButton.setEnabled(false);
            customButton.setEnabled(false);
            checkoutButton.setEnabled(false);
        }
    }

    /** This method is used to determine what is going to be written on the JTextArea.
     */
    private void writeOnArea(Car car){
        textArea.setText("");
        textArea.setText("~~~~ Factory Specifications: ~~~~~\n");
        textArea.append("\n");
        textArea.append("   * Car Brand: '" + car.getBrand() + "'\n");
        textArea.append("   * Car Model: '"+ car.getModel() + "'\n");
        textArea.append("   * Car Color: '"+ car.getCarColour() + "'\n");
        textArea.append("   * Seat Color: '"+ car.getSeatsColour() + "'\n");
        textArea.append("   * Car Engine: '"+ car.getMotorName() + "'\n");
        textArea.append("   * Car Tires: '"+ car.getTireType() + "'\n");
        textArea.append("   * Car Price: '"+ car.getPrice() + "' kn\n");
    }
}

