import java.util.List;

public class RecipeService {
    private final RecipeManager recipeManager; // Verweis auf RecipeManager

    // Konstruktor
    public RecipeService(RecipeManager recipeManager) {
        this.recipeManager = recipeManager; // Zuweisen des RecipeManager
    }

    // Methode zum Hinzufügen eines Rezepts
    public void addRecipe(Recipe recipe) {
        // Validierung der Zutaten
        List<Ingredient> missingIngredients = recipeManager.checkRecipeIngredients(recipe);
        if (missingIngredients.isEmpty()) {
            recipeManager.addRecipe(recipe); // Rezept hinzufügen, wenn alle Zutaten vorhanden sind
            System.out.println("Rezept \"" + recipe.getName() + "\" wurde hinzugefügt.");
        } else {
            System.out.println("Das Rezept \"" + recipe.getName() + "\" kann nicht hinzugefügt werden, da folgende Zutaten fehlen:");
            for (Ingredient missing : missingIngredients) {
                System.out.println("- " + missing.getName() + ": " + missing.getAmount() + " " + missing.getUnit());
            }
        }
    }

    // Methode zum Entfernen eines Rezepts
    public void removeRecipe(Recipe recipe) {
        recipeManager.removeRecipe(recipe); // Rezept vom RecipeManager entfernen
        System.out.println("Rezept \"" + recipe.getName() + "\" wurde entfernt.");
    }

    // Methode zum Überprüfen der Zutaten eines Rezepts
    public List<Ingredient> getMissingIngredients(Recipe recipe) {
        return recipeManager.checkRecipeIngredients(recipe); // Fehlende Zutaten zurückgeben
    }

    // Methode zur Anpassung der Zutatenmengen eines Rezepts
    public void adjustRecipe(Recipe recipe, double adjustmentFactor) {
        recipeManager.adjustRecipeIngredients(recipe, adjustmentFactor); // Zutatenmengen anpassen
        System.out.println("Die Mengen der Zutaten für das Rezept \"" + recipe.getName() + "\" wurden angepasst.");
    }

    // Methode zur Abrufung aller Rezepte
    public List<Recipe> getAllRecipes() {
        return recipeManager.getAllRecipes(); // Alle Rezepte zurückgeben
    }
}
