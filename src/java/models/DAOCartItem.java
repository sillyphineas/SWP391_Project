/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import entities.CartItem;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DAOCartItem extends DBConnection {

    public int addCartItem(CartItem other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CartItem]\n"
                + "           ([CartID]\n"
                + "           ,[ProductID]\n"
                + "           ,[Price]\n"
                + "           ,[Quantity]\n"
                + "           ,[DiscountAmount]\n"
                + "           ,[TotalPrice]\n"
                + "           ,[isDisabled])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, other.getCartID());
            pre.setInt(2, other.getProductID());
            pre.setDouble(3, other.getPrice());
            pre.setInt(4, other.getQuantity());
            pre.setDouble(5, other.getDiscountAmount());
            pre.setDouble(6, other.getTotalPrice());
            pre.setBoolean(7, other.isIsDisabled());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int updateCartItem(CartItem other) {
        int n = 0;
        String sql = "UPDATE [dbo].[CartItem]\n"
                + "   SET [CartID] = ?\n"
                + "      ,[ProductID] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[Quantity] = ?\n"
                + "      ,[DiscountAmount] = ?\n"
                + "      ,[TotalPrice] = ?\n"
                + "      ,[isDisabled] = ?\n"
                + " WHERE <CartItemID = ?>";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, other.getCartID());
            pre.setInt(2, other.getProductID());
            pre.setDouble(3, other.getPrice());
            pre.setInt(4, other.getQuantity());
            pre.setDouble(5, other.getDiscountAmount());
            pre.setDouble(6, other.getTotalPrice());
            pre.setBoolean(7, other.isIsDisabled());
            pre.setInt(8, other.getCartItemID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public CartItem getCarItemtById(int id) {
        String sql = "Select * From Cart where CartID = ?";
        CartItem cartItem = null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                cartItem = new CartItem(rs.getInt("CartItemID"),
                        rs.getInt("CartID"),
                        rs.getInt("ProductID"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"),
                        rs.getDouble("DiscountAmount"),
                        rs.getDouble("TotalPrice"),
                        rs.getBoolean("isDisabled"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cartItem;
    }

    public Vector<CartItem> getCartItem(String sql) {
        Vector<CartItem> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                CartItem cartItem = new CartItem(rs.getInt("CartItemID"),
                        rs.getInt("CartID"),
                        rs.getInt("ProductID"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"),
                        rs.getDouble("DiscountAmount"),
                        rs.getDouble("TotalPrice"),
                        rs.getBoolean("isDisabled"));
                vector.add(cartItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int delete(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[CartItem]\n"
                + "      WHERE CartItemID = ?";
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
        
    }
}
