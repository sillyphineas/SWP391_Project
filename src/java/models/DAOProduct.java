/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import entities.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOProduct extends DBConnection {

    public int addProduct(Product other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([brandID]\n"
                + "           ,[name]\n"
                + "           ,[price]\n"
                + "           ,[stock]\n"
                + "           ,[description]\n"
                + "           ,[isDisabled]\n"
                + "           ,[feedbackCount]\n"
                + "           ,[status]\n"
                + "           ,[imageURL]\n"
                + "           ,[chipset]\n"
                + "           ,[ram]\n"
                + "           ,[storage]\n"
                + "           ,[screenSize]\n"
                + "           ,[screenType]\n"
                + "           ,[resolution]\n"
                + "           ,[batteryCapacity]\n"
                + "           ,[cameraSpecs]\n"
                + "           ,[os]\n"
                + "           ,[simType]\n"
                + "           ,[connectivity])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, other.getBrandID());
            pre.setString(2, other.getName());
            pre.setDouble(3, other.getPrice());
            pre.setInt(4, other.getStock());
            pre.setString(5, other.getDescription());
            pre.setBoolean(6, other.isIsDisabled());
            pre.setInt(7, other.getFeedbackCount());
            pre.setString(8, other.getStatus());
            pre.setString(9, other.getImageURL());
            pre.setString(10, other.getChipset());
            pre.setInt(11, other.getRam());
            pre.setInt(12, other.getStorage());
            pre.setDouble(13, other.getScreenSize());
            pre.setString(14, other.getScreenType());
            pre.setString(15, other.getResolution());
            pre.setInt(16, other.getBatteryCapacity());
            pre.setString(17, other.getCameraSpecs());
            pre.setString(18, other.getOs());
            pre.setString(19, other.getSimType());
            pre.setString(20, other.getConnectivity());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int UpdateProduct(Product other) {
        int n = 0;
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [id] = ?\n"
                + "      ,[brandID] = ?\n"
                + "      ,[name] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[stock] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[feedbackCount] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[imageURL] = ?\n"
                + "      ,[chipset] = ?\n"
                + "      ,[ram] = ?\n"
                + "      ,[storage] = ?\n"
                + "      ,[screenSize] = ?\n"
                + "      ,[screenType] = ?\n"
                + "      ,[resolution] = ?\n"
                + "      ,[batteryCapacity] = ?\n"
                + "      ,[cameraSpecs] = ?\n"
                + "      ,[os] = ?\n"
                + "      ,[simType] = ?\n"
                + "      ,[connectivity] = ?\n"
                + " WHERE <id = ?>";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, other.getBrandID());
            pre.setString(2, other.getName());
            pre.setDouble(3, other.getPrice());
            pre.setInt(4, other.getStock());
            pre.setString(5, other.getDescription());
            pre.setBoolean(6, other.isIsDisabled());
            pre.setInt(7, other.getFeedbackCount());
            pre.setString(8, other.getStatus());
            pre.setString(9, other.getImageURL());
            pre.setString(10, other.getChipset());
            pre.setInt(11, other.getRam());
            pre.setInt(12, other.getStorage());
            pre.setDouble(13, other.getScreenSize());
            pre.setString(14, other.getScreenType());
            pre.setString(15, other.getResolution());
            pre.setInt(16, other.getBatteryCapacity());
            pre.setString(17, other.getCameraSpecs());
            pre.setString(18, other.getOs());
            pre.setString(19, other.getSimType());
            pre.setString(20, other.getConnectivity());
            pre.setInt(21, other.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Product getProductById(int id) {
        String sql = "Select * From Products where id = ?";
        Product product = null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getInt("brandID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("description"),
                        rs.getBoolean("isDisabled"),
                        rs.getInt("feedbackCount"),
                        rs.getString("status"),
                        rs.getString("imageURL"),
                        rs.getString("chipset"),
                        rs.getInt("ram"),
                        rs.getInt("storage"),
                        rs.getDouble("screenSize"),
                        rs.getString("screenType"),
                        rs.getString("resolution"),
                        rs.getInt("batteryCapacity"),
                        rs.getString("cameraSpecs"),
                        rs.getString("os"),
                        rs.getString("simType"),
                        rs.getString("connectivity")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public Vector<Product> getProducts(String sql) {
        Vector<Product> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getInt("brandID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("description"),
                        rs.getBoolean("isDisabled"),
                        rs.getInt("feedbackCount"),
                        rs.getString("status"),
                        rs.getString("imageURL"),
                        rs.getString("chipset"),
                        rs.getInt("ram"),
                        rs.getInt("storage"),
                        rs.getDouble("screenSize"),
                        rs.getString("screenType"),
                        rs.getString("resolution"),
                        rs.getInt("batteryCapacity"),
                        rs.getString("cameraSpecs"),
                        rs.getString("os"),
                        rs.getString("simType"),
                        rs.getString("connectivity")
                );
                vector.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int delete(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[Products]\n"
                + "      WHERE id = ? ";
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
