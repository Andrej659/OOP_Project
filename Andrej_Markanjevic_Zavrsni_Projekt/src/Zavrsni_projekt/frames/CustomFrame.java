package Zavrsni_projekt.frames;

import Zavrsni_projekt.classes.Car;
import Zavrsni_projekt.classes.User;
import Zavrsni_projekt.enums.CarMotor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Frame used for customising the car.
 * @author Andrej Markanjević
 */
public class CustomFrame extends JFrame{

    private Car car;
    private User loggedUser;
    private JLabel carColor;
    private JLabel seatColor;
    private JLabel engine;
    private JLabel tires;

    private JButton returnButton;
    private JButton checkoutButton;

    private JRadioButton carColor1;
    private JRadioButton carColor2;
    private JRadioButton carColor3;
    private JRadioButton carColor4;
    private ButtonGroup carGroup;

    private JRadioButton seatColor1;
    private JRadioButton seatColor2;
    private JRadioButton seatColor3;
    private ButtonGroup seatGroup;

    private JRadioButton tires1;
    private JRadioButton tires2;
    private ButtonGroup tireGroup;

    private JComboBox<CarMotor> engineBox;
    private JPanel contentPane;
    private JButton homeButton;

    /** Creates a CustomFrame object.
     * @param loggedUser One user is customising the car.
     * @param car The car that is being customised.
     */
    public CustomFrame(User loggedUser,Car car){
        this.loggedUser = loggedUser;
        this.car = car;
        initCustomFrame();
        layoutCustomFrame();
        setupPanelInfo();
        activateCustomFrame();
    }

    /** Creates the components for my frame.
     */
    public void initCustomFrame(){
        carColor = new JLabel("Pick Your Car Color:");
        seatColor = new JLabel("Pick Your Seat Color:");
        engine = new JLabel("Pick Your Engine:");
        tires = new JLabel("Pick Your Tires:");
        returnButton = new JButton("Return");
        checkoutButton = new JButton("Checkout");
        carColor1 = new JRadioButton();
        carColor2 = new JRadioButton();
        carColor3 = new JRadioButton();
        carColor4 = new JRadioButton();
        carGroup = new ButtonGroup();
        seatColor1 = new JRadioButton();
        seatColor2 = new JRadioButton();
        seatColor3 = new JRadioButton();
        seatGroup = new ButtonGroup();
        tires1 = new JRadioButton();
        tires2 = new JRadioButton();
        tireGroup = new ButtonGroup();
        engineBox = new JComboBox<>(CarMotor.values());
        contentPane = new JPanel();
        homeButton = new JButton(new ImageIcon("src/homeButton.png"));
    }

    /** Aside from making sure everything is placed on the frame properly, this method is also used to edit the looks of the compartments.
     */
    public void layoutCustomFrame(){
        setTitle("Customize");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        carColor.setBounds(30, 30, 150, 25);
        seatColor.setBounds(240, 30, 150, 25);
        engine.setBounds(30, 200, 120, 25);
        tires.setBounds(240, 200, 100, 25);
        returnButton.setBounds(250, 320, 100, 25);
        checkoutButton.setBounds(360, 320, 100, 25);
        carColor1.setBounds(30, 60, 70, 25);
        carColor2.setBounds(30, 90, 70, 25);
        carColor3.setBounds(100, 60, 70, 25);
        carColor4.setBounds(100, 90, 70, 25);
        seatColor1.setBounds(230, 60, 70, 25);
        seatColor2.setBounds(230, 90, 70, 25);
        seatColor3.setBounds(300, 60, 70, 25);
        tires1.setBounds(220, 230, 80, 25);
        tires2.setBounds(300, 230, 70, 25);
        engineBox.setBounds(40, 240, 120, 25);
        homeButton.setBounds(440,20,20,20);

        carGroup.add(carColor1);
        carGroup.add(carColor2);
        carGroup.add(carColor3);
        carGroup.add(carColor4);

        seatGroup.add(seatColor1);
        seatGroup.add(seatColor2);
        seatGroup.add(seatColor3);

        tireGroup.add(tires1);
        tireGroup.add(tires2);

        contentPane.add(carColor);
        contentPane.add(seatColor);
        contentPane.add(engine);
        contentPane.add(tires);
        contentPane.add(returnButton);
        contentPane.add(checkoutButton);
        contentPane.add(carColor1);
        contentPane.add(carColor2);
        contentPane.add(carColor3);
        contentPane.add(carColor4);
        contentPane.add(seatColor1);
        contentPane.add(seatColor2);
        contentPane.add(seatColor3);
        contentPane.add(tires1);
        contentPane.add(tires2);
        contentPane.add(homeButton);
        contentPane.add(engineBox);

        carColor1.setSelected(true);
        seatColor1.setSelected(true);
        tires1.setSelected(true);
    }

    /** Filled with ActionListeners, the purpose of this method is to give functionalities to various parts of the frame.
     */
    public void activateCustomFrame(){
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarDealershipFrame cdf = new CarDealershipFrame(loggedUser,car);
                dispose();
            }
        });
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car.setMotorName(engineBox.getSelectedItem().toString());
                CheckoutFrame checkoutFrame = new CheckoutFrame(loggedUser,car);
                dispose();
            }
        });
        carColor1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car.setCarColour(carColor1.getText());
            }
        });
        carColor2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car.setCarColour(carColor2.getText());
            }
        });
        carColor3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car.setCarColour(carColor3.getText());
            }
        });
        carColor4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car.setCarColour(carColor4.getText());
            }
        });
        seatColor1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car.setSeatsColour(seatColor1.getText());
            }
        });
        seatColor2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car.setSeatsColour(seatColor2.getText());
            }
        });
        seatColor3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car.setSeatsColour(seatColor3.getText());
            }
        });
        tires1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car.setTireType(tires1.getText());
            }
        });
        tires2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car.setTireType(tires2.getText());
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

    /** Just an additional method to keep track what is written on each compartment.
     */
    private void setupPanelInfo(){
        carColor1.setText("Red");
        carColor1.setActionCommand("");
        carColor2.setText("Blue");
        carColor2.setActionCommand("");
        carColor3.setText("Black");
        carColor3.setActionCommand("");
        carColor4.setText("Yellow");
        carColor4.setActionCommand("");
        seatColor1.setText("Black");
        seatColor1.setActionCommand("");
        seatColor2.setText("White");
        seatColor2.setActionCommand("");
        seatColor3.setText("Grey");
        seatColor3.setActionCommand("");
        tires1.setText("Summer");
        tires1.setActionCommand("");
        tires2.setText("Winter");
        tires2.setActionCommand("");
    }
}
