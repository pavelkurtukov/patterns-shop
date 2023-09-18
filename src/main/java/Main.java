import Store.*;
import UserMenu.*;

public class Main {
    public static String userName;

    public static void main(String[] args) {
        //UserMenuBuilder menuBuilder;
        Store store = Store.getInstance();

        StoreFiller.fill(store);

        userName = Asker.askString("Представьтесь, пожалуйста"); // Имя пользователя

        System.out.println("Привет, " + userName + "!");

        /*menuBuilder = new UserMenuBuilder();
        menuBuilder.setMenuName("Подменю");
        menuBuilder.addMenuItem("Написать \"Трям!\" и вернуться в главное меню!", () -> System.out.println("Трям!"), true);
        menuBuilder.addMenuItem("Второй пункт подменю", () -> System.out.println("Действие для второго пункта подменю"));
        menuBuilder.addMenuItem("Назад", null, true);
        UserMenu subMenu = menuBuilder.build();*/

        // Главное меню магазина
        /*menuBuilder = new UserMenuBuilder();
        menuBuilder.setMenuName("Главное меню");
        menuBuilder.addMenuItem("Каталог товаров", store::showCatalog);
        menuBuilder.addMenuItem("Корзина", store::showShoppingCart);
        //menuBuilder.addMenuItem("Мои заказы", store::showOrders);
        //menuBuilder.addMenuItem("Подменю", subMenu::show);
        menuBuilder.addMenuItem("Выход", () -> sayBye(userName), true);
        UserMenu mainMenu = menuBuilder.build();

        mainMenu.show();*/
        showMainMenu();
    }

    static void showMainMenu() {
        UserMenuBuilder menuBuilder;
        Store store = Store.getInstance();
        ShoppingCart shoppingCart = ShoppingCart.getInstance();

        menuBuilder = new UserMenuBuilder();
        menuBuilder.setMenuName("Главное меню");
        menuBuilder.addMenuItem("Каталог товаров", store::showCatalog);
        menuBuilder.addMenuItem("Корзина", shoppingCart::showShoppingCart);
        //menuBuilder.addMenuItem("Мои заказы", store::showOrders);
        //menuBuilder.addMenuItem("Подменю", subMenu::show);
        menuBuilder.addMenuItem("Выход", () -> sayBye(userName), true);
        UserMenu mainMenu = menuBuilder.build();

        mainMenu.show();
    }

    static void sayBye(String userName) {
        System.out.println("До свидания, " + userName + "!");
    }
}
