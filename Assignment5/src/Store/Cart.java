package Store;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String, CartItem> cartItems;

    public Cart() {
        cartItems = new HashMap<>();
    }

    public Collection<CartItem> getCartItems() {
        return cartItems.values();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public void addProduct(Product product) {
        if (cartItems.containsKey(product.getID())) {
            CartItem existingItem = cartItems.get(product.getID());
            existingItem.setNum(existingItem.getNum() + 1);
        } else {
            CartItem newProduct = new CartItem(product, 1);
            cartItems.put(product.getID(), newProduct);
        }
    }

    public void updateProduct(String productId, int newNum) {
        if (cartItems.containsKey(productId)) {
            if (newNum <= 0) {
                cartItems.remove(productId);
                return;
            }

            cartItems.get(productId).setNum(newNum);
        }
    }

    public double totalCost() {
        double cost = 0.0;
        for (CartItem ci : cartItems.values()) {
            cost += ci.getCost();
        }
        return cost;
    }
}
