package Store;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private static ShoppingCart shoppingCart;
    private Map<Product, Integer> products = new HashMap<>();

    private ShoppingCart() { }

    public static ShoppingCart getInstance() {
        shoppingCart = (shoppingCart == null) ? new ShoppingCart() : shoppingCart;
        return shoppingCart;
    }

    public Map<Product, Integer> getCategories() {
        return products;
    }

    public void addProduct(Product product, int count) throws AvailableProductCountExceededException {
        product.reserve(count);
        products.put(product, count);
    }

    // Показать Корзину
    public void showShoppingCart() {
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        Map<Product, Integer> categories = shoppingCart.getCategories();
        if (!categories.isEmpty()) {
            int totalSum = 0;
            System.out.println("Содержимое корзины:");
            for (Map.Entry<Product, Integer> cartRow : categories.entrySet()) {
                Product product = cartRow.getKey();
                int cartCount = cartRow.getValue();
                System.out.printf("%d x %s\n", cartCount, product);
                totalSum += cartCount * product.getPrice();
            }
            System.out.println("Сумма к оплате: " + totalSum);
        } else {
            System.out.println("Корзина пуста");
        }
    }
}
