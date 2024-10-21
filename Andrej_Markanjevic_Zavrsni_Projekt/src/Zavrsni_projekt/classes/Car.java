package Zavrsni_projekt.classes;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

/** A class for creating Car objects and their methods.
 * @author Andrej Markanjević
 */

public class Car implements Serializable {

    private String brand;
    private String model;
    private String price;
    private String carColour;
    private String motorName;
    private String seatsColour;
    private String tireType;

    /** Car object constructor,also sets a random car price.
     * @param brand A string that represents a brand of a car.
     * @param model A string that represents a model of a car.
     * @param carColour A string that represents a colour of a car.
     * @param motorName A string that represents a motor of a car.
     * @param seatsColour A string that represents a colour of seats inside the car.
     * @param tireType A string that represents a type of a car tires.
     */

    public Car(String brand, String model, String carColour, String motorName, String seatsColour, String tireType) {
        this.brand = brand;
        this.model = model;
        this.carColour = carColour;
        this.motorName = motorName;
        this.seatsColour = seatsColour;
        this.tireType = tireType;
        this.price = Integer.toString(ThreadLocalRandom.current().nextInt(10000,20000));
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getCarColour() {
        return carColour;
    }

    public String getMotorName() {
        return motorName;
    }

    public String getSeatsColour() {
        return seatsColour;
    }

    public String getTireType() {
        return tireType;
    }

    public String getPrice() {
        return price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCarColour(String carColour) {
        this.carColour = carColour;
    }

    public void setMotorName(String motorName) {
        this.motorName = motorName;
    }

    public void setSeatsColour(String seatsColour) {
        this.seatsColour = seatsColour;
    }

    public void setTireType(String tireType) {
        this.tireType = tireType;
    }


    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carColour='" + carColour + '\'' +
                ", motorName='" + motorName + '\'' +
                ", seatsColour='" + seatsColour + '\'' +
                ", tireType='" + tireType + '\'' +
                ", price='" + price + '\'' +
                "$}";
    }
}
