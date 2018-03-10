package until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by asus on 1/2/2018.
 */
public class DBConnection {

    public final static String DB_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public final static String DB_URL = "jdbc:sqlserver://localhost:1433;instanceName=;databaseName=foodi";
    public final static String DB_USERNAME = "sa";
    public final static String DB_PASSWORD = "123";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Connection con = null;

        // load the Driver Class
        Class.forName(DB_DRIVER_CLASS);

        // create the connection now
        con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        System.out.println("DB Connection created successfully");
        return con;
    }
}
