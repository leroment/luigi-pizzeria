import java.util.*;
/**
 * Write a description of class Ingredient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ingredient
{
    // instance variables - replace the example below with your own
    private String name;
    private double price;
    private Category category;
    private int sold;

    /**
     * Constructor for objects of class Ingredient
     */
    public Ingredient(String name, double price, Category category)
    {
        // initialise instance variables
        this.name = name;
        this.price = price;
        this.category = category;
        this.sold = 0;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasName(String name) {
        int count = 0;
        for (int i = 0; i< name.length(); i++) {
            if (name.toLowerCase().charAt(i) == this.name.toLowerCase().charAt(i) && (name.length() <= this.name.length())) {
                count++;
            }
            else {
                return false;
            }

            if (count == name.length()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCategory(Category category) {
        return this.category == category;
    }

    public Category getCategory() {
        return this.category;
    }

    public double getPrice() {
        return this.price;
    }

    public void addSold() {
        this.sold++;
    }

    public void subtractSold() {
        this.sold--;
    }

    public int getSold() {
        return this.sold;
    }

    public double getRev() {
        return this.price * this.sold;
    }

    @Override
    public String toString() 
    {
        return this.name + " " + this.category;
    }
}
