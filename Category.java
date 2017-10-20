
/**
 * Write a description of class Category here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Category
{
    // instance variables - replace the example below with your own
    private String name;
    private int min;
    private int max;

    /**
     * Constructor for objects of class Category
     */
    public Category(String name, int min, int max)
    {
        this.name = name;
        this.min = min;
        this.max = max;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public int getMin() {
        return this.min;
    }
    @Override
    public String toString() 
    {
        return this.name;
    }
}
