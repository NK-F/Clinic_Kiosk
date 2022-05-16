
/**
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */

public class Timeslot implements Comparable<Timeslot> {
    private Date date;
    private Time time;

    public Timeslot(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public Date getDate(){
        return this.date;
    }

    @Override
    public String toString() {
        return this.date.toString() + ", " + this.time.toString();
    }

    @Override
    public int compareTo(Timeslot slot) {
        if (this == slot) {
            return 0;
        } else if (this.date.compareTo(slot.date) > 0) {
            return 1;
        } else if (this.date.compareTo(slot.date) < 0) {
            return -1;
        } else {
            if (this.time.compareTo(slot.time) > 0) {
                return 1;
            } else if (this.time.compareTo(slot.time) < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}