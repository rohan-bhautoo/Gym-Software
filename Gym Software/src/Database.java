import java.sql.*;

public class Database {

    private static Connection connection;

    public static Connection connectDb() {
        try {
            String driver = "jdbc:mysql://localhost/Gym";
            String user = "Rohan";
            String password = "password";

            connection = DriverManager.getConnection(driver, user, password);
            return connection;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}
