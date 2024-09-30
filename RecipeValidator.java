import java.util.ArrayList;
import java.util.List;

public class RecipeValidator {
    
    // Methode zur Validierung eines Rezepts
    public List<String> validate(Recipe recipe) {
        List<String> errors = new ArrayList<>(); // Liste zur Speicherung von Fehlernachrichten

        // Überprüfen, ob der Rezeptname nicht leer ist
        if (recipe.getName() == null || recipe.getName().trim().isEmpty()) {
            errors.add("Rezeptname darf nicht leer sein.");
        }

        // Überprüfen, ob mindestens eine Zutat vorhanden ist
        if (recipe.getIngredients().isEmpty()) {
            errors.add("Das Rezept muss mindestens eine Zutat enthalten.");
        } else {
            // Überprüfen, ob jede Zutat gültig ist
            for (Ingredient ingredient : recipe.getIngredients()) {
                String ingredientValidationError = validateIngredient(ingredient);
                if (ingredientValidationError != null) {
                    errors.add(ingredientValidationError);
                }
            }
        }

        return errors; // Fehlerliste zurückgeben
    }

    // Hilfsmethode zur Validierung einer einzelnen Zutat
    private String validateIngredient(Ingredient ingredient) {
        // Überprüfen, ob der Name der Zutat nicht leer ist
        if (ingredient.getName() == null || ingredient.getName().trim().isEmpty()) {
            return "Zutat hat einen leeren Namen.";
        }

        // Überprüfen, ob die Menge positiv ist
        if (ingredient.getAmount() <= 0) {
            return "Die Menge für die Zutat \"" + ingredient.getName() + "\" muss positiv sein.";
        }

        // Überprüfen, ob die Einheit nicht leer ist
        if (ingredient.getUnit() == null || ingredient.getUnit().trim().isEmpty()) {
            return "Die Einheit für die Zutat \"" + ingredient.getName() + "\" darf nicht leer sein.";
        }

        return null; // Keine Fehler
    }
}
