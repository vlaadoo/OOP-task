package GUI;

import logic.Board;
import logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {

    JFrame frame;
    private int whiteScoreValue;
    private int blackScoreValue;
    private String currTurn;
    private Game game;
    private String side;


    JPanel mainPanel, infoBox, scoreBox, turnBox, boardPanel, sideBox;
    JLabel whiteScore, blackScore, turnTitle, turn, sideTitle, sideText;
    JMenuBar menuBar;
    JMenu gameOptions;
    JMenuItem restart, exit;

    public GUI(Board board, Game game, String color) {
        setCurrTurn("Белые");
        setWhiteScoreValue(0);
        setBlackScoreValue(0);
        boardPanel = board;
        setSide(color);
        createAndShowGUI();
        setGame(game);
    }

    // Создание GUI
    public void createAndShowGUI() {
        frame = new JFrame("OOP-chess");
        frame.getContentPane().add(createPanels());
        frame.setJMenuBar(createMenu());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    // Создание меню
    public JMenuBar createMenu() {
        menuBar = new JMenuBar();

        gameOptions = new JMenu("Options");
        menuBar.add(gameOptions);

        restart = new JMenuItem("Restart");
        exit = new JMenuItem("Exit");

        gameOptions.add(restart);
        gameOptions.add(exit);

        return menuBar;
    }

    /*
     * Создание JPanel'ов в GUI
     * Представление доски, текущих хода и счета
     */
    public JPanel createPanels() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        infoBox = new JPanel();
        infoBox.setLayout(new BorderLayout());

        scoreBox = new JPanel();
        whiteScore = new JLabel("Счет белых: " + whiteScoreValue);
        blackScore = new JLabel("Счет черных: " + blackScoreValue + " ");
        scoreBox.add(whiteScore);
        scoreBox.add(blackScore);

        turnBox = new JPanel();
        turnTitle = new JLabel("Текущий ход: ");
        turn = new JLabel("" + getCurrTurn());
        turnBox.add(turnTitle);
        turnBox.add(turn);

        infoBox.add(scoreBox, BorderLayout.SOUTH);
        infoBox.add(turnBox, BorderLayout.NORTH);

        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(infoBox, BorderLayout.EAST);

        return mainPanel;
    }

    // Появляется уведомление при проигрыше одной из сторон
    public void notifyCheckmate(String turn) {
        turnTitle.setText("");
        turnSwitchDisplay("Шах и мат! " + turn + " проиграли!" );
        JOptionPane.showMessageDialog(frame, "Шах и мат! " + turn + " проиграли!");
        if (turn == "Черные") {
            incrementWhiteScore();
        } else if (turn == "Белые") {
            incrementBlackScore();
        }
    }

    // Если нет возможности ходить
    public void notifyStalemate() {
        turnTitle.setText("");
        turnSwitchDisplay("Пат!");
        JOptionPane.showMessageDialog(frame, "Пат! Игра закончилась ничьей!");
    }

    // Предупреждение о шахе
    public void notifyChecked(String turn) {
        if (turn == "Белые") {
            JOptionPane.showMessageDialog(frame, "Шах у белых!");
        } else {
            JOptionPane.showMessageDialog(frame, "Шах у черных!");
        }
    }

    /*
     * Обновление JLabel currTurn, чтобы обновить информацию
     * о том, какая сторона ходит
     */
    public void turnSwitchDisplay(String string) {
        setCurrTurn(string);
        turn.setText("" + getCurrTurn());
    }

    /*
     * Добавление ActionListener для команд рестарта и выхода из игры
     * в JMenuItems
     */
    public void addActionListeners(ActionListener a) {
        restart.addActionListener(a);
        restart.setActionCommand("restart");

        exit.addActionListener(a);
        exit.setActionCommand("exit");
    }

    /*
     * Создание окна JOptionPane, которое запрашивает подтверждение для перезапуска игры
     * Возвращается boolean значение с решением игрока
     */
    public boolean askRestart() {
        int result = JOptionPane.showConfirmDialog(frame, "Хотите перезапустить игру?", "Restart", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }

    /*
     * Создание окна JOptionPane, которое запрашивает подтверждение для выхода из игры
     * Возвращается boolean значение с решением игрока
     */
    public boolean askExit() {
        int result = JOptionPane.showConfirmDialog(frame, "Вы действительно хотите выйти?", "Exit", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }

    /*
     * Функция для сброса доски к первоначальному положению фигур.
     * Удаляет текущую панель доски и повторно добавляет уже
     * новую с правильным положением фигур.
     * Также проверяет их расположение.
     */
    public void resetBoardPanel(Board boardReset) {
        mainPanel.remove(boardPanel);
        boardPanel = boardReset;
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        frame.invalidate();
        frame.validate();
    }

    /*
     * Увеличение счета белых на 1 и обновление соответствующего JLabel
     */
    public void incrementWhiteScore() {
        setWhiteScoreValue(getWhiteScoreValue() + 1);
        whiteScore.setText("Счет белых: " + getWhiteScoreValue());
    }

    /*
     * Увеличение счета черных на 1 и обновление соответствующего JLabel
     */
    public void incrementBlackScore() {
        setBlackScoreValue(getBlackScoreValue() + 1);
        blackScore.setText("Счет черных: " + getBlackScoreValue());
    }

    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }

    public int getWhiteScoreValue() {
        return whiteScoreValue;
    }

    public void setWhiteScoreValue(int whiteScoreValue) {
        this.whiteScoreValue = whiteScoreValue;
    }

    public int getBlackScoreValue() {
        return blackScoreValue;
    }

    public void setBlackScoreValue(int blackScoreValue) {
        this.blackScoreValue = blackScoreValue;
    }

    public String getCurrTurn() {
        return currTurn;
    }

    public void setCurrTurn(String currTurn) {
        this.currTurn = currTurn;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

}