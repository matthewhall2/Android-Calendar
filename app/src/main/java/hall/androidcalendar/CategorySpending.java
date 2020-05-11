package hall.androidcalendar;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class CategorySpending extends Budget {
    private String name;

    public CategorySpending(double amount, String name){
        super(amount);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }


}
