import java.util.*;
import java.text.*;
/**
 * Write a description of class Pizza here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pizza
{
    // instance variables - replace the example below with your own
    private LinkedList<Ingredient> ingredients = new LinkedList<Ingredient>();
    private int sold;

    /**
     * Constructor for objects of class Pizza
     */
    public Pizza()
    {
        // initialise instance variables
        this.sold = 0;
    }

    public LinkedList<Ingredient> getIngredientsList() {
        return ingredients;
    }
    
    public void addSold() {
        this.sold++;
    }
    
    public int getSold() {
        return this.sold;
    }
    
    public int setSold(int sold) {
        return this.sold = sold;
    }

    public void add(String ingredient, Kitchen kitchen) {
        Ingredient ingredientObject;
        if (ingredient.equals(".")) {
            return;
        }
        for (String i: ingredient.split(",")) {
            String name = kitchen.findName(i);
            if (kitchen.findPrice(name) != 0 && kitchen.findCategory(name) != null && name != "/") {
                if (!isDup(name)) {
                    if (!exceedsMax(name, kitchen)) {
                        ingredientObject = new Ingredient(name, kitchen.findPrice(name), kitchen.findCategory(name));
                        ingredientObject.addSold();
                        ingredients.add(ingredientObject);
                    }
                    else {
                        System.out.println(showMaxRequirements(name));
                        return;
                    }
                }
                else {
                    System.out.println("Already added " + name + " " + kitchen.findCategory(i));
                    return;
                }
            }
            else {
                System.out.println("No ingredient matching " + ingredient);
                return;
            }
        }
        System.out.println(toString());
    }

    public void remove(String ingredient, Kitchen kitchen) {
        for (Ingredient i: ingredients) {
            if (i.getName().equals(kitchen.findTheName(ingredient))) { 
                i.subtractSold();
                ingredients.remove(i);
                break;
            }
        }
        System.out.println(toString());
    }

    public boolean isDup(String ingredient) {
        Kitchen kitchen = new Kitchen();
        for (Ingredient i: ingredients) {
            if (i.getName().equals(kitchen.findTheName(ingredient))) {
                return true;
            }
        }
        return false;
    }

    private String showMaxRequirements(String i) {
        Kitchen kitchen = new Kitchen();
        String s = "";
        s =  "Can only add " + kitchen.findCategory(i).getMax() + " " + kitchen.findCategory(i);        
        if (kitchen.findCategory(i).getMax() > 1) {
            s += "s";
        }
        else {
            s += "";
        }
        return s;
    }

    private String showMinRequirements(LinkedList<Category> c) {
        Kitchen kitchen = new Kitchen();
        String s = "";
        String separator = "";
        for (Category category: c) {
            s += separator + "Must have at least " + category.getMin() + " " + category.getName();
            separator = "\n";
            if (category.getMin() > 1) {
                s += "s";
            }
            else {
                s += "";
            }
        }
        return s;
    }

    private boolean exceedsMax(String i, Kitchen kitchen) {
        if (categoryCount(kitchen.findCategory(i)) >= kitchen.findCategory(i).getMax()) {
            return true;
        }
        return false;
    }

    public boolean needsMoreIngredients(Kitchen kitchen) {
        LinkedList<Category> cat = new LinkedList<Category>();
        boolean decider = false;
        for (Category c: kitchen.getCategories()) {
            if (categoryCount(c) < c.getMin()) {
                cat.add(c);
            }
        }
        if (!cat.isEmpty()) {
            decider = true;
            System.out.println(showMinRequirements(cat));
        }
        else {
            decider = false;
        }
        return decider;
    }

    private int categoryCount(Category category) {
        int count = 0;
        for (Ingredient ingredient: ingredients) {
            if (ingredient.hasCategory(category)) {
                count++;
            }
        }
        return count;
    }

    public Ingredient ingredient(Category category) {
        for (Ingredient ingredient: ingredients) {
            if (ingredient.hasCategory(category)) {
                return ingredient;
            }
        }
        return null;
    }

    public String crust() {
        Ingredient ingredient = ingredient(Kitchen.CRUST);
        if (ingredient != null) {
            return ingredient.toString();
        }
        else {
            return "no crust";
        }
    }

    public String sauce() {
        Ingredient ingredient = ingredient(Kitchen.SAUCE);
        if (ingredient != null) {
            return ingredient.toString();
        }
        else {
            return "no sauce";
        }
    }

    public String toppings() {
        LinkedList<Ingredient> toppings = new LinkedList<Ingredient>();
        String s = "";
        String separator = "";
        for (Ingredient ingredient: ingredients) {
            if (ingredient.hasCategory(Kitchen.TOPPING)) {
                toppings.add(ingredient);
            }
        }
        if (!toppings.isEmpty()) {
            for (Ingredient topping: toppings) {
                s += separator + topping.getName();
                separator = ", ";
            }
        }
        else {
            s = "no toppings";
        }
        return s;
    }

    public double price() {
        double price = 0.00;
        for (Ingredient ingredient: ingredients) {
            price += ingredient.getPrice();
        }
        return price;
    }

    @Override
    public String toString()
    {
        String s = crust() + " pizza with " + toppings() + " and " + sauce() + ": $";
        double price = 0.00;
        for (Ingredient ingredient: ingredients) {
            price += ingredient.getPrice();
        }
        s += "" + formatted(price);
        return s;
    }

    public int totalIngredientSold(Ingredient i) {
        int totalSold = 0;
        for (Ingredient ingredient: ingredients) {
            if (i.getName().equals(ingredient.getName())) {
                totalSold += ingredient.getSold();
            }
        }
        return totalSold;
    }

    private String formatted(double amount) {
        return new DecimalFormat("###,##0.00").format(amount);
    }

    private String firstLetterCaps(String data) {
        String firstLetter = data.substring(0,1).toUpperCase();
        String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }
}
