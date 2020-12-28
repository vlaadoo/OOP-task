package connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Connectable {

    private Socket clientSocket;
    private Packet packet;

    Client() {
        setColor("Черные");
    }

    void run(String ip, int port) {
        try {
            // Создание сокета для подключения клиента
            clientSocket = new Socket(ip, port);
            System.out.println("Подключено к " + ip + " через порт " + port);
            // Входящий и исходящий потоки данных
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());
            // Подключение к серверу
            do {
                try {
                    packet = (Packet) in.readObject();
                    handlePacket(packet);
                } catch (ClassNotFoundException classNot) {
                    System.err.println("Данные получены в неверном формате");
                }
            } while (!packet.isExit());  // сигнал закрытия
        } catch (UnknownHostException unknownHost) {
            System.err.println("Подключение к несуществующему хосту");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            // Закрывание соединения
            try {
                closeConnections();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void closeConnections() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String args[]) {
        Client client = new Client();
        client.run("localhost", 7534);
    }
}
