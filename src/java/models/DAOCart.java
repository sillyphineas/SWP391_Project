/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import entities.Cart;
import entities.CartItem;
import entities.Order;
import entities.OrderDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.jakartaee.commons.lang3.ArrayUtils;

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

    public Cart getCartByCustomerID(int customerID) {
        Cart cart = null;
        String sql = "SELECT * FROM Cart WHERE CustomerID = ? AND CartStatus = 'Active'";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, customerID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                cart = new Cart();
                cart.setCartID(rs.getInt("CartID"));
                cart.setCustomerID(rs.getInt("CustomerID"));
                cart.setCartStatus(rs.getString("CartStatus"));
                cart.setTotalPrice(rs.getDouble("TotalPrice"));
                cart.setCreatedAt(rs.getString("CreatedAt"));
                cart.setUpdatedAt(rs.getString("UpdatedAt"));
                cart.setCartItems(getCartItemsByCartID(cart.getCartID()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    public List<CartItem> getCartItemsByCartID(int cartID) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM CartItem WHERE CartID = ? AND isDisabled = false";
        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, cartID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setCartItemID(rs.getInt("CartItemID"));
                item.setCartID(rs.getInt("CartID"));
                item.setProductID(rs.getInt("ProductID"));
                item.setPrice(rs.getDouble("Price"));
                item.setQuantity(rs.getInt("Quantity"));
                item.setDiscountAmount(rs.getDouble("DiscountAmount"));
                item.setTotalPrice(rs.getDouble("TotalPrice"));
                item.setDisabled(rs.getBoolean("isDisabled"));
                cartItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    public void deleteCartItem(int cartItemID) {
        String sql = "UPDATE CartItem SET isDisabled = true WHERE CartItemID = ?";
        try (PreparedStatement pre = conn.prepareStatement(sql);) {
            pre.setInt(1, cartItemID);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCartItemQuantity(CartItem cartItem, int newQuantity) {
        String sql = "UPDATE CartItem SET Quantity = ?, TotalPrice = ? WHERE CartItemID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            double updatedTotalPrice = cartItem.getPrice() * newQuantity;

            stmt.setInt(1, newQuantity);
            stmt.setDouble(2, updatedTotalPrice);
            stmt.setInt(3, cartItem.getCartItemID());

            stmt.executeUpdate();  // Thực thi câu lệnh UPDATE
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteCartItemsByCartID(int cartID) {
        String sql = "DELETE FROM CartItems WHERE cartID = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateCartStatus(int cartID, String status) {
        String query = "UPDATE Cart SET status = ? WHERE cartID = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, cartID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

    }
}
