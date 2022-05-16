import java.util.StringTokenizer;

/**
 * This class is meant to create the Time object, which holds the properties
 * hour and minute. This class initializes the Time object, checks if two Time
 * objects are the same as well as if the time actually is valid, and creates a
 * String sentence in the format "hh:mm".
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */

public class Time implements Comparable<Time> {
    private int hour;
    private int minute;

    /**
     * This constructor initializes the Time object by converting a String input
     * into a numerical time.
     * @param time
     */
    public Time(String time) {
        StringTokenizer timeParser = new StringTokenizer(time, ":");

        // Validates that String Contains Exactly 2 Tokens, delimited by char :
        if (timeParser.countTokens() == 2) {
            // Attempts to parse String into time components
            try {
                hour = Integer.parseInt(timeParser.nextToken());
                minute = Integer.parseInt(timeParser.nextToken());
            }
            // If parseInt throws numberFormatException, i.e. String passed is not of form integer, then time is invalid
            catch (NumberFormatException e) {
                hour = minute = -1;
            }
        }
    }

    /**
     * This method checks if the time given is a valid time or not.
     * @return true if time is valid, false if not.
     */
    public boolean isValid() {
        boolean validDate = false;

        if (this.hour >= 9 && this.hour <= 16) {
            if (this.hour == 16) {
                if (this.minute >= 0 && this.minute <= 45 && this.minute % 15 == 0) {
                    validDate = true;
                }
            } else {
                if (this.minute >= 0 && this.minute <= 59 && this.minute % 15 == 0) {
                    validDate = true;
                }
            }
        }
        return validDate;
    }

    /**
     * This method returns a String sentence of the time in format "hh:mm"
     * @return String sentence of the time in format "hh:mm"
     */
    @Override
    public String toString() {
        return this.hour + ":" + String.format("%02d", this.minute);
    }

    /**
     * This method compares two Time objects to see if they are the same, one is greater or
     * lesser than the other.
     * @param time
     * @return 0 if the same time, 1 if one time is after the other, -1 if one is
     *          before the other
     */
    @Override
    public int compareTo(Time time) {
        if (this == time) {
            return 0;
        } else if (this.hour > time.hour) {
            return 1;
        } else if (this.hour < time.hour) {
            return -1;
        } else {
            if (this.minute > time.minute) {
                return 1;
            } else if (this.minute < time.minute) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}