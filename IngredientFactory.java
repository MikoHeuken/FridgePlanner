import java.time.LocalDate;

public class IngredientFactory {

    // Methode zur Erstellung einer neuen Zutat
    public Ingredient createIngredient(String name, double amount, String unit, LocalDate expirationDate) {
        return new Ingredient(name, amount, unit, expirationDate); // Erstellen einer neuen Ingredient-Instanz
    }

    // Beispielmethode zur Erstellung einer Zutat mit Standardwerten
    public Ingredient createDefaultIngredient() {
        String defaultName = "Standard Ingredient";
        double defaultAmount = 1.0;
        String defaultUnit = "kg";
        LocalDate defaultExpirationDate = LocalDate.now().plusDays(7); // 7 Tage in der Zukunft
        return createIngredient(defaultName, defaultAmount, defaultUnit, defaultExpirationDate); // Verwendung der createIngredient-Methode
    }

    // Beispielmethode zur Erstellung einer Zutat aus einer Beschreibung
    public Ingredient createIngredientFromDescription(String description) {
        // Hier könnte eine Logik implementiert werden, um die Beschreibung zu analysieren und eine Zutat zu erstellen
        // Zum Beispiel: "Tomate, 2, Stück, 2024-10-15"
        String[] parts = description.split(", ");
        if (parts.length == 4) {
            String name = parts[0];
            double amount = Double.parseDouble(parts[1]);
            String unit = parts[2];
            LocalDate expirationDate = DateUtils.parseDate(parts[3],"YYYY-MM-DD"); // Verwendung einer Hilfsmethode zur Datumskonvertierung
            return createIngredient(name, amount, unit, expirationDate);
        }
        return null; // Gibt null zurück, wenn die Beschreibung ungültig ist
    }
}
