package UserMenu;

import java.util.ArrayList;

public class UserMenuBuilder {
    private String menuName; // Наименование меню
    ArrayList<Action> actions = new ArrayList<>(); // Пункты меню с их функциональностью

    public UserMenuBuilder setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public UserMenuBuilder addMenuItem(Action action) {
        actions.add(action);
        return this;
    }

    public UserMenuBuilder addMenuItem(String actionName, Executable actionMethod, boolean isExit) {
        actions.add(new Action(actionName, actionMethod, isExit));
        return this;
    }

    public UserMenuBuilder addMenuItem(String actionName, Executable actionMethod) {
        return addMenuItem(actionName, actionMethod, false);
    }

    public UserMenu build(){
        boolean hasExit = false;

        if (this.menuName == null) {
            throw new IllegalArgumentException("Должно быть указано название меню!");
        }

        if (this.actions.size() == 0) {
            throw new IllegalArgumentException("Должен быть задан хотя бы один пункт меню!");
        }

        for (Action a : actions) {
           if (a.getIsExit()) {
               hasExit = true;
               break;
           }
        }

        if (!hasExit) throw new IllegalArgumentException("Меню должно содержать выход!");

        return new UserMenu(menuName, actions);
    }
}
