package Store;

import UserMenu.*;

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

    public void addProduct(Product product, int count) throws AvailableProductCountExceededException, IllegalArgumentException {
        // Если товар уже добавлен в корзину - увеличиваем кол-во товара
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + count);
        } else {
            products.put(product, count);
        }
        // Резервируем товар на складе
        product.reserve(count);
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

            showShoppingCartMenu();
        } else {
            System.out.println("Корзина пуста");
        }
    }

    // Очистка корзины
    public void clearShoppingCart() {
        while (true) {
            String cleanConfirm = Asker.askString("Вы действительно хотите удалить все товары из корзины [Y/N]?");
            if (cleanConfirm.equalsIgnoreCase("y")) {
                for (Map.Entry<Product, Integer> productEntry : products.entrySet()) {
                    Product product = productEntry.getKey();
                    int count = productEntry.getValue();
                    try {
                        product.unreserve(count); // Возвращаем товар из резерва
                    } catch (AvailableUnreserveProductCountExceedException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                products.clear();
                System.out.println("Корзина очищена");
                return;
            }
            if (cleanConfirm.equalsIgnoreCase("n")) {
                return;
            }
        }
    }

    // Меню корзины
    public void showShoppingCartMenu() {
        UserMenuBuilder menuBuilder = new UserMenuBuilder();
        menuBuilder.setMenuName("Корзина");
        menuBuilder.addMenuItem("Очистить корзину", this::clearShoppingCart);
        //menuBuilder.addMenuItem("Удалить позицию", this::clearShoppingCart);
        menuBuilder.addMenuItem("Назад (в главное меню)", null, true);
        menuBuilder.build().show();
    }
}
