package logic;

import GUI.*;
import main.Startup;
import main.Packet;
import main.Chess;

public class Game {
    private Model model;
    private GUI gui;
    private Controller controller;
    private Startup startup;

    public Game(Startup startup) {
        model = new Model();
        setConnect(startup);
        if (startup.getClass() == Chess.class) {
            gui = new GUI(model.getBoard(), this, "Белые");
        }
        setController(new Controller(model, gui, this));

    }

    // Создает пакет данных и отправляет его
    public void sendPacket(Command command, boolean restart, boolean restartConfirm, boolean exit) {
        startup.sendPacket(new Packet(command, restart, restartConfirm, exit));
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setConnect(Startup startup) {
        this.startup = startup;
    }

}
