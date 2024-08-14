import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class HospitalManagementSystem {

    private static Scanner scanner = new Scanner(System.in);
    private static int patientIdCounter = 101; // Patient ID starts at 101
    private static int doctorIdCounter = 101; // Doctor ID starts at 101

    public static void main(String[] args) {
        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
        BillingDAO billingDAO = new BillingDAO();

        while (true) {
            System.out.println("\nHospital Management System");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Manage Medical Records");
            System.out.println("5. Manage Billing");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    managePatients(patientDAO);
                    break;
                case 2:
                    manageDoctors(doctorDAO);
                    break;
                case 3:
                    manageAppointments(appointmentDAO);
                    break;
                case 4:
                    manageMedicalRecords(medicalRecordDAO);
                    break;
                case 5:
                    manageBilling(billingDAO);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void managePatients(PatientDAO patientDAO) {
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
                break;

            case 2:
                System.out.print("Enter patient ID: ");
                int patientId = scanner.nextInt();
                Patient patient = patientDAO.getPatientById(patientId);
                if (patient != null) {
                    System.out.println("Patient Name: " + patient.getName());
                } else {
                    System.out.println("Patient not found.");
                }
                break;

            case 3:
                System.out.print("Enter patient ID to update: ");
                int updatePatientId = scanner.nextInt();
                scanner.nextLine(); // consume newline
                Patient updatePatient = patientDAO.getPatientById(updatePatientId);
                if (updatePatient != null) {
                    System.out.print("Enter new name: ");
                    name = scanner.nextLine();
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
                    gender = scanner.nextLine();
                    while (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
                        System.out.print("Invalid input. Enter gender (M/F): ");
                        gender = scanner.nextLine();
                    }
                    updatePatient.setGender(gender);

                    System.out.print("Enter new address: ");
                    updatePatient.setAddress(scanner.nextLine());

                    System.out.print("Enter new phone number: ");
                    phoneNumber = scanner.nextLine();
                    while (!phoneNumber.matches("\\d{10}")) {
                        System.out.print("Invalid input. Enter a valid 10-digit phone number: ");
                        phoneNumber = scanner.nextLine();
                    }
                    updatePatient.setPhoneNumber(phoneNumber);

                    System.out.print("Enter new admission date (yyyy-mm-dd): ");
                    admissionDate = null;
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
                break;

            case 4:
                System.out.print("Enter patient ID to delete: ");
                int deletePatientId = scanner.nextInt();
                patientDAO.deletePatient(deletePatientId);
                System.out.println("Patient deleted successfully.");
                break;

            case 5:
                List<Patient> patients = patientDAO.getAllPatients();
                for (Patient p : patients) {
                    System.out.println("Patient ID: " + p.getPatientId() + ", Name: " + p.getName());
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageDoctors(DoctorDAO doctorDAO) {
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
                break;

            case 2:
                System.out.print("Enter doctor ID: ");
                int doctorId = scanner.nextInt();
                Doctor doctor = doctorDAO.getDoctorById(doctorId);
                if (doctor != null) {
                    System.out.println("Doctor Name: " + doctor.getName());
                } else {
                    System.out.println("Doctor not found.");
                }
                break;

            case 3:
                System.out.print("Enter doctor ID to update: ");
                int updateDoctorId = scanner.nextInt();
                scanner.nextLine(); // consume newline
                Doctor updateDoctor = doctorDAO.getDoctorById(updateDoctorId);
                if (updateDoctor != null) {
                    System.out.print("Enter new name: ");
                    name = scanner.nextLine();
                    while (!name.matches("[a-zA-Z ]+")) {
                        System.out.print("Invalid input. Enter a valid name (only alphabets): ");
                        name = scanner.nextLine();
                    }
                    updateDoctor.setName(name);

                    System.out.print("Enter new specialization: ");
                    updateDoctor.setSpecialization(scanner.nextLine());

                    System.out.print("Enter new phone number: ");
                    phoneNumber = scanner.nextLine();
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
                break;

            case 4:
                System.out.print("Enter doctor ID to delete: ");
                int deleteDoctorId = scanner.nextInt();
                doctorDAO.deleteDoctor(deleteDoctorId);
                System.out.println("Doctor deleted successfully.");
                break;

            case 5:
                List<Doctor> doctors = doctorDAO.getAllDoctors();
                for (Doctor d : doctors) {
                    System.out.println("Doctor ID: " + d.getDoctorId() + ", Name: " + d.getName());
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageAppointments(AppointmentDAO appointmentDAO) {
        System.out.println("\nManage Appointments");
        System.out.println("1. Add Appointment");
        System.out.println("2. View Appointment");
        System.out.println("3. Update Appointment");
        System.out.println("4. Delete Appointment");
        System.out.println("5. View All Appointments");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                Appointment newAppointment = new Appointment();
                System.out.print("Enter patient ID: ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid input. Enter a valid patient ID (integer): ");
                    scanner.next(); // discard invalid input
                }
                newAppointment.setPatientId(scanner.nextInt());
                System.out.print("Enter doctor ID: ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid input. Enter a valid doctor ID (integer): ");
                    scanner.next(); // discard invalid input
                }
                newAppointment.setDoctorId(scanner.nextInt());
                scanner.nextLine(); // consume newline
                System.out.print("Enter appointment date (yyyy-mm-dd): ");
                LocalDate appointmentDate = null;
                while (appointmentDate == null) {
                    try {
                        appointmentDate = LocalDate.parse(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.print("Invalid date format. Enter appointment date (yyyy-mm-dd): ");
                    }
                }
                newAppointment.setAppointmentDate(appointmentDate);

                System.out.print("Enter status: ");
                newAppointment.setStatus(scanner.nextLine());
                appointmentDAO.addAppointment(newAppointment);
                System.out.println("Appointment added successfully.");
                break;

            case 2:
                System.out.print("Enter appointment ID: ");
                int appointmentId = scanner.nextInt();
                Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
                if (appointment != null) {
                    System.out.println("Appointment Status: " + appointment.getStatus());
                } else {
                    System.out.println("Appointment not found.");
                }
                break;

            case 3:
                System.out.print("Enter appointment ID to update: ");
                int updateAppointmentId = scanner.nextInt();
                scanner.nextLine(); // consume newline
                Appointment updateAppointment = appointmentDAO.getAppointmentById(updateAppointmentId);
                if (updateAppointment != null) {
                    System.out.print("Enter new patient ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Invalid input. Enter a valid patient ID (integer): ");
                        scanner.next(); // discard invalid input
                    }
                    updateAppointment.setPatientId(scanner.nextInt());
                    System.out.print("Enter new doctor ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Invalid input. Enter a valid doctor ID (integer): ");
                        scanner.next(); // discard invalid input
                    }
                    updateAppointment.setDoctorId(scanner.nextInt());
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter new appointment date (yyyy-mm-dd): ");
                    appointmentDate = null;
                    while (appointmentDate == null) {
                        try {
                            appointmentDate = LocalDate.parse(scanner.nextLine());
                        } catch (Exception e) {
                            System.out.print("Invalid date format. Enter appointment date (yyyy-mm-dd): ");
                        }
                    }
                    updateAppointment.setAppointmentDate(appointmentDate);

                    System.out.print("Enter new status: ");
                    updateAppointment.setStatus(scanner.nextLine());
                    appointmentDAO.updateAppointment(updateAppointment);
                    System.out.println("Appointment updated successfully.");
                } else {
                    System.out.println("Appointment not found.");
                }
                break;

            case 4:
                System.out.print("Enter appointment ID to delete: ");
                int deleteAppointmentId = scanner.nextInt();
                appointmentDAO.deleteAppointment(deleteAppointmentId);
                System.out.println("Appointment deleted successfully.");
                break;

            case 5:
                List<Appointment> appointments = appointmentDAO.getAllAppointments();
                for (Appointment a : appointments) {
                    System.out.println("Appointment ID: " + a.getAppointmentId() + ", Status: " + a.getStatus());
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageMedicalRecords(MedicalRecordDAO medicalRecordDAO) {
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
            int patientId;
            while (true) {
                try {
                    System.out.print("Enter patient ID: ");
                    patientId = Integer.parseInt(scanner.nextLine());
                    if (patientId <= 0) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid patient ID. Please enter a positive integer: ");
                }
            }
            newRecord.setPatientId(patientId);
            
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
            int recordId;
            while (true) {
                try {
                    System.out.print("Enter record ID: ");
                    recordId = Integer.parseInt(scanner.nextLine());
                    if (recordId <= 0) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid record ID. Please enter a positive integer: ");
                }
            }
            
            MedicalRecord record = medicalRecordDAO.getMedicalRecordById(recordId);
            if (record != null) {
                System.out.println("Diagnosis: " + record.getDiagnosis());
            } else {
                System.out.println("Medical record not found.");
            }
            break;
            
        case 3:
            int updateRecordId;
            while (true) {
                try {
                    System.out.print("Enter record ID to update: ");
                    updateRecordId = Integer.parseInt(scanner.nextLine());
                    if (updateRecordId <= 0) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid record ID. Please enter a positive integer: ");
                }
            }
            
            MedicalRecord updateRecord = medicalRecordDAO.getMedicalRecordById(updateRecordId);
            if (updateRecord != null) {
                while (true) {
                    try {
                        System.out.print("Enter new patient ID: ");
                        int newPatientId = Integer.parseInt(scanner.nextLine());
                        if (newPatientId <= 0) {
                            throw new NumberFormatException();
                        }
                        updateRecord.setPatientId(newPatientId);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid patient ID. Please enter a positive integer: ");
                    }
                }
                
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
            int deleteRecordId;
            while (true) {
                try {
                    System.out.print("Enter record ID to delete: ");
                    deleteRecordId = Integer.parseInt(scanner.nextLine());
                    if (deleteRecordId <= 0) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid record ID. Please enter a positive integer: ");
                }
            }
            
            medicalRecordDAO.deleteMedicalRecord(deleteRecordId);
            System.out.println("Medical record deleted successfully.");
            break;
            
        case 5:
            List<MedicalRecord> records = medicalRecordDAO.getAllMedicalRecords();
            if (records.isEmpty()) {
                System.out.println("No medical records found.");
            } else {
                for (MedicalRecord r : records) {
                    System.out.println("Record ID: " + r.getRecordId() + ", Diagnosis: " + r.getDiagnosis());
                }
            }
            break;
            
        default:
            System.out.println("Invalid choice.");
    }
}


private static void manageBilling(BillingDAO billingDAO) {
    System.out.println("\nManage Billing");
    System.out.println("1. Add Billing");
    System.out.println("2. View Billing");
    System.out.println("3. Update Billing");
    System.out.println("4. Delete Billing");
    System.out.println("5. View All Billings");
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
            Billing newBilling = new Billing();
            
            int patientId;
            while (true) {
                try {
                    System.out.print("Enter patient ID: ");
                    patientId = Integer.parseInt(scanner.nextLine());
                    if (patientId <= 0) {
                        throw new NumberFormatException();
                    }
                    newBilling.setPatientId(patientId);
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid patient ID. Please enter a positive integer: ");
                }
            }
            
            double totalAmount;
            while (true) {
                try {
                    System.out.print("Enter total amount: ");
                    totalAmount = Double.parseDouble(scanner.nextLine());
                    if (totalAmount < 0) {
                        throw new NumberFormatException();
                    }
                    newBilling.setTotalAmount(totalAmount);
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid total amount. Please enter a non-negative number: ");
                }
            }
            
            double paidAmount;
            while (true) {
                try {
                    System.out.print("Enter paid amount: ");
                    paidAmount = Double.parseDouble(scanner.nextLine());
                    if (paidAmount < 0) {
                        throw new NumberFormatException();
                    }
                    newBilling.setPaidAmount(paidAmount);
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid paid amount. Please enter a non-negative number: ");
                }
            }
            
            LocalDate paymentDate;
            while (true) {
                try {
                    System.out.print("Enter payment date (yyyy-mm-dd): ");
                    paymentDate = LocalDate.parse(scanner.nextLine());
                    newBilling.setPaymentDate(paymentDate);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.print("Invalid date format. Please use yyyy-mm-dd: ");
                }
            }
            
            billingDAO.addBilling(newBilling);
            System.out.println("Billing added successfully.");
            break;
            
        case 2:
            int billId;
            while (true) {
                try {
                    System.out.print("Enter bill ID: ");
                    billId = Integer.parseInt(scanner.nextLine());
                    if (billId <= 0) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid bill ID. Please enter a positive integer: ");
                }
            }
            
            Billing billing = billingDAO.getBillingById(billId);
            if (billing != null) {
                System.out.println("Billing Total Amount: " + billing.getTotalAmount());
            } else {
                System.out.println("Billing record not found.");
            }
            break;
            
        case 3:
            int updateBillId;
            while (true) {
                try {
                    System.out.print("Enter bill ID to update: ");
                    updateBillId = Integer.parseInt(scanner.nextLine());
                    if (updateBillId <= 0) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid bill ID. Please enter a positive integer: ");
                }
            }
            
            Billing updateBilling = billingDAO.getBillingById(updateBillId);
            if (updateBilling != null) {
                double newTotalAmount;
                while (true) {
                    try {
                        System.out.print("Enter new total amount: ");
                        newTotalAmount = Double.parseDouble(scanner.nextLine());
                        if (newTotalAmount < 0) {
                            throw new NumberFormatException();
                        }
                        updateBilling.setTotalAmount(newTotalAmount);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid total amount. Please enter a non-negative number: ");
                    }
                }
                
                double newPaidAmount;
                while (true) {
                    try {
                        System.out.print("Enter new paid amount: ");
                        newPaidAmount = Double.parseDouble(scanner.nextLine());
                        if (newPaidAmount < 0) {
                            throw new NumberFormatException();
                        }
                        updateBilling.setPaidAmount(newPaidAmount);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid paid amount. Please enter a non-negative number: ");
                    }
                }
                
                LocalDate newPaymentDate;
                while (true) {
                    try {
                        System.out.print("Enter new payment date (yyyy-mm-dd): ");
                        newPaymentDate = LocalDate.parse(scanner.nextLine());
                        updateBilling.setPaymentDate(newPaymentDate);
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.print("Invalid date format. Please use yyyy-mm-dd: ");
                    }
                }
                
                billingDAO.updateBilling(updateBilling);
                System.out.println("Billing updated successfully.");
            } else {
                System.out.println("Billing record not found.");
            }
            break;
            
        case 4:
            int deleteBillId;
            while (true) {
                try {
                    System.out.print("Enter bill ID to delete: ");
                    deleteBillId = Integer.parseInt(scanner.nextLine());
                    if (deleteBillId <= 0) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid bill ID. Please enter a positive integer: ");
                }
            }
            
            billingDAO.deleteBilling(deleteBillId);
            System.out.println("Billing deleted successfully.");
            break;
            
        case 5:
            List<Billing> billings = billingDAO.getAllBillings();
            if (billings.isEmpty()) {
                System.out.println("No billing records found.");
            } else {
                for (Billing b : billings) {
                    System.out.println("Bill ID: " + b.getBillId() + ", Total Amount: " + b.getTotalAmount());
                }
            }
            break;
            
        default:
            System.out.println("Invalid choice.");
        }
    }
}
