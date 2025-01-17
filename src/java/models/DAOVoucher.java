/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import entities.Voucher;
import java.sql.Date;
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
public class DAOVoucher extends DBConnection {

    public int addVoucher(Voucher voucher) {
        int n = 0;
        String sql = "INSERT INTO Vouchers (VoucherCode, VoucherType, Description, DiscountAmount, StartDate, ExpiredDate, UsageLimit, isDisabled) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, voucher.getVoucherCode());
            pre.setString(2, voucher.getVoucherType());
            pre.setString(3, voucher.getDescription());
            pre.setString(4, voucher.getDiscountAmount());
            pre.setDate(5, (Date) voucher.getStartDate());
            pre.setDate(6, (Date) voucher.getExpiredDate());
            pre.setInt(7, voucher.getUsageLimit());
            pre.setBoolean(8, voucher.isIsDisabled());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Voucher> getVouchers(String sql) {
        Vector<Voucher> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Voucher voucher = new Voucher(
                        rs.getInt("VoucherID"),
                        rs.getString("VoucherCode"),
                        rs.getString("VoucherType"),
                        rs.getString("Description"),
                        rs.getString("DiscountAmount"),
                        rs.getDate("StartDate"),
                        rs.getDate("ExpiredDate"),
                        rs.getInt("UsageLimit"),
                        rs.getBoolean("isDisabled")
                );
                vector.add(voucher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Voucher getVoucherByVoucherId(int voucherId) {
        String sql = "SELECT * FROM Vouchers WHERE VoucherID = ?";
        Voucher voucher = null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, voucherId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                voucher = new Voucher(
                        rs.getInt("VoucherID"),
                        rs.getString("VoucherCode"),
                        rs.getString("VoucherType"),
                        rs.getString("Description"),
                        rs.getString("DiscountAmount"),
                        rs.getDate("StartDate"),
                        rs.getDate("ExpiredDate"),
                        rs.getInt("UsageLimit"),
                        rs.getBoolean("isDisabled")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOVoucher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voucher;
    }

    public int updateVoucher(Voucher voucher) {
        int n = 0;
        String sql = "UPDATE Vouchers "
                + "SET VoucherCode = ?, VoucherType = ?, Description = ?, DiscountAmount = ?, StartDate = ?, ExpiredDate = ?, UsageLimit = ?, isDisabled = ? "
                + "WHERE VoucherID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, voucher.getVoucherCode());
            pre.setString(2, voucher.getVoucherType());
            pre.setString(3, voucher.getDescription());
            pre.setString(4, voucher.getDiscountAmount());
            pre.setDate(5, (Date) voucher.getStartDate());
            pre.setDate(6, (Date) voucher.getExpiredDate());
            pre.setInt(7, voucher.getUsageLimit());
            pre.setBoolean(8, voucher.isIsDisabled());
            pre.setInt(9, voucher.getVoucherID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int deleteVoucher(int voucherId) {
        int n = 0;
        String sql = "DELETE FROM Vouchers WHERE VoucherID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, voucherId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

}
