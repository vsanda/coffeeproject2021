package coffee2022.menu;

import coffee2022.baseobjs.ConnectedClass;
import coffee2022.enums.RecipeTypes;
import coffee2022.objs.Ingredients;
import coffee2022.objs.Recipe;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Menu extends ConnectedClass {

    private final ArrayList<Recipe> recipes = new ArrayList<>();

    public Menu() {
        //recipe for cappuccino
        Recipe cappuccino = new Recipe("Cappuccino", RecipeTypes.BEVERAGE);
        cappuccino.ingredients.put(Ingredients.sugar, 1);
        cappuccino.ingredients.put(Ingredients.coffeeBeans, 3);
        cappuccino.setPrice(3.75);
        cappuccino.setPrepTime(3);
        recipes.add(cappuccino);

        //recipe for Americano
        Recipe americano = new Recipe("Americano", RecipeTypes.BEVERAGE);
        americano.ingredients.put(Ingredients.coffeeBeans, 2);
        americano.setPrice(2.95);
        americano.setPrepTime(2);
        recipes.add(americano);

        //recipe for Pumpkin Latte
        Recipe pumpkinLatte = new Recipe("Pumpkin Latte", RecipeTypes.BEVERAGE);
        pumpkinLatte.ingredients.put(Ingredients.coffeeBeans, 3);
        pumpkinLatte.ingredients.put(Ingredients.sugar, 2);
        pumpkinLatte.ingredients.put(Ingredients.milk, 2);
        pumpkinLatte.ingredients.put(Ingredients.vanilla, 1);
        pumpkinLatte.ingredients.put(Ingredients.pumpkinSpice, 1);
        //Ask benoit on how to make whipped cream optional?
        pumpkinLatte.ingredients.put(Ingredients.whippedCream, 1);
        pumpkinLatte.setPrice(5.75);
        pumpkinLatte.setPrepTime(5);
        recipes.add(pumpkinLatte);

        //recipe for ham & cheese croissant
        Recipe hamCheeseCroissant = new Recipe("Ham and Cheese Croissant", RecipeTypes.FOOD);
        hamCheeseCroissant.ingredients.put(Ingredients.ham, 2);
        hamCheeseCroissant.ingredients.put(Ingredients.swissCheese, 1);
        hamCheeseCroissant.ingredients.put(Ingredients.croissant, 1);
        hamCheeseCroissant.setPrice(5.95);
        hamCheeseCroissant.setPrepTime(5);
        recipes.add(hamCheeseCroissant);

        //recipe for bacon & egg croissant
        Recipe baconSandwich = new Recipe("Bacon, Cheddar and Egg Sandwich", RecipeTypes.FOOD);
        baconSandwich.ingredients.put(Ingredients.bacon, 3);
        baconSandwich.ingredients.put(Ingredients.cheddarCheese, 1);
        baconSandwich.ingredients.put(Ingredients.egg, 1);
        baconSandwich.ingredients.put(Ingredients.croissant, 1);
        baconSandwich.setPrice(5.95);
        baconSandwich.setPrepTime(5);
        recipes.add(baconSandwich);

        //recipe for tomato & feta basil wrap
        Recipe tomatoWrap = new Recipe("Tomato Feta Basil Wrap (v)", RecipeTypes.FOOD);
        tomatoWrap.ingredients.put(Ingredients.tomato, 2);
        tomatoWrap.ingredients.put(Ingredients.fetaCheese, 1);
        tomatoWrap.ingredients.put(Ingredients.basil, 1);
        tomatoWrap.ingredients.put(Ingredients.tortilla, 1);
        tomatoWrap.setPrice(4.95);
        tomatoWrap.setPrepTime(3);
        recipes.add(tomatoWrap);

        // Pastries
        Recipe brownie = new Recipe("Chocolate Brownie", RecipeTypes.PASTRIES);
        Recipe lemonBar = new Recipe("Lemon Bar", RecipeTypes.PASTRIES);
        Recipe redVelvet = new Recipe("Red Velvet Cupcake", RecipeTypes.PASTRIES);
        recipes.add(brownie);
        recipes.add(lemonBar);
        recipes.add(redVelvet);

    }

    /**
     * This function will filter the recipes array (which contains all recipe regardless of type)
     * and return a new list containing only the recipes for the given type.
     */
    public ArrayList<Recipe> getMenuForType(RecipeTypes type) {
        return recipes.stream().filter(r -> r.type == type).collect(Collectors.toCollection(ArrayList::new));
    }


}
