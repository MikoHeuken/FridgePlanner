import java.util.List;

public class NotificationService {
    private final FridgeManager fridgeManager; // Verweis auf den FridgeManager

    // Konstruktor
    public NotificationService(FridgeManager fridgeManager) {
        this.fridgeManager = fridgeManager;
    }

    // Methode zur Überprüfung des Verfallsdatums und Benachrichtigung
    public void checkExpirationDates() {
        List<Ingredient> ingredients = fridgeManager.getIngredients(); // Zutaten aus dem Kühlschrank abrufen
        for (Ingredient ingredient : ingredients) {
            if (ingredient.isExpired()) { // Überprüfen, ob die Zutat abgelaufen ist
                String message = "Die Zutat \"" + ingredient.getName() + "\" ist abgelaufen!";
                sendNotification(message); // Benachrichtigung senden
            }
        }
    }

    // Methode zur Benachrichtigung über eine neue Zutat
    public void notifyNewIngredient(Ingredient ingredient) {
        String message = "Neue Zutat hinzugefügt: " + ingredient.getName() + " (" + ingredient.getAmount() + " " + ingredient.getUnit() + ")";
        sendNotification(message); // Benachrichtigung senden
    }

    // Methode zur Benachrichtigung einer abgelaufenen Zutat
    public void notifyExpiration(Ingredient ingredient) {
        String message = "Das Produkt ist abgelaufen: " + ingredient.getName();
        sendNotification(message); // Benachrichtigung senden
    }

    // Methode zur Benachrichtigung, dass eine Zutat demnächst abläuft
    public void notifyUpcomingExpiration(Ingredient ingredient, int daysUntilExpiration) {
        String message = "Das Produkt läuft demnächst ab: " + ingredient.getName();
        sendNotification(message); // Benachrichtigung senden
    }

    public void sendNotification(String message){
      //todo
    }
}
