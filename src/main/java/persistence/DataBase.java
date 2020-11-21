package persistence;

import pieces.AbstractPiece;
import pieces.Chessboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class DataBase {


    private Connection getConnect() throws SQLException{
        return DriverManager.getConnection("jdbc:h2:tcp://localhost:1521/chess", "", "");
    }

    public void saveToDb(String winColor) throws SQLException {
        try (Connection c = getConnect()){
            PreparedStatement statement = c.prepareStatement("insert into ChessGame(color) values (?)");
            statement.setString(1, winColor);
            statement.executeUpdate();
        }
    }
}
