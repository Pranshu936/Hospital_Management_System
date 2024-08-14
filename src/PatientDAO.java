import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // Create (Insert)
    public void addPatient(Patient patient) {
        String query = "INSERT INTO Patients (name, age, gender, address, phone_number, admission_date, doctor_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString(3, patient.getGender());
            pstmt.setString(4, patient.getAddress());
            pstmt.setString(5, patient.getPhoneNumber());
            pstmt.setDate(6, Date.valueOf(patient.getAdmissionDate()));
            pstmt.setInt(7, patient.getDoctorId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read (Select)
    public Patient getPatientById(int patientId) {
        String query = "SELECT * FROM Patients WHERE patient_id = ?";
        Patient patient = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                patient = new Patient();
                patient.setPatientId(rs.getInt("patient_id"));
                patient.setName(rs.getString("name"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(rs.getString("gender"));
                patient.setAddress(rs.getString("address"));
                patient.setPhoneNumber(rs.getString("phone_number"));
                patient.setAdmissionDate(rs.getDate("admission_date").toLocalDate());
                patient.setDoctorId(rs.getInt("doctor_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    // Update
    public void updatePatient(Patient patient) {
        String query = "UPDATE Patients SET name = ?, age = ?, gender = ?, address = ?, phone_number = ?, doctor_id = ? WHERE patient_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString(3, patient.getGender());
            pstmt.setString(4, patient.getAddress());
            pstmt.setString(5, patient.getPhoneNumber());
            pstmt.setInt(6, patient.getDoctorId());
            pstmt.setInt(7, patient.getPatientId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deletePatient(int patientId) {
        String query = "DELETE FROM Patients WHERE patient_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, patientId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List all patients
    public List<Patient> getAllPatients() {
        String query = "SELECT * FROM Patients";
        List<Patient> patients = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("patient_id"));
                patient.setName(rs.getString("name"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(rs.getString("gender"));
                patient.setAddress(rs.getString("address"));
                patient.setPhoneNumber(rs.getString("phone_number"));
                patient.setAdmissionDate(rs.getDate("admission_date").toLocalDate());
                patient.setDoctorId(rs.getInt("doctor_id"));

                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }
}
