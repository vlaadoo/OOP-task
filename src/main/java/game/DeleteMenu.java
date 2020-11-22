package game;

import persistence.DataBase;

import java.sql.SQLException;
import java.util.Scanner;

import static game.Colors.*;

public class DeleteMenu {
    public static void deleteMenu() throws SQLException {
        Scanner del_menu = new Scanner(System.in);
        DataBase db = new DataBase();

        System.out.println(BG_BLUE + F_BLACK + " Что вы хотите удалить? " +
                F_RESET + "\n" +
                "1. Определенную строчку из таблицы. \n" +
                "2. Всю таблицу целиком. \n" +
                "3. Вернуться назад."
        );

        int ch = del_menu.nextInt();
        switch (ch) {
            case 1:
                db.getAllGames();
                System.out.print(BG_BLACK + " Какую строку удалить? " + F_RESET + " ");
                int row_id = del_menu.nextInt();
                db.deleteFromDb(row_id);
                break;

            case 2:
                db.clearTable();
                System.out.println("Таблица очищена!");
                break;

            case 3:

                return;
            default:
                System.out.println(BG_RED + F_WHITE + " Выбран неправильный пункт меню! " + F_RESET);
                deleteMenu();
                break;
        }
    }
}

