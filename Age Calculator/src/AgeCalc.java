import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;


public class AgeCalc {

    private Period period;
    private LocalDate dateOfBirth;
    private final LocalDate currentDate;
    private final String[] maleDayName;
    private final String[] femaleDayName;

    /**
     * Constructor for class AgeCalc
     */
    public AgeCalc()
    {
        currentDate = LocalDate.now();
        maleDayName = new String[]{"Kwadwo", "Kwabena", "Kwaku", "Yaw", "Kofi", "Kwame", "Akwasi"};
        femaleDayName = new String[]{"Adwoa", "Abena", "Akua", "Yaa", "Afia", "Ama", "Akosua"};
    }

    /**
     * @return dateOfBirth The date of birth input by user
     */
    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * @return femaleDayName The name from list of male names
     *  * based on their date of birth
     */
    public String[] getMaleDayName()
    {
        return maleDayName;
    }

/**
 * @return femaleDayName The name from list of female names
 * based on their date of birth
 */
public String[] getFemaleDayName()
{
    return femaleDayName;
}

    /**
     * Set the date of birth
     * @param year the birth year
     * @param month the birth month
     * @param day the birth day
     */
    public void setDateOfBirth(int year, int month, int day)
    {
        try
        {
            dateOfBirth = LocalDate.of(year, month, day);
        }
        catch(DateTimeException e)
        {
            System.out.println("Enter a valid date of birth" + e);
        }
    }

    /**
     * Calculates the years, months and days spent since date of birth
     */
    public void calcDifference()
    {
        try {
            period = Period.between(dateOfBirth, currentDate);
        }
        catch(NullPointerException e){
            System.out.println("period in null - enter appropriate date of birth" + e);
        }
    }

    /**
     * Get the day of birth i.e. Monday, ...
     */
    public String weekDayOfBirth()
    {
       return dateOfBirth.getDayOfWeek().toString();
    }

    /**
     * @return Years depending on the date of birth
     */
    public int getYears()
    {
        return period.getYears();
    }

    /**
     * @return number of months remaining depending on the date of birth
     */
    public int getMonth()
    {
        return period.getMonths();
    }

    /**
     * @return number of days remaining depending on the date of birth
     */
    public int getDays()
    {
        return period.getDays();
    }
}
