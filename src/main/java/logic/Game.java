package logic;

import GUI.*;
import main.Launch;
import main.Packet;
import main.Chess;

public class Game {
    private Model model;
    private GUI gui;
    private Controller controller;
    private Launch launch;

    public Game(Launch launch) {
        model = new Model();
        setLaunch(launch);
        gui = new GUI(model.getBoard(), this, "Белые");
        setController(new Controller(model, gui, this));
    }

    public void sendPacket(Command command, boolean restart, boolean restartConfirm, boolean exit) {
        launch.sendPacket(new Packet(command, restart, restartConfirm, exit));
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setLaunch(Launch launch) {
        this.launch = launch;
    }

}
