import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
        BillingDAO billingDAO = new BillingDAO();

        PatientManager patientManager = new PatientManager(patientDAO);
        DoctorManager doctorManager = new DoctorManager(doctorDAO);
        AppointmentManager appointmentManager = new AppointmentManager(appointmentDAO, scanner);
        MedicalRecordManager medicalRecordManager = new MedicalRecordManager(medicalRecordDAO, scanner);
        BillingManager billingManager = new BillingManager(billingDAO, scanner);

        while (true) {
            System.out.println("\nHospital Management System");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Manage Medical Records");
            System.out.println("5. Manage Billing");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidInteger("Choose an option: ");
            
            switch (choice) {
                case 1:
                    patientManager.managePatients();
                    break;
                case 2:
                    doctorManager.manageDoctors();
                    break;
                case 3:
                    appointmentManager.manageAppointments();
                    break;
                case 4:
                    medicalRecordManager.manageMedicalRecords();
                    break;
                case 5:
                    billingManager.manageBilling();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getValidInteger(String prompt) {
        int value;
        while (true) {
            try {
                System.out.print(prompt);
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.print("Invalid input. Please enter a positive integer: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a positive integer: ");
            }
        }
    }
}
