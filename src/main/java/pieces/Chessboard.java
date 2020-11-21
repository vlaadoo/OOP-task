package pieces;

import persistence.DataBase;

import java.sql.SQLException;
import java.util.Scanner;


public class Chessboard {

    private Boolean gameRunning;
    // Задается массив, который выступает шахматной доской
    private AbstractPiece[][] chessboard = new AbstractPiece[numOfRowsAndCols][numOfRowsAndCols];
    Scanner user_input = new Scanner(System.in);
    private static final int numOfRowsAndCols = 8;
    private static int srcRow, srcCol, destRow, destCol;
    private static int whiteScore = 0, blackScore = 0;
    private static Boolean whitesTurnToMove;
    // Устанавливается значение true если шаг неправильный. Просит повторный ввод шага в move()
    private static Boolean invalidMove = false;
    // Строка с командой передвижения от пользователя
    String move;
    private DataBase database = new DataBase();
    private static String delimeterLine = "———————————————————————————————————————————————————\n";

    // Используется для изменения цвета текста при выводе
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLACK = "\u001B[37m";
    // Используется для цвета фона при выводе
    public static final String ANSI_WHITEBG = "\u001B[40m";
    public static final String ANSI_REDBG = "\u001B[41m";
    public static final String ANSI_GREENBG = "\u001B[42m";
    public static final String ANSI_YELLOWBG = "\u001B[43m";
    public static final String ANSI_BLUEBG = "\u001B[44m";
    public static final String ANSI_PURPLEBG = "\u001B[45m";
    public static final String ANSI_CYANBG = "\u001B[46m";
    public static final String ANSI_BLACKBG = "\u001B[47m";

    /*
     * Создается шахматная доска и заполняется фигурами
     * Также запускает шахматную партию через gameRunning = true
     */
    public Chessboard() {
        initialiseBoard(chessboard);
        gameRunning = true;
    }


    public Boolean getGameRunning() {
        return this.gameRunning;
    }


    private static void initialiseBoard(AbstractPiece[][] chessboard) {
        // шахматаная доска с фигурами из матрицы 8x8
        // на рядах [0] и [1] черные фигуры
        // на рядах [6] и [7] белые фигуры

        for (int row = 0; row < chessboard.length; row++) {
            for (int col = 0; col < chessboard[row].length; col++) {
                if (row == 0) {
                    switch (col) {
                        case 0:
                            chessboard[row][col] = new Rook(false);
                            break;
                        case 1:
                            chessboard[row][col] = new Knight(false);
                            break;
                        case 2:
                            chessboard[row][col] = new Bishop(false);
                            break;
                        case 3:
                            chessboard[row][col] = new Queen(false);
                            break;
                        case 4:
                            chessboard[row][col] = new King(false);
                            break;
                        case 5:
                            chessboard[row][col] = new Bishop(false);
                            break;
                        case 6:
                            chessboard[row][col] = new Knight(false);
                            break;
                        case 7:
                            chessboard[row][col] = new Rook(false);
                            break;
                    }
                } else if (row == 1) {
                    chessboard[row][col] = new Pawn(false);
                } else if (row == 6) {
                    chessboard[row][col] = new Pawn(true);
                } else if (row == 7) {
                    switch (col) {
                        case 0:
                            chessboard[row][col] = new Rook(true);
                            break;
                        case 1:
                            chessboard[row][col] = new Knight(true);
                            break;
                        case 2:
                            chessboard[row][col] = new Bishop(true);
                            break;
                        case 3:
                            chessboard[row][col] = new Queen(true);
                            break;
                        case 4:
                            chessboard[row][col] = new King(true);
                            break;
                        case 5:
                            chessboard[row][col] = new Bishop(true);
                            break;
                        case 6:
                            chessboard[row][col] = new Knight(true);
                            break;
                        case 7:
                            chessboard[row][col] = new Rook(true);
                            break;
                    }
                } else {
                    chessboard[row][col] = null;
                }
            }
        }

        // Назначает первый ход белым
        whitesTurnToMove = Boolean.TRUE;

    }

    /*
     * Печатает цифры 1-8 рядом со рядами и буквы a-h по столбцам
     * Выводит Юникод для каждой фигуры в консоль с помощью метода draw()
     * из класса соответсвующей фигуры.
     */
    public void printBoard() {

        // Берет массив 8x8 в качестве шахматной доски

        System.out.println("\ta\tb\tc\td\te\tf\tg\th");
        for (int row = 0; row < chessboard.length; row++) {
            System.out.print(8 - row + ".\t");
            for (int col = 0; col < chessboard[row].length; col++) {
                if (chessboard[row][col] != null) {
                    chessboard[row][col].draw();
                    System.out.print("\t");

                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    /*
     * Допустимость хода проверяется в 2 этапа:
     * 1) Общие правила, которым должна следовать каждая фигура
     * 2) Специалньый isMoveValid() метод из класса фигуры, который проверяет правила
     * движения определенной фигуры
     */

    private boolean moveValid() {

        // Выдает ошибку, если ход начинается и/или заканчивается за границами доски

        if (srcRow < 0 || srcRow > 7 || srcCol < 0 || srcCol > 7 || destRow < 0
                || destRow > 7 || destCol < 0 || destCol > 7) {
            System.err.println("Ход начинается/заканчивается за границами доски!");
            return false;
        }

        // Проверка на то, что доска не пустая
        if (chessboard[srcRow][srcCol] == null) {
            System.err.println("Доска пустая!");
            return false;
        }

        // Проверка на выбор фигуры (чтобы белые не ходили черными фигурами
        if ((chessboard[srcRow][srcCol].isWhite && !whitesTurnToMove)
                || (!chessboard[srcRow][srcCol].isWhite && whitesTurnToMove)) {
            System.err.println("Ходит другой цвет фигур!");
            return false;
        }

        // Возвращает false, если заданное передвижение не соотвествует фигуре
        if (!chessboard[srcRow][srcCol].isMoveValid(srcRow, srcCol, destRow,
                destCol)) {
            System.err.println("Данная фигура не может ходить таким образом!");
            return false;
        }

        // Останавливает проверку, чтобы isWhite() не проверялся
        // на пустой доске
        if (chessboard[destRow][destCol] == null) {
            return true;
        }

        // Проверка движения на то, чтобы белая фигура не поедала и
        // не проходила сквозь другую белую фигуру
        if (chessboard[srcRow][srcCol].isWhite
                && chessboard[destRow][destCol].isWhite) {
            System.err.println("Белая фигура не могут заканчивать ход на другой белой фигуре!");
            return false;
        }

        // Проверка движения на то, чтобы черная фигура не поедала и
        // не проходила сквозь другую черную фигуру
        if (!chessboard[srcRow][srcCol].isWhite
                && !chessboard[destRow][destCol].isWhite) {
            System.err.println("Черная фигура не может заканчивать ход на другой черной фигуре!");
            return false;
        }

        return true;

    }


    // Метод для обновления счета после того, как будет совершен ход
    private void updateScore() {
        if (chessboard[destRow][destCol] == null) {
            return;
        }
        if (whitesTurnToMove) {
            whiteScore += chessboard[destRow][destCol].relativeValue();
        } else {
            blackScore += chessboard[destRow][destCol].relativeValue();

        }
    }


    /*
     * Принимает ввод пользователя для движения фигуры в виде
     * "начальная и конечная координаты", например "b2 - b4" и
     * преобразует эту строку в массив координат для шахматной доски.
     * Проверяет, допустимо ли перемещение, используя moveValid()
     * Если допустимо - передвигает фигуру в конечную координату и обновляет
     * счет с помощью updateScore(). Если перемещение недопустимо, тогда
     * выводит ошибку и вызывает сам себя для повторного ввода.
     */

    public void move() throws SQLException {

        // Проверка победы одного из цветов
        if (blackScore > 10000 || whiteScore > 10000) {
            System.out.println(ANSI_YELLOW + "Игра окончена" + ANSI_RESET);
            if (whiteScore > 10000) {
                System.out.println(ANSI_CYAN + "Победа белых!" + ANSI_RESET);
                database.saveToDb("Белые победили!");
            } else {
                System.out.println(ANSI_CYAN + "Победа черных!" + ANSI_RESET);
                database.saveToDb("Черные победили!");
            }
            System.exit(0);
        }

        System.out.println(delimeterLine
                + "Счет: Белые "
                + whiteScore
                + " | "
                + blackScore
                + " Черные");

        if (invalidMove) {
            System.err.println("Неправильный ход! Введите корректный ход:\n");
            invalidMove = false;
        } else if (whitesTurnToMove) {
            System.out.print(delimeterLine
                    + "Ход белых\n"
                    + delimeterLine);
        } else {
            System.out.print(delimeterLine
                    + "Ход черных\n"
                    + delimeterLine);
        }

        System.out.println(ANSI_BLACK + "Если хотите завершить игру, тогда напишите 'exit'" + ANSI_RESET);
        System.out.print("Введите ход " + ANSI_BLUE + "(в формате b2-b4) " + ANSI_RESET + ": ");
        move = user_input.nextLine();

        if (move.equalsIgnoreCase("exit")) {
            System.out.println(ANSI_BLACKBG + " Вы уверены, что хотите завершить игру? (y/n) " + ANSI_RESET);
            move = user_input.nextLine();
            if (move.equalsIgnoreCase("y")) {
                System.out.print(ANSI_YELLOW + " Игра завершена! ");
                gameRunning = false;
            } else {
                System.out.println(ANSI_YELLOW + " Игра продолжается! " + ANSI_RESET);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }

            return;
        }

        String lowerCase = move.toLowerCase();
        String[] components = lowerCase.split("-");

        /*
         * Если перемещение фигуры в виде "b2-b4" тогда
         * components[0].chartAt(0) = 'b'
         * components [0].charAt (1) = '2'
         */

        srcRow = 7 - (components[0].charAt(1) - '1');
        srcCol = components[0].charAt(0) - 'a';
        destRow = 7 - (components[1].charAt(1) - '1');
        destCol = components[1].charAt(0) - 'a';

        if (moveValid()) {
            updateScore();
            chessboard[destRow][destCol] = chessboard[srcRow][srcCol];
            chessboard[srcRow][srcCol] = null;
            whitesTurnToMove = !whitesTurnToMove;
        } else {
            invalidMove = true;
            move();

        }

    }

}
