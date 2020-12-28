package connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Connectable {

    private ServerSocket serverSocket;
    private Socket clientSocket;

    private Packet packet;
    public static String color = "Белые";

    Server() {
        setColor(color);
    }


    public void run(int port) {
        try {
            // Создание сокета для сервера
            serverSocket = new ServerSocket(port);
            // Ожидание подключение клиента
            clientSocket = serverSocket.accept();
            // Входящий и исходящий потоки данных
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Подключено");
            // Взаимодействие клиента с серверов через потоки входных и выходных данных
            do {
                try {
                    packet = (Packet) in.readObject();
                    handlePacket(packet);
                } catch (ClassNotFoundException classnot) {
                    System.err.println("Данные получены в неверном формате");
                }
            } while (!packet.isExit());  // сигнал закрытия
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
        serverSocket.close();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run(7534);
    }
}
