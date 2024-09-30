import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RecipeUI extends JFrame {
    private final RecipeService recipeService; // Verweis auf RecipeService

    private JTextField recipeNameField; // Eingabefeld für den Rezeptnamen
    private JTextArea ingredientsArea; // Textbereich für Zutaten
    private JTextArea outputArea; // Textbereich für Ausgaben

    // Konstruktor
    public RecipeUI(RecipeService recipeService) {
        this.recipeService = recipeService; // Zuweisen des RecipeService
        setupUI(); // Benutzeroberfläche einrichten
    }

    // Methode zum Einrichten der Benutzeroberfläche
    private void setupUI() {
        setTitle("Rezeptverwaltung");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Eingabefelder für Rezeptnamen und Zutaten
        recipeNameField = new JTextField();
        ingredientsArea = new JTextArea(5, 20);
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        // Buttons für verschiedene Aktionen
        JButton addButton = new JButton("Rezept Hinzufügen");
        JButton removeButton = new JButton("Rezept Entfernen");
        JButton showButton = new JButton("Alle Rezepte Anzeigen");

        // Panel für die Eingabe
        JPanel inputPanel = new JPanel(new GridLayout(3, 1));
        inputPanel.add(new JLabel("Rezeptname:"));
        inputPanel.add(recipeNameField);
        inputPanel.add(new JLabel("Zutaten (Name, Menge, Einheit, jeweils in neuer Zeile):"));
        inputPanel.add(new JScrollPane(ingredientsArea));

        // Panel für Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(showButton);

        // Hinzufügen der Panels zur Frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        // Listener für die Buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecipe();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeRecipe();
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllRecipes();
            }
        });

        setVisible(true); // Fenster sichtbar machen
    }

    // Methode zum Hinzufügen eines Rezepts
    private void addRecipe() {
        String name = recipeNameField.getText();
        String[] ingredientsText = ingredientsArea.getText().split("\n");
        Recipe recipe = new Recipe(name);

        for (String ingredientText : ingredientsText) {
            String[] parts = ingredientText.split(","); // Annahme: Name, Menge, Einheit
            if (parts.length == 3) {
                String ingredientName = parts[0].trim();
                double amount = Double.parseDouble(parts[1].trim());
                String unit = parts[2].trim();
                Ingredient ingredient = new Ingredient(ingredientName, amount, unit, DateUtils.getCurrentDate());
                recipe.addIngredient(ingredient);
            }
        }

        recipeService.addRecipe(recipe); // Rezept über den RecipeService hinzufügen
        outputArea.append("Rezept \"" + name + "\" wurde hinzugefügt.\n");
    }

    // Methode zum Entfernen eines Rezepts
    private void removeRecipe() {
        String name = recipeNameField.getText();
        List<Recipe> recipes = recipeService.getAllRecipes();
        Recipe recipeToRemove = null;

        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                recipeToRemove = recipe;
                break;
            }
        }

        if (recipeToRemove != null) {
            recipeService.removeRecipe(recipeToRemove); // Rezept entfernen
            outputArea.append("Rezept \"" + name + "\" wurde entfernt.\n");
        } else {
            outputArea.append("Rezept \"" + name + "\" nicht gefunden.\n");
        }
    }

    // Methode zum Anzeigen aller Rezepte
    private void showAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        outputArea.setText("Alle Rezepte:\n");
        for (Recipe recipe : recipes) {
            outputArea.append("- " + recipe.getName() + "\n");
        }
    }

    // Main-Methode zur Ausführung der Anwendung
    public static void main(String[] args) {
        // Beispiel für RecipeService und FridgeManager
        FridgeManager fridgeManager = new FridgeManager();
        RecipeManager recipeManager = new RecipeManager(fridgeManager);
        RecipeService recipeService = new RecipeService(recipeManager);

        // Start der Benutzeroberfläche
        new RecipeUI(recipeService);
    }
}
