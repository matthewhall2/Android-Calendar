package hall.androidcalendar;

import java.util.ArrayList;

public class Budget {

    private double maxSpending;
    private double totalSpending;
    private ArrayList<CategorySpending> categories;


    public Budget(double maxSpending){
        this.maxSpending = maxSpending;
        this.categories = new ArrayList<>();
        this.totalSpending = 0d;
    }

    public double getMaxSpending(){
        return this.maxSpending;
    }

    public void setMaxSpending(double amount){
        this.maxSpending = amount;
    }

    public void addMaxSpending(double amount){
        this.maxSpending += amount;
    }

    public void takeMaxSpending(double amount){
        this.maxSpending -= amount;
    }

    public void addSpending(String category, double amount){
        this.totalSpending += amount;
        for(CategorySpending c: this.categories){
            if(c.getName().equals(category)){
                c.addSpending(amount);
            }
        }
    }

    public void addSpending(double amount){
        this.totalSpending += amount;
    }

    public double getTotalSpending(){
        return this.totalSpending;
    }

    public double getCategorySpending(String category){
        return 0d;
    }

}
