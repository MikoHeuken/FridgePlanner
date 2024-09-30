import java.util.ArrayList;
import java.util.List;

public class IngredientCategory {
    private String name;                          // Name der Kategorie
    private List<Ingredient> ingredients;         // Liste der Zutaten in dieser Kategorie

    // Konstruktor
    public IngredientCategory(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();     // Initialisierung der Zutatenliste
    }

    // Getter für den Namen der Kategorie
    public String getName() {
        return name;
    }

    // Methode zum Hinzufügen einer Zutat zur Kategorie
    public void addIngredient(Ingredient ingredient) {
        if (!ingredients.contains(ingredient)) {
            ingredients.add(ingredient);          // Zutat zur Liste hinzufügen
        }
    }

    // Methode zum Entfernen einer Zutat aus der Kategorie
    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);            // Zutat aus der Liste entfernen
    }

    // Methode zum Abrufen aller Zutaten in der Kategorie
    public List<Ingredient> getIngredients() {
        return ingredients;                        // Gibt die Liste der Zutaten zurück
    }

    // Methode zur Überprüfung, ob die Kategorie Zutaten enthält
    public boolean hasIngredients() {
        return !ingredients.isEmpty();             // Überprüfen, ob die Liste nicht leer ist
    }

    // Methode zum Ausgeben der Zutaten der Kategorie als String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Kategorie: " + name + "\nZutaten:\n");
        for (Ingredient ingredient : ingredients) {
            sb.append("- ").append(ingredient.getName())
              .append(" (Menge: ").append(ingredient.getAmount()).append(" ")
              .append(ingredient.getUnit()).append(", Ablaufdatum: ")
              .append(ingredient.getExpirationDate()).append(")\n");
        }
        return sb.toString();
    }
}
