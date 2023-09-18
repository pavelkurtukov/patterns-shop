package Store;

import UserMenu.*;

import java.util.*;

public class Store {
    private static Store store;
    private List<Category> productCategories = new ArrayList<>();
    private List<Service> services = new ArrayList<>();

    private Store() { }

    public static Store getInstance() {
        store = (store == null) ? new Store() : store;
        return store;
    }

    public List<Category> getProductCategories() {
        return productCategories;
    }

    public List<Service> getServices() {
        return services;
    }

    public void addCategory(Category category) {
        productCategories.add(category);
    }

    public void addService(Service service) {
        services.add(service);
    }

    // Показать все товары из каталога
    public void showProductCatalog() {
        UserMenuBuilder menuBuilder = new UserMenuBuilder();
        menuBuilder.setMenuName("Категории");
        // Для каждой категории добавляем пункт в меню
        for (Category category : productCategories) {
            menuBuilder.addMenuItem(category.getName(), () -> showProductCategory(category));
        }
        menuBuilder.addMenuItem("Сделать заказ по артикулу", this::addProductToCart);
        menuBuilder.addMenuItem("Назад (в главное меню)", null, true);
        UserMenu categoryMenu = menuBuilder.build();
        categoryMenu.show();
    }

    // Показать все товары из каталога
    public void showServices() {
        showServiceList();

        UserMenuBuilder menuBuilder = new UserMenuBuilder();
        menuBuilder.setMenuName("Услуги");
        menuBuilder.addMenuItem("Показать список услуг", this::showServiceList);
        menuBuilder.addMenuItem("Добавить услугу по артикулу", this::addServiceToCart);
        menuBuilder.addMenuItem("Назад (в главное меню)", null, true);
        UserMenu categoryMenu = menuBuilder.build();
        categoryMenu.show();
    }

    public void showServiceList() {
        System.out.println("Список услуг:");

        for (Service service : services) {
            System.out.println(service);
        }
    }

    public void addProductToCart() {
        String articul = Asker.askString("Укажите арикул товара, который хотите добавить в корзину");

        // Ищем товар и выводим информацию о нём
        Product product;
        try {
            product = getProductByAtricul(articul);
            System.out.println("Вы добавляете товар: " + product.getName());
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
            ShoppingCart.getInstance().add(product, count);
            System.out.printf("Товар добавлен в корзину:  %s\n", product);
            //System.out.println("Возвращаемся в главное меню...");
        } catch (AvailableProductCountExceededException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addServiceToCart() {
        String articul = Asker.askString("Укажите арикул услуги, которую хотите добавить в корзину");

        // Ищем услугу и выводим информацию о ней
        Service service;
        try {
            service = getServiceByAtricul(articul);
            System.out.println("Вы добавляете услугу: " + service.getName());
            System.out.println("Артикул: " + service.getArticul());
            System.out.println("Стоимость: " + service.getPrice() + "\n");
        } catch (ProductNotFoundException e) {
            System.out.println("К сожалению, услуга с артикулом " + articul + " не найдена!");
            return;
        }

        // Добавялем услугу в корзину
        while (true) {
            String addConfirm = Asker.askString("Добавить [Y/N]?");
            if (addConfirm.equalsIgnoreCase("n")) {
                return;
            }
            if (addConfirm.equalsIgnoreCase("y")) {
                try {
                    ShoppingCart.getInstance().add(service, 1);
                    System.out.printf("Услуга \"%s\" добавлена в корзину\n", service.getName());
                } catch (AvailableProductCountExceededException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                return;
            }
        }
    }

    // Показать список товаров в категории
    public void showProductCategory(Category category) {
        System.out.println("Список товаров категории \"" + category.getName() + "\":");
        for (Product product : category.getProducts()) {
            System.out.println(product);
        }
    }

    // Поиск товара по артикулу
    public Product getProductByAtricul(String articul) throws ProductNotFoundException {
        // Ищем продукт, перебирая все коллекции
        for (Category category : productCategories) {
            for (Product product : category.getProducts()) {
                if (product.getArticul().equalsIgnoreCase(articul)) {
                    return product;
                }
            }
        }
        // Если дошли до сюда, значит, не нашли продукт с таким артикулом
        throw new ProductNotFoundException();
    }

    // Поиск услуги по артикулу
    public Service getServiceByAtricul(String articul) throws ProductNotFoundException {
        // Ищем продукт, перебирая все коллекции
        for (Service service : services) {
            if (service.getArticul().equalsIgnoreCase(articul)) {
                return service;
            }
        }
        // Если дошли до сюда, значит, не нашли продукт с таким артикулом
        throw new ProductNotFoundException();
    }
}
