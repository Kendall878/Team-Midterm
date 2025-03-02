package src.medicationtracking;
import java.util.Scanner;

public class EmptyMenu {
    public static void main(String[] args) {
        MedicationTrackingSystem medicationTracking = new MedicationTrackingSystem();
        Scanner scanner = new Scanner(System.in); // Declare Scanner outside the loop
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Welcome To The Pharmacy Med Tracking System =====");
            System.out.println("What would you like to do?");
            System.out.println("1: Add A New Patient");
            System.out.println("2: Add A New Doctor");
            System.out.println("3: Add A New Medication To The Pharmacy");
            System.out.println("4: Print System Report");
            System.out.println("5: Check If Meds Are Expired");
            System.out.println("6: Process A New Prescription");
            System.out.println("7: Print All Prescriptions For A Specific Doctor");
            System.out.println("8: Restock the Drugs in the Pharmacy");
            System.out.println("9: Print All Prescriptions for a Specific Patient");
            System.out.println("10: Remove a Patient");
            System.out.println("11: Remove a Doctor");
            System.out.println("12: Remove a Medication");
            System.out.println("13: Exit");
            System.out.print("Enter your choice: ");

            int option = getValidChoice(scanner, 1, 13);

            switch (option) {
                case 1:
                    addANewPatient(scanner, medicationTracking);
                    break;
                case 2:
                    addANewDoctor(scanner, medicationTracking);
                    break;
                case 3:
                    addNewMedicationToPharmacy(scanner, medicationTracking);
                    break;
                case 4:
                    printPharmacyReport(medicationTracking);
                    break;
                case 5:
                    medicationTracking.checkExpiredMeds();
                    break;
                case 6:
                    processANewScript(scanner, medicationTracking);
                    break;
                case 7:
                    printScriptsForSpecificDoctor(scanner, medicationTracking);
                    break;
                case 8:
                    restockPharmacyDrugs(scanner, medicationTracking);
                    break;
                case 9:
                    printAllScriptsForPatientByName(scanner, medicationTracking);
                    break;
                    case 10:
                    removePatient(scanner, medicationTracking);
                    break;
                case 11:
                    removeDoctor(scanner, medicationTracking);
                    break;
                case 12:
                    removeMedication(scanner, medicationTracking);
                    break;
                case 13:
                    exit = true;
                    System.out.println("Exiting The System! Goodbye!");
                    break;
                default:
                    System.out.println(" Invalid option. Please try again.");
            }
        }
        scanner.close(); // Close scanner before exiting
    }

    private static int getValidChoice(Scanner scanner, int min, int max) {
        int choice;
        while (true) {
            System.out.print("Enter choice (" + min + "-" + max + "): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice >= min && choice <= max) {
                    return choice;
                }
            } else {
                scanner.nextLine(); // Consume invalid input
            }
            System.out.println("Invalid choice. Please enter a number between 1 and 13.");
        }
    }

    // Remove a Patient
    private static void removePatient(Scanner scanner, MedicationTrackingSystem system) {
        System.out.print("Enter Patient's Name to Remove: ");
        String patientName = scanner.nextLine().trim();
        if (system.removePatient(patientName)) {
            System.out.println("Patient '" + patientName + "' removed successfully.");
        } else {
            System.out.println(" Patient not found.");
        }
    }

    // Remove a Doctor
    private static void removeDoctor(Scanner scanner, MedicationTrackingSystem system) {
        System.out.print("Enter Doctor's Name to Remove: ");
        String doctorName = scanner.nextLine().trim();
        if (system.removeDoctor(doctorName)) {
            System.out.println("Doctor '" + doctorName + "' removed successfully.");
        } else {
            System.out.println(" Doctor not found.");
        }
    }

    // Remove a Medication
    private static void removeMedication(Scanner scanner, MedicationTrackingSystem system) {
        System.out.print("Enter Medication Name to Remove: ");
        String medicationName = scanner.nextLine().trim();
        if (system.removeMedication(medicationName)) {
            System.out.println("Medication '" + medicationName + "' removed successfully.");
        } else {
            System.out.println("Medication not found.");
        }
    }
    private static void addANewPatient(Scanner scanner, MedicationTrackingSystem system) {
        String idStr;
        int id;
    
        while (true) {
            System.out.print("Enter Patient ID (5-digit number): ");
            idStr = scanner.nextLine().trim();
    
            if (idStr.matches("\\d{5}")) {  // Ensures exactly 5 digits
                id = Integer.parseInt(idStr);  // Convert valid input to integer
                break;
            } else {
                System.out.println("Invalid ID! Must be exactly 5 digits.");
            }
        }
    
        String firstName;
        while (true) {
            System.out.print("Enter Patient's First Name: ");
            firstName = scanner.nextLine().trim();
            if (firstName.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("Invalid name! Use only letters.");
            }
        }
    
        String lastName;
        while (true) {
            System.out.print("Enter Patient's Last Name: ");
            lastName = scanner.nextLine().trim();
            if (lastName.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("Invalid name! Use only letters.");
            }
        }
    
        String phone;
        while (true) {
            System.out.print("Enter Patient Phone Number (10 digits): ");
            phone = scanner.nextLine().trim();
            if (phone.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Invalid phone number! Must be exactly 10 digits.");
            }
        }
    
        String fullName = firstName + " " + lastName;
        Patient newPatient = new Patient(id, fullName, 0, phone);
    
        while (true) {
            System.out.print("Enter Medication Name (Or Type 'End' To Finish): ");
            String medicationName = scanner.nextLine().trim();
    
            if (medicationName.equalsIgnoreCase("End")) {
                break;
            }
    
            Medication medication = system.findMedicationByName(medicationName);
            if (medication == null) {
                System.out.println("Medication not found. Please enter a valid medication name.");
            } else {
                newPatient.addMedication(medication);
                System.out.println("Medication added to patient.");
            }
        }
    
        system.addPatient(newPatient);
        System.out.println("Patient Added Successfully!");
    }

      private static void addANewDoctor(Scanner scanner, MedicationTrackingSystem system) {
        int id;
        while (true) {
            System.out.print("Enter Doctor ID (5-digit number): ");
            String idInput = scanner.nextLine();
            if (idInput.matches("\\d{5}")) {  // Ensures exactly 5 digits
                id = Integer.parseInt(idInput);
                break;
            } else {
                System.out.println("Invalid ID! It must be exactly 5 digits.");
            }
        }

        String name;
        while (true) {
            System.out.print("Enter Doctor Name: ");
            name = scanner.nextLine();
            if (name.matches("[a-zA-Z ]+")) {  // Allows only letters and spaces
                break;
            } else {
                System.out.println("Invalid Name! Only letters are allowed.");
            }
        }

        int age;
        while (true) {
            System.out.print("Enter Doctor Age: ");
            String ageInput = scanner.nextLine();
            if (ageInput.matches("\\d+")) {  // Ensures only numbers
                age = Integer.parseInt(ageInput);
                break;
            } else {
                System.out.println("Invalid Age! Only numbers are allowed.");
            }
        }

        String phone;
        while (true) {
            System.out.print("Enter Doctor Phone Number (10-digit number): ");
            phone = scanner.nextLine();
            if (phone.matches("\\d{10}")) {  // Ensures exactly 10 digits
                break;
            } else {
                System.out.println("Invalid Phone Number! It must be 10 digits.");
            }
        }

        String specialization;
        while (true) {
            System.out.print("Enter Doctor Specialization (letters only): ");
            specialization = scanner.nextLine();
            if (specialization.matches("[a-zA-Z ]+")) {  // Allows only letters and spaces
                break;
            } else {
                System.out.println("Invalid Specialization! Only letters are allowed.");
            }
        }

        Doctor newDoctor = new Doctor(id, name, age, phone, specialization);
        system.addDoctor(newDoctor);
        System.out.println("Doctor Added Successfully!");
    }


    private static void addNewMedicationToPharmacy(Scanner scanner, MedicationTrackingSystem system) {
        int id;
        while (true) {
            System.out.print("Enter Medication ID (5-digit number): ");
            String idInput = scanner.nextLine();
            if (idInput.matches("\\d{5}")) {  // Ensures exactly 5 digits
                id = Integer.parseInt(idInput);
                break;
            } else {
                System.out.println("Invalid ID! It must be exactly 5 digits.");
            }
        }

        System.out.print("Enter Medication Name: ");
        String name = scanner.nextLine().trim();

        String dose;
        while (true) {
            System.out.print("Enter Dosage In mg: ");
            dose = scanner.nextLine().trim();
            if (dose.matches("\\d+")) {  // Ensures only numbers
                break;
            } else {
                System.out.println("Invalid Dosage! Only numbers are allowed.");
            }
        }

        int quantity;
        while (true) {
            System.out.print("Enter Quantity in Stock (integer only): ");
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid quantity.");
                scanner.next(); // Clear invalid input
            }
        }

        int year;
        while (true) {
            System.out.print("Enter Expiration Year (4-digit year): ");
            String yearInput = scanner.nextLine();
            if (yearInput.matches("\\d{4}")) {  // Ensures exactly 4 digits
                year = Integer.parseInt(yearInput);
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid 4-digit year.");
            }
        }

        int month;
        while (true) {
            System.out.print("Enter Expiration Month (1-12): ");
            if (scanner.hasNextInt()) {
                month = scanner.nextInt();
                if (month >= 1 && month <= 12) {
                    scanner.nextLine(); // Consume newline
                    break;
                }
            }
            System.out.println("Invalid input! Please enter a month between 1-12.");
            scanner.nextLine(); // Clear invalid input
        }

        int day;
        while (true) {
            System.out.print("Enter Expiration Day (1-31): ");
            if (scanner.hasNextInt()) {
                day = scanner.nextInt();
                if (day >= 1 && day <= 31) {
                    scanner.nextLine(); // Consume newline
                    break;
                }
            }
            System.out.println("Invalid input! Please enter a day between 1-31.");
            scanner.nextLine(); // Clear invalid input
        }

        // Create and add medication
        Medication newMedication = new Medication(id, name, dose, quantity, year, month, day);
        system.addMedication(newMedication);

        System.out.println("Medication '" + name + "' added successfully!");
    }
    

    private static void printPharmacyReport(MedicationTrackingSystem system) {
        system.generateReport();
    }

    private static void processANewScript(Scanner scanner, MedicationTrackingSystem system) {
        System.out.print("Enter Doctor's Name: ");
        String doctorName = scanner.nextLine();

        System.out.print("Enter Patient's Name: ");
        String patientName = scanner.nextLine();

        System.out.print("Enter Medication Name: ");
        String medicationName = scanner.nextLine();

        System.out.println("Processing Prescription...");
        system.processPrescription(doctorName, patientName, medicationName);

        System.out.println(" Prescription Processed Successfully!");
    }

    private static void printScriptsForSpecificDoctor(Scanner scanner, MedicationTrackingSystem system) {
        System.out.print("Enter Doctor's Name: ");
        String doctorName = scanner.nextLine();

        system.printPrescriptionsByDoctor(doctorName);
    }

    private static void restockPharmacyDrugs(Scanner scanner, MedicationTrackingSystem system) {
        System.out.println("Restocking Medications...");
        system.restockMedications();
        System.out.println(" Medications Restocked!");
    }

    private static void printAllScriptsForPatientByName(Scanner scanner, MedicationTrackingSystem system) {
        System.out.print("Enter Patient's Name: ");
        String patientName = scanner.nextLine();

        system.printPrescriptionsByPatient(patientName);
    }
}

