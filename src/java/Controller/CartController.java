/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import entities.Cart;
import entities.CartItem;
import entities.Order;
import entities.OrderDetail;
import entities.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import models.DAOCart;
import models.DAOCartItem;
import models.DAOOrder;
import models.DAOOrderDetail;
import models.DAOProduct;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartURL"})
public class CartController extends HttpServlet {

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
        HttpSession session = request.getSession();
        int customerID = (int) session.getAttribute("userID");
        DAOCart dao = new DAOCart();
        DAOCartItem daoItem = new DAOCartItem();
        DAOProduct daoPro = new DAOProduct();
        DAOOrder daoOrder = new DAOOrder();
        DAOOrderDetail daoOD = new DAOOrderDetail();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service == null) {
                service = "showCart";
            }
            if (customerID == 0) {
                // Nếu customerID là null hoặc không hợp lệ, xử lý lỗi
                request.setAttribute("error", "You need to be logged in to view your cart.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            if (service.equals("search")) {
                String query = request.getParameter("query");
                if (query == null || query.trim().isEmpty()) {
                    request.setAttribute("error", "Please enter a search term!");
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                    return;
                }
                Cart cart = dao.getCartByCustomerID(customerID);
                if (cart == null) {
                    request.setAttribute("error", "Your cart is empty!");
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                    return;
                }
                List<CartItem> cartItems = dao.getCartItemsByCartID(cart.getCartID());

                // Lọc các sản phẩm theo từ khóa
                List<CartItem> filteredCartItems = cartItems.stream()
                        .filter(item -> item.getProduct().getName().toLowerCase().contains(query.toLowerCase())
                        || String.valueOf(item.getProduct().getPrice()).contains(query))
                        .toList();

                if (filteredCartItems.isEmpty()) {
                    request.setAttribute("message", "No results found.");
                }

                // Tính tổng giá trị sau tìm kiếm
                double totalOrderPrice = filteredCartItems.stream()
                        .mapToDouble(CartItem::getTotalPrice)
                        .sum();

                // Truyền danh sách sản phẩm và tổng giá trị vào request
                request.setAttribute("cartItems", filteredCartItems); // Cập nhật danh sách đã lọc
                request.setAttribute("totalOrderPrice", totalOrderPrice);
                request.setAttribute("query", query);

                request.getRequestDispatcher("cart.jsp").forward(request, response);
            }

            if (service.equals("showCart")) {
                Cart cart = dao.getCartByCustomerID(customerID);
                if (cart != null) {
                    List<CartItem> cartItems = dao.getCartItemsByCartID(cart.getCartID());
                    Double totalOrderPrice = 0.0;
                    for (CartItem item : cartItems) {
                        Product product = daoPro.getProductById(item.getProductID());
                        item.setProduct(product);
                        totalOrderPrice += item.getTotalPrice();
                    }

                    request.setAttribute("cartItems", cartItems);
                    request.setAttribute("totalOrderPrice", totalOrderPrice);
                } else {
                    request.setAttribute("showMessage", "Your cart is empty!");
                }
                request.setAttribute("cart", cart);
                request.getRequestDispatcher("cart.jsp").forward(request, response);
            }
            if (service.equals("updateQuantity")) {
                int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
                int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));

                if (newQuantity > 0) {
                    CartItem cartItem = daoItem.getCarItemtById(cartItemId);
                    cartItem.setQuantity(newQuantity);
                    double newTotalPrice = cartItem.getPrice() * newQuantity;  // Tính lại giá trị tổng của item
                    cartItem.setTotalPrice(newTotalPrice);

                    daoItem.updateCartItem(cartItem);
                }

                Cart cart = dao.getCartByCustomerID(customerID);
                List<CartItem> cartItems = dao.getCartItemsByCartID(cart.getCartID());
                double totalOrderPrice = 0.0;
                for (CartItem item : cartItems) {
                    totalOrderPrice += item.getTotalPrice();
                }

                response.getWriter().write(String.format("%.2f", totalOrderPrice));
            }

            if (service.equals("removeItem")) {
                int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
                dao.deleteCart(cartItemId);
                response.sendRedirect("CartController?service=viewCart");
            }
            if (service.equals("checkOut")) {
                Cart cart = dao.getCartByCustomerID(customerID);
                if (cart != null) {
                    List<CartItem> cartItems = dao.getCartItemsByCartID(cart.getCartID());
                    double totalOrderPrice = 0.0;

                    for (CartItem item : cartItems) {
                        Product product = daoPro.getProductById(item.getProductID());
                        item.setProduct(product);
                        totalOrderPrice += item.getTotalPrice();
                    }

                    String fullName = (String) session.getAttribute("Name");
                    String email = (String) session.getAttribute("Email");
                    String PassHash = (String) session.getAttribute("PassHash");
                    String mobile = (String) session.getAttribute("PhoneNumber");
                    String address = (String) session.getAttribute("Address");
                        
                    request.setAttribute("cartItems", cartItems);
                    request.setAttribute("totalOrderPrice", totalOrderPrice);
                    request.setAttribute("fullName", fullName);
                    request.setAttribute("email", email);
                    request.setAttribute("PassHash", PassHash);
                    request.setAttribute("mobile", mobile);
                    request.setAttribute("address", address);
                } else {
                    request.setAttribute("error", "Your cart is empty!");
                }
                request.getRequestDispatcher("checkout.jsp").forward(request, response);
            }
            if (service.equals("submitOrder")) {
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String address1 = request.getParameter("address1");
            String address2 = request.getParameter("address2");
            String message = request.getParameter("message");

            Cart cart = dao.getCartByCustomerID(customerID);
            if (cart == null || dao.getCartItemsByCartID(cart.getCartID()).isEmpty()) {
                request.setAttribute("error", "Your cart is empty or invalid!");
                request.getRequestDispatcher("checkout.jsp").forward(request, response);
                return;
            }

            Order order = new Order();
            order.setBuyerID(customerID);
            order.setShippingAddress(address1 + " " + address2);
            order.setMessage(message);
            order.setTotalPrice(cart.getTotalPrice());
            order.setOrderTime(new Date());
            order.setOrderStatus("Pending");

            int orderId = daoOrder.addOrder(order);
            if (orderId == -1) {
                request.setAttribute("error", "Order submission failed due to a technical error.");
                request.getRequestDispatcher("checkout.jsp").forward(request, response);
                return;
            }

            List<CartItem> cartItems = dao.getCartItemsByCartID(cart.getCartID());
            for (CartItem cartItem : cartItems) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderID(orderId);
                orderDetail.setProductID(cartItem.getProductID());
                orderDetail.setQuantity(cartItem.getQuantity());
                orderDetail.setProductPrice(cartItem.getPrice());
                daoOD.addOrderDetail(orderDetail);
            }

            dao.deleteCartItemsByCartID(cart.getCartID());
            dao.updateCartStatus(cart.getCartID(), "Completed");

            session.setAttribute("cart", null);  // Reset giỏ hàng
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("error", "Invalid action.");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
