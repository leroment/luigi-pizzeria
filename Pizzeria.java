import java.util.*;
import java.text.*;
/**
 * Write a description of class Pizzeria here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pizzeria
{
    // instance variables - replace the example below with your own
    private LinkedList<Customer> customers = new LinkedList<Customer>();
    private Kitchen kitchen;

    public static void main(String[] args) 
    {
        new Pizzeria().use();
    }

    /**
     * Constructor for objects of class Pizzeria
     */
    public Pizzeria()
    {
        kitchen = new Kitchen();
    }

    public void use() 
    {
        System.out.println("Welcome to Luigi's Pizzeria!");
        char choice;
        while ((choice = readPizzeriaChoice()) != 'x') 
        {
            switch (choice) 
            {
                case 'a': addCustomer(); break;
                case 'v': viewCustomer(); break;
                case 's': serveCustomer(); break;
                case 'r': getReport(); break;
                default: help(); break;
            }
        }
    }

    private char readPizzeriaChoice() 
    {
        System.out.print("Pizzeria choice (a/v/s/r/x): ");
        return In.nextChar();
    }

    private void addCustomer()
    {
        String phoneNumber = readNumber();
        LinkedList<Customer> existingCustomers = lookupExistingCustomer(phoneNumber);
        if (existingCustomers.size() > 0) {
            System.out.println("An existing customer has that phone number");
        } 
        else {
            customers.add(new Customer(phoneNumber, readName()));
        }
    }

    private void viewCustomer()
    {
        for(Customer customer: customers) {
            System.out.println(customer);
        }
    }

    private void serveCustomer() 
    {
        String phoneNumber = readNumber();
        LinkedList<Customer> existingCustomer = lookupExistingCustomer(phoneNumber);
        if (existingCustomer.size() == 0) {
            System.out.println("No such customer");
        }
        else {
            for (Customer customer: existingCustomer) {
                customer.serve(kitchen);
            }
        }

    }

    public int totalIngredient(Ingredient i) {
        int totalSold = 0;
        for (Customer c: customers) {
            totalSold += c.totalSold(i);
        }
        return totalSold;
    }
    
    public void getReport() {
        double income = 0;
        int total = 0;
        String s = "";        
        for (Ingredient i: kitchen.getIngredients()) {
            if (totalIngredient(i) != 1) {
                s+= "s";
            }
            System.out.println(totalIngredient(i) + " " + i + s + " sold worth $" + formatted((totalIngredient(i) * i.getPrice())));
            income += (totalIngredient(i) * i.getPrice());
            s = "";
        }
        System.out.println("Income: $" + formatted(income));
    }

    private String readNumber() {
        System.out.print("Phone: ");
        return In.nextLine();
    }

    private String readName() {
        System.out.print("Name: ");
        return In.nextLine();
    }

    private LinkedList<Customer> lookupExistingCustomer(String phoneNumber) {
        LinkedList<Customer> existingCustomer = new LinkedList<Customer>();
        for (Customer customer: this.customers) {
            if (customer.matches(phoneNumber)) {
                existingCustomer.add(customer);
            }
        }
        return existingCustomer;
    }

    private void help()
    {
        System.out.println("a = add customer");
        System.out.println("v = view customers");
        System.out.println("s = serve customer");
        System.out.println("r = show report");
        System.out.println("x = exit");
    }
    
        private String formatted(double amount) {
        return new DecimalFormat("###,##0.00").format(amount);
    }

}
