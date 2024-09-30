import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    
    private String url;       // Datenbank-URL
    private String user;      // Benutzername
    private String password;  // Passwort

    // Konstruktor
    public DatabaseManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    // Methode zur Verbindung mit der Datenbank
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Methode zum Hinzufügen einer Zutat zur Datenbank
    public void addIngredient(Ingredient ingredient) {
        String sql = "INSERT INTO ingredients (name, amount, unit, expiration_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ingredient.getName());
            pstmt.setDouble(2, ingredient.getAmount());
            pstmt.setString(3, ingredient.getUnit());
            pstmt.setDate(4, Date.valueOf(ingredient.getExpirationDate()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Abrufen aller Zutaten aus der Datenbank
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM ingredients";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                double amount = rs.getDouble("amount");
                String unit = rs.getString("unit");
                LocalDate expirationDate = rs.getDate("expiration_date").toLocalDate();
                ingredients.add(new Ingredient(name, amount, unit, expirationDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    // Methode zum Aktualisieren einer Zutat in der Datenbank
    public void updateIngredient(Ingredient ingredient) {
        String sql = "UPDATE ingredients SET amount = ?, unit = ?, expiration_date = ? WHERE name = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, ingredient.getAmount());
            pstmt.setString(2, ingredient.getUnit());
            pstmt.setDate(3, Date.valueOf(ingredient.getExpirationDate()));
            pstmt.setString(4, ingredient.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Löschen einer Zutat aus der Datenbank
    public void deleteIngredient(String ingredientName) {
        String sql = "DELETE FROM ingredients WHERE name = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ingredientName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Hinzufügen eines Rezepts zur Datenbank
    public void addRecipe(Recipe recipe) {
        String sql = "INSERT INTO recipes (name) VALUES (?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, recipe.getName());
            pstmt.executeUpdate();
            // Hier könnte eine zusätzliche Logik zum Speichern der Zutaten für das Rezept hinzugefügt werden
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Abrufen aller Rezepte aus der Datenbank
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM recipes";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                recipes.add(new Recipe(name));
                // Hier könnte eine zusätzliche Logik zum Abrufen der Zutaten für jedes Rezept hinzugefügt werden
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
