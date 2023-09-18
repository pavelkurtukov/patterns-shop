package Store;

import UserMenu.*;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private static final int MIN_ORDER_SUM = 1000; // Минимальная сумма заказа
    private static ShoppingCart shoppingCart;
    private Map<Saleable, Integer> content = new HashMap<>();

    private ShoppingCart() { }

    public static ShoppingCart getInstance() {
        shoppingCart = (shoppingCart == null) ? new ShoppingCart() : shoppingCart;
        return shoppingCart;
    }

    public Map<Saleable, Integer> getContent() {
        return content;
    }

    public int getTotalSum() {
        int totalSum = 0;
        for (Map.Entry<Saleable, Integer> productEntry : content.entrySet()) {
            Saleable product = productEntry.getKey();
            int count = productEntry.getValue();
            totalSum += count * product.getPrice();
        }
        return totalSum;
    }

    // Добавление товара или услуги в корзину
    public void add(Saleable product, int count) throws AvailableProductCountExceededException, IllegalArgumentException {
        // Если товар уже добавлен в корзину - увеличиваем кол-во товара
        if (content.containsKey(product)) {
            content.put(product, content.get(product) + count);
        } else {
            content.put(product, count);
        }
        // Резервируем товар на складе
        product.reserve(count);
    }

    // Показать Корзину
    public void showShoppingCart() {
        showShoppingCartContent();
        showShoppingCartMenu();
    }

    // Очистка корзины
    public void clearShoppingCart() {
        while (true) {
            String cleanConfirm = Asker.askString("Вы действительно хотите удалить все товары из корзины [Y/N]?");
            if (cleanConfirm.equalsIgnoreCase("y")) {
                for (Map.Entry<Saleable, Integer> productEntry : content.entrySet()) {
                    Saleable product = productEntry.getKey();
                    int count = productEntry.getValue();
                    try {
                        product.unreserve(count); // Возвращаем товар из резерва
                    } catch (AvailableUnreserveProductCountExceedException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                content.clear();
                System.out.println("Корзина очищена");
                return;
            }
            if (cleanConfirm.equalsIgnoreCase("n")) {
                return;
            }
        }
    }

    // Отображение содержимого корзины
    public void showShoppingCartContent() {
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        Map<Saleable, Integer> categories = shoppingCart.getContent();
        if (!categories.isEmpty()) {
            int totalSum = 0;
            System.out.println("Содержимое корзины:");
            for (Map.Entry<Saleable, Integer> cartRow : categories.entrySet()) {
                Saleable product = cartRow.getKey();
                int cartCount = cartRow.getValue();
                System.out.printf("%d x %s\n", cartCount, product);
                totalSum += cartCount * product.getPrice();
            }
            System.out.println("Сумма к оплате: " + totalSum);
        } else {
            System.out.println("<Корзина пуста>");
        }
    }

    // Создание заказа
    public void makeOrder() {
        int totalSum = getTotalSum();
        if (totalSum < MIN_ORDER_SUM) {
            System.out.printf("Минимальная сумма заказа равна %d. Добавьте товаров или услуг ещё на %d\n", MIN_ORDER_SUM, MIN_ORDER_SUM - totalSum);
            return;
        }
        System.out.println("[Оформление заказа не реализовано]");
    }

    // Меню корзины
    public void showShoppingCartMenu() {
        UserMenuBuilder menuBuilder = new UserMenuBuilder();
        menuBuilder.setMenuName("Корзина");
        menuBuilder.addMenuItem("Показать состав корзины", this::showShoppingCartContent);
        menuBuilder.addMenuItem("Очистить корзину", this::clearShoppingCart);
        menuBuilder.addMenuItem("Оформить заказ", this::makeOrder);
        menuBuilder.addMenuItem("Назад (в главное меню)", null, true);
        menuBuilder.build().show();
    }
}
