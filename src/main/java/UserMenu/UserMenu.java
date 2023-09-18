package UserMenu;

import java.util.ArrayList;

public class UserMenu {
    private static final String LINE = "====================";

    private final String menuName; // Наименование меню
    ArrayList<Action> actions; // Пункты меню с их функциональностью

    public UserMenu(String userName, ArrayList<Action> actionList) {
        this.menuName = userName;
        this.actions = actionList;
    }

    // Отрисовка меню с запросом выбора одного из пунктов (по номеру)
    public void show() {
        System.out.printf("\n%s\n%s\n", menuName, LINE);
        // Выводим пункты меню на экран
        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("[%s] %s%n", i, actions.get(i).getActionName());
        }
        System.out.println(LINE);
        // Запрашиваем у пользователя выбор пункта меню
        int choose = Asker.askInt("Выберите пункт меню");
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
