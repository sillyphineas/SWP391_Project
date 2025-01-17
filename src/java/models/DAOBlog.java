/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import entities.Blog;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DAOBlog extends DBConnection {

    public int addBlogs(Blog other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Blogs]\n"
                + "           ([authorID]\n"
                + "           ,[postTime]\n"
                + "           ,[title]\n"
                + "           ,[content]\n"
                + "           ,[imageURL]\n"
                + "           ,[status]\n"
                + "           ,[isDisabled])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, other.getAuthorID());
            pre.setString(2, other.getPostTime());
            pre.setString(3, other.getTitle());
            pre.setString(4, other.getContent());
            pre.setString(5, other.getContent());
            pre.setString(6, other.getImageURL());
            pre.setBoolean(7, other.isIsDisabled());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int UpdateBlogs(Blog other) {
        int n = 0;
        String sql = "UPDATE [dbo].[Blogs]\n"
                + "   SET [authorID] = ?\n"
                + "      ,[postTime] = ?\n"
                + "      ,[title] = ?\n"
                + "      ,[content] = ?\n"
                + "      ,[imageURL] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[isDisabled] = ?\n"
                + " WHERE <id = ?>";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, other.getAuthorID());
            pre.setString(2, other.getPostTime());
            pre.setString(3, other.getTitle());
            pre.setString(4, other.getContent());
            pre.setString(5, other.getImageURL());
            pre.setString(6, other.getStatus());
            pre.setBoolean(7, other.isIsDisabled());
            pre.setInt(8, other.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Blog getBlogById(int id) {
        String sql = "Select * From Blogs where id = ?";
        Blog blog = null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                blog = new Blog(
                        rs.getInt("id"),
                        rs.getInt("authorID"),
                        rs.getString("postTime"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("imageURL"),
                        rs.getString("status"),
                        rs.getBoolean("isDisabled")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blog;
    }

    public Vector<Blog> getBlogs(String sql) {
        Vector<Blog> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Blog blog = new Blog(
                        rs.getInt("id"),
                        rs.getInt("authorID"),
                        rs.getString("postTime"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("imageURL"),
                        rs.getString("status"),
                        rs.getBoolean("isDisabled")
                );
                vector.add(blog);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int delete(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[Blogs]\n"
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
