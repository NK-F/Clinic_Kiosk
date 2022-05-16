import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class acts as the terminal to the Appointments list, meaning all inputs from
 * the user go through this class and get sorted. Given a command from the user, this
 * class will manipulate the Appointment array by deciphering what the command means
 * and then calling the class to perform the command.
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */

public class Kiosk {

    private final static int NOT_FOUND = -1;

    public void run() {
        boolean exit = false;
        String line = null;
        int tokenCount = 0;
        String command = null;
        Scanner userIn = new Scanner(System.in);
        Schedule sessionSchedule = new Schedule();

        System.out.println("Kiosk running. Ready to process transactions.");

        while (exit != true) {
            line = userIn.nextLine();
            StringTokenizer inputParser = new StringTokenizer(line, " ");

            tokenCount = inputParser.countTokens();

            if (tokenCount >= 1) {
                command = inputParser.nextToken();

                if (command.equals("B")) {
                    if (tokenCount == 7) {
                        bookAppointment(sessionSchedule, inputParser.nextToken(), inputParser.nextToken(), inputParser.nextToken(), inputParser.nextToken(), inputParser.nextToken(), inputParser.nextToken());
                    }
                } else if (command.equals("C")) {
                    cancelSingleAppointment(sessionSchedule, inputParser.nextToken(), inputParser.nextToken(), inputParser.nextToken(), inputParser.nextToken(), inputParser.nextToken(), inputParser.nextToken());
                } else if (command.equals("CP")) {
                    cancelAllAppointments(sessionSchedule, inputParser.nextToken(), inputParser.nextToken(), inputParser.nextToken());
                } else if (command.equals("P")) {
                    sessionSchedule.print();
                } else if (command.equals("PZ")) {
                    sessionSchedule.printByZip();
                } else if (command.equals("PP")) {
                    sessionSchedule.printByPatient();
                } else if (command.equals("Q")) {
                    exit = true;
                } else {
                    System.out.println("Invalid command!");
                }
            }
        }
        System.out.println("Kiosk session ended.");
    }

    private void cancelSingleAppointment(Schedule sessionSchedule, String dob, String fname, String lname, String apptDate, String apptTime, String location) {
        Date patientDOB = new Date(dob);
        Date schdApptDate = new Date(apptDate);
        Time schdApptTime = new Time(apptTime);
        Timeslot cnclTimeslot = new Timeslot(schdApptDate, schdApptTime);

        Patient patient = new Patient(fname, lname, patientDOB);

        Appointment cnclAppt = new Appointment(patient, cnclTimeslot, location);

        if (sessionSchedule.appointmentExists(cnclAppt)) {
            sessionSchedule.remove(cnclAppt);
            System.out.println("Appointment cancelled.");
        } else {
            System.out.println("Not cancelled, appointment does not exist.");
        }
    }

    public void cancelAllAppointments(Schedule sessionSchedule, String dob, String fname, String lname) {
        Date patientDOB = new Date(dob);
        Patient patient = new Patient(fname, lname, patientDOB);
        Date apptDate = new Date();
        Time apptTime = new Time("09:00");
        Timeslot cnclTimeslot = new Timeslot(apptDate, apptTime);

        Appointment cnclAppt = new Appointment(patient, cnclTimeslot, "Morris");

        if (sessionSchedule.cancelAllAppts(cnclAppt) == true) {
            System.out.println("All appointments for " + fname + " " + lname + ", DOB: " + dob + " have been cancelled.");
        } else {
        }
    }

    private void bookAppointment(Schedule sessionSchedule, String dob, String fname, String lname, String apptDate, String apptTime, String location) {
        Date patientDOB = new Date(dob);

        if (validateDOB(patientDOB)) {
            Patient patient = new Patient(fname, lname, patientDOB);

            Date schdApptDate = new Date(apptDate);

            if (validateApptDate(schdApptDate)) {
                Time schdApptTime = new Time(apptTime);

                if (!(schdApptTime.isValid())) {
                    System.out.println("Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval.");
                } else {
                    boolean locationFound = validateLocation(location);

                    if (locationFound) {
                        Timeslot bkTimeslot = new Timeslot(schdApptDate, schdApptTime);
                        Appointment bkAppt = new Appointment(patient, bkTimeslot, location);

                        boolean apptAccepted = validateAppt(sessionSchedule, bkAppt);

                        if (apptAccepted) {
                            sessionSchedule.add(bkAppt);
                            System.out.println("Appointment booked and added to the schedule.");
                        }
                    }
                }
            }
        }
    }

    private boolean validateDOB(Date patientDOB) {
        boolean result = true;

        Date today = new Date();

        if (!(patientDOB.isValid())) {
            System.out.println("Invalid date of birth!");
            result = false;
        } else if (!(patientDOB.isValidDOB(today))) {
            System.out.println("Date of birth invalid -> it is a future date.");
            result = false;
        }
        return result;
    }

    private boolean validateApptDate(Date schdApptDate) {
        boolean result = true;

        Date today = new Date();

        if (!(schdApptDate.isValid()) || !schdApptDate.isCurrentYear()) {
            System.out.println("Invalid appointment date!");
            result = false;
        } else if (!(schdApptDate.compareTo(today) > 0)) {
            System.out.println("Appointment date invalid -> must be a future date.");
            result = false;
        }

        return result;
    }

    private boolean validateLocation(String location) {
        boolean locationFound = false;
        try {
            Location lctn = Location.valueOf(location.toUpperCase());
            locationFound = true;
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid location!");
        }

        return locationFound;
    }

    private boolean validateAppt(Schedule sessionSchedule, Appointment bkAppt) {
        boolean apptAccepted = true;

        if (sessionSchedule.appointmentExists(bkAppt)) {
            System.out.println("Same appointment exists in the schedule.");
            apptAccepted = false;
        } else {
            if (!sessionSchedule.timeslotAvailable(bkAppt)) {
                System.out.println("Time slot has been taken at this location.");
                apptAccepted = false;
            } else {
                if (!sessionSchedule.canPatientBook(bkAppt)) {
                    System.out.println("Same patient cannot book an appointment with the same date.");
                    apptAccepted = false;
                }
            }
        }
        return apptAccepted;
    }

}