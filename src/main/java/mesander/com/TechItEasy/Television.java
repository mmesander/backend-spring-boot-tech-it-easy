package mesander.com.TechItEasy;

public class Television {
    // Variables
    private String name;
    private String brand;
    private long serialNumber;
    private double price;


    // Constructor
    public Television(String name, String brand, long serialNumber, double price) {
        this.name = name;
        this.brand = brand;
        this.serialNumber = serialNumber;
        this.price = price;
    }


    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
