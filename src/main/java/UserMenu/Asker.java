package UserMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Asker {

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
}
