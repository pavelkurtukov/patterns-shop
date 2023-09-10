import Catalog.Catalog;
import UserMenu.*;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        UserMenuBuilder menuBuilder;
        String userName = UserMenu.askString("Представьтесь, пожалуйста"); // Имя пользователя

        System.out.println("Привет, " + userName + "!");

        menuBuilder = new UserMenuBuilder();
        menuBuilder.setMenuName("Подменю");
        menuBuilder.addMenuItem("Написать \"Трям!\" и вернуться в главное меню!", () -> System.out.println("Трям!"), true);
        menuBuilder.addMenuItem("Второй пункт подменю", () -> System.out.println("Действие для второго пункта подменю"));
        menuBuilder.addMenuItem("Назад", null, true);
        UserMenu subMenu = menuBuilder.build();
        
        menuBuilder = new UserMenuBuilder();
        menuBuilder.setMenuName("Главное меню");
        menuBuilder.addMenuItem("Первый пункт меню", catalog::showCatalog);
        menuBuilder.addMenuItem("Второй пункт меню", catalog::showShoppingCart);
        menuBuilder.addMenuItem("Подменю", subMenu::show);
        menuBuilder.addMenuItem("Выход", () -> sayBye(userName), true);
        UserMenu mainMenu = menuBuilder.build();

        mainMenu.show();
    }

    static void sayBye(String userName) {
        System.out.println("До свидания, " + userName + "!");
    }
}
