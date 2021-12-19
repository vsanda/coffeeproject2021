import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static LocalTime startHours;
    public static LocalTime closingHours;
    public static ArrayList<Recipe> bevRecipes;
    public static ArrayList<Recipe> foodRecipes;
    public static int customerId;

    public static void main(String[] args) {
        startHours = LocalTime.parse("08:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        closingHours = LocalTime.parse("19:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));

        //adding new ingredients here
        Ingredient sugar = new Ingredient("sugar");
        Ingredient coffeeBeans = new Ingredient("coffee beans");
        Ingredient milk = new Ingredient("milk");
        Ingredient vanilla = new Ingredient("vanilla extract");
        Ingredient pumpkinSpice = new Ingredient("pumpkin pie spice");
        Ingredient whippedCream = new Ingredient("whipped cream");

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
        pumpkinLatte.setPrice(5.75);
        pumpkinLatte.setPrepTime(5);

        //recipe for ham & Cheese croissant
        Recipe hamCheeseCroissant = new Recipe("Ham and Cheese Croissant");
        hamCheeseCroissant.setPrice(5.95);
        hamCheeseCroissant.setPrepTime(5);
        Recipe baconSandwich = new Recipe("Bacon, Cheddar and Egg Sandwich");
        baconSandwich.setPrice(5.95);
        baconSandwich.setPrepTime(5);
        Recipe tomatoWrap = new Recipe("Tomato Feta Basil Wrap (v)");
        tomatoWrap.setPrice(4.95);
        tomatoWrap.setPrepTime(3);

        bevRecipes = new ArrayList<Recipe>();
        bevRecipes.add(cappuccino);
        bevRecipes.add(americano);
        bevRecipes.add(pumpkinLatte);

        foodRecipes = new ArrayList<Recipe>();
        foodRecipes.add(hamCheeseCroissant);
        foodRecipes.add(baconSandwich);
        foodRecipes.add(tomatoWrap);

        //can get timestamp next time and throw exception if store is closed.
        System.out.println("Hello, welcome to the store, what can I get for you today?");
        Scanner sc = new Scanner(System.in);

        OrderManagement om = new OrderManagement();

        do {
            om.addItem(showMenu());
            System.out.println("Anything else that you want to order? (Y/N):");

            if (!sc.nextLine().equals("Y")) {
                break;
            }
        } while (true);

        System.out.println("Your total order: ");
        om.printList();
        double totalPrice = om.countTotal();
        double tax = om.countTax();
        System.out.printf("\nYour total will be $%.2f + $%.2f sales tax = $%.2f. What's your name?", totalPrice, tax,
                (totalPrice + tax));

        String name = sc.nextLine();

        // will create new id for the user
        customerId = om.getNewId();
        om.customer = new Customer(customerId, name);
        int totalTime = om.countPrepTime();
        int numberItems = om.getNumberItems();

        //saving the orders to orderManagement class
        om.save(name, numberItems, totalPrice);
        System.out.println(om.customer.name + ", your order will be ready in " + totalTime + " minutes.");

    }

    public static int showMenuCategory() {
        Scanner sc = new Scanner(System.in);
        int typeInput;

        while (true) {
            System.out.println("Type 1 to look at the beverage menu, or 2 to look at the food menu:");
            // do we need to catch string error as well?
            typeInput = sc.nextInt();
            if (typeInput == 1 || typeInput == 2) break;
            System.out.println("Wrong number, please try again :) ");
        }

        return typeInput;
    }

    public static Recipe showMenu() {
        // Either get 1 for food or 2 for food
        int category = showMenuCategory();

        // Store the list of choices depending the category selected by the user
        ArrayList<Recipe> currentChoices;
        if (category == 1) {
            currentChoices = bevRecipes;
        } else {
            currentChoices = foodRecipes;
        }

        Recipe selectedRecipe = null;
        while (selectedRecipe == null) {
            // Display the choices available
            for (int i = 0; i < currentChoices.size(); i++) {
                System.out.println((i + 1) + ". " + currentChoices.get(i).name);
            }

            // Prompt the user for a choice
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the number to place your order: ");
            int input = sc.nextInt();

            if (input >= currentChoices.size() + 1) {
                System.out.println("Wrong number, try again");
            } else {
                selectedRecipe = currentChoices.get(input - 1);
                System.out.println("You have ordered " + selectedRecipe.name);
            }
        }

        return selectedRecipe;
    }
}
