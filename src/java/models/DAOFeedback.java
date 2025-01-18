/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import entities.Feedback;
import java.sql.Date;
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
public class DAOFeedback extends DBConnection {

    public int Feedback(Feedback other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Feedbacks]\n"
                + "           ([reviewerID]]\n"
                + "           ,[reviewTime]\n"
                + "           ,[rating]\n"
                + "           ,[content]\n"
                + "           ,[isDisabled])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, other.getReviewerID());
            pre.setDate(2, (Date) other.getReviewTime());
            pre.setInt(3, other.getRating());
            pre.setString(4, other.getContent());
            pre.setBoolean(5, other.isIsDisabled());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;

    }

    public int updateCartItem(Feedback other) {
        int n = 0;
        String sql = "UPDATE [dbo].[Feedbacks]\n"
                + "   SET [ReviewerID] = ?\n"
                + "      ,[ReviewTime] = ?\n"
                + "      ,[Rating] = ?\n"
                + "      ,[Content] = ?\n"
                + "      ,[isDisabled] = ?\n"
                + " WHERE <ProductID = ?>";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, other.getReviewerID());
            pre.setDate(2, (Date) other.getReviewTime());
            pre.setInt(3, other.getRating());
            pre.setString(4, other.getContent());
            pre.setBoolean(5, other.isIsDisabled());
            pre.setInt(6, other.getProrductID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Feedback getFeedbackById(int id) {
        String sql = "Select * From Feedbacks where productId = ?";
        Feedback feedback = null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                feedback = new Feedback(
                        rs.getInt("productID"),
                        rs.getInt("reviewerID"),
                        rs.getDate("reviewTime"),
                        rs.getInt("rating"),
                        rs.getString("content"),
                        rs.getBoolean("isDisabled")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedback;
    }

    public Vector<Feedback> getFeedbacks(String sql) {
        Vector<Feedback> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Feedback feedback = new Feedback(
                        rs.getInt("productID"),
                        rs.getInt("reviewerID"),
                        rs.getDate("reviewTime"),
                        rs.getInt("rating"),
                        rs.getString("content"),
                        rs.getBoolean("isDisabled")
                );
                vector.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int delete(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[Feedbacks]\n"
                + "      WHERE ProductID = ?";
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