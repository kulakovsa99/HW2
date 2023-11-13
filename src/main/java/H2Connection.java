import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection extends DBConnection {
    public H2Connection(String url, String user, String password) {
        super(url, user, password);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}