
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionOracle {
    
    public static Connection getJDBCConeection (){
        final String url = "jdbc:oracle:thin:@localhost:1521:orcl1";
        final String user = "QLBV";
        final String password = "Password";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try {
                return DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionOracle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static void main (String[] agrs){
          Connection c =getJDBCConeection();
        try {
            Statement statement = c.createStatement();
            
            if (statement != null){
                System.out.println("thanh cong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
