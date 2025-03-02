package src.medicationtracking;
import java.time.LocalDate;

public class Medication {
    private int id;
    private String name;
    private String dose;
    private int quantityInStock;
    private LocalDate expiryDate;  // Expiration date manually entered

    // Constructor that accepts manual expiration date
    public Medication(int id, String name, String dose, int quantityInStock, int year, int month, int day) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantityInStock = quantityInStock;
        this.expiryDate = LocalDate.of(year, month, day); // Expiry date manually set
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDose() {
        return dose;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(int year, int month, int day) {
        this.expiryDate = LocalDate.of(year, month, day);
    }

    // Check if the medication is expired
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }
}
