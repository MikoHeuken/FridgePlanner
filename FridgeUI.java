import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class FridgeUI extends JFrame {
    private FridgeService fridgeService; // Dienstklasse für Kühlschrankoperationen

    private JTextArea textArea;           // Textbereich zur Anzeige von Zutaten
    private JTextField ingredientNameField; // Eingabefeld für Zutatennamen
    private JTextField ingredientAmountField; // Eingabefeld für die Menge der Zutat
    private JTextField ingredientUnitField; // Eingabefeld für die Einheit der Zutat
    private JTextField expirationDateField; // Eingabefeld für Ablaufdatum

    // Konstruktor
    public FridgeUI(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
        setTitle("Kühlschrankverwaltungstool");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Textbereich für die Anzeige von Zutaten
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Panel für die Eingabe von Zutaten
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        ingredientNameField = new JTextField(10);
        ingredientAmountField = new JTextField(5);
        ingredientUnitField = new JTextField(5);
        expirationDateField = new JTextField(10); // Erwartet Datumsformat "YYYY-MM-DD"
        JButton addButton = new JButton("Zutat hinzufügen");
        JButton removeButton = new JButton("Zutat entfernen");
        JButton showButton = new JButton("Zutaten anzeigen");
        JButton checkButton = new JButton("Verfallsdaten überprüfen");

        inputPanel.add(new JLabel("Zutat:"));
        inputPanel.add(ingredientNameField);
        inputPanel.add(new JLabel("Menge:"));
        inputPanel.add(ingredientAmountField);
        inputPanel.add(new JLabel("Einheit:"));
        inputPanel.add(ingredientUnitField);
        inputPanel.add(new JLabel("Ablaufdatum (YYYY-MM-DD):"));
        inputPanel.add(expirationDateField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(showButton);
        inputPanel.add(checkButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Action Listener für die Buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addIngredient();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeIngredient();
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showIngredients();
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkExpiration();
            }
        });
    }

    // Methode zum Hinzufügen einer Zutat
    private void addIngredient() {
        String name = ingredientNameField.getText();
        String amountText = ingredientAmountField.getText();
        String unit = ingredientUnitField.getText();
        String expirationDateString = expirationDateField.getText();

        // Eingabewerte überprüfen
        if (name.isEmpty() || amountText.isEmpty() || unit.isEmpty() || expirationDateString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte füllen Sie alle Felder aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText); // Menge in einen double umwandeln
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie eine gültige Menge ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date expirationDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            expirationDate = sdf.parse(expirationDateString); // Ablaufdatum parsen
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie ein gültiges Ablaufdatum ein (YYYY-MM-DD).", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Ingredient ingredient = new Ingredient(name, amount, unit, Instant.ofEpochMilli(expirationDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        fridgeService.addIngredient(ingredient); // Zutat über den Service hinzufügen
        JOptionPane.showMessageDialog(this, "Zutat hinzugefügt: " + name);
        ingredientNameField.setText("");         // Felder zurücksetzen
        ingredientAmountField.setText("");
        ingredientUnitField.setText("");
        expirationDateField.setText("");
    }

    // Methode zum Entfernen einer Zutat
    private void removeIngredient() {
        String name = ingredientNameField.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie den Namen der zu entfernenden Zutat ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        fridgeService.removeIngredient(name); // Zutat über den Service entfernen
        JOptionPane.showMessageDialog(this, "Zutat entfernt: " + name);
        ingredientNameField.setText("");        // Feld zurücksetzen
    }

    // Methode zum Anzeigen aller Zutaten
    private void showIngredients() {
        List<Ingredient> ingredients = fridgeService.getAllIngredients(); // Alle Zutaten abrufen
        textArea.setText(""); // Textbereich leeren

        if (ingredients.isEmpty()) {
            textArea.setText("Der Kühlschrank ist leer.");
        } else {
            StringBuilder sb = new StringBuilder("Aktuelle Zutaten im Kühlschrank:\n");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Ingredient ingredient : ingredients) {
                sb.append("- ").append(ingredient.getName())
                  .append(" (Menge: ").append(ingredient.getAmount()).append(" ")
                  .append(ingredient.getUnit()).append(", Ablaufdatum: ")
                  .append(sdf.format(ingredient.getExpirationDate())).append(")\n");
            }
            textArea.setText(sb.toString());
        }
    }

    // Methode zur Überprüfung der Verfallsdaten
    private void checkExpiration() {
        fridgeService.checkIngredientExpiration(); // Überprüfen der Verfallsdaten
        JOptionPane.showMessageDialog(this, "Verfallsdaten überprüft.");
    }

    // Hauptmethode zum Starten der Anwendung
    public static void main(String[] args) {
        // Erstellen von Beispieldaten für den DatabaseManager
        DatabaseManager databaseManager = new DatabaseManager("xxx","xxx","xxx");
        FridgeService fridgeService = new FridgeService(new FridgeManager(), new IngredientValidator(), databaseManager);
        FridgeUI fridgeUI = new FridgeUI(fridgeService);
        fridgeUI.setVisible(true);
    }
}
