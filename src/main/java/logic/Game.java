package logic;

import GUI.*;
import connect.Client;
import connect.Connectable;
import connect.Packet;
import connect.Server;

public class Game {
    private Model model;
    private GUI view;
    private Controller controller;
    private Connectable connectable;

//TODO перевести
    public Game(Connectable connectable) {
        model = new Model();
        setConnectable(connectable);
        if (connectable.getClass() == Server.class) {
            view = new GUI(model.getBoard(), this, "Белые");
        }
        if (connectable.getClass() == Client.class) {
            view = new GUI(model.getBoard(), this, "Черные");
        }
        setController(new Controller(model, view, this));

    }

    /**
     * Creates a connect.Packet based on the parameters and sends it by calling connectable.sendPacket
     *
     * @param command        logic.Command, could be null
     * @param restart        true if one player wishes to restart
     * @param restartConfirm true if both parties wish to restart the game
     * @param exit           true if a player exits the game
     */
    public void sendPacket(Command command, boolean restart, boolean restartConfirm, boolean exit) {
        connectable.sendPacket(new Packet(command, restart, restartConfirm, exit));
    }

    /**
     * Performs various actions using the logic.Controller based on the contents of a connect.Packet
     *
     * @param packet the packet in question
     */
    public void handleReceivedPacket(Packet packet) {
        if (packet.getCom() != null) {
            //use controller move to update the board
            controller.move(packet.getCom().getFrom(), packet.getCom().getTo());
        }
        if (packet.isRestart() == true) {
            if (packet.isRestartConfirm() == true) {
                //this occurs from the origin of the restart request,  only after the askee sends back an affirmative reply, then restart the game
                controller.resetBoard();
                return;
            }
            //ask the user if you want to restart
            boolean restarted = view.askRestart();
            if (restarted == true) {
                sendPacket(null, true, true, false);
                controller.resetBoard();
            }
        }
        if (packet.isExit() == true) {
            //call close
            view.opponentQuit();
            view.close();
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
