import java.util.HashMap;
import java.util.Map;

public class ShoppingList {
    private final Map<Ingredient, Double> items; // Zutaten und deren benötigte Mengen

    // Konstruktor
    public ShoppingList() {
        items = new HashMap<>(); // HashMap zur Speicherung der Zutaten
    }

    // Methode zum Hinzufügen einer Zutat zur Einkaufsliste
    public void addIngredient(Ingredient ingredient, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Die Menge muss positiv sein.");
        }

        // Überprüfen, ob die Zutat bereits auf der Liste ist
        if (items.containsKey(ingredient)) {
            // Menge erhöhen, wenn die Zutat bereits vorhanden ist
            items.put(ingredient, items.get(ingredient) + amount);
        } else {
            items.put(ingredient, amount); // Zutat hinzufügen
        }
    }

    // Methode zum Entfernen einer Zutat von der Einkaufsliste
    public void removeIngredient(Ingredient ingredient) {
        if (items.containsKey(ingredient)) {
            items.remove(ingredient); // Zutat entfernen
        }
    }

    // Methode zum Anzeigen der Einkaufsliste
    public void showShoppingList() {
        if (items.isEmpty()) {
            System.out.println("Die Einkaufsliste ist leer.");
            return;
        }

        System.out.println("Einkaufsliste:");
        for (Map.Entry<Ingredient, Double> entry : items.entrySet()) {
            Ingredient ingredient = entry.getKey();
            double amount = entry.getValue();
            System.out.println("- " + ingredient.getName() + ": " + amount + " " + ingredient.getUnit());
        }
    }

    // Methode zum Überprüfen, ob die Liste Zutaten enthält
    public boolean hasIngredients() {
        return !items.isEmpty(); // Gibt true zurück, wenn die Liste nicht leer ist
    }

    // Getter für die Zutaten der Einkaufsliste
    public Map<Ingredient, Double> getItems() {
        return items; // Gibt die Zutaten als Map zurück
    }
}
