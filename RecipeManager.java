import java.util.ArrayList;
import java.util.List;

public class RecipeManager {
    private final List<Recipe> recipes; // Liste von Rezepten
    private final FridgeManager fridgeManager; // Verweis auf FridgeManager zur Überprüfung von Zutaten

    // Konstruktor
    public RecipeManager(FridgeManager fridgeManager) {
        this.recipes = new ArrayList<>(); // Initialisieren der Rezeptliste
        this.fridgeManager = fridgeManager; // Zuweisen des FridgeManager
    }

    // Methode zum Hinzufügen eines Rezepts
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe); // Rezept zur Liste hinzufügen
    }

    // Methode zum Entfernen eines Rezepts
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe); // Rezept aus der Liste entfernen
    }

    // Methode zum Abrufen aller Rezepte
    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes); // Alle Rezepte zurückgeben
    }

    // Methode zur Überprüfung der Zutaten eines Rezepts
    public List<Ingredient> checkRecipeIngredients(Recipe recipe) {
        List<Ingredient> missingIngredients = new ArrayList<>(); // Liste der fehlenden Zutaten

        for (Ingredient ingredient : recipe.getIngredients()) {
            // Überprüfen, ob die Zutat im Kühlschrank vorhanden ist
            if (!fridgeManager.hasIngredient(ingredient)) {
                missingIngredients.add(ingredient); // Zutat zur Liste der fehlenden Zutaten hinzufügen
            }
        }

        return missingIngredients; // Liste der fehlenden Zutaten zurückgeben
    }

    // Methode zur Anpassen der Zutaten eines Rezepts (z.B. Mengen anpassen)
    public void adjustRecipeIngredients(Recipe recipe, double adjustmentFactor) {
        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.setAmount(ingredient.getAmount() * adjustmentFactor); // Menge anpassen
        }
    }
}
