package connect;

import logic.Game;

import java.io.IOException;
import java.io.ObjectOutputStream;

public abstract class Connect {
    protected Game game;
    protected String color;
    protected ObjectOutputStream out;

    public Connect() {
        this.setGame(new Game(this));
    }

    public void sendPacket(Packet packet) {
        try {
            if (out != null) {
                out.writeObject(packet);
                out.flush();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
