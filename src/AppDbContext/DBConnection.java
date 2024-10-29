package AppDbContext;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static String host = "jdbc:mysql://localhost:3307/degree_java_atm";
    private static String user = "root";
    private static String password = "";
    public static Connection conn;
    
    public DBConnection() {
        createConnection();
    }
    
    public static void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(host, user, password);
            if (conn != null) {
                //System.out.println("Connect Success");
            }
        } catch (Exception ex) {
            //System.out.println("Connection Failed!");
        }
    }
    
    public static void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
	
	
}
