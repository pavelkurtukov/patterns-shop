package UserMenu;

public class Action {
    private final String actionName;       // Наименование пункта меню
    private final Executable actionMethod; // Метод, соответствующий этому пункту меню
    private final boolean isExit;          // Если false - после выполнения метода заново отобразится меню

    Action(String actionName, Executable actionMethod, boolean isExit) {
        this.actionName = actionName;
        this.actionMethod = actionMethod;
        this.isExit = isExit;
    }

    String getActionName() {
        return actionName;
    }

    boolean getIsExit() {
        return isExit;
    }

    // Выполнение метода, привязанного к данному пункту меню
    void doAction() {
        if (actionMethod != null) {
            actionMethod.execute();
        }
    }
}
