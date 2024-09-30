import java.util.ArrayList;
import java.util.List;

public class ExpirationObserver {
    
    private List<Ingredient> ingredients;  // Liste der überwachten Zutaten
    private NotificationService notificationService;  // Benachrichtigungsdienst

    // Konstruktor
    public ExpirationObserver(NotificationService notificationService) {
        this.ingredients = new ArrayList<>();
        this.notificationService = notificationService;
    }

    // Methode zum Hinzufügen einer Zutat zur Überwachung
    public void addIngredientToMonitor(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    // Methode zum Entfernen einer Zutat von der Überwachung
    public void removeIngredientFromMonitor(String ingredientName) {
        ingredients.removeIf(i -> i.getName().equalsIgnoreCase(ingredientName));
    }

    // Methode zur Überprüfung des Verfallsdatums aller überwachten Zutaten
    public void checkExpirationDates() {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.isExpired()) {
                notificationService.notifyExpiration(ingredient);
            } else {
                int daysUntilExpiration = DateUtils.daysUntilExpiryDate(ingredient.getExpirationDate());
                if (daysUntilExpiration <= 3) {  // Benachrichtige, wenn die Zutat in 3 Tagen abläuft
                    notificationService.notifyUpcomingExpiration(ingredient, daysUntilExpiration);
                }
            }
        }
    }

    // Methode zum Abrufen der überwachten Zutaten
    public List<Ingredient> getMonitoredIngredients() {
        return new ArrayList<>(ingredients);  // Rückgabe einer Kopie der Liste
    }
}
