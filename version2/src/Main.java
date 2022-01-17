import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static LocalTime openingHours;
    public static LocalTime closingHours;

    public static void main(String[] arg) {
        //This section is for all time related
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        openingHours = LocalTime.parse("08:00:00", dtf);
        closingHours = LocalTime.parse("17:00:00", dtf);
        LocalTime nowTime = LocalTime.now();

        // Note: is exit the right way to go about it or..?
        if (nowTime.isBefore(openingHours)) {
            long diff = nowTime.until(openingHours, ChronoUnit.MINUTES);
            if (diff < 60) {
                System.out.println("We will be open in [x] minutes. Pls come back again at " + openingHours);
            } else {
                int hour = (int) (diff / 60);
                int minute = (int) diff - (hour * 60);
                System.out.printf("We will be open in %d hour(s) and %d minutes. Pls come back again at %s.", hour,
                        minute, openingHours);
            }
            System.exit(0);
        } else if (nowTime.isAfter(closingHours)) {
            System.out.println("We are currently closed. Pls come back again tomorrow at " + openingHours);
            System.exit(0);
        }

        // If the store is open, the program will kick off:
        System.out.println("Welcome to the Steamy Beans Coffee Shop!");
        System.out.println("Select from the options below or type 'X' to exit:");

        Menu menu = new Menu();
        TestData.setUpMenu(menu);
        menu.showMenu();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter here: ");
        String input = sc.nextLine();

        OrderManagement om = new OrderManagement();
        Recipe recipe = null;

        try {
            if (input.charAt(0) == 'X'){
                System.exit(0);
            } else {
                recipe = menu.showItems(Integer.parseInt(input));
            }
        } catch (InputMismatchException e) {
            System.out.println("Option is not available! Try again.");
            menu.showMenu();
            sc.next();
        }

        om.addItem(recipe);
    }
}
