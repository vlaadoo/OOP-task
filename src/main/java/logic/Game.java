package logic;

import GUI.*;
import main.Connect;
import main.Packet;
import main.Chess;

public class Game {
    private Model model;
    private GUI gui;
    private Controller controller;
    private Connect connect;

    public Game(Connect connect) {
        model = new Model();
        setConnect(connect);
        if (connect.getClass() == Chess.class) {
            gui = new GUI(model.getBoard(), this, "Белые");
        }
        setController(new Controller(model, gui, this));

    }

    // Создает пакет данных и отправляет его
    public void sendPacket(Command command, boolean restart, boolean restartConfirm, boolean exit) {
        connect.sendPacket(new Packet(command, restart, restartConfirm, exit));
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setConnect(Connect connect) {
        this.connect = connect;
    }

}
