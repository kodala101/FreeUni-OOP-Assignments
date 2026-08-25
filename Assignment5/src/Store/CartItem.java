package Store;

public class CartItem {
    private Product product;
    private int num;

    public CartItem(Product product, int num) {
        this.product = product;
        this.num = num;
    }

    public Product getProduct() { return product; }
    public int getNum() { return num; }
    public void setNum(int num) { this.num = num; }
    public double getCost() { return product.getPrice() * num; }
}
