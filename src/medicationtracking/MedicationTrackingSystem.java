package src.medicationtracking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MedicationTrackingSystem {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Medication> medications;
    private List<Prescription> prescriptions;

    public MedicationTrackingSystem() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    //  Add a New Patient
    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println(" Patient added: " + patient.getName());
    }

    //  Add a New Doctor
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println(" Doctor added: " + doctor.getName());
    }

    //  Add a New Medication
    public void addMedication(Medication medication) {
        medications.add(medication);
        System.out.println(" Medication added: " + medication.getName());
    }

    //  Generate System Report
    public void generateReport() {
        System.out.println("=== System Report ===");
        System.out.println("Patients: " + patients.size());
        System.out.println("Doctors: " + doctors.size());
        System.out.println("Medications: " + medications.size());
        System.out.println("Prescriptions: " + prescriptions.size());
    }

    //  Check for Expired Medications
    public void checkExpiredMeds() {
        System.out.println("=== Checking Expired Medications ===");
        if (medications.isEmpty()) {
            System.out.println("Medications are Empty.");
            return;

        }

        boolean foundExpired = false;
        for (Medication med : medications) {
            if (med.getExpiryDate().isBefore(LocalDate.now())) {
                System.out.println("Expired: " + med.getName() + " (Expired on " + med.getExpiryDate() + ")");
            }
        }
        if (!foundExpired) {
            System.out.println("No Expired Medications Found.");
        }
    }

    //  Process a New Prescription (Fixed)
    public void processPrescription(String doctorName, String patientName, String medicationName) {
        Doctor doctor = findDoctorByName(doctorName);
        Patient patient = findPatientByName(patientName);
        Medication medication = findMedicationByName(medicationName);

        if (doctor == null || patient == null || medication == null) {
            System.out.println("Error: Doctor, Patient, or Medication not found.");
            return;
        }

        int prescriptionId = generatePrescriptionId(); // Generates a unique ID
        Prescription newPrescription = new Prescription(prescriptionId, doctor, patient, medication);
        prescriptions.add(newPrescription);

        System.out.println("Prescription added for " + patient.getName() + " by Dr. " + doctor.getName());
    }

    // Print All Prescriptions for a Specific Doctor
    public void printPrescriptionsByDoctor(String doctorName) {
        System.out.println("=== Prescriptions for Dr. " + doctorName + " ===");
        for (Prescription p : prescriptions) {
            if (p.getDoctor().getName().equalsIgnoreCase(doctorName)) {
                System.out.println("Patient: " + p.getPatient().getName() + " | Medication: " + p.getMedication().getName());
            }
        }
    }

    // Print All Prescriptions for a Specific Patient
    public void printPrescriptionsByPatient(String patientName) {
        System.out.println("=== Prescriptions for " + patientName + " ===");
        for (Prescription p : prescriptions) {
            if (p.getPatient().getName().equalsIgnoreCase(patientName)) {
                System.out.println("Doctor: " + p.getDoctor().getName() + " | Medication: " + p.getMedication().getName());
            }
        }
    }

    // Restock Medications (Fixed)
    public void restockMedications() {
        for (Medication med : medications) {
            int newStock = med.getQuantityInStock() + (new Random().nextInt(10) + 1); // Adds 1-10 units
            med.setQuantityInStock(newStock);
            System.out.println("Restocked " + med.getName() + ". New quantity: " + med.getQuantityInStock());
        }
    }

    // 🔎 Helper Methods to Find Entities
    private Doctor findDoctorByName(String name) {
        return doctors.stream().filter(d -> d.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    private Patient findPatientByName(String name) {
        return patients.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    Medication findMedicationByName(String name) {
        return medications.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    private int generatePrescriptionId() {
        return new Random().nextInt(10000); // Generates a random ID
    }
    public boolean removePatient(String name) {
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) {
                patients.remove(p);
                return true; // Patient found and removed
            }
        }
        return false; //  Patient not found
    }
    
    public boolean removeDoctor(String name) {
        for (Doctor d : doctors) {
            if (d.getName().equalsIgnoreCase(name)) {
                doctors.remove(d);
                return true; // Doctor found and removed
            }
        }
        return false; //  Doctor not found
    }
    
    public boolean removeMedication(String name) {
        for (Medication m : medications) {
            if (m.getName().equalsIgnoreCase(name)) {
                medications.remove(m);
                return true; // Medication found and removed
            }
        }
        return false; //  Medication not found
    }
    
}
