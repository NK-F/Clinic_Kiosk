/**
 * This class is meant to create the Patient object, which holds the patient's
 * first and last name, as well as their date of birth (DOB). This class
 * initializes these properties, as well as creates a string sentence of them
 * and compares two patient objects.
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */

public class Patient implements Comparable<Patient> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * This constructor initializes the Patient object's properties,
     * the first name, last name, and DOB.
     * @param fname first name
     * @param lname last name
     * @param dob date of birth
     */
    public Patient(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * This method returns all the Patient object's properties as a String
     * sentence.
     * @return String of first name, last name, and DOB
     */
    @Override
    public String toString() {
        //returns string of patient name and DOB
        return this.fname + " " + this.lname + ", DOB: " + String.valueOf(this.dob);
    }


    /**
     * This method compares two String names and checks if they are the same, alphabetically
     * higher or lower. It does this by comparing each letter at of each name at the same spot
     * @param patientName
     * @param thisName
     * @return 0 if they are the same, 1 if one name is alphabetically higher, -1 if one name is
     *          alphabetically lower
     */
    public int comparingStrings(String patientName, String thisName) {
        for (int i = 0; i < patientName.length() && i < thisName.length(); i++) {
            if ((int) patientName.charAt(i) == (int) thisName.charAt(i)) {
                continue;
            } else {
                if ((int) patientName.charAt(i) < (int) thisName.charAt(i)) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }

    /**
     * This method compares two Patient objects to see if they are the same or not.
     * It does this by comparing their last names, then their first names, then their
     * DOB.
     * @param patient
     * @return 0 if they are the same, 1 if one name is alphabetically higher, -1 if one name is
     *          alphabetically lower
     */
    @Override
    public int compareTo(Patient patient) {
        //Last names are compared first
        if (!patient.lname.equals(this.lname)) {
            return comparingStrings(patient.lname, this.lname);
        } else {
            //Then first names are compared
            if (!patient.fname.equals(this.fname)) {
                return comparingStrings(patient.fname, this.fname);
            } else {
                //Then DOBs are compared
                if (!(patient.dob.compareTo(this.dob) == 0)) {
                    return patient.dob.compareTo(this.dob);
                }
            }
        }
        return 0;
    }

}
