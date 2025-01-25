/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import entities.Blog;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import java.util.List;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DAOBlog;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "BlogController", urlPatterns = {"/BlogURL"})
public class BlogController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        DAOBlog dao = new DAOBlog();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllBlogs";
            }
            if (service.equals( "listAllBlogs")) {
                String pageParam = request.getParameter("page");
                int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
                int pageSize = 3;
                List<Blog> blogs = dao.getPaginatedBlogs(page, pageSize);
                int totalBlogs = dao.getTotalBlogs();
                int totalPages = (int) Math.ceil((double) totalBlogs / pageSize);

                request.setAttribute("blogs", blogs);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("currentPage", page);
                request.getRequestDispatcher("blog.jsp").forward(request, response);
            }
            if (service.equals("search")) { 
                String query = request.getParameter("query");

                if (query == null || query.trim().isEmpty()) {
                    request.setAttribute("error", "Please enter a search!!");
                    request.getRequestDispatcher("blog.jsp").forward(request, response);
                    return;
                }

                List<Blog> blogs = dao.searchBlogs(query);

                if (blogs.isEmpty()) {
                    request.setAttribute("message", "No results found.");
                }
                request.setAttribute("blogs", blogs);
                request.setAttribute("query", query);
                request.getRequestDispatcher("blog.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
