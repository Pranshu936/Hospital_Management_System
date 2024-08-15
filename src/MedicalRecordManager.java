import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MedicalRecordManager {
    private MedicalRecordDAO medicalRecordDAO;
    private Scanner scanner;
    private static int recordIdCounter = 5001; // Initialize medical record ID counter

    public MedicalRecordManager(MedicalRecordDAO medicalRecordDAO, Scanner scanner) {
        this.medicalRecordDAO = medicalRecordDAO;
        this.scanner = scanner;
        // Load or initialize recordIdCounter from a persistent store if needed
    }

    public void manageMedicalRecords() {
        System.out.println("\nManage Medical Records");
        System.out.println("1. Add Medical Record");
        System.out.println("2. View Medical Record");
        System.out.println("3. Update Medical Record");
        System.out.println("4. Delete Medical Record");
        System.out.println("5. View All Medical Records");
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
                MedicalRecord newRecord = new MedicalRecord();
                newRecord.setRecordId(recordIdCounter++);

                // Other medical record details
                System.out.print("Enter patient ID: ");
                newRecord.setPatientId(Integer.parseInt(scanner.nextLine()));

                System.out.print("Enter diagnosis: ");
                newRecord.setDiagnosis(scanner.nextLine().trim());

                System.out.print("Enter treatment: ");
                newRecord.setTreatment(scanner.nextLine().trim());

                System.out.print("Enter prescription: ");
                newRecord.setPrescription(scanner.nextLine().trim());

                LocalDate recordDate;
                while (true) {
                    try {
                        System.out.print("Enter record date (yyyy-mm-dd): ");
                        recordDate = LocalDate.parse(scanner.nextLine());
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.print("Invalid date format. Please use yyyy-mm-dd: ");
                    }
                }
                newRecord.setRecordDate(recordDate);

                medicalRecordDAO.addMedicalRecord(newRecord);
                System.out.println("Medical record added successfully.");
                break;

            case 2:
                System.out.print("Enter record ID: ");
                int recordId = Integer.parseInt(scanner.nextLine());
                MedicalRecord record = medicalRecordDAO.getMedicalRecordById(recordId);
                if (record != null) {
                    System.out.println("Diagnosis: " + record.getDiagnosis() +
                                       ", Treatment: " + record.getTreatment() +
                                       ", Prescription: " + record.getPrescription() +
                                       ", Date: " + record.getRecordDate());
                } else {
                    System.out.println("Medical record not found.");
                }
                break;

            case 3:
                System.out.print("Enter record ID to update: ");
                int updateRecordId = Integer.parseInt(scanner.nextLine());
                MedicalRecord updateRecord = medicalRecordDAO.getMedicalRecordById(updateRecordId);
                if (updateRecord != null) {
                    // Update medical record details
                    System.out.print("Enter new patient ID: ");
                    updateRecord.setPatientId(Integer.parseInt(scanner.nextLine()));

                    System.out.print("Enter new diagnosis: ");
                    updateRecord.setDiagnosis(scanner.nextLine().trim());

                    System.out.print("Enter new treatment: ");
                    updateRecord.setTreatment(scanner.nextLine().trim());

                    System.out.print("Enter new prescription: ");
                    updateRecord.setPrescription(scanner.nextLine().trim());

                    LocalDate newRecordDate;
                    while (true) {
                        try {
                            System.out.print("Enter new record date (yyyy-mm-dd): ");
                            newRecordDate = LocalDate.parse(scanner.nextLine());
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.print("Invalid date format. Please use yyyy-mm-dd: ");
                        }
                    }
                    updateRecord.setRecordDate(newRecordDate);

                    medicalRecordDAO.updateMedicalRecord(updateRecord);
                    System.out.println("Medical record updated successfully.");
                } else {
                    System.out.println("Medical record not found.");
                }
                break;

            case 4:
                System.out.print("Enter record ID to delete: ");
                int deleteRecordId = Integer.parseInt(scanner.nextLine());
                medicalRecordDAO.deleteMedicalRecord(deleteRecordId);
                System.out.println("Medical record deleted successfully.");
                break;

            case 5:
                List<MedicalRecord> records = medicalRecordDAO.getAllMedicalRecords();
                if (records.isEmpty()) {
                    System.out.println("No medical records found.");
                } else {
                    for (MedicalRecord r : records) {
                        System.out.println("Record ID: " + r.getRecordId() +
                                           ", Diagnosis: " + r.getDiagnosis());
                    }
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}
