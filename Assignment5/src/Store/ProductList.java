package Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductList {
    private Map<String, Product> items;

    private void putItems() {
        String url = "jdbc:mysql://localhost:3306/hw3_db";
        String user = "root";
        String password = "Luka2006#";  //!!!!!!!!!!!!!!!!!

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM products";    //!!!!!!!!!!!!!!
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String id = result.getString("productid");
                String name = result.getString("name");
                String image = result.getString("imagefile");
                double price = result.getDouble("price");

                Product product = new Product(id, name, image, price);
                items.put(id, product);
            }

            result.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProductList() {
        items = new LinkedHashMap<>();
        putItems();
    }

    public Collection<Product> getAllItems() { return items.values(); }
    public Product getItem(String id) { return items.get(id); }
}
