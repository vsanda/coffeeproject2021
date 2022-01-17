import java.util.ArrayList;
import java.util.Hashtable;

public class TestData {

    public static void setUpMenu(Menu menu){

        //adding new ingredients here
        Ingredient sugar = new Ingredient("sugar");
        Ingredient coffeeBeans = new Ingredient("coffee beans");
        Ingredient milk = new Ingredient("milk");
        Ingredient vanilla = new Ingredient("vanilla extract");
        Ingredient pumpkinSpice = new Ingredient("pumpkin pie spice");
        Ingredient whippedCream = new Ingredient("whipped cream");
        Ingredient ham = new Ingredient("ham");
        Ingredient bacon = new Ingredient("bacon");
        Ingredient tomato = new Ingredient("tomato");
        Ingredient basil = new Ingredient("fresh basil");
        Ingredient swissCheese = new Ingredient("swiss cheese");
        Ingredient cheddarCheese = new Ingredient("cheddar cheese");
        Ingredient fetaCheese = new Ingredient("feta cheese");
        Ingredient croissant = new Ingredient("croissant");
        Ingredient egg = new Ingredient("egg");
        Ingredient tortilla = new Ingredient("tortilla");

        //recipe for cappuccino
        Recipe cappuccino = new Recipe("Cappuccino");
        cappuccino.ingredients.put(sugar, 1);
        cappuccino.ingredients.put(coffeeBeans, 3);
        cappuccino.setPrice(3.75);
        cappuccino.setPrepTime(3);

        //recipe for Americano
        Recipe americano = new Recipe("Americano");
        americano.ingredients.put(coffeeBeans, 2);
        americano.setPrice(2.95);
        americano.setPrepTime(2);

        //recipe for Pumpkin Latte
        Recipe pumpkinLatte = new Recipe("Pumpkin Latte");
        pumpkinLatte.ingredients.put(coffeeBeans, 3);
        pumpkinLatte.ingredients.put(sugar, 2);
        pumpkinLatte.ingredients.put(milk, 2);
        pumpkinLatte.ingredients.put(vanilla, 1);
        pumpkinLatte.ingredients.put(pumpkinSpice, 1);
        //Ask benoit on how to make whipped cream optional?
        pumpkinLatte.ingredients.put(whippedCream, 1);
        pumpkinLatte.setPrice(5.75);
        pumpkinLatte.setPrepTime(5);

        //recipe for ham & cheese croissant
        Recipe hamCheeseCroissant = new Recipe("Ham and Cheese Croissant");
        hamCheeseCroissant.ingredients.put(ham, 2);
        hamCheeseCroissant.ingredients.put(swissCheese, 1);
        hamCheeseCroissant.ingredients.put(croissant, 1);
        hamCheeseCroissant.setPrice(5.95);
        hamCheeseCroissant.setPrepTime(5);

        //recipe for bacon & egg croissant
        Recipe baconSandwich = new Recipe("Bacon, Cheddar and Egg Sandwich");
        baconSandwich.ingredients.put(bacon, 3);
        baconSandwich.ingredients.put(cheddarCheese, 1);
        baconSandwich.ingredients.put(egg, 1);
        baconSandwich.ingredients.put(croissant, 1);
        baconSandwich.setPrice(5.95);
        baconSandwich.setPrepTime(5);

        //recipe for tomato & feta basil wrap
        Recipe tomatoWrap = new Recipe("Tomato Feta Basil Wrap (v)");
        tomatoWrap.ingredients.put(tomato, 2);
        tomatoWrap.ingredients.put(fetaCheese, 1);
        tomatoWrap.ingredients.put(basil, 1);
        tomatoWrap.ingredients.put(tortilla, 1);
        tomatoWrap.setPrice(4.95);
        tomatoWrap.setPrepTime(3);

        // for pastries
        Recipe brownie = new Recipe("Chocolate Brownie");
        Recipe lemonBar = new Recipe("Lemon Bar");
        Recipe redVelvet = new Recipe("Red Velvet Cupcake");

        menu.addBev(cappuccino);
        menu.addBev(americano);
        menu.addBev(pumpkinLatte);

        menu.addFood(hamCheeseCroissant);
        menu.addFood(baconSandwich);
        menu.addFood(tomatoWrap);

        menu.addPastry(brownie);
        menu.addPastry(lemonBar);
        menu.addPastry(redVelvet);

    }

}
