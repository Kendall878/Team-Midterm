package src.medicationtracking;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prescription {
    private int id;
    private Doctor doctor;
    private Patient patient;
    private Medication medication;
    private LocalDate prescriptionExpiry;

    public Prescription(int id, Doctor doctor, Patient patient, Medication medication) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.prescriptionExpiry = LocalDate.now().plus(1, ChronoUnit.YEARS);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public LocalDate getPrescriptionExpiry() {
        return prescriptionExpiry;
    }

    public void setPrescriptionExpiry(LocalDate prescriptionExpiry) {
        this.prescriptionExpiry = prescriptionExpiry;
    }
}