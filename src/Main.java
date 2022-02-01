import coffee2022.enums.RecipeTypes;
import coffee2022.objs.Customer;
import coffee2022.menu.Menu;
import coffee2022.monitoring.OrderManagement;
import coffee2022.objs.Recipe;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Store the opening and closing hours
    public static LocalTime startHours = LocalTime.parse("08:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
    public static LocalTime closingHours = LocalTime.parse("22:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));

    // Menu containing all the recipes
    public static Menu menu = new Menu();
    public static int customerId;

    // Scanner shared across
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        if (LocalTime.now().isBefore(startHours)) {
            long diff =  LocalTime.now().until(startHours, ChronoUnit.MINUTES);
            if (diff < 60) {
                System.out.println("We will be open in [x] minutes. Pls come back again at " + startHours);
            } else {
                int hour = (int) (diff / 60);
                int minute = (int) diff - (hour * 60);
                System.out.printf("We will be open in %d hour(s) and %d minutes. Pls come back again at %s.", hour,
                        minute, startHours);
            }
            System.exit(0);
        } else if (LocalTime.now().isAfter(closingHours)) {
            System.out.println("We are currently closed. Pls come back again tomorrow at " + startHours);
            System.exit(0);
        }

        System.out.println("Hello, welcome to the Steam Beans store! What can I get for you today?");
        OrderManagement om = new OrderManagement();

        do {
            om.addItem(showMenu());
            System.out.print("Anything else that you want to order? (Y/N):");
            // Should we make this case insensitive?
        } while (!sc.next().equals("N"));

        System.out.println("Your total order: ");
        om.printList();
        double totalPrice = om.countTotal();
        double tax = om.countTax();
        System.out.printf("\nYour total will be $%.2f + $%.2f sales tax = $%.2f.",  om.countTotal(), om.countTax(),
                om.countGrandTotal());
        System.out.print("\nWhat's your name?");

        String name = sc.next();

        // will create new id for the user
        customerId = om.getNewId();
        om.customer = new Customer(customerId, name);

        //saving the orders to orderManagement class
        om.save();
        System.out.println(om.customer.name + ", your order will be ready in " + om.countPrepTime() + " minutes.");
    }

    public static RecipeTypes showMenuCategory() {

        while (true) {
            // Display the various menu categories
            System.out.println("Menu");
            System.out.println("--------------------------");
            int index = 1;
            for (RecipeTypes t : RecipeTypes.values()) {
                System.out.println(index + " : "+t.label);
                index++;
            }

            // Prompts the user to choose a category
            System.out.print("Choose a category: ");

            // Validate that the user actually input a number
            int typeInput = 0;
            try {
                typeInput = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Sorry, please try again :)");
                sc.next();
                continue;
            }

            // Validate that the user input a correct number (1 to RecipeTypes.values().length)
            if (typeInput < 1 || typeInput > RecipeTypes.values().length) {
                System.out.println("Wrong number, please try again :)");
                continue;
            }

            // We have a valid category, returns it
            // We need to -1 the typeInput because the user category list starts at 1 while the array actually starts at 0
            return RecipeTypes.values()[typeInput-1];
        }
    }

    public static Recipe showMenu() {
        RecipeTypes recipeType = showMenuCategory();
        // Display a header
        System.out.println("\n\n"+recipeType.label);
        System.out.println("-------------------");

        // Store the list of choices depending on the category selected by the user
        ArrayList<Recipe> currentChoices = menu.getMenuForType(recipeType);

        Recipe selectedRecipe = null;
        while (selectedRecipe == null) {
            // Display the choices available
            for (int i = 0; i < currentChoices.size(); i++) {
                System.out.println((i + 1) + ". " + currentChoices.get(i).name);
            }

            // Prompt the user for a choice
            System.out.print("Enter the number to place your order: ");
            int input = 0; // Initializing this before the try-catch block
            try {
                input = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Sorry, please try again :)");
                sc.next();
                continue;
            }
            //add an extra logic to catch string or character (above)
            if (input >= currentChoices.size() + 1) {
                System.out.println("Wrong number, try again");
            } else {
                selectedRecipe = currentChoices.get(input - 1);
                System.out.println("\nYou have ordered " + selectedRecipe.name);
            }
        }
        return selectedRecipe;
    }
}
