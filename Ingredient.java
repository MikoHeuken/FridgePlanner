import java.time.LocalDate;

public class Ingredient {

    // Attribute der Zutat
    private String name;  // Name der Zutat
    private double amount;  // Menge der Zutat
    private String unit;  // Einheit der Menge (z.B. "g", "ml", "Stück")
    private LocalDate expirationDate;  // Verfallsdatum der Zutat

    // Konstruktor
    public Ingredient(String name, double amount, String unit, LocalDate expirationDate) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.expirationDate = expirationDate;
    }

    // Getter für den Namen der Zutat
    public String getName() {
        return name;
    }

    // Setter für den Namen der Zutat
    public void setName(String name) {
        this.name = name;
    }

    // Getter für die Menge der Zutat
    public double getAmount() {
        return amount;
    }

    // Setter für die Menge der Zutat
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter für die Einheit der Zutat
    public String getUnit() {
        return unit;
    }

    // Setter für die Einheit der Zutat
    public void setUnit(String unit) {
        this.unit = unit;
    }

    // Getter für das Verfallsdatum
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    // Setter für das Verfallsdatum
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Methode zum Überprüfen, ob die Zutat abgelaufen ist
    public boolean isExpired() {
        return DateUtils.isExpired(expirationDate);
    }

}
