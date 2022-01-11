import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Tester {

    public static void main(String[] args){

        // Get current time in Java
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        System.out.println(dtf.format(localDate));

        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime nowTime = LocalTime.now();
        System.out.println(dtfTime.format(nowTime));

        LocalTime startHours = LocalTime.parse("08:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime closingHours = LocalTime.parse("19:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(startHours.isBefore(nowTime));

    }
}
