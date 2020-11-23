package game;

import persistence.DataBase;
import pieces.*;

import static game.Colors.*;
import static game.DeleteMenu.deleteMenu;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StartMenu {
    public static void startMenu() throws SQLException {

        Chessboard myChessboard = new Chessboard();
        DataBase db = new DataBase();
        Scanner menu_input = new Scanner(System.in);
        List<String> results;

        // Стартовое меню
        System.out.println(BG_GREEN + F_BLACK +
                " Выберите пункт меню: " +
                F_RESET + "\n" +
                "1. Играть.\n" +
                "2. Посмотреть историю игр.\n" +
                "3. Удалить данные из таблицы. \n" +
                "4. Завершить программу.");
        System.out.print(F_BLUE + " Введите выбранный пункт меню: " + F_RESET);
        int choice = menu_input.nextInt();


        switch (choice) {
            case 1:
                System.out.println(BG_CYAN + F_BLACK + " Начинается игра... " + F_RESET);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (myChessboard.getGameRunning()) {
                    myChessboard.printBoard();
                    myChessboard.move();
                }
                break;

            case 2:
                System.out.println(BG_BLUE + " Результаты игр: " + F_RESET);
                results = db.getAllGames();
                if (results.size() == 0) {
                    System.out.println("В таблице нет игр");
                }
                for (String result : results) {
                    System.out.println(result);
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case 3:
                deleteMenu();
                break;

            case 4:
                System.out.println(F_YELLOW + "Завершение программы...");
                System.exit(0);

            case 4035:
                Colors.printColors();
                break;

            default:
                System.out.println(BG_RED + F_WHITE + " Выбран неправильный пункт меню! " + F_RESET + "\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startMenu();
    }
}