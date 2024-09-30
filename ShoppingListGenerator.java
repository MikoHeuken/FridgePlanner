import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingListGenerator {
    
    // Methode zur Generierung der Einkaufsliste basierend auf den Rezepten
    public ShoppingList generateShoppingList(List<Recipe> recipes, FridgeManager fridgeManager) {
        ShoppingList shoppingList = new ShoppingList(); // Neue Einkaufsliste erstellen
        Map<Ingredient, Double> requiredIngredients = new HashMap<>(); // Benötigte Zutaten

        // Durchlaufen der Rezepte, um die benötigten Zutaten zu sammeln
        for (Recipe recipe : recipes) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                double requiredAmount = ingredient.getAmount(); // Menge der Zutat
                // Überprüfen, ob die Zutat bereits auf der Liste ist
                if (requiredIngredients.containsKey(ingredient)) {
                    requiredIngredients.put(ingredient, requiredIngredients.get(ingredient) + requiredAmount);
                } else {
                    requiredIngredients.put(ingredient, requiredAmount);
                }
            }
        }

        // Überprüfen, welche Zutaten im Kühlschrank fehlen und zur Einkaufsliste hinzufügen
        for (Map.Entry<Ingredient, Double> entry : requiredIngredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            double amountNeeded = entry.getValue();
            // Prüfen, ob der Kühlschrank die Zutat hat und die Menge
            if (!fridgeManager.hasIngredient(ingredient)) {
                shoppingList.addIngredient(ingredient, amountNeeded); // Zutat zur Einkaufsliste hinzufügen
            } else {
                double availableAmount = fridgeManager.getAvailableAmount(ingredient); // Verfügbare Menge
                if (availableAmount < amountNeeded) {
                    double missingAmount = amountNeeded - availableAmount; // Fehlende Menge
                    shoppingList.addIngredient(ingredient, missingAmount); // Fehlende Menge zur Einkaufsliste hinzufügen
                }
            }
        }

        return shoppingList; // Rückgabe der generierten Einkaufsliste
    }
}
