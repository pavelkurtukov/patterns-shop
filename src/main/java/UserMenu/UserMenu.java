package UserMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UserMenu {
    private static final String LINE = "====================";

    private final String menuName; // Наименование меню
    ArrayList<Action> actions; // Пункты меню с их функциональностью

    public UserMenu(String userName, ArrayList<Action> actionList) {
        this.menuName = userName;
        this.actions = actionList;
    }

    // Запрос строковой информации с вводом данных от пользователя
    public static String askString(String question) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(question);
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
        System.out.printf("\n%s:\n%s\n", menuName, LINE);
        // Выводим пункты меню на экран
        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("[%s] %s%n", i, actions.get(i).getActionName());
        }
        System.out.println(LINE);
        // Запрашиваем у пользователя выбор пункта меню
        int choose = askInt("Выберите пункт меню");
        // Выполняем действие, соответствующее выбранному пункту меню
        if (choose >= 0 && choose < actions.size()) {
            Action action = actions.get(choose);
            action.doAction();
            if (!action.getIsExit()) {
                show();
            }
        } else {
            System.out.println("Выбран несуществующий пункт меню. Попробуйте ещё раз...");
            show();
        }
    }
}
