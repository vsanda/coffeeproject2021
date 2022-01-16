import java.util.HashMap;

public class Inventory extends ConnectedClass {

    private final HashMap<Ingredient, Integer> inventory;

    public Inventory() {
        this.inventory = new HashMap<Ingredient, Integer>();
        this.inventory.put(Ingredients.sugar, 100);
        this.inventory.put(Ingredients.coffeeBeans, 200);
        this.inventory.put(Ingredients.milk, 100);
        this.inventory.put(Ingredients.vanilla, 50);
        this.inventory.put(Ingredients.pumpkinSpice, 25);
        this.inventory.put(Ingredients.whippedCream, 25);
        this.inventory.put(Ingredients.ham, 100);
        this.inventory.put(Ingredients.bacon, 150);
        this.inventory.put(Ingredients.tomato, 75);
        this.inventory.put(Ingredients.basil, 50);
        this.inventory.put(Ingredients.swissCheese, 75);
        this.inventory.put(Ingredients.fetaCheese, 75);
        this.inventory.put(Ingredients.cheddarCheese, 75);
        this.inventory.put(Ingredients.croissant, 100);
        this.inventory.put(Ingredients.egg, 150);
        this.inventory.put(Ingredients.tortilla, 100);
    }

    public void addInventory(Ingredient ingredient, int quantity){
        connection.insertInventory(ingredient.name, quantity);
    }


}
