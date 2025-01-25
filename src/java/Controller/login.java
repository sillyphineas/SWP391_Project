/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "login", urlPatterns = {"/loginURL"})
public class login extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            User user = new User();
            user.setId(1);
            user.setName("John Doe");
            user.setEmail("johndoe@example.com");
            user.setPassHash("hashedpassword123"); // Hash mật khẩu
            user.setGender(true);                 // true = Nam, false = Nữ
            user.setPhoneNumber("0123456789");
            user.setResetToken("resetToken123");
            user.setResetTokenExpired(new java.sql.Date(2024, 10, 21)); // 1 giờ từ hiện tại
            user.setAddress("123 Main Street, City, Country");
            user.setDateOfBirth(new java.sql.Date(2004, 9, 4)); // 01/01/1990
            user.setRoleId(2);                // Ví dụ: 2 = Khách hàng
            user.setDisabled(false);          // Không bị vô hiệu hóa

            HttpSession session = request.getSession();
            session.setAttribute("userID", user.getId());
            session.setAttribute("Name", user.getName());
            session.setAttribute("Email", user.getEmail());
            session.setAttribute("PassHash", user.getPassHash());
            session.setAttribute("PhoneNumber", user.getPhoneNumber());
            session.setAttribute("Address", user.getAddress());
            session.setAttribute("user", user);
            
            response.sendRedirect("cart.jsp");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
