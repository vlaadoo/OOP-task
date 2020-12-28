package logic;

import GUI.GUI;
import connect.Server;
import persistence.DataBase;

import javax.sql.DataSource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.DataBuffer;
import java.sql.SQLException;
import java.util.List;

public class Controller implements ActionListener {

    private Model model;
    private GUI gui;
    private Square selectedSquare;
    private String turn;
    private Game game;


    public Controller(Model model, GUI gui, Game game) {
        this.setModel(model);
        this.setGui(gui);
        this.setGame(game);
        this.setTurn("Белые");
        this.setSelectedSquare(null);
        model.addActionListeners(this);
        gui.addActionListeners(this);
    }

    /*
     * Описание, что происходит, когда действие выполняется в различные периоды партии .
     */
    public void actionPerformed(ActionEvent e) {

        String action_com = e.getActionCommand();
        if (action_com == "square") {
            isClicked((Square) e.getSource());  // передает квадрат, на котором произошло событие, в функцию isClicked.
        }

        if (action_com == "restart") {
            boolean restarted = gui.askRestart();
            if (restarted == true) {
                game.sendPacket(null, true, false, false);
            }
        }
        if (action_com == "exit") {
            boolean exit = gui.askExit();
            if (exit == true) {
                game.sendPacket(null, false, false, true);
                gui.close();
            }
        }
    }

    /*
     * Возвращает доску к первоначальному состоянию
     */
    public void resetBoard() {
        this.setModel(new Model(model.getWhiteScore(), model.getBlackScore(), false));
        this.setTurn("Белые");
        gui.turnSwitchDisplay("Белые");
        this.setSelectedSquare(null);
        model.addActionListeners(this);
        gui.resetBoardPanel(getModel().getBoard());
    }

    /*
     * Обработка нажатий на определнный квадрат
     */
    public void isClicked(Square square) {
        if (getSelectedSquare() == null) {
            if (square.getOccupier() != null) {        // есть ли в данном квадрате фигура
                if (checkProperTurn(square)) {        // совпадает ли цвет фигуры и текущего игрока
                    select(square);
                }
            }
        } else if (getSelectedSquare() != null) {
            if (getSelectedSquare() == square) {
                deselect(square);
            } else if (square.isHighlighted()) {
                game.sendPacket(new Command(selectedSquare.getPosition().getLocation(), square.getPosition().getLocation()), false, false, false); //SEND THE PACKET
                move(selectedSquare.getPosition(), square.getPosition());

                if (getSelectedSquare() != null) {
                    deselect(getSelectedSquare());
                }
            }
        }
    }

    /*
     * Выделение выбранной клетки и всех возможных ходов
     */
    public void select(Square square) {
        square.setBackground(Color.green);

        setSelectedSquare(square);    // определение выбранного квадрата

        List<Point> moves = square.getOccupier().getFilteredMoves();  // получение возможных ходов

        for (Point p : moves) {            // выделение возможных ходов
            model.getBoard().getSquare(p).setBackground(Color.green);
            model.getBoard().getSquare(p).setHighlighted(true);
        }
    }

    /*
     * Функция для отмены выделения клетки и возможных ходов
     */
    public void deselect(Square square) {
        square.setBackground(square.getOrigColor());

        setSelectedSquare(null);                    // заменяет выбранный квадрат на null

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                model.getBoard().getSquare(new Point(i, j)).setBackground(model.getBoard().getSquare(new Point(i, j)).getOrigColor());
                model.getBoard().getSquare(new Point(i, j)).setHighlighted(false);
            }
        }
    }

    /*
     * Перемещает фигуру из начальной точки в конечную
     */
    public void move(Point from, Point to) {
        model.move(from, to);                // перемещение фигуры по правильным условиям
        switchTurns();                        // смена ходящей стороны
        testGameStatus(getTurn());            // проверка соперника на шах, мат и пат
    }

    /*
     * Провека наличия шаха, мата или пата с учетом стороны, которая ходит
     * При наличии чего-либо происходит уведомление игрока с помощью появления предупреждения
     */
    //TODO заносить результат в бд
    public void testGameStatus(String turn) {
        if (model.getBoard().isCheckmate(turn)) { // если у игрока мат
            gui.notifyCheckmate(turn); // передает в GUI окно "шах и мат" и добавляет одно очко победившей стороне
            resetBoard();
            gui.resetBoardPanel(getModel().getBoard());
            System.out.println("результат в бд");
            return;
        }

        if (model.getBoard().isStalemate(turn)) { // Если пат
            gui.notifyStalemate();
            resetBoard();
            gui.resetBoardPanel(getModel().getBoard());
            return;
        }

        if (model.getBoard().isChecked(turn)) {
            gui.notifyChecked(turn);
            return;
        }
    }

    /*
     * Смена текущего цвета с последующим обновлением GUI
     */
    public void switchTurns() {
        if (this.getTurn() == "Белые") {
            this.setTurn("Черные");
            Server.color = "Черные";
            gui.turnSwitchDisplay("Черные");  // смена текущей стороны, которая должна ходить
        } else if (this.getTurn() == "Черные") {
            this.setTurn("Белые");
            gui.turnSwitchDisplay("Белые");
        }
    }

    /*
     * Проверяет, имеет ли фигура на квадрате тот же цвет, что и текущий ход, что позволяет ее перемещать
     */
    public boolean checkProperTurn(Square test) {
        if ((test.getOccupier().getColor() == this.getTurn()) && (this.getTurn() == getGame().getConnectable().getColor())) {
            return true;
        }
        return false;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public Square getSelectedSquare() {
        return selectedSquare;
    }

    public void setSelectedSquare(Square selectedSquare) {
        this.selectedSquare = selectedSquare;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
