
/**
 * This class is meant to create the Schedule object, which holds the
 * array of appointments. This class manipulates the appointment array
 * by adding elements to the array, removing them, and printing the array
 * in various ways.
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */

public class Schedule {
    private Appointment[] appointments;
    private int numAppts;

    private final static int CAPACITY = 4;
    private final static int NOT_FOUND = -1;

    /**
     * This constructor initializes the Schedule object's properties,
     * the Appointment array and the size of that array.
     */
    public Schedule() {
        this.appointments = new Appointment[CAPACITY];
        this.numAppts = 0;
    }

    /**
     * This method finds the desired appointment, Appointment appt, and
     * returns the index of that element in the appointment array if it
     * is in the array.
     * @param appt
     * @return index of element, -1 if not found
     */
    private int find(Appointment appt) {
        for (int i = 0; i < numAppts; i++) {
            if (appointments[i].equals(appt)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * This method increases the size of the Appointment list by 4
     */
    private void grow() {
        Appointment[] clone = new Appointment[numAppts];
        for (int i = 0; i < numAppts; i++) {
            clone[i] = appointments[i];
        }

        appointments = new Appointment[numAppts + 4];

        for (int i = 0; i < numAppts; i++) {
            appointments[i] = clone[i];
        }
    }


    /**
     * This method adds one appointment element to the Appointment
     * array, and if added to the end of the array it then calls
     * the grow() method.
     * @param appt
     * @return true if element was added, false if not able to
     */
    public boolean add(Appointment appt) {
        if (numAppts == appointments.length) {
            grow();
        }
        if (find(appt) == NOT_FOUND) {
            appointments[numAppts] = appt;
            numAppts++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method removes the parameter from the Appointment
     * array. It searches for the specific appointment element, then
     * removes it by having the next element take its place in the
     * array, and after continuously doing this it leaves the last
     * spot of the array empty.
     * @param appt
     * @return true if process was completed, false if not
     */
    public boolean remove(Appointment appt) {
        for (int i = 0; i < numAppts; i++) {
            if (appointments[i].equals(appt)) {
                for (int j = i; j < numAppts - 1; j++) {
                    appointments[j] = appointments[j + 1];
                }
                appointments[numAppts] = null;
                numAppts--;
                return true;
            }
        }
        return false;
    }

    /**
     * This method removes all appointments from the Appointment
     * array that have the same name property, and coordinately
     * fills up the empty spaces with the non-deleted elements.
     * @param appt
     * @return true if process was complete, false if not
     */
    public boolean cancelAllAppts(Appointment appt) {
        Patient apptPatient = appt.getPatient();
        int apptCount = 0;

        for (int i = 0; i < numAppts; i++) {
            if (appointments[i].getPatient().compareTo(apptPatient) == 0) {
                apptCount++;
            }
        }

        if (apptCount == 0) {
            return false;
        } else {
            Appointment[] clone = new Appointment[numAppts];

            int currentIndex = 0;
            for (int i = 0; i < numAppts; i++) {
                if (!(appointments[i].getPatient().compareTo(apptPatient) == 0)) {
                    clone[currentIndex] = appointments[i];
                    currentIndex++;
                }
            }
            appointments = clone;
            numAppts -= apptCount;
            return true;
        }
    }

    /**
     * This method searches to see if appointment appt exists in the
     * Appointment array. It does so by calling the find() method.
     * @param appt
     * @return true if appointment found, false if not
     */
    public boolean appointmentExists(Appointment appt) {
        if (find(appt) != NOT_FOUND) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method finds out if a certain timeslot has been taken or
     * not in the Appointments list. It does this by comparing the
     * timeslots of different appointments, and seeing if they are
     * at the same location or not.
     * @param appt
     * @return true if there is a free timeslot, false if not
     */
    public boolean timeslotAvailable(Appointment appt) {
        boolean result = true;
        for (int i = 0; i < numAppts; i++) {
            if (appointments[i].getTimeslot().compareTo(appt.getTimeslot()) == 0) {
                if (appointments[i].getLocation() == appt.getLocation()) {
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * This method finds out if a patient can book their appointment
     * or not. It does so by seeing if that patient's name matches with
     * any other appointment, and then checks if their timeslot and date
     * are the same as any other appointments they have, if any.
     * @param appt
     * @return true if they can book an appointment, false if not
     */
    public boolean canPatientBook(Appointment appt) {
        boolean result = true;
        for (int i = 0; i < numAppts; i++) {
            if (appointments[i].getPatient().compareTo(appt.getPatient()) == 0) {
                if (appointments[i].getTimeslot().getDate().compareTo(appt.getTimeslot().getDate()) == 0) {
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * This method prints the Appointment array in the order as is
     */
    public void print() {
        System.out.println("*list of appointments in the schedule*");
        for (int i = 0; i < numAppts; i++) {
            System.out.println(appointments[i].toString());
        }
        System.out.println("*end of schedule*");
    }

    /**
     * This method prints the Appointment array elements after it has
     * been sorted by zip code.
     */
    public void printByZip() {
        Appointment[] arrayToSort = new Appointment[numAppts];
        for (int i = 0; i < numAppts; i++) {
            arrayToSort[i] = appointments[i];
        }

        sortByZipCode(arrayToSort);

        System.out.println("*list of appointments by zip and time slot.");
        for (int i = 0; i < numAppts; i++) {
            System.out.println(arrayToSort[i].toString());
        }
        System.out.println("*end of schedule.");

    }

    /**
     * This method prints the Appointment array elements after it has
     * been sorted by patient name, alphabetically.
     */
    public void printByPatient() {
        Appointment[] arrayToSort = new Appointment[numAppts];
        for (int i = 0; i < numAppts; i++) {
            arrayToSort[i] = appointments[i];
        }

        sortByPatient(arrayToSort);

        System.out.println("*list of appointments by patient.");
        for (int i = 0; i < numAppts; i++) {
            System.out.println(arrayToSort[i].toString());
        }
        System.out.println("*end of list");
    }

    /**
     * This method sorts a given Appointment array by zipcode. It does this
     * by using the Bubble Sort method.
     * @param arrayToSort
     */
    private void sortByZipCode(Appointment[] arrayToSort) {
        Appointment temp;

        //First Iteration Bubble Sort. Sorts Array by Zone Improvement Plan (ZIP) Code
        for (int i = 0; i < numAppts; i++) {
            for (int j = 1; j < (numAppts - i); j++) {
                if (arrayToSort[j - 1].getLocation().zipCode.compareTo(arrayToSort[j].getLocation().zipCode) > 0) {
                    //swap elements
                    temp = arrayToSort[j - 1];
                    arrayToSort[j - 1] = arrayToSort[j];
                    arrayToSort[j] = temp;
                }
            }
        }

        //Second Iteration Bubble Sort. Sorts Array by Timeslot, leaving intact ordering by ZIP Code
        for (int i = 0; i < numAppts; i++) {
            for (int j = 1; j < (numAppts - i); j++) {
                if (arrayToSort[j - 1].getLocation().zipCode.compareTo(arrayToSort[j].getLocation().zipCode) == 0) {
                    if (arrayToSort[j - 1].getTimeslot().compareTo(arrayToSort[j].getTimeslot()) > 0) {
                        //swap elements
                        temp = arrayToSort[j - 1];
                        arrayToSort[j - 1] = arrayToSort[j];
                        arrayToSort[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * This method sorts a given Appointment array by patient name, alphabetically.
     * It does this by using the Bubble Sort method.
     * @param arrayToSort
     */
    private void sortByPatient(Appointment[] arrayToSort) {
        Appointment temp;
        for (int i = 0; i < numAppts; i++) {
            for (int j = 1; j < (numAppts - i); j++) {
                if (arrayToSort[j - 1].getPatient().compareTo(arrayToSort[j].getPatient()) > 0) {
                    //swap elements
                    temp = arrayToSort[j - 1];
                    arrayToSort[j - 1] = arrayToSort[j];
                    arrayToSort[j] = temp;
                }
            }
        }
    }

}