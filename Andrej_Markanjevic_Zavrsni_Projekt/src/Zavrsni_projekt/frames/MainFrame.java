package Zavrsni_projekt.frames;

import Zavrsni_projekt.app.FileHandler;
import Zavrsni_projekt.classes.Car;
import Zavrsni_projekt.classes.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** First frame that is initialized,has login,signup and login as admin options.
 * @author Andrej Markanjević
 */
public class MainFrame extends JFrame {

    /** This attribute is assigned to every frame as a way to keep my cars' customization intact.
     */
    private Car car;

    /** Keeps track of which user is currently logged in.
     */
    private User loggedUser;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JButton loginButton;
    private JButton signUpButton;
    private JButton adminButton;
    private JPanel contentPane;
    private JLabel errorLabel;

    /** Used for creating Mainframe objects.
     */

    public MainFrame(){
        initMainFrame();
        layoutMainFrame();
        activateMainFrame();
        this.car = new Car(null,null,null,null,null,null);
    }

    /** Creates the components for my frame.
     */
    public void initMainFrame() {
        usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField();
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");
        adminButton = new JButton("Admin Login");
        contentPane = new JPanel();
        errorLabel = new JLabel();
    }

    /** Aside from making sure everything is placed on the frame properly, this method is also used to edit the looks of the compartments.
     */
    public void layoutMainFrame() {
        setTitle("Welcome");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 250);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        errorLabel.setForeground(Color.red);

        usernameLabel.setBounds(60, 60, 100, 25);
        usernameField.setBounds(140, 60, 150, 25);
        loginButton.setBounds(50, 100, 100, 25);
        signUpButton.setBounds(170, 100, 100, 25);
        adminButton.setBounds(70, 160, 180, 25);
        errorLabel.setBounds(140, 40, 150, 25);

        contentPane.add(usernameLabel);
        contentPane.add(usernameField);
        contentPane.add(loginButton);
        contentPane.add(signUpButton);
        contentPane.add(adminButton);
        contentPane.add(errorLabel);
    }

    /** Filled with ActionListeners, the purpose of this method is to give functionalities to various parts of the frame.
     */
    public void activateMainFrame() {
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean exists = false;
                for(User user : FileHandler.loadUsersFromFile("./src/Users.bin")){
                    if (user.getUsername().equals(usernameField.getText())){
                        exists = true;
                        loggedUser = user;
                        break;
                    }
                }
                if (exists){
                    openCarDealershipFrame();
                }else{
                    errorLabel.setText("User does not exist!");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean exists = false;
                for(User user : FileHandler.loadUsersFromFile("./src/Users.bin")){
                    if (user.getUsername().equals(usernameField.getText())){
                        exists = true;
                        break;
                    }
                }
                if (!exists){
                    User temp = new User(usernameField.getText());
                    FileHandler.writeUserToFile("./src/Users.bin",temp);
                    loggedUser = temp;
                    openCarDealershipFrame();
                }else{
                    errorLabel.setText("User already exists!");
                }
            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().equals("admin")){
                    openAdminFrame();
                }else{
                    errorLabel.setText("You are not the admin!!!");
                }
            }
        });
    }

    /** Opens up next frame in line.
     */
    private void openCarDealershipFrame(){
        CarDealershipFrame cdf = new CarDealershipFrame(loggedUser,car);
        dispose();
    }

    /** Opens up the admin frame.
     */
    private void openAdminFrame(){
        AdminFrame adminFrame = new AdminFrame();
        dispose();
    }
}
