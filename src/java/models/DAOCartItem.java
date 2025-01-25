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
import java.util.ArrayList;
import java.util.List;
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

    public List<CartItem> getCartDetails(int customerId) throws SQLException {
        String sql = """
        SELECT ci.CartItemID, ci.ProductID, p.Title AS ProductTitle, ci.Price, 
               ci.Quantity, ci.TotalPrice, c.TotalPrice AS CartTotal, c.CartStatus
        FROM CartItem ci
        JOIN Cart c ON ci.CartID = c.CartID
        JOIN Products p ON ci.ProductID = p.id
        WHERE c.CustomerID = ? AND ci.isDisabled = 0 AND c.CartStatus = 'Pending'
    """;
        List<CartItem> cartItems = new ArrayList<>();
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, customerId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                cartItems.add(new CartItem(
                        rs.getInt("CartItemID"),
                        rs.getInt("ProductID"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"),
                        rs.getDouble("TotalPrice")
                ));
            }
        }
        return cartItems;
    }

    public void removeCartItem(int cartItemId) throws SQLException {
        String sql = "UPDATE CartItem SET isDisabled = 1 WHERE CartItemID = ?";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, cartItemId);
            pre.executeUpdate();
        }
    }

    public boolean updateCartItemQuantity(CartItem cartItem, int newQuantity) {
    String sql = "UPDATE CartItems SET quantity = ? WHERE id = ?";
    try ( PreparedStatement pre = conn.prepareStatement(sql)) {

        pre.setInt(1, newQuantity);
        pre.setInt(2, cartItem.getCartItemID());
        return pre.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    

    public static void main(String[] args) {

    }
}
