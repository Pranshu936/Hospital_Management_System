import java.util.List;
import java.util.Scanner;

public class DoctorManager {
    private static Scanner scanner = new Scanner(System.in);
    private DoctorDAO doctorDAO;
    private int doctorIdCounter = 101; // Initialize doctor ID counter

    public DoctorManager(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    public void manageDoctors() {
        System.out.println("\nManage Doctors");
        System.out.println("1. Add Doctor");
        System.out.println("2. View Doctor");
        System.out.println("3. Update Doctor");
        System.out.println("4. Delete Doctor");
        System.out.println("5. View All Doctors");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                addDoctor();
                break;

            case 2:
                viewDoctor();
                break;

            case 3:
                updateDoctor();
                break;

            case 4:
                deleteDoctor();
                break;

            case 5:
                viewAllDoctors();
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private void addDoctor() {
        Doctor newDoctor = new Doctor();
        newDoctor.setDoctorId(doctorIdCounter++); // Automatically set doctor ID starting from 101

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        while (!name.matches("[a-zA-Z ]+")) {
            System.out.print("Invalid input. Enter a valid name (only alphabets): ");
            name = scanner.nextLine();
        }
        newDoctor.setName(name);

        System.out.print("Enter specialization: ");
        newDoctor.setSpecialization(scanner.nextLine());

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        while (!phoneNumber.matches("\\d{10}")) {
            System.out.print("Invalid input. Enter a valid 10-digit phone number: ");
            phoneNumber = scanner.nextLine();
        }
        newDoctor.setPhoneNumber(phoneNumber);

        System.out.print("Enter email: ");
        newDoctor.setEmail(scanner.nextLine());

        doctorDAO.addDoctor(newDoctor);
        System.out.println("Doctor added successfully.");
    }

    private void viewDoctor() {
        System.out.println("1. Search by Doctor ID");
        System.out.println("2. Search by Doctor Name");
        System.out.print("Choose an option: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (searchChoice) {
            case 1:
                System.out.print("Enter doctor ID: ");
                int doctorId = scanner.nextInt();
                Doctor doctor = doctorDAO.getDoctorById(doctorId);
                if (doctor != null) {
                    displayDoctorDetails(doctor);
                } else {
                    System.out.println("Doctor not found.");
                }
                break;

            case 2:
                System.out.print("Enter doctor name: ");
                String searchName = scanner.nextLine();
                List<Doctor> doctorsByName = doctorDAO.getDoctorsByName(searchName);
                if (!doctorsByName.isEmpty()) {
                    for (Doctor d : doctorsByName) {
                        displayDoctorDetails(d);
                    }
                } else {
                    System.out.println("No doctors found with the name " + searchName);
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private void updateDoctor() {
        System.out.print("Enter doctor ID to update: ");
        int updateDoctorId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Doctor updateDoctor = doctorDAO.getDoctorById(updateDoctorId);
        if (updateDoctor != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            while (!name.matches("[a-zA-Z ]+")) {
                System.out.print("Invalid input. Enter a valid name (only alphabets): ");
                name = scanner.nextLine();
            }
            updateDoctor.setName(name);

            System.out.print("Enter new specialization: ");
            updateDoctor.setSpecialization(scanner.nextLine());

            System.out.print("Enter new phone number: ");
            String phoneNumber = scanner.nextLine();
            while (!phoneNumber.matches("\\d{10}")) {
                System.out.print("Invalid input. Enter a valid 10-digit phone number: ");
                phoneNumber = scanner.nextLine();
            }
            updateDoctor.setPhoneNumber(phoneNumber);

            System.out.print("Enter new email: ");
            updateDoctor.setEmail(scanner.nextLine());

            doctorDAO.updateDoctor(updateDoctor);
            System.out.println("Doctor updated successfully.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private void deleteDoctor() {
        System.out.print("Enter doctor ID to delete: ");
        int deleteDoctorId = scanner.nextInt();
        doctorDAO.deleteDoctor(deleteDoctorId);
        System.out.println("Doctor deleted successfully.");
    }

    private void viewAllDoctors() {
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        for (Doctor d : doctors) {
            System.out.println("Doctor ID: " + d.getDoctorId() + ", Name: " + d.getName());
        }
    }

    private void displayDoctorDetails(Doctor doctor) {
        // Implement this method to display detailed information about a doctor
        System.out.println("Doctor ID: " + doctor.getDoctorId());
        System.out.println("Name: " + doctor.getName());
        System.out.println("Specialization: " + doctor.getSpecialization());
        System.out.println("Phone Number: " + doctor.getPhoneNumber());
        System.out.println("Email: " + doctor.getEmail());
        System.out.println("---------------------------------");
    }
}
