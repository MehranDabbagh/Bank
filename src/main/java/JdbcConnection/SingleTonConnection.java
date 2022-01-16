package JdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleTonConnection {

    private Connection connection;

    private SingleTonConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            setConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class Singleton {
        private static final SingleTonConnection INSTANCE = new SingleTonConnection();
    }

    public static SingleTonConnection getInstance() {
        return Singleton.INSTANCE;
    }


    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                setConnection();
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

