package coffee2022.objs;

import coffee2022.enums.RecipeTypes;

import java.util.HashMap;

public class Recipe {

    public Recipe recipe;
    public HashMap<Ingredient, Integer> ingredients;
    public String name;
    public RecipeTypes type;
    public double price;
    public int preptime;

    public Recipe(String name, RecipeTypes type) {
        this.name = name;
        this.ingredients = new HashMap<Ingredient, Integer>();
        this.price = 0.0;
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   public void setPrepTime(int time){
        this.preptime = time;
   }
}
