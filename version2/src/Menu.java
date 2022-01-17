import java.util.*;

//Show Menu and Add menu
public class Menu {

    private final ArrayList<Recipe> bevRecipes;
    private final ArrayList<Recipe> foodRecipes;
    private final ArrayList<Recipe> pastryRecipes;
    private Hashtable<String, ArrayList<Recipe>> menuDict;
    // private final Hashtable<Integer, Hashtable<String, ArrayList<Recipe>>> nestedDict;

    public Menu() {
        bevRecipes = new ArrayList<Recipe>();
        foodRecipes = new ArrayList<Recipe>();
        pastryRecipes = new ArrayList<Recipe>();
        menuDict = new Hashtable<String, ArrayList<Recipe>>();
        menuDict.put("Beverages", bevRecipes);
        menuDict.put("Food", foodRecipes);
        menuDict.put("Pastry", pastryRecipes);
    }

    public void showMenu() {
        int idx = 1;
        for (String s : this.menuDict.keySet()) {
            System.out.println(idx + ". " + s);
            idx++;
        }
    }

    public Recipe showItems(int input) {
        //Benoit: is there any chance we can automate this instead of hardcoding it?
        ArrayList<Recipe> recipes = new ArrayList<Recipe>(0);
        if (input == 2) {
            recipes = bevRecipes;
        } else if (input == 1) {
            recipes = foodRecipes;
        } else if (input == 3) {
            recipes = pastryRecipes;
        }

        for (int i = 0; i  < recipes.size(); i ++) {
            System.out.println((i+1) + ". " + recipes.get(i).name);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("What would you like to order? Enter here: ");
        Recipe selectedRecipe = null;
        try {
            int order = sc.nextInt();
            selectedRecipe = recipes.get(order - 1);
            System.out.println("You have selected " + selectedRecipe.name);
        } catch (InputMismatchException e) {
            System.out.println("Item is not available.  Pls try again.");
            sc.nextInt();
        }

        return selectedRecipe;
    }

    public void addBev(Recipe recipe) {
        bevRecipes.add(recipe);
    }

    public void addFood(Recipe recipe) {
        foodRecipes.add(recipe);
    }

    public void addPastry(Recipe recipe) {
        pastryRecipes.add(recipe);
    }

    // get all categories

}
