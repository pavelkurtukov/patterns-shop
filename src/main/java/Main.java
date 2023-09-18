import Store.*;
import UserMenu.*;

public class Main {
    public static String userName;

    public static void main(String[] args) {
        Store store = Store.getInstance();

        StoreFiller.fill(store);

        userName = Asker.askString("Представьтесь, пожалуйста"); // Имя пользователя

        System.out.println("Привет, " + userName + "!");

        // Наполняем корзину (для время дебага)
        /*ShoppingCart cart = ShoppingCart.getInstance();
        try {
            cart.add(store.getProductByAtricul("item-003"), 1);
            cart.add(store.getProductByAtricul("item-005"), 1);
        } catch (AvailableProductCountExceededException e) {
            throw new RuntimeException(e);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }*/

        showMainMenu();
    }

    static void showMainMenu() {
        UserMenuBuilder menuBuilder;
        Store store = Store.getInstance();
        ShoppingCart shoppingCart = ShoppingCart.getInstance();

        menuBuilder = new UserMenuBuilder();
        menuBuilder.setMenuName("Главное меню");
        menuBuilder.addMenuItem("Каталог товаров", store::showProductCatalog);
        menuBuilder.addMenuItem("Каталог услуг", store::showServices);
        menuBuilder.addMenuItem("Корзина", shoppingCart::showShoppingCart);
        menuBuilder.addMenuItem("Выход", () -> sayBye(userName), true);
        UserMenu mainMenu = menuBuilder.build();

        mainMenu.show();
    }

    static void sayBye(String userName) {
        System.out.println("До свидания, " + userName + "!");
    }
}
