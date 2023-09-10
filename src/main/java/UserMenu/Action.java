package UserMenu;

import java.util.concurrent.Callable;

public class Action {
    private final String actionName; // Наименование пукта меню
    private final Executable actionMethod; // Метод, соответствующий этому пункту меню

    public Action(String actionName, Executable actionMethod) {
        this.actionName = actionName;
        this.actionMethod = actionMethod;
    }

    public String getActionName() {
        return actionName;
    }

    void doAction() {
        actionMethod.execute();
    }
}
