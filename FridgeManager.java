import java.util.ArrayList;
import java.util.List;

public class FridgeManager {

  // Liste von Zutaten im Kühlschrank
  private List<Ingredient> ingredients;

  // Konstruktor
  public FridgeManager() {
    this.ingredients = new ArrayList<>();
  }

  // Methode zum Hinzufügen einer Zutat
  public void addIngredient(Ingredient ingredient) {
    boolean found = false;
    for (Ingredient i : ingredients) {
      if (i.getName().equalsIgnoreCase(ingredient.getName())) {
        i.setAmount(i.getAmount() + ingredient.getAmount()); // Menge erhöhen, wenn die Zutat bereits existiert
        found = true;
        break;
      }
    }
    if (!found) {
      ingredients.add(ingredient); // Neue Zutat hinzufügen, falls sie noch nicht existiert
    }
  }

  // Methode zum Entfernen einer Zutat
  public void removeIngredient(String ingredientName) {
    ingredients.removeIf(i -> i.getName().equalsIgnoreCase(ingredientName));
  }

  // Methode zum Abrufen der Zutatenliste
  public List<Ingredient> getIngredients() {
    return new ArrayList<>(ingredients); // Rückgabe einer Kopie der Zutatenliste, um die Originaldaten zu schützen
  }

  // Methode zum Überprüfen, ob genügend Zutaten für ein Rezept vorhanden sind
  public boolean hasEnoughIngredients(Recipe recipe) {
    for (Ingredient recipeIngredient : recipe.getIngredients()) {
      Ingredient fridgeIngredient = findIngredientByName(recipeIngredient.getName());
      if (fridgeIngredient == null || fridgeIngredient.getAmount() < recipeIngredient.getAmount()) {
        return false; // Nicht genügend von einer oder mehreren Zutaten
      }
    }
    return true; // Genügend Zutaten für das Rezept vorhanden
  }

  // Methode zum Reduzieren der Menge einer Zutat im Kühlschrank basierend auf
  // einem Rezept
  public void useIngredients(Recipe recipe) {
    for (Ingredient recipeIngredient : recipe.getIngredients()) {
      Ingredient fridgeIngredient = findIngredientByName(recipeIngredient.getName());
      if (fridgeIngredient != null) {
        fridgeIngredient.setAmount(fridgeIngredient.getAmount() - recipeIngredient.getAmount());
      }
    }
  }

  // Hilfsmethode zum Finden einer Zutat nach ihrem Namen
  private Ingredient findIngredientByName(String name) {
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getName().equalsIgnoreCase(name)) {
        return ingredient;
      }
    }
    return null;
  }

  // Methode zum Überprüfen, ob eine bestimmte Zutat im Kühlschrank vorhanden ist
  public boolean hasIngredient(Ingredient ingredient) {
    for (Ingredient ing : ingredients) {
      if (ing.getName().equals(ingredient.getName()) &&
          ing.getAmount() >= ingredient.getAmount()) { // Überprüfen des Namens und der Menge
        return true; // Zutat vorhanden
      }
    }
    return false; // Zutat nicht vorhanden
  }

  public double getAvailableAmount(Ingredient ingredient) {
    // Überprüfen, ob die Zutat im Kühlschrank vorhanden ist
    if (ingredients.contains(ingredient)) {
      for(Ingredient ing : ingredients) {
        if(ing.equals(ingredient)) {
          return ing.getAmount();
        }
      }
    }
    return 0; // Wenn nicht vorhanden, 0 zurückgeben
}

}
