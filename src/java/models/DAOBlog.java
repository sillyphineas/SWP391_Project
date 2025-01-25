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
import java.util.ArrayList;
import java.util.List;
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

    public List<Blog> getPaginatedBlogs(int page, int pageSize) throws SQLException {
        String sql = "SELECT * FROM Blogs WHERE isDisabled = 0 ORDER BY postTime DESC LIMIT ? OFFSET ?";
        List<Blog> blogs = new ArrayList<>();
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, pageSize);
            pre.setInt(2, (page - 1) * pageSize);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                blogs.add(new Blog(
                    rs.getInt("id"),
                    rs.getInt("authorID"),
                    rs.getString("postTime"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("imageURL"),
                    rs.getString("status"),
                    rs.getBoolean("isDisabled")
                ));
            }
        }
        return blogs;
    }
    public int getTotalBlogs() throws SQLException {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM Blogs";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        }
        return total;
    }
    
    public List<Blog> searchBlogs(String query) throws SQLException {
        String sql = "SELECT * FROM Blogs WHERE title LIKE ? AND isDisabled = 0";
        List<Blog> blogs = new ArrayList<>();
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, "%" + query + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                blogs.add(new Blog(
                    rs.getInt("id"),
                    rs.getInt("authorID"),
                    rs.getString("postTime"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("imageURL"),
                    rs.getString("status"),
                    rs.getBoolean("isDisabled")
                ));
            }
        }
        return blogs;
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
        DAOBlog dao =new DAOBlog();
        try {
            //        try {
//            System.out.println(dao.getTotalBlogs());
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOBlog.class.getName()).log(Level.SEVERE, null, ex);
//        }
        List<Blog> list = dao.getPaginatedBlogs(1, 3);
            for (Blog blog : list) {
                System.out.println(blog);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
