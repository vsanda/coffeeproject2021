import java.util.ArrayList;

public class Menu extends ConnectedClass {

    public ArrayList<Recipe> bevRecipes = new ArrayList<>();
    public ArrayList<Recipe> foodRecipes = new ArrayList<>();

    public Menu() {
        //recipe for cappuccino
        Recipe cappuccino = new Recipe("Cappuccino");
        cappuccino.ingredients.put(Ingredients.sugar, 1);
        cappuccino.ingredients.put(Ingredients.coffeeBeans, 3);
        cappuccino.setPrice(3.75);
        cappuccino.setPrepTime(3);

        //recipe for Americano
        Recipe americano = new Recipe("Americano");
        americano.ingredients.put(Ingredients.coffeeBeans, 2);
        americano.setPrice(2.95);
        americano.setPrepTime(2);

        //recipe for Pumpkin Latte
        Recipe pumpkinLatte = new Recipe("Pumpkin Latte");
        pumpkinLatte.ingredients.put(Ingredients.coffeeBeans, 3);
        pumpkinLatte.ingredients.put(Ingredients.sugar, 2);
        pumpkinLatte.ingredients.put(Ingredients.milk, 2);
        pumpkinLatte.ingredients.put(Ingredients.vanilla, 1);
        pumpkinLatte.ingredients.put(Ingredients.pumpkinSpice, 1);
        //Ask benoit on how to make whipped cream optional?
        pumpkinLatte.ingredients.put(Ingredients.whippedCream, 1);
        pumpkinLatte.setPrice(5.75);
        pumpkinLatte.setPrepTime(5);

        //recipe for ham & cheese croissant
        Recipe hamCheeseCroissant = new Recipe("Ham and Cheese Croissant");
        hamCheeseCroissant.ingredients.put(Ingredients.ham, 2);
        hamCheeseCroissant.ingredients.put(Ingredients.swissCheese, 1);
        hamCheeseCroissant.ingredients.put(Ingredients.croissant, 1);
        hamCheeseCroissant.setPrice(5.95);
        hamCheeseCroissant.setPrepTime(5);

        //recipe for bacon & egg croissant
        Recipe baconSandwich = new Recipe("Bacon, Cheddar and Egg Sandwich");
        baconSandwich.ingredients.put(Ingredients.bacon, 3);
        baconSandwich.ingredients.put(Ingredients.cheddarCheese, 1);
        baconSandwich.ingredients.put(Ingredients.egg, 1);
        baconSandwich.ingredients.put(Ingredients.croissant, 1);
        baconSandwich.setPrice(5.95);
        baconSandwich.setPrepTime(5);

        //recipe for tomato & feta basil wrap
        Recipe tomatoWrap = new Recipe("Tomato Feta Basil Wrap (v)");
        tomatoWrap.ingredients.put(Ingredients.tomato, 2);
        tomatoWrap.ingredients.put(Ingredients.fetaCheese, 1);
        tomatoWrap.ingredients.put(Ingredients.basil, 1);
        tomatoWrap.ingredients.put(Ingredients.tortilla, 1);
        tomatoWrap.setPrice(4.95);
        tomatoWrap.setPrepTime(3);

        // Add the recipes to the appropriate category
        bevRecipes.add(cappuccino);
        bevRecipes.add(americano);
        bevRecipes.add(pumpkinLatte);

        foodRecipes.add(hamCheeseCroissant);
        foodRecipes.add(baconSandwich);
        foodRecipes.add(tomatoWrap);
    }
}
