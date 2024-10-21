package Zavrsni_projekt.app;

import Zavrsni_projekt.classes.Car;
import Zavrsni_projekt.classes.Receipt;
import Zavrsni_projekt.classes.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/** A class for managing objects entering and exiting from files.
 * @author Andrej Markanjević
 */
public class FileHandler {

    /** A method for serializing more User objects into a file,after it's emptied out.
     * @param filePath A path to the file.
     * @param users A list of users you want to serialize.
     */
    public static void writeAllUsersToFile(String filePath,ArrayList<User> users ){

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (User temp : users) {
                oos.writeObject(temp);
            }
            oos.close();

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException is caught");
        } catch (IOException e) {
            System.out.println("IOException is caught");
        }
    }

    /** A method for serializing a User object into a file.
     * @param filePath A path to the file.
     * @param user A single user you want saved.
     */
    public static void writeUserToFile(String filePath,User user){

        ArrayList<User> users = loadUsersFromFile(filePath);
        users.add(user);

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (User temp : users) {
                oos.writeObject(temp);
            }
            oos.close();

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException is caught");
        } catch (IOException e) {
            System.out.println("IOException is caught");
        }
    }

    /** A method for deserializing users from a certain file.
     * @param filePath A path to the file.
     * @return A list of User objects.
     */
    public static ArrayList<User> loadUsersFromFile(String filePath){

        ArrayList<User> users = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (fis.available() > 0) {
                User user = (User) ois.readObject();
                users.add(user);
            }
            ois.close();
            fis.close();
        } catch(EOFException ignored) {

        } catch(IOException ex) {
            System.out.println("IOException is caught");
        } catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return users;
    }

    /** A method that serializes Car objects into a file.
     * @param fileName A path to the file.
     * @param cars Receives a list of Car objects which needs to be serialized.
     */
    public static void writeCarsToFile(File fileName, ArrayList<Car> cars){

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Car car : cars) {
                oos.writeObject(car);
            }
            oos.close();

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException is caught");
        } catch (IOException e) {
            System.out.println("IOException is caught");
        }
    }

    /** A method that deserializes Car objects from a file.
     * @param filePath A path to the file.
     * @return Returns a list of Car objects.
     */
    public static ArrayList<Car> loadCarsFromFile(String filePath){

        ArrayList<Car> cars = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (fis.available() > 0) {
                Car car;
                car = (Car) ois.readObject();
                cars.add(car);
            }
            ois.close();
            fis.close();

        } catch(IOException ex) {
            System.out.println("IOException is caught");
        } catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return cars;
    }

    /** A method that reads all Car models from a csv file.
     * @return Returns a list of String arrays.
     */
    public static ArrayList<String[]> getAllModels() {
        ArrayList<String[]> models = new ArrayList<>();
        if (Files.exists(Path.of("src/models.csv"))) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/models.csv"))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] values = line.split(",");
                    models.add(values);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File models.csv doesn't exist!");
        }
        return models;
    }

    /** A method that serializes Receipt objects to a file.
     * @param filePath A path to the file.
     * @param receipts Receives a list of Receipt objects which needs to be serialized.
     */
    public static void writeReceiptsToFile(String filePath, ArrayList<Receipt> receipts){

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Receipt r : receipts) {
                oos.writeObject(r);
            }
            oos.close();

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException is caught");
        } catch (IOException e) {
            System.out.println("IOException is caught");
        }
    }

    /** A method that deserializes Receipt objects from a file.
     * @param filePath A path to the file.
     * @return A list of Receipt objects.
     */
    public static ArrayList<Receipt> loadReceiptsFromFile(String filePath){

        ArrayList<Receipt> receipts = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (fis.available() > 0) {
                Receipt receipt = (Receipt) ois.readObject();
                receipts.add(receipt);
            }
            ois.close();
            fis.close();
        } catch(EOFException ignored) {

        } catch(IOException ex) {
            System.out.println("IOException is caught");
        } catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return receipts;
    }
}
