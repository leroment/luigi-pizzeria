import java.util.*;
import java.text.*;
/**
 * Write a description of class Customer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer
{
    // instance variables - replace the example below with your own
    private String phone;
    private String name;
    private LinkedList<Pizza> ordered = new LinkedList<Pizza>();
    private LinkedList<Pizza> order = new LinkedList<Pizza>();

    /**
     * Constructor for objects of class Customer
     */
    public Customer(String phone, String name)
    {
        // initialise instance variables
        this.phone = phone;
        this.name = name;
    }

    public LinkedList<Pizza> getOrderedList() {
        return ordered;
    }

    public Boolean matches(String phoneNumber) {
        return this.phone.equals(phoneNumber);
    }

    public void serve(Kitchen kitchen) {
        System.out.println("Serving " + this.name);
        char choice;
        while ((choice = readCustomerChoice()) != 'o')
        {
            switch (choice)
            {
                case 'c': createPizza(kitchen); break;
                case 'p': pastPizza(); break;
                default: help(); break;
            }
        }
        if (!order.isEmpty()) {
            submitOrder();
        }
        else {
            System.out.println("Empty order discarded");
            System.out.println();
        }
    }

    public char readCustomerChoice() {
        System.out.print("Customer choice (c/p/o): ");
        return In.nextChar();
    }

    public void createPizza(Kitchen kitchen) {
        System.out.println("Creating new pizza");
        Pizza pizza = new Pizza();
        double totalPrice = 0.00;
        String choice;
        while (!(choice = readIngredients()).equals(".") || pizza.needsMoreIngredients(kitchen)) {
            if (!choice.equals("")) {
                if (choice.charAt(0) != '-') {
                    pizza.add(choice, kitchen);
                }
                else {
                    choice = removeCharAt(choice, 0);
                    pizza.remove(choice, kitchen);
                }
            }
            else {
                choice = " ";
                pizza.add(choice, kitchen);
            }
        }
        pizza.addSold();
        order.add(pizza);
        System.out.println("ORDER SUMMARY");
        for (Pizza p: order) {
            System.out.println(p.toString());
            totalPrice += p.price();
        }
        System.out.println("Total: $" + formatted(totalPrice));
    }

    public void pastPizza() {
        double totalPrice = 0.00;
        Pizza pizza;
        ordered = sortPizza(ordered);
        System.out.println("Select from popular pizzas:");
        if (ordered.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                System.out.println((i + 1) + ". (" + ordered.get(i).getSold() + "x)" + " " + ordered.get(i));
            }
        }
        else {
            for (Pizza p: ordered) {
                System.out.println((ordered.indexOf(p) + 1) + ". (" + p.getSold() + "x)" + " " + p);
            }
        }
        pizza = ordered.get(readSelection() - 1);
        pizza.addSold();
        order.add(pizza);
        System.out.println("ORDER SUMMARY");
        for (Pizza p: order) {
            System.out.println(p.toString());
            totalPrice += p.price();
        }
        System.out.println("Total: $" + formatted(totalPrice));
    }

    public void submitOrder() {
        System.out.println("Order submitted");
        System.out.println();
        for (Pizza p: order) {
            ordered.add(p);
        }
        order.clear();
    }

    public LinkedList<Pizza> sortPizza(LinkedList<Pizza> ordered) {
        for (int i = 0; i < ordered.size(); i++) {
            for (int j = 0; j < ordered.size() - i - 1; j++) {
                if (ordered.get(j).getSold() < ordered.get(j + 1).getSold()) {
                    Pizza temp = ordered.get(j);
                    ordered.set(j, ordered.get(j + 1));
                    ordered.set(j + 1, temp);
                }
            }
        }
        return ordered;
    }

    public int totalSold(Ingredient i) {
        int total = 0;
        for (Pizza p: ordered) {
            total += p.totalIngredientSold(i);
        }
        return total;
    }

    public String readIngredients() {
        System.out.print("Ingredient(s): ");
        return In.nextLine();
    }

    public int readSelection() {
        System.out.print("Selection: ");
        return In.nextInt();
    }

    public void help()
    {
        System.out.println("c = create new pizza");
        System.out.println("p = select from popular past pizzas");
        System.out.println("o = submit order");
    }

    @Override
    public String toString() 
    {
        return this.name + ": " + this.phone;
    }

    private static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    private String formatted(double amount) {
        return new DecimalFormat("###,##0.00").format(amount);
    }
}
