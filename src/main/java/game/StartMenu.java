package game;

import persistence.DataBase;
import pieces.*;

import java.sql.SQLException;
import java.util.Scanner;

public class StartMenu {
    public static void StartMenu() throws SQLException {
        Scanner menu_input = new Scanner(System.in);
        System.out.println("Выберите пункт меню:\n" +
                "1. Играть.\n" +
                "2. Посмотреть историю игр.\n" +
                "3. \n" +
                "4. \n");
        int choice = menu_input.nextInt();


        switch (choice) {
            case 1:
                System.out.println("Начинается игра...");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Chessboard myChessboard = new Chessboard();
                while (myChessboard.getGameRunning()) {
                    myChessboard.printBoard();
                    myChessboard.move();
                }
            case 2:
                DataBase db = new DataBase();
                db.getAllGames();
                break;
            case 3:

                break;
            case 4:

                break;

        }
    }
}
