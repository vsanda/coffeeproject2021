import java.util.ArrayList;

public class Tester {

    public static void main(String[] args){
        ArrayList<String> beverages = new ArrayList<String>();
        beverages.add("1. Cappuccino");
        beverages.add("2. Americano");
        beverages.add("3. Pumpkin Latte");

        String result = beverages.get(0);
        System.out.println(result.substring(3));

        Recipe hamCheeseCroissant = new Recipe("Ham and Cheese Croissant");
        hamCheeseCroissant.setPrice(5.95);
        hamCheeseCroissant.setPrepTime(5);
        Recipe baconSandwich = new Recipe("Bacon, Cheddar and Egg Sandwich");
        baconSandwich.setPrice(5.95);
        baconSandwich.setPrepTime(5);
        Recipe tomatoWrap = new Recipe("Tomato Feta Basil Wrap (v)");
        tomatoWrap.setPrice(4.95);
        tomatoWrap.setPrepTime(3);

        ArrayList<Recipe> foodRecipes = new ArrayList<Recipe>();
        foodRecipes.add(hamCheeseCroissant);
        foodRecipes.add(baconSandwich);
        foodRecipes.add(tomatoWrap);

    }
}
