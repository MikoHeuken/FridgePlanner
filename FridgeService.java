import java.util.List;

public class FridgeService {
    
    private FridgeManager fridgeManager;       // Kühlschrankmanager zur Verwaltung der Zutaten
    private IngredientValidator ingredientValidator;  // Validator für Zutaten
    private DatabaseManager databaseManager;    // Datenbankmanager für Datenpersistenz

    // Konstruktor
    public FridgeService(FridgeManager fridgeManager, IngredientValidator ingredientValidator, DatabaseManager databaseManager) {
        this.fridgeManager = fridgeManager;
        this.ingredientValidator = ingredientValidator;
        this.databaseManager = databaseManager;
    }

    // Methode zum Hinzufügen einer Zutat
    public void addIngredient(Ingredient ingredient) {
        if (ingredientValidator.validate(ingredient)) {
            fridgeManager.addIngredient(ingredient);  // Zutat zum Kühlschrank hinzufügen
            databaseManager.addIngredient(ingredient);  // Zutat zur Datenbank hinzufügen
        } else {
            System.out.println("Ungültige Zutat: " + ingredient.getName());
        }
    }

    // Methode zum Entfernen einer Zutat
    public void removeIngredient(String ingredientName) {
        fridgeManager.removeIngredient(ingredientName);  // Zutat vom Kühlschrank entfernen
        databaseManager.deleteIngredient(ingredientName); // Zutat aus der Datenbank entfernen
    }

    // Methode zum Überprüfen des Verfallsdatums
    public void checkIngredientExpiration() {
        List<Ingredient> ingredients = fridgeManager.getIngredients();  // Alle Zutaten abrufen
        for (Ingredient ingredient : ingredients) {
            if (ingredient.isExpired()) {
                // Fügen Sie hier Logik für abgelaufene Zutaten hinzu, z.B. Benachrichtigung
                System.out.println("Die Zutat '" + ingredient.getName() + "' ist abgelaufen.");
            }
        }
    }

    // Methode zum Abrufen aller Zutaten
    public List<Ingredient> getAllIngredients() {
        return fridgeManager.getIngredients();  // Alle Zutaten aus dem Kühlschrank abrufen
    }
}
