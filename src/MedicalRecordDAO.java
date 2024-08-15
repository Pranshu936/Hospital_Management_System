import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {

    // Create (Insert)
    public void addMedicalRecord(MedicalRecord record) {
        String query = "INSERT INTO MedicalRecords (patient_id, diagnosis, treatment, prescription, record_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, record.getPatientId());
            pstmt.setString(2, record.getDiagnosis());
            pstmt.setString(3, record.getTreatment());
            pstmt.setString(4, record.getPrescription());
            pstmt.setDate(5, Date.valueOf(record.getRecordDate()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read (Select)
    public MedicalRecord getMedicalRecordById(int recordId) {
        String query = "SELECT * FROM MedicalRecords WHERE record_id = ?";
        MedicalRecord record = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, recordId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                record = new MedicalRecord();
                record.setRecordId(rs.getInt("record_id"));
                record.setPatientId(rs.getInt("patient_id"));
                record.setDiagnosis(rs.getString("diagnosis"));
                record.setTreatment(rs.getString("treatment"));
                record.setPrescription(rs.getString("prescription"));
                record.setRecordDate(rs.getDate("record_date").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return record;
    }

    // Update
    public void updateMedicalRecord(MedicalRecord record) {
        String query = "UPDATE MedicalRecords SET patient_id = ?, diagnosis = ?, treatment = ?, prescription = ?, record_date = ? WHERE record_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, record.getPatientId());
            pstmt.setString(2, record.getDiagnosis());
            pstmt.setString(3, record.getTreatment());
            pstmt.setString(4, record.getPrescription());
            pstmt.setDate(5, Date.valueOf(record.getRecordDate()));
            pstmt.setInt(6, record.getRecordId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteMedicalRecord(int recordId) {
        String query = "DELETE FROM MedicalRecords WHERE record_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, recordId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List all medical records
    public List<MedicalRecord> getAllMedicalRecords() {
        String query = "SELECT * FROM MedicalRecords";
        List<MedicalRecord> records = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                MedicalRecord record = new MedicalRecord();
                record.setRecordId(rs.getInt("record_id"));
                record.setPatientId(rs.getInt("patient_id"));
                record.setDiagnosis(rs.getString("diagnosis"));
                record.setTreatment(rs.getString("treatment"));
                record.setPrescription(rs.getString("prescription"));
                record.setRecordDate(rs.getDate("record_date").toLocalDate());

                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }
}
