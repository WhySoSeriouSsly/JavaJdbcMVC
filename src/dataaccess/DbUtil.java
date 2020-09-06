package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static String userName="root";
    private static String password="123456";
    private static String dbUrl ="jdbc:mysql://localhost:3306/stockcart?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static Connection connection;
    @SuppressWarnings("static-access")
	public  Connection baglanti() throws SQLException {
    	connection=DriverManager.getConnection(dbUrl,userName,password);
        return this.connection;
    }

    public static void showErrorMessage(SQLException exception){
    	 System.out.println("Stock Not Added");
        System.out.println("Error : "+ exception.getMessage());
        System.out.println("Error code : "+ exception.getErrorCode());
    }
}
