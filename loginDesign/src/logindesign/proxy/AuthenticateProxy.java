package logindesign.proxy;

import logindesign.singelton.JDBCSingelton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class AuthenticateProxy implements IAuthenticateProxy {
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    @Override
    public boolean authenticate(String userName, String pass) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            pst = JDBCSingelton.getConnection().prepareStatement(sql);
            pst.setString(1, userName);
            pst.setString(2, pass);
            rs = pst.executeQuery();
            
            return rs.next();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }
}
