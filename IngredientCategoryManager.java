import java.util.ArrayList;
import java.util.List;

public class IngredientCategoryManager {
    private List<IngredientCategory> categories; // Liste der Zutatenkategorien

    // Konstruktor
    public IngredientCategoryManager() {
        this.categories = new ArrayList<>(); // Initialisierung der Kategorienliste
    }

    // Methode zum Hinzufügen einer Kategorie
    public void addCategory(IngredientCategory category) {
        if (!categories.contains(category)) {
            categories.add(category); // Kategorie zur Liste hinzufügen
        }
    }

    // Methode zum Entfernen einer Kategorie
    public void removeCategory(IngredientCategory category) {
        categories.remove(category); // Kategorie aus der Liste entfernen
    }

    // Methode zum Abrufen aller Kategorien
    public List<IngredientCategory> getCategories() {
        return categories; // Gibt die Liste der Kategorien zurück
    }

    // Methode zum Überprüfen, ob eine bestimmte Kategorie existiert
    public boolean categoryExists(String categoryName) {
        for (IngredientCategory category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return true; // Kategorie existiert bereits
            }
        }
        return false; // Kategorie existiert nicht
    }

    // Methode zur Validierung einer bestimmten Kategorie
    public boolean validateCategory(IngredientCategory category) {
        // Hier könnten Validierungsbedingungen für die spezifische Kategorie implementiert werden
        // Beispielsweise, dass eine Kategorie mindestens eine Zutat enthalten sollte
        return category.hasIngredients(); // Gibt true zurück, wenn die Kategorie Zutaten hat
    }

    // Methode zur Validierung aller Kategorien
    public boolean validateAllCategories() {
        boolean allValid = true;
        for (IngredientCategory category : categories) {
            if (!validateCategory(category)) {
                allValid = false; // Setzt allValid auf false, wenn eine Kategorie ungültig ist
            }
        }
        return allValid; // Gibt true zurück, wenn alle Kategorien gültig sind
    }

    // Methode zum Ausgeben aller Kategorien als String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Zutatenkategorien:\n");
        for (IngredientCategory category : categories) {
            sb.append(category.toString()).append("\n"); // Fügt jede Kategorie zur Ausgabe hinzu
        }
        return sb.toString();
    }
}
