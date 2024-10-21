package Zavrsni_projekt.frames;


import Zavrsni_projekt.app.FileHandler;
import Zavrsni_projekt.classes.Car;
import Zavrsni_projekt.classes.Receipt;
import Zavrsni_projekt.classes.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** This is the admin frame,in this class you can see all users,bought cars and receipts.
 * @author Andrej Markanjević
 */
public class AdminFrame extends JFrame{
    private JButton userView;
    private JButton factoryCarView;
    private JButton boughtCarView;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JPanel contentPane;
    private JButton homeButton;
    private JButton receiptsButton;


    /** Creates objects of AdminFrame.
     */
    public AdminFrame(){
        initAdminFrame();
        layoutAdminFrame();
        activateAdminFrame();
    }

    /** Creates the components for my frame.
     */
    public void initAdminFrame() {
        userView = new JButton("Users");
        boughtCarView = new JButton("Bought Cars");
        factoryCarView = new JButton("Factory Cars");
        receiptsButton = new JButton("Receipts");


        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contentPane = new JPanel();
        homeButton = new JButton(new ImageIcon("src/homeButton.png"));

    }

    /** Aside from making sure everything is placed on the frame properly, this method is also used to edit the looks of the compartments.
     */
    public void layoutAdminFrame() {
        setTitle("Admin");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        userView.setBounds(10, 320, 100, 25);
        boughtCarView.setBounds(120, 320, 100, 25);
        factoryCarView.setBounds(230, 320, 100, 25);
        scrollPane.setBounds(30, 20, 430, 280);
        homeButton.setBounds(450,320,20,25);
        receiptsButton.setBounds(340, 320, 100, 25);

        textArea.setEditable(false);

        contentPane.add(userView);
        contentPane.add(boughtCarView);
        contentPane.add(factoryCarView);
        contentPane.add(scrollPane);
        contentPane.add(homeButton);
        contentPane.add(receiptsButton);
    }

    /** Filled with ActionListeners, the purpose of this method is to give functionalities to various parts of the frame.
     */
    public void activateAdminFrame() {
        userView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                ArrayList<User> users = FileHandler.loadUsersFromFile("./src/Users.bin");
                for(User user : users){
                    textArea.append("Username: " + user.getUsername() + ", ID: " + user.getId() + "\n");
                }
            }
        });

        boughtCarView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                ArrayList<User> users = FileHandler.loadUsersFromFile("./src/Users.bin");
                for(User user : users){
                    textArea.append("********************************** USER-" + user.getUsername() + "**********************************" + "\n");
                    for(Car car : user.getCars()){
                        textArea.append(car + "\n");
                    }

                }
            }
        });

        factoryCarView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                ArrayList<Car> factoryCars = FileHandler.loadCarsFromFile("./src/FactoryCars.bin");
                for(Car car : factoryCars){
                    textArea.append(car + "\n");
                }
            }
        });

        receiptsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                ArrayList<Receipt> receipts = FileHandler.loadReceiptsFromFile("./src/Receipts.bin");
                for(Receipt r : receipts){
                    textArea.append(r + "\n");
                }
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
}
