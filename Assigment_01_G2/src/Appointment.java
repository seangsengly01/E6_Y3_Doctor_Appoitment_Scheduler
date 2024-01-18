public class Appointment {
    User user;
    Doctor doctor;
    String date;
    String time;

    public Appointment(User user, Doctor doctor, String date, String time) {
        this.user = user;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }
}
