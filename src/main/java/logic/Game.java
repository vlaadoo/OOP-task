package logic;

import GUI.*;
import connect.Client;
import connect.Connectable;
import connect.Packet;
import connect.Server;

public class Game {
    private Model model;
    private GUI gui;
    private Controller controller;
    private Connectable connectable;

//TODO перевести
    public Game(Connectable connectable) {
        model = new Model();
        setConnectable(connectable);
        if (connectable.getClass() == Server.class) {
            gui = new GUI(model.getBoard(), this, "Белые");
        }
        if (connectable.getClass() == Client.class) {
            gui = new GUI(model.getBoard(), this, "Черные");
        }
        setController(new Controller(model, gui, this));

    }

    // Создает пакет для соединения и отправляет его
    public void sendPacket(Command command, boolean restart, boolean restartConfirm, boolean exit) {
        connectable.sendPacket(new Packet(command, restart, restartConfirm, exit));
    }

    // Выполнение действий с помощью контроллера
    public void handleReceivedPacket(Packet packet) {
        if (packet.getCom() != null) {
            controller.move(packet.getCom().getFrom(), packet.getCom().getTo());
        }
        if (packet.isRestart() == true) {
            if (packet.isRestartConfirm() == true) {
                controller.resetBoard();
                return;
            }
            boolean restarted = gui.askRestart();
            if (restarted == true) {
                sendPacket(null, true, true, false);
                controller.resetBoard();
            }
        }
        if (packet.isExit() == true) {
            gui.opponentQuit();
            gui.close();
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Connectable getConnectable() {
        return connectable;
    }

    public void setConnectable(Connectable connectable) {
        this.connectable = connectable;
    }

}
