/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import entities.Cart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DAOCart extends DBConnection {

    public int addCart(Cart other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Cart]\n"
                + "           ([CustomerID]\n"
                + "           ,[CartStatus]\n"
                + "           ,[TotalPrice]\n"
                + "           ,[CreatedAt]\n"
                + "           ,[UpdatedAt])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, other.getCustomerID());
            pre.setString(2, other.getCartStatus());
            pre.setDouble(3, other.getTotalPrice());
            pre.setString(4, other.getCreatedAt());
            pre.setString(5, other.getUpdatedAt());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateCart(Cart other) {
        int n = 0;
        String sql = "UPDATE [dbo].[Cart]\n"
                + "   SET [CustomerID] = ?\n"
                + "      ,[CartStatus] = ?\n"
                + "      ,[TotalPrice] = ?\n"
                + "      ,[CreatedAt] = ?\n"
                + "      ,[UpdatedAt] = ?\n"
                + " WHERE <CartID = ?>";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, other.getCustomerID());
            pre.setString(2, other.getCartStatus());
            pre.setDouble(3, other.getTotalPrice());
            pre.setString(4, other.getCreatedAt());
            pre.setString(5, other.getUpdatedAt());
            pre.setInt(6, other.getCartID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Cart getCartById(int id) {
        String sql = "Select * From Cart where CartID = ?";
        Cart cart = null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                cart = new Cart(rs.getInt("CartID"),
                        rs.getInt("CustomerID"),
                        rs.getString("CartStatus"),
                        rs.getDouble("TotalPrice"),
                        rs.getString("CreatedAt"),
                        rs.getString("UpdatedAt"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cart;
    }

    public Vector<Cart> getCart(String sql) {
        Vector<Cart> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Cart cart = new Cart(rs.getInt("CartID"),
                        rs.getInt("CustomerID"),
                        rs.getString("CartStatus"),
                        rs.getDouble("TotalPrice"),
                        rs.getString("CreatedAt"),
                        rs.getString("UpdatedAt"));
                vector.add(cart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public void changeStatus(String newvalue, int id) {
        String sql = "UPDATE [dbo].[Cart] SET [CartStatus] = " + newvalue + " WHERE CartID =" + id;
        try {
            Statement state = conn.createStatement();
            state.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int deleteCart(int id) {
        int n = 0;
        String sqlCheck = "Select * From CartItem Where CartID =" + id;
        ResultSet rs = getData(sqlCheck);
        try {
            while (rs.next()) {
                changeStatus("Null", id);
                return 0;
            }
            String sql = "DELETE FROM [dbo].[Cart]\n"
                    + "      WHERE CartID =" + id;
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int delete(int id) {
        int n = 0;        
        String sql = "DELETE FROM [dbo].[Cart]\n"
                    + "      WHERE CartID = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

}
