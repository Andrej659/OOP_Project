package Zavrsni_projekt.classes;

import java.io.Serializable;
import java.util.ArrayList;

/** A class for creating User objects and their methods.
 * @author Andrej Markanjević
 */
public class User implements Serializable,Comparable<User> {

    private String username;
    private int id;

    /** A list that holds all the cars the user has.
     */
    private ArrayList<Car> cars = new ArrayList<>();

    /** A constructor for User objects.
     * @param username A name of a User.
     */
    public User(String username) {
        this.username = username;
        this.id = username.hashCode();
}

    public String getUsername() {
        return username;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public int getId() {
        return id;
    }

    public void addCar(Car car){
        this.cars.add(car);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", cars=" + cars +
                '}';
    }

    /** An overriden method that sorts and returns same objects.
     * @param o An user you're comparing to.
     */
    @Override
    public int compareTo(User o) {
        if (this.id == (o.getId())){
            return 1;
        }else {
            return 0;
        }
    }
}
