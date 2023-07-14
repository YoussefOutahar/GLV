package Models;

import java.awt.image.BufferedImage;

public class Car {
    private int id;
    private String name;
    private String color;
    private int price;
    private BufferedImage image;

    public Car(int id, String name, String color, int price, BufferedImage image) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.image = image;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setColor(String color) {
        this.color = color;

    }

    public void setPrice(int price) {
        this.price = price;

    }

    public void setImage(BufferedImage image) {
        this.image = image;

    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", name=" + name + ", color=" + color + ", price=" + price + ", image=" + image + "]";
    }
}
