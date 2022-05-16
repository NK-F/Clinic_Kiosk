/**
 * This Appointment Class is made to create the Appointment object,
 * which is used in the Appointment[] array in the Schedule Class.
 * This class creates the Appointment object, with patient, timeslot, and
 * location properties, and not only prints the desired information but
 * also checks if two appointments are the same.
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */
public class Appointment {
    private Patient patient;
    private Timeslot slot;
    private Location location;

    /**
     * This public constructor constructs the Appointment object,
     * initializing its properties: patient, slot, and location
     * @param patient, slot, location, the properties of Appointment
     */
    public Appointment(Patient patient, Timeslot slot, String location) {
        this.patient = patient;
        this.slot = slot;
        this.location = Location.valueOf(location);
    }

    /**
     * A get statement that only returns the private instance patient
     * @return the instance variable patient
     */
    public Patient getPatient(){
        return this.patient;
    }

    /**
     * A get statement that only returns the private instance location
     * @return the instance variable location
     */
    public Location getLocation(){
        return this.location;
    }

    /**
     * A get statement that only returns the private instance slot
     * @return the instance variable slot
     */
    public Timeslot getTimeslot(){
        return this.slot;
    }

    /**
     * This method takes an Object obj, which is an Appointment object,
     * and compares this to another appointment and returns if they are the same.
     * @param obj the appointment object
     * @return true if the two appointments are the same, false if they are different
     */
    @Override
    public boolean equals(Object obj) {
        //checks to see if object given (appointment) matches any other entry in appointment list
        if (obj instanceof Appointment) {
            Appointment appointment = (Appointment) obj;
            if ((appointment.patient.compareTo(this.patient) == 0) && (appointment.slot.compareTo(this.slot) == 0) && appointment.location == this.location) {
                return true;
            }
        }
        return false;
    }


    /**
     *This method only returns the required statement that shows all properties of
     * the appointment as a String statement.
     * @return String sentence of appointment details
     */
    @Override
    public String toString() {
        //returns the appointment and patient information
        return patient.toString() + ", Appointment detail: " + slot.toString() +", " + location.getLocationString();
    }
}