package src.medicationtracking;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private List<Medication> medications;
    private List<Prescription> prescriptions;

    public Patient(int id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void addMedication(Medication medication) {
        this.medications.add(medication);
    }

    public void addPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
    }

    public void printMedications(){
        if (medications.isEmpty()) {
            System.out.println(getName() + "Is Not Taking Any Medications.");
        }
        else {
            System.out.println(getName() + "Is Taking:");
            for (Medication med : medications) {
                System.out.println(" - " + med.getName() + "(" + med.getDose() + ")");
            }
        }
    }

    public boolean isTakingMedication(String medicationName) {
        for (Medication med : medications) {
            if (med.getName().equalsIgnoreCase(medicationName)) {
                return true;
            }
        }
        return false;
    }


}