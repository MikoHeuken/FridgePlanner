import java.time.LocalDate;

public class IngredientValidator {

    // Methode zur Validierung einer Zutat
    public boolean validate(Ingredient ingredient) {
        // Überprüfen, ob der Name nicht leer ist
        if (ingredient.getName() == null || ingredient.getName().isEmpty()) {
            return false; // Ungültig, wenn der Name fehlt
        }
        
        // Überprüfen, ob die Menge positiv ist
        if (ingredient.getAmount() <= 0) {
            return false; // Ungültig, wenn die Menge nicht positiv ist
        }

        // Überprüfen, ob die Einheit nicht leer ist
        if (ingredient.getUnit() == null || ingredient.getUnit().isEmpty()) {
            return false; // Ungültig, wenn die Einheit fehlt
        }

        // Überprüfen, ob das Verfallsdatum in der Zukunft liegt
        if (ingredient.getExpirationDate() != null && 
            ingredient.getExpirationDate().isBefore(LocalDate.now())) {
            return false; // Ungültig, wenn das Verfallsdatum in der Vergangenheit liegt
        }

        return true; // Gültig, wenn alle Bedingungen erfüllt sind
    }
}
