package models;

import entities.User;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author HP
 */
public class DAOUser extends DBConnection {

    public int addUser(User user) {
        int n = 0;
        String sql = "INSERT INTO Users (name, email, passHash, gender, phoneNumber, resetToken, resetTokenExpired, Address, DateOfBirth, roleId, isDisabled) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user.getName());
            pre.setString(2, user.getEmail());
            pre.setString(3, user.getPassHash());
            pre.setBoolean(4, user.isGender());
            pre.setString(5, user.getPhoneNumber());
            pre.setString(6, user.getResetToken());
            pre.setDate(7, user.getResetTokenExpired());
            pre.setString(8, user.getAddress());
            pre.setDate(9, user.getDateOfBirth());
            pre.setInt(10, user.getRoleId());
            pre.setBoolean(11, user.isDisabled());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<User> getUsers(String sql) {
        Vector<User> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("passHash"),
                    rs.getBoolean("gender"),
                    rs.getString("phoneNumber"),
                    rs.getString("resetToken"),
                    rs.getDate("resetTokenExpired"),
                    rs.getString("Address"),
                    rs.getDate("DateOfBirth"),
                    rs.getInt("roleId"),
                    rs.getBoolean("isDisabled")
                );
                vector.add(user);
            }
         } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public User getUserById(int id) {
        String sql = "SELECT * FROM Users WHERE id = ?";
        User user = null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("passHash"),
                    rs.getBoolean("gender"),
                    rs.getString("phoneNumber"),
                    rs.getString("resetToken"),
                    rs.getDate("resetTokenExpired"),
                    rs.getString("Address"),
                    rs.getDate("DateOfBirth"),
                    rs.getInt("roleId"),
                    rs.getBoolean("isDisabled")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public int updateUser(User user) {
        int n = 0;
        String sql = "UPDATE Users "
                   + "SET name = ?, email = ?, passHash = ?, gender = ?, phoneNumber = ?, resetToken = ?, resetTokenExpired = ?, Address = ?, DateOfBirth = ?, roleId = ?, isDisabled = ? "
                   + "WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user.getName());
            pre.setString(2, user.getEmail());
            pre.setString(3, user.getPassHash());
            pre.setBoolean(4, user.isGender());
            pre.setString(5, user.getPhoneNumber());
            pre.setString(6, user.getResetToken());
            pre.setDate(7, user.getResetTokenExpired());
            pre.setString(8, user.getAddress());
            pre.setDate(9, user.getDateOfBirth());
            pre.setInt(10, user.getRoleId());
            pre.setBoolean(11, user.isDisabled());
            pre.setInt(12, user.getId());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int deleteUser(int id) {
        int n = 0;        
        String sql = "DELETE FROM Users WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public static void main(String[] args) {
        DAOUser dao = new DAOUser();
        User user = dao.getUserById(2);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User not found.");
        }
    }
}
