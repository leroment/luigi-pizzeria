import java.util.*;
/**
 * Write a description of class Kitchen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kitchen
{
    // instance variables - replace the example below with your own
    public static final Category CRUST = new Category("crust", 1, 1);
    public static final Category SAUCE = new Category("sauce", 1, 1);
    public static final Category TOPPING = new Category("topping", 2, 3);
    private Category[] categories = { CRUST, SAUCE, TOPPING };
    private LinkedList<Ingredient> ingredients = new LinkedList<Ingredient>();

    /**
     * Constructor for objects of class Kitchen
     */
    public Kitchen()
    {
        // initialise instance variables
        ingredients.add(new Ingredient("Thin", 3.00, CRUST));
        ingredients.add(new Ingredient("Thick", 3.50, CRUST));
        ingredients.add(new Ingredient("Tomato", 1.00, SAUCE));
        ingredients.add(new Ingredient("Barbeque", 1.00, SAUCE));
        ingredients.add(new Ingredient("Capsicum", 0.50, TOPPING));
        ingredients.add(new Ingredient("Olives", 1.50, TOPPING));
        ingredients.add(new Ingredient("Jalapenos", 1.00, TOPPING));
        ingredients.add(new Ingredient("Beef", 2.75, TOPPING));
        ingredients.add(new Ingredient("Pepperoni", 2.50, TOPPING));
    }

    public LinkedList<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public Category[] getCategories() {
        return categories;
    }

    public double findPrice(String i) {
        for (Ingredient ingredient: ingredients) {
            if (ingredient.hasName(i)) {
                return ingredient.getPrice();
            }
        }
        return 0;
    }

    public Category findCategory(String i) {
        for (Ingredient ingredient: ingredients) {
            if (ingredient.hasName(i)) {
                return ingredient.getCategory();
            }
        }
        return null;
    }

    public String findName(String i) {
        LinkedList<Ingredient> matchIngredient = new LinkedList<Ingredient>();
        if (i.equals(" ")) {
            for (Ingredient ingredient: ingredients) {
                matchIngredient.add(ingredient);
            }
            System.out.println("Select from matches below:");
            for (Ingredient ingredient: matchIngredient) {
                System.out.println((matchIngredient.indexOf(ingredient) + 1) + ". " + ingredient);
            }
            return matchIngredient.get(readSelection() - 1).getName();
        }

        for (Category category: categories) {
            if (i.equals(category.getName())) {
                for (Ingredient ingredient: ingredients) {
                    if (ingredient.hasCategory(category)) {
                        matchIngredient.add(ingredient);
                    }
                }
                System.out.println("Select from matches below:");
                for (Ingredient ingredient: matchIngredient) {
                    System.out.println((matchIngredient.indexOf(ingredient) + 1) + ". " + ingredient);
                }
                return matchIngredient.get(readSelection() - 1).getName();
            }
        }

        for (Ingredient ingredient: ingredients) {
            if (ingredient.hasName(i)) {
                matchIngredient.add(ingredient);
            }
        }

        if (matchIngredient.size() > 1) {
            System.out.println("Select from matches below:");
            for (Ingredient ingredient: matchIngredient) {
                System.out.println((matchIngredient.indexOf(ingredient) + 1) + ". " + ingredient);
            }
            return matchIngredient.get(readSelection() - 1).getName();
        } else if (matchIngredient.size() == 1) 
        {
            return matchIngredient.get(0).getName();
        }
        else {
            return "/";
        }
    }

    public String findTheName(String i) {
        for (Ingredient ingredient: ingredients) {
            if (ingredient.hasName(i)) {
                return ingredient.getName();
            }
        }
        return null;
    }

    public int readSelection() {
        System.out.print("Selection: ");
        return In.nextInt();
    }
}
