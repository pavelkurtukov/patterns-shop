package UserMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UserMenu {
    private static final String LINE = "====================";

    private final String userName; // Имя пользователя
    ArrayList<Action> actions = new ArrayList<>(); // Пункты меню с их функциональностью

    public UserMenu() {
        this.userName = askString("Представьтесь, пожалуйста");
        System.out.println("Привет, " + this.userName + "!");

        // Наполняем меню пунктами с их функционалом
        actions.add(new Action("Каталог товаров", this::showCatalog));
        actions.add(new Action("Корзина", this::showShoppingCart));
        actions.add(new Action("Оформить заказ", this::showOrder));
        actions.add(new Action("Выход", () -> System.out.println("До свидания, " + getUserName() + "!")));

        //actions.add(new Action("Повторный показ меню", () -> System.out.println(13123)));
        //actions.add(new Action("Вывод имени пользователя", this::printUserName));
        //actions.add(new Action("Пустой пункт меню", null));
    }

    public String getUserName() {
        return userName;
    }

    // Запрос строковой информации с вводом данных от пользователя
    public static String askString(String question) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(question + ": ");
        try {
            String answer = reader.readLine();
            System.out.println();
            return answer;
        } catch (IOException e) {
            System.out.println("Ошибка ввода! Попробуйте ещё раз...");
            return askString(question);
        }
    }

    // Запрос числовой информации с вводом данных от пользователя
    public static int askInt(String question) {
        try {
            return Integer.parseInt(askString(question));
        } catch (NumberFormatException e) {
            System.out.println("Выбран некорректный пункт меню. Попробуйте ещё раз...\n");
            return askInt(question);
        }

    }

    // Отрисовка меню с запросом выбора одного из пунктов (по номеру)
    public void show() {
        System.out.println("\nМеню:\n" + LINE);
        // Выводим пункты меню на экран
        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("[%s] %s%n", i, actions.get(i).getActionName());
        }
        System.out.println(LINE);
        // Запрашиваем у пользователя выбор пункта меню
        int choose = askInt("Выберите пункт меню");
        // Выполняем действие, соответствующее выбранному пункту меню
        if (choose >= 0 && choose < actions.size()) {
            actions.get(choose).doAction();
        } else {
            System.out.println("Выбран несуществующий пункт меню. Попробуйте ещё раз...");
            show();
        }
    }

    // Показать все товары из каталога
    void showCatalog() {
        System.out.println("Тут отрисовывается список товаров");
        this.show();
    }

    // Показать Корзину
    void showShoppingCart() {
        System.out.println("Показываем содержимое корзины");
        this.show();
    }

    // Сделать заказ
    void showOrder() {
        System.out.println("Делаем заказ...");
        this.show();
    }
}
