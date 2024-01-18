import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppointmentSystem {
    static Map<String, User> users = new HashMap<>();
    static Map<String, Doctor> doctors = new HashMap<>();
    static Map<String, Appointment> appointments = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {


        // User Registration with Validation
        String username = getValidUsername();
        String phoneNumber = getValidPhoneNumber();
        String email = getValidEmail();

        User newUser = new User(username, phoneNumber, email);
        users.put(username, newUser);

        // Allow the user to make an appointment or cancel a scheduled appointment
        System.out.println("Options:");
        System.out.println("\n1. Make an appointment with a doctor");
        System.out.println("2. Cancel a scheduled appointment");
        System.out.println("3. Exit");

        System.out.print("Choose an option (1, 2 or 3): ");
        int option = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        if (option == 1) {

            // Doctor Details
            System.out.print("===================================\n");
            Doctor doctor3 = new Doctor("Dr. Patel", "Neurologist", true);
            Doctor doctor4 = new Doctor("Dr. Garcia", "Pediatrician", false);
            Doctor doctor5 = new Doctor("Dr. Kim", "Oncologist", true);
            Doctor doctor6 = new Doctor("Dr. Davis", "Ophthalmologist", false);
            Doctor doctor7 = new Doctor("Dr. Wilson", "Psychiatrist", true);
            Doctor doctor8 = new Doctor("Dr. Anderson", "Surgeon", false);
            Doctor doctor9 = new Doctor("Dr. Thomas", "Radiologist", true);
            Doctor doctor10 = new Doctor("Dr. Moore", "Gastroenterologist", false);
            Doctor doctor11 = new Doctor("Dr. Taylor", "Endocrinologist", true);
            Doctor doctor12 = new Doctor("Dr. Clark", "Anesthesiologist", false);

            doctors.put(doctor3.name, doctor3);
            doctors.put(doctor4.name, doctor4);
            doctors.put(doctor5.name, doctor5);
            doctors.put(doctor6.name, doctor6);
            doctors.put(doctor7.name, doctor7);
            doctors.put(doctor8.name, doctor8);
            doctors.put(doctor9.name, doctor9);
            doctors.put(doctor10.name, doctor10);
            doctors.put(doctor11.name, doctor11);
            doctors.put(doctor12.name, doctor12);

            // Appointment Scheduling
            System.out.println("Appointment Scheduling:");
            System.out.println("Available Doctors:");
            System.out.printf("%-3s %-20s %-20s %-10s%n", "No.", "Name", "Specialization", "Status");
            System.out.println("-------------------------------------------------------------------");
            int index = 1;
            for (Doctor doctor : doctors.values()) {
                System.out.printf("%-3d %-20s %-20s %-10s%n", index, doctor.name, doctor.specialization, doctor.available ? "Available" : "Unavailable");
                index++;
            }
            System.out.println("-------------------------------------------------------------------");
            System.out.print("Select a doctor (enter the corresponding number): ");
            int selectedDoctorNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Doctor chosenDoctor = null;
            int count = 0;
            for (Doctor doctor : doctors.values()) {
                count++;
                if (selectedDoctorNumber == count) {
                    chosenDoctor = doctor;
                    break;
                }
            }

            if (chosenDoctor != null && chosenDoctor.available) {
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Appointment Scheduling:");

                // Get user input for date and time with validation
                LocalDate date = getValidDateInput();
                LocalTime time = getValidTimeInput();

                // Display appointment details for confirmation
                System.out.println("\n===================================");
                System.out.println("Appointment Details:");
                System.out.println("User: " + newUser.username);
                System.out.println("Doctor: " + chosenDoctor.name);
                System.out.println("Date: " + date);
                System.out.println("Time: " + time);
                System.out.println("===================================");

                // Ask for confirmation with a more user-friendly prompt
                System.out.print("Do you want to confirm this appointment? (yes/no): ");
                String confirmation = scanner.nextLine().toLowerCase();

                if ("yes".equals(confirmation)) {
                    // Add the appointment to the appointments map
                    appointments.put(username, new Appointment(newUser, chosenDoctor, date.toString(), time.toString()));
                    System.out.println("\nAppointment Confirmed!");
                } else {
                    System.out.println("\nAppointment Cancelled.");
                }
            } else {
                System.out.println("Invalid doctor selection or doctor not available.");
            }

        } else if (option == 2) {
            // Cancel a scheduled appointment
            System.out.print("Enter doctor's name: ");
            String doctorName = scanner.nextLine();

            if (doctorName.equals("Sen Pisey")) {//
                //   System.out.print("Enter doctor's name: ");
                System.out.print("Enter appointment date (MM/DD/YYYY): ");
                String date = scanner.nextLine();
                System.out.print("Enter appointment time: ");
                String time = scanner.nextLine();
                System.out.print("===================================\n");
                System.out.println("Appointment Canceled:");
                System.out.println("User: " + newUser.username);
                System.out.println("Doctor: " + "Sen Pisey");
                System.out.println("Date: " + date);
                System.out.println("Time: " + time);
            } else {
                System.out.println("No scheduled appointments found for this user.");
            }
        } else if (option == 3) {
            System.out.println("Exiting the appointment system.");
        } else {
            System.out.println("Invalid option selected.");
        }

    }
    // Validation Methods
    // Additional Methods for Date and Time Validation
    private static LocalDate getValidDateInput() {
        LocalDate date = null;
        do {
            System.out.print("Enter appointment date (YYYY-MM-DD): ");
            try {
                String dateString = scanner.nextLine().trim();
                date = LocalDate.parse(dateString);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        } while (date == null || date.isBefore(LocalDate.now())); // Ensure the date is not in the past
        return date;
    }

    private static LocalTime getValidTimeInput() {
        LocalTime time = null;
        do {
            System.out.print("Enter appointment time (HH:mm): ");
            try {
                String timeString = scanner.nextLine().trim();
                time = LocalTime.parse(timeString);
            } catch (Exception e) {
                System.out.println("Invalid time format. Please use HH:mm. \n");
            }
        } while (time == null);
        return time;
    }
    private static String getValidUsername() {
        String username;
        do {
            System.out.print("សូមបញ្ចូលឈ្មោះអ្នកប្រើប្រាស់ជាភាសាអង់គ្លេស (យ៉ាងតិច 6តួអក្សរ និងច្រើនបំផុត 15តួអក្សរ, ត្រូវតែតួអក្សរធំ)\n Enter username (6-15 characters, uppercase letters): ");
            username = scanner.nextLine().trim();
        } while (!isValidUsername(username));
        return username;
    }

    private static boolean isValidUsername(String username) {
        String regex = "^[A-Z\\s]{6,15}$";
        return username.matches(regex);
    }

    private static String getValidPhoneNumber() {
        String phoneNumber;
        do {
            System.out.print("សូមបញ្ចូលលេខទូរស័ព្ទរបស់លោកអ្នក(យ៉ាងតិច 6ខ្ទង់ និងច្រើនបំផុត 15ខ្ទង់, ត្រូវតែជាលេខ) \n Enter phone number (6-12 digits, numbers only): ");
            phoneNumber = scanner.nextLine().trim();
        } while (!isValidPhoneNumber(phoneNumber));
        return phoneNumber;
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^[0-9\\s]{6,12}$";
        return phoneNumber.matches(regex);
    }

    private static String getValidEmail() {
        String email;
        do {
            System.out.print("សូមបញ្ចូលអ៊ីមែលរបស់លោកអ្នក (ឧ. username@gmail.com)\n Enter email (e.g., username@gmail.com): ");
            email = scanner.nextLine().trim();
        } while (!isValidEmail(email));
        return email;
    }

    private static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@gmail.com$";
        return email.matches(regex);
    }
}