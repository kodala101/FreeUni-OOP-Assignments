package Store;

public class Product {
    private String productID;
    private String name;
    private String image;
    private double price;

    public Product(String productID, String name, String image, double price) {
        this.productID = productID;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getID() { return productID; }
    public String getName() { return name; }
    public String getImg() { return image; }
    public double getPrice() { return price; }
}
