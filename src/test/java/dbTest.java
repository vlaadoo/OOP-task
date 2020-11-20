import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class dbTest {

    @Test
    public void testDbConnect() throws SQLException {
        DriverManager.getConnection("jdbc:h2:tcp://localhost:1521/chess", "", "");
    }
}
