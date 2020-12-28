package logic;

import GUI.*;
import connect.Connect;
import connect.Packet;
import connect.Chess;

public class Game {
    private Model model;
    private GUI gui;
    private Controller controller;
    private Connect connect;

    public Game(Connect connect) {
        model = new Model();
        setConnectable(connect);
        if (connect.getClass() == Chess.class) {
            gui = new GUI(model.getBoard(), this, "Белые");
        }
        setController(new Controller(model, gui, this));

    }

    // Создает пакет для соединения и отправляет его
    public void sendPacket(Command command, boolean restart, boolean restartConfirm, boolean exit) {
        connect.sendPacket(new Packet(command, restart, restartConfirm, exit));
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setConnectable(Connect connect) {
        this.connect = connect;
    }

}
