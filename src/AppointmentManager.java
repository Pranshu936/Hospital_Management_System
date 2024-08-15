import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AppointmentManager {
    private AppointmentDAO appointmentDAO;
    private Scanner scanner;
    private static int appointmentIdCounter = 1001; // Initialize appointment ID counter

    public AppointmentManager(AppointmentDAO appointmentDAO, Scanner scanner) {
        this.appointmentDAO = appointmentDAO;
        this.scanner = scanner;
        // Load or initialize appointmentIdCounter from a persistent store if needed
    }

    public void manageAppointments() {
        System.out.println("\nManage Appointments");
        System.out.println("1. Add Appointment");
        System.out.println("2. View Appointment");
        System.out.println("3. Update Appointment");
        System.out.println("4. Delete Appointment");
        System.out.println("5. View All Appointments");
        System.out.print("Choose an option: ");
        
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 5) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid choice. Please enter a number between 1 and 5: ");
            }
        }

        switch (choice) {
            case 1:
                Appointment newAppointment = new Appointment();
                newAppointment.setAppointmentId(appointmentIdCounter++);

                // Other appointment details
                System.out.print("Enter patient ID: ");
                newAppointment.setPatientId(Integer.parseInt(scanner.nextLine()));

                System.out.print("Enter doctor ID: ");
                newAppointment.setDoctorId(Integer.parseInt(scanner.nextLine()));

                System.out.print("Enter appointment date (yyyy-mm-dd): ");
                newAppointment.setAppointmentDate(LocalDate.parse(scanner.nextLine()));

                appointmentDAO.addAppointment(newAppointment);
                System.out.println("Appointment added successfully.");
                break;
            
            case 2:
                System.out.print("Enter appointment ID: ");
                int appointmentId = Integer.parseInt(scanner.nextLine());
                Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
                if (appointment != null) {
                    System.out.println("Appointment Details: " + appointment);
                } else {
                    System.out.println("Appointment not found.");
                }
                break;

            case 3:
                System.out.print("Enter appointment ID to update: ");
                int updateAppointmentId = Integer.parseInt(scanner.nextLine());
                Appointment updateAppointment = appointmentDAO.getAppointmentById(updateAppointmentId);
                if (updateAppointment != null) {
                    // Update appointment details
                    System.out.print("Enter new patient ID: ");
                    updateAppointment.setPatientId(Integer.parseInt(scanner.nextLine()));

                    System.out.print("Enter new doctor ID: ");
                    updateAppointment.setDoctorId(Integer.parseInt(scanner.nextLine()));

                    System.out.print("Enter new appointment date (yyyy-mm-dd): ");
                    updateAppointment.setAppointmentDate(LocalDate.parse(scanner.nextLine()));

                    appointmentDAO.updateAppointment(updateAppointment);
                    System.out.println("Appointment updated successfully.");
                } else {
                    System.out.println("Appointment not found.");
                }
                break;

            case 4:
                System.out.print("Enter appointment ID to delete: ");
                int deleteAppointmentId = Integer.parseInt(scanner.nextLine());
                appointmentDAO.deleteAppointment(deleteAppointmentId);
                System.out.println("Appointment deleted successfully.");
                break;

            case 5:
                List<Appointment> appointments = appointmentDAO.getAllAppointments();
                if (appointments.isEmpty()) {
                    System.out.println("No appointments found.");
                } else {
                    for (Appointment a : appointments) {
                        System.out.println("Appointment ID: " + a.getAppointmentId() + ", Date: " + a.getAppointmentDate());
                    }
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}
