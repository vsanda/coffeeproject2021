import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static LocalTime startHours;
    public static LocalTime closingHours;
    public static ArrayList<Recipe> bevRecipes;
    public static ArrayList<Recipe> foodRecipes;
    public static int customerId;

    // Order Taking
    // Database setup
    // Menu changing
    // start from scratch, read the same databases

    public static void main(String[] args) throws SQLException {
        // need to change this to your local drive or it will throw an error
        Connect con = new Connect("jdbc:sqlite:C:/sqlite/db/chinook.db");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        startHours = LocalTime.parse("08:00:00", dtf);
        closingHours = LocalTime.parse("19:00:00", dtf);
        LocalTime nowTime = LocalTime.now();

        Menu menu = new Menu();
        bevRecipes = Menu.bevRecipes;
        foodRecipes = Menu.foodRecipes;

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
        OrderManagement om = new OrderManagement(con);


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
                return typeInput;
            } else {
                System.out.println("Wrong number, please try again :)");
            }

        }

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
