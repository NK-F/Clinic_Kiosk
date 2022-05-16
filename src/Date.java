import java.util.StringTokenizer;
import java.util.Calendar;

/**
 * This class is meant to create the Date object, which holds the year, month, and day
 * of a given date. This class initializes the object, as well as use the Calendar class
 * and other methods to check if there is a Leap Year. It also compares two dates as well
 * as create a String sentence of the date.
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * This constructor initializes the Date object by taking the date in
     * "mm/dd/yyyy" format.
     * @param date
     */
    public Date(String date) {
        StringTokenizer dateParser = new StringTokenizer(date, "/");

        // Validates that String Contains Exactly 3 Tokens, delimited by char /
        if (dateParser.countTokens() == 3) {

            // Attempts to parse String into a date components
            try {
                month = Integer.parseInt(dateParser.nextToken());
                day = Integer.parseInt(dateParser.nextToken());

                // Stores Next Token in Temporary Sting so that we can ascertain its length
                String parseYear = dateParser.nextToken();
                // If String is exactly 4 characters, then it proceeds to parsing
                if (parseYear.length() == 4) {
                    year = Integer.parseInt(parseYear);
                }
                // If String fails the length check, Date is initialized as zero
                else {
                    month = day = year = 0;
                }
            }
            // If parseInt throws numberFormatException, i.e. String passed is not of form integer, then date is invalid
            catch (NumberFormatException e) {
                month = day = year = 0;
            }
        }
        // If String Cannot be Tokenized as expected, Date is initialized as zero
        else {
            month = day = year = 0;
        }
    }

    // Creates an object with today’s date (see Calendar class)

    /**
     * This constructor uses the Calendar class to help initialize the
     * Date object with the current date, not date given.
     */
    public Date() {

        // Gets Calendar instance populated with current date
        Calendar cal = Calendar.getInstance();

        // Retrieves Month Day and Year from calendar object
        // We increment Calendar.MONTH by 1 because the Java Time API is dumb and indexes months from zero.
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        year = cal.get(Calendar.YEAR);
    }

    /**
     * This method checks to see if the date is a real date or not.
     * @return true if date is real, false if not
     */
    public boolean isValid() {
        boolean isValidDate = false;

        if (month > 0 && month <= 12) {
            int maxDaysInMonth = getDaysInMonth();
            if (day > 0 && day <= maxDaysInMonth) {
                isValidDate = true;
            }
        }

        return isValidDate;
    }

    /**
     * This method checks to see if a patient's date of birth is real
     * or not by checking if the DOB is before or after today's date.
     * @param today
     * @return true if DOB is valid, false if not
     */
    public boolean isValidDOB(Date today) {
        if (!(this.compareTo(today) <= 0)) {
            return false;
        }
        return true;
    }

    /**
     * This method checks to see if the year given is the current year.
     * @return true if given year is current year, false if not
     */
    public boolean isCurrentYear() {
        int CURRENT_YEAR = 2022;
        if (this.year > CURRENT_YEAR) {
            return false;
        } else return true;
    }

    /**
     * This method gets the number of days in a given month, given if the month is
     * in a leap year or not.
     * @return the number of days in that month
     */
    private int getDaysInMonth() {
        // Defines Constants for use in month calculations
        int JANUARY = 1, FEBRUARY = 2, MARCH = 3, APRIL = 4, MAY = 5, JUNE = 6, JULY = 7, AUGUST = 8, SEPTEMBER = 9, OCTOBER = 10, NOVEMBER = 11, DECEMBER = 12;

        // Responsible for storing number of days in a given month. Reduces overall number of return statements
        int numDays = 0;

        //Case for Months with 31 days
        if (month == JANUARY || month == MARCH || month == MAY || month == JULY || month == AUGUST || month == OCTOBER || month == DECEMBER) {
            numDays = 31;
        }

        // Case for months with 30 days
        else if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
            numDays = 30;
        }

        // Case for February
        else if (month == FEBRUARY) {
            // Case for Leap Year
            if (isLeapYear()) {
                numDays = 29;
            }
            // Default Case
            else {
                numDays = 28;
            }
        }
        return numDays;
    }

    /**
     * This method determines if the given year is a leap year or not.
     * @return true if year is leap year, false if not
     */
    private boolean isLeapYear() {
        // Defines Constants for use in leap year calculations
        int QUADRENNIAL = 4;
        int CENTENNIAL = 100;
        int QUARTERCENTENNIAL = 400;

        // Responsible for tracking if a given year is a leap year. Reduces overall number of return statements
        boolean isLeapYear = false;

        // Evaluates if given year is a leap year
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUARTERCENTENNIAL == 0) {
                    isLeapYear = true;
                }
            } else {
                isLeapYear = true;
            }
        }
        return isLeapYear;
    }

    /**
     * This method returns a String sentence containing the date given
     * @return the String date in format "mm/dd/yyyy"
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * This method compares two dates to see if they are the same, after, or before.
     * @param date
     * @return 0 if the same date, 1 if one date is after the other, -1 if one is
     *          before the other
     */
    @Override
    public int compareTo(Date date) {
        if (this == date) {
            return 0;
        } else if (this.year > date.year) {
            return 1;
        } else if (this.year < date.year) {
            return -1;
        } else {
            if (this.month > date.month) {
                return 1;
            } else if (this.month < date.month) {
                return -1;
            } else {
                if (this.day > date.day) {
                    return 1;
                } else if (this.day < date.day) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}