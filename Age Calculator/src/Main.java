import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        int year = 0, month = 0, day = 0;

        boolean check = false;

        while (!check) {
            System.out.print("> ");
            System.out.println("Enter your birth day(Day) eg: 01, 02,.. ");
            System.out.print("> ");
            day = reader.nextInt();

            if (!(String.valueOf(day).length() > 2) && (day <= 31 && day >= 1 )) {
                check = true;
            }
        }

        while (check) {
            System.out.print("> ");
            System.out.println("Enter your birth month(Month) eg: 01, 02,.. ");
            System.out.print("> ");
            month = reader.nextInt();

            if (!(String.valueOf(month).length() > 2 && String.valueOf(month).length() == 0) &&
                                                    (month <= 12 && month > 0 )) {
                check = false;
            }
        }

        while (!check) {
            System.out.print("> ");
            System.out.println("Enter your birth year eg: 1998 ");
            System.out.print("> ");
            year = reader.nextInt();

            if (!(year > LocalDate.now().getYear())) {
                check = true;
            }
        }

        AgeCalc dob = new AgeCalc();
       dob.setDateOfBirth(year, month, day);
        dob.calcDifference();

        if(dob.getMonth() < 0){
            month = LocalDate.now().getMonthValue();
            day = LocalDate.now().getDayOfMonth();
            dob.setDateOfBirth(year, month, day);
            dob.calcDifference();
        }

        if(dob.getDays() < 0){
            day = LocalDate.now().getDayOfMonth();
            dob.setDateOfBirth(year, month, day);
            dob.calcDifference();
       }

        System.out.print("> ");
        System.out.println("Your are " + dob.getYears() + " years " + dob.getMonth() + " months "
                + dob.getDays() + " days old" + "\n" +
                "You were born on " + dob.weekDayOfBirth());
        }
    }

