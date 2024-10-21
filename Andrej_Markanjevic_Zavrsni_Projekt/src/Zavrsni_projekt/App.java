package Zavrsni_projekt;

import Zavrsni_projekt.app.FileHandler;
import Zavrsni_projekt.classes.Car;
import Zavrsni_projekt.frames.MainFrame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/** Main class in which I make some presaved factory cars and start the rest of the app .
 * @author Andrej Markanjević
 */

public class App {

    public static void main(String[] args) throws IOException {

        ArrayList<Car> factoryCars = new ArrayList<>();

        Car car1 = new Car("Audi", "Elektro", "Blue", "Blue", "motor1", "Summer");
        Car car2 = new Car("Audi", "A4", "Blue", "Blue", "motor1", "Summer");
        Car car3 = new Car("Audi", "A5", "Blue", "Blue", "motor1", "Summer");
        Car car4 = new Car("Audi", "A6", "Blue", "Blue", "motor1", "Summer");
        Car car5 = new Car("Audi", "A7", "Blue", "Blue", "motor1", "Summer");
        Car car6 = new Car("Audi", "Q5", "Blue", "Blue", "motor1", "Summer");
        Car car7 = new Car("Audi", "Q7", "Blue", "Blue", "motor1", "Summer");

        Car car8 = new Car("Renault", "Clio", "NavyBlue", "NavyBlue", "motor2", "Winter");
        Car car9 = new Car("Renault", "Twingo", "NavyBlue", "NavyBlue", "motor2", "Winter");
        Car car10 = new Car("Renault", "Captur", "NavyBlue", "NavyBlue", "motor2", "Winter");
        Car car11 = new Car("Renault", "Megane", "NavyBlue", "NavyBlue", "motor2", "Winter");

        Car car12 = new Car("BMW", "M3", "Orange", "Orange", "motor1", "Winter");
        Car car13 = new Car("BMW", "M4", "Orange", "Orange", "motor1", "Winter");
        Car car14 = new Car("BMW", "X1", "Orange", "Orange", "motor1", "Winter");
        Car car15 = new Car("BMW", "X6", "Orange", "Orange", "motor1", "Winter");
        Car car16 = new Car("BMW", "I8", "Orange", "Orange", "motor1", "Winter");

        Car car17 = new Car("Mercedes", "ABB", "Red", "Red", "motor3", "Summer");
        Car car18 = new Car("Mercedes", "CDD", "Red", "Red", "motor3", "Summer");
        Car car19 = new Car("Mercedes", "EFF", "Red", "Red", "motor3", "Summer");
        Car car20 = new Car("Mercedes", "EQE", "Red", "Red", "motor3", "Summer");
        Car car21 = new Car("Mercedes", "EQS", "Red", "Red", "motor3", "Summer");

        factoryCars.add(car1);
        factoryCars.add(car2);
        factoryCars.add(car3);
        factoryCars.add(car4);
        factoryCars.add(car5);
        factoryCars.add(car6);
        factoryCars.add(car7);
        factoryCars.add(car8);
        factoryCars.add(car9);
        factoryCars.add(car10);
        factoryCars.add(car11);
        factoryCars.add(car12);
        factoryCars.add(car13);
        factoryCars.add(car14);
        factoryCars.add(car15);
        factoryCars.add(car16);
        factoryCars.add(car17);
        factoryCars.add(car18);
        factoryCars.add(car19);
        factoryCars.add(car20);
        factoryCars.add(car21);

        File fileUsers = new File("./src/Users.bin");
        File fileCars = new File("./src/FactoryCars.bin");
        File fileReceipts = new File("./src/Receipts.bin");
        fileUsers.createNewFile();
        fileCars.createNewFile();
        fileReceipts.createNewFile();

        FileHandler.writeCarsToFile(fileCars,factoryCars);

        new MainFrame();
    }

}
