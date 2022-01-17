import java.util.HashMap;

public class Recipe {

    public Recipe recipe;
    public String name;
    public HashMap<Ingredient, Integer> ingredients;
    public double price;
    public int preptime;

    public Recipe(String name){
        this.name = name;
        this.ingredients = new HashMap<Ingredient, Integer>();
        this.price = 0.0;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setPrepTime(int time){
        this.preptime = time;
    }
}
