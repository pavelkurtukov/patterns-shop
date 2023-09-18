package Store;

import UserMenu.*;

import java.util.*;

public class Store {
    private static Store store;
    private List<Category> categories = new ArrayList<>();

    private Store() { }

    public static Store getInstance() {
        store = (store == null) ? new Store() : store;
        return store;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    // Показать все товары из каталога
    public void showCatalog() {
        System.out.println("Выберите категорию:");

        UserMenuBuilder menuBuilder = new UserMenuBuilder();
        menuBuilder.setMenuName("Список категорий");
        // Для каждой категории добавляем пункт в меню
        for (Category category : categories) {
            menuBuilder.addMenuItem(category.getName(), () -> showCategory(category));
        }
        menuBuilder.addMenuItem("Сделать заказ по артикулу", this::addProductToCart);
        menuBuilder.addMenuItem("Назад (в главное меню)", null, true);
        UserMenu categoryMenu = menuBuilder.build();
        categoryMenu.show();
    }

    public void addProductToCart() {
        String articul = Asker.askString("Укажите арикул товара, который хотите добавить в корзину");

        // Ищем товар и выводим информацию о нём
        Product product;
        try {
            product = getProductByAtricul(articul);
            System.out.println("Вы добавляете: " + product.getName());
            System.out.println("Артикул: " + product.getArticul());
            System.out.println("Цена: " + product.getPrice());
            System.out.println("В наличии: " + product.getAvailable() + " шт.");
        } catch (ProductNotFoundException e) {
            System.out.println("К сожалению, товар с артикулом " + articul + " не найден!");
            return;
        }

        // Добавялем товар в корзину и резервируем его в магазине
        try {
            int count = Asker.askInt("\nУкажите кол-во товара, которое хотите добавить в корзину");
            ShoppingCart.getInstance().addProduct(product, count);
            System.out.printf("Товар добавлен в корзину:  %s\n", product);
            System.out.println("Возвращаемся в главное меню...");
        } catch (AvailableProductCountExceededException e) {
            System.out.println(e.getMessage());
        }
    }

    // Показать список товаров в категории
    public void showCategory(Category category) {
        System.out.println("Список товаров категории \"" + category.getName() + "\":");
        for (Product product : category.getProducts()) {
            System.out.println(product);
        }
    }

    // Поиск товара по артикулу
    public Product getProductByAtricul(String articul) throws ProductNotFoundException {
        // Ищем продукт, перебирая все коллекции
        for (Category category : categories) {
            for (Product product : category.getProducts()) {
                if (product.getArticul().equalsIgnoreCase(articul)) {
                    return product;
                }
            }
        }
        // Если дошли до сюда, значит, не нашли продукт с таким артикулом
        throw new ProductNotFoundException();
    }

    // Сделать заказ
    /*public void showOrders() {
        System.out.println("Делаем заказ...");
        //his.show();
    }*/
}
