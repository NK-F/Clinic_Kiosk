public class RunProject1 {
    public static void main(String[] args) {
        new Kiosk().run();
    }

    /*
    public static void main(String[] args){
        Date date1 = new Date("10/16/2000");
        Patient patient1 = new Patient("Jane", "Doe", date1);
        Date date2 = new Date("7/8/2001");
        Patient patient2 = new Patient("John", "Dane", date2);
        Date date3 = new Date("4/16/1998");
        Patient patient3 = new Patient("Joe", "Mama", date3);

        Date date4 = new Date("12/1/2022");
        Time time1 = new Time(9, 0);
        Timeslot timeslot1 = new Timeslot(date4, time1);
        Date date5 = new Date("11/1/2022");
        Time time2 = new Time(9, 0);
        Timeslot timeslot2 = new Timeslot(date5, time2);
        Date date6 = new Date("10/1/2022");
        Time time3 = new Time(9, 0);
        Timeslot timeslot3 = new Timeslot(date6, time3);
        Appointment appointment1 = new Appointment(patient1, timeslot1, "SOMERSET");
        Appointment appointment2 = new Appointment(patient2, timeslot2, "MIDDLESEX");
        Appointment appointment3 = new Appointment(patient3, timeslot3, "MERCER");

        Schedule schedule = new Schedule();
        schedule.add(appointment1);
        schedule.add(appointment2);
        schedule.add(appointment3);

        System.out.println(schedule.remove(appointment2));
        schedule.print();

    }*/
}
