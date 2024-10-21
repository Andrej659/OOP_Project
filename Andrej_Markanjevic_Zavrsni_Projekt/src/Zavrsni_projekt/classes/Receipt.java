package Zavrsni_projekt.classes;

import java.io.Serializable;

/** A class for creating Receipt objects and their methods.
 * @author Andrej Markanjević
 */
public class Receipt implements Serializable {

    private User user;
    private Car car;
    private String paymentMethod;
    private int price;

    /** Creates the receipts that the company needs.
     * @param user A user that bought a car.
     * @param car A car that user bought.
     * @param paymentMethod A String representation of a payment way.
     * @param price A price of the car you're buying.
     */
    public Receipt(User user, Car car, String paymentMethod,int price) {
        this.user = user;
        this.car = car;
        this.paymentMethod = paymentMethod;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "user=" + user.getUsername() +
                ", car=" + car +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", price=" + price +
                '}';
    }
}
