import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PatientManager {
    private static Scanner scanner = new Scanner(System.in);
    private static int patientIdCounter = 101; // Starting ID counter for patients

    private PatientDAO patientDAO;

    public PatientManager(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public void managePatients() {
        System.out.println("\nManage Patients");
        System.out.println("1. Add Patient");
        System.out.println("2. View Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");
        System.out.println("5. View All Patients");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                addPatient();
                break;

            case 2:
                viewPatient();
                break;

            case 3:
                updatePatient();
                break;

            case 4:
                deletePatient();
                break;

            case 5:
                viewAllPatients();
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private void addPatient() {
        Patient newPatient = new Patient();
        newPatient.setPatientId(patientIdCounter++); // Automatically set patient ID starting from 101

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        while (!name.matches("[a-zA-Z ]+")) {
            System.out.print("Invalid input. Enter a valid name (only alphabets): ");
            name = scanner.nextLine();
        }
        newPatient.setName(name);

        System.out.print("Enter age: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a valid age (integer): ");
            scanner.next(); // discard invalid input
        }
        newPatient.setAge(scanner.nextInt());
        scanner.nextLine(); // consume newline

        System.out.print("Enter gender (M/F): ");
        String gender = scanner.nextLine();
        while (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
            System.out.print("Invalid input. Enter gender (M/F): ");
            gender = scanner.nextLine();
        }
        newPatient.setGender(gender);

        System.out.print("Enter address: ");
        newPatient.setAddress(scanner.nextLine());

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        while (!phoneNumber.matches("\\d{10}")) {
            System.out.print("Invalid input. Enter a valid 10-digit phone number: ");
            phoneNumber = scanner.nextLine();
        }
        newPatient.setPhoneNumber(phoneNumber);

        System.out.print("Enter admission date (yyyy-mm-dd): ");
        LocalDate admissionDate = null;
        while (admissionDate == null) {
            try {
                admissionDate = LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Invalid date format. Enter admission date (yyyy-mm-dd): ");
            }
        }
        newPatient.setAdmissionDate(admissionDate);

        System.out.print("Enter doctor ID: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a valid doctor ID (integer): ");
            scanner.next(); // discard invalid input
        }
        newPatient.setDoctorId(scanner.nextInt());
        patientDAO.addPatient(newPatient);
        System.out.println("Patient added successfully.");
    }

    private void viewPatient() {
        System.out.println("1. Search by Patient ID");
        System.out.println("2. Search by Patient Name");
        System.out.print("Choose an option: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (searchChoice) {
            case 1:
                System.out.print("Enter patient ID: ");
                int patientId = scanner.nextInt();
                Patient patient = patientDAO.getPatientById(patientId);
                if (patient != null) {
                    displayPatientDetails(patient);
                } else {
                    System.out.println("Patient not found.");
                }
                break;

            case 2:
                System.out.print("Enter patient name: ");
                String searchName = scanner.nextLine();
                List<Patient> patientsByName = patientDAO.getPatientsByName(searchName);
                if (!patientsByName.isEmpty()) {
                    for (Patient p : patientsByName) {
                        displayPatientDetails(p);
                    }
                } else {
                    System.out.println("No patients found with the name " + searchName);
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private void updatePatient() {
        System.out.print("Enter patient ID to update: ");
        int updatePatientId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Patient updatePatient = patientDAO.getPatientById(updatePatientId);
        if (updatePatient != null) {
            // Update patient details (similar to addPatient method)
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            while (!name.matches("[a-zA-Z ]+")) {
                System.out.print("Invalid input. Enter a valid name (only alphabets): ");
                name = scanner.nextLine();
            }
            updatePatient.setName(name);

            System.out.print("Enter new age: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a valid age (integer): ");
                scanner.next(); // discard invalid input
            }
            updatePatient.setAge(scanner.nextInt());
            scanner.nextLine(); // consume newline

            System.out.print("Enter new gender (M/F): ");
            String gender = scanner.nextLine();
            while (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
                System.out.print("Invalid input. Enter gender (M/F): ");
                gender = scanner.nextLine();
            }
            updatePatient.setGender(gender);

            System.out.print("Enter new address: ");
            updatePatient.setAddress(scanner.nextLine());

            System.out.print("Enter new phone number: ");
            String phoneNumber = scanner.nextLine();
            while (!phoneNumber.matches("\\d{10}")) {
                System.out.print("Invalid input. Enter a valid 10-digit phone number: ");
                phoneNumber = scanner.nextLine();
            }
            updatePatient.setPhoneNumber(phoneNumber);

            System.out.print("Enter new admission date (yyyy-mm-dd): ");
            LocalDate admissionDate = null;
            while (admissionDate == null) {
                try {
                    admissionDate = LocalDate.parse(scanner.nextLine());
                } catch (Exception e) {
                    System.out.print("Invalid date format. Enter admission date (yyyy-mm-dd): ");
                }
            }
            updatePatient.setAdmissionDate(admissionDate);

            System.out.print("Enter new doctor ID: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a valid doctor ID (integer): ");
                scanner.next(); // discard invalid input
            }
            updatePatient.setDoctorId(scanner.nextInt());

            patientDAO.updatePatient(updatePatient);
            System.out.println("Patient updated successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private void deletePatient() {
        System.out.print("Enter patient ID to delete: ");
        int deletePatientId = scanner.nextInt();
        patientDAO.deletePatient(deletePatientId);
        System.out.println("Patient deleted successfully.");
    }

    private void viewAllPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        for (Patient p : patients) {
            System.out.println("Patient ID: " + p.getPatientId() + ", Name: " + p.getName());
        }
    }

    private void displayPatientDetails(Patient patient) {
        System.out.println("Patient ID: " + patient.getPatientId());
        System.out.println("Name: " + patient.getName());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Address: " + patient.getAddress());
        System.out.println("Phone Number: " + patient.getPhoneNumber());
        System.out.println("Admission Date: " + patient.getAdmissionDate());
        System.out.println("Doctor ID: " + patient.getDoctorId());
        System.out.println("---------------------------------");
    }
}

