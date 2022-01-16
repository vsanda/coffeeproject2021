import coffee2022.enums.RecipeTypes;
import coffee2022.objs.Customer;
import coffee2022.objs.Menu;
import coffee2022.objs.OrderManagement;
import coffee2022.objs.Recipe;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Store the opening and closing hours
    public static LocalTime startHours;
    public static LocalTime closingHours;

    // coffee2022.objs.Menu containing all the recipes
    public static Menu menu = new Menu();
    public static int customerId;

    // Scanner shared across
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        startHours = LocalTime.parse("08:00:00", dtf);
        closingHours = LocalTime.parse("22:00:00", dtf);
        LocalTime nowTime = LocalTime.now();

        //can get timestamp next time and throw exception if store is closed.
        if (nowTime.isBefore(startHours)) {
            //Need to work out the calculation next time
            System.out.println("We will be open in [x] minutes. Pls come back again at " + startHours);
            System.exit(0);
        } else if (nowTime.isAfter(closingHours)) {
            System.out.println("We are currently closed. Pls come back again tomorrow at " + startHours);
            System.exit(0);
        }

        System.out.println("Hello, welcome to the store, what can I get for you today?");
        Scanner sc = new Scanner(System.in);
        OrderManagement om = new OrderManagement();

        do {
            om.addItem(showMenu());
            System.out.println("Anything else that you want to order? (Y/N):");
        } while (sc.nextLine().equals("Y"));

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

    public static RecipeTypes showMenuCategory() {
        while (true) {
            int typeInput = 0;
            System.out.println("Type 1 to look at the beverage menu, or 2 to look at the food menu:");
            try {
                typeInput = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Sorry, please try again :)");
                sc.next();
                continue;
            }
            if (typeInput == 1 || typeInput == 2) {
                return typeInput == 1 ? RecipeTypes.FOOD:RecipeTypes.BEVERAGE;
            } else {
                System.out.println("Wrong number, please try again :)");
            }
        }
    }

    public static Recipe showMenu() {
        RecipeTypes recipeType = showMenuCategory();

        // Store the list of choices depending on the category selected by the user
        ArrayList<Recipe> currentChoices = menu.getMenuForType(recipeType);

        Recipe selectedRecipe = null;
        while (selectedRecipe == null) {
            // Display the choices available
            for (int i = 0; i < currentChoices.size(); i++) {
                System.out.println((i + 1) + ". " + currentChoices.get(i).name);
            }

            // Prompt the user for a choice
            System.out.println("Enter the number to place your order: ");
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
                System.out.println("You have ordered " + selectedRecipe.name);
            }
        }

        return selectedRecipe;
    }
}
