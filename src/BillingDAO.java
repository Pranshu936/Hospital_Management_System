import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {

    // Create (Insert)
    public void addBilling(Billing billing) {
        String query = "INSERT INTO Billing (patient_id, total_amount, paid_amount, payment_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, billing.getPatientId());
            pstmt.setDouble(2, billing.getTotalAmount());
            pstmt.setDouble(3, billing.getPaidAmount());
            pstmt.setDate(4, Date.valueOf(billing.getPaymentDate()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read (Select)
    public Billing getBillingById(int billId) {
        String query = "SELECT * FROM Billing WHERE bill_id = ?";
        Billing billing = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, billId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                billing = new Billing();
                billing.setBillId(rs.getInt("bill_id"));
                billing.setPatientId(rs.getInt("patient_id"));
                billing.setTotalAmount(rs.getDouble("total_amount"));
                billing.setPaidAmount(rs.getDouble("paid_amount"));
                billing.setPaymentDate(rs.getDate("payment_date").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billing;
    }

    // Update
    public void updateBilling(Billing billing) {
        String query = "UPDATE Billing SET patient_id = ?, total_amount = ?, paid_amount = ?, payment_date = ? WHERE bill_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, billing.getPatientId());
            pstmt.setDouble(2, billing.getTotalAmount());
            pstmt.setDouble(3, billing.getPaidAmount());
            pstmt.setDate(4, Date.valueOf(billing.getPaymentDate()));
            pstmt.setInt(5, billing.getBillId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteBilling(int billId) {
        String query = "DELETE FROM Billing WHERE bill_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, billId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List all billings
    public List<Billing> getAllBillings() {
        String query = "SELECT * FROM Billing";
        List<Billing> billings = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Billing billing = new Billing();
                billing.setBillId(rs.getInt("bill_id"));
                billing.setPatientId(rs.getInt("patient_id"));
                billing.setTotalAmount(rs.getDouble("total_amount"));
                billing.setPaidAmount(rs.getDouble("paid_amount"));
                billing.setPaymentDate(rs.getDate("payment_date").toLocalDate());

                billings.add(billing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billings;
    }
}
