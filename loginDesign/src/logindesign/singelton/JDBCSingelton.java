package logindesign.singelton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class JDBCSingelton {
    
    static Connection con = null;
    
    private JDBCSingelton(){}
    
    public static Connection getConnection(){
        
        try{
            if(con == null){
                con = DriverManager.getConnection("jdbc:mysql://localhost/salesdb","root","");
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }
    
}
