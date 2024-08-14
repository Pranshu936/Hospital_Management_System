import java.time.LocalDate;

public class Billing {
    private int billId;
    private int patientId;
    private double totalAmount;
    private double paidAmount;
    private LocalDate paymentDate;

    // Constructor
    public Billing() {
    }

    public Billing(int billId, int patientId, double totalAmount, double paidAmount, LocalDate paymentDate) {
        this.billId = billId;
        this.patientId = patientId;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.paymentDate = paymentDate;
    }

    // Getters and Setters
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
