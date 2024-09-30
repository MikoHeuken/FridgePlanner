import java.util.ArrayList;
import java.util.List;

public class Recipe {

    // Attribute des Rezepts
    private String name;  // Name des Rezepts
    private List<Ingredient> ingredients;  // Liste der Zutaten im Rezept

    // Konstruktor
    public Recipe(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    // Getter für den Namen des Rezepts
    public String getName() {
        return name;
    }

    // Setter für den Namen des Rezepts
    public void setName(String name) {
        this.name = name;
    }

    // Methode zum Hinzufügen einer Zutat zum Rezept
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    // Methode zum Entfernen einer Zutat aus dem Rezept
    public void removeIngredient(String ingredientName) {
        ingredients.removeIf(i -> i.getName().equalsIgnoreCase(ingredientName));
    }

    // Methode zum Abrufen der Liste der Zutaten im Rezept
    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);  // Rückgabe einer Kopie der Zutatenliste, um die Originaldaten zu schützen
    }

    // Methode zum Anpassen der Menge einer Zutat im Rezept
    public void updateIngredientAmount(String ingredientName, double newAmount) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
                ingredient.setAmount(newAmount);
                break;
            }
        }
    }

    // Methode zum Berechnen der Gesamtmenge einer Zutat im Rezept
    public double getIngredientAmount(String ingredientName) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
                return ingredient.getAmount();
            }
        }
        return 0.0;  // Falls die Zutat nicht vorhanden ist
    }

    // Methode zur Überprüfung, ob das Rezept eine bestimmte Zutat enthält
    public boolean hasIngredient(String ingredientName) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
                return true;
            }
        }
        return false;
    }
}
