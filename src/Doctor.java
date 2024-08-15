public class Doctor {
    private int doctorId;
    private String name;
    private String specialization;
    private String phoneNumber;
    private String email;

    // Constructor
    public Doctor() {
    }

    public Doctor(int doctorId, String name, String specialization, String phoneNumber, String email) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and Setters
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
