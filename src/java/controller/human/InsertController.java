/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.human;

import dal.DBContext;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Human;
import model.HumanType;
/**
 *
 * @author HELLO
 */
public class InsertController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DBContext db = new DBContext();
        ArrayList<HumanType> types = db.getTypes();
        request.setAttribute("types", types);
        request.getRequestDispatcher("../view/human/insert.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String idStr = request.getParameter("humanid");
        String name = request.getParameter("humanname");
        String genderStr = request.getParameter("humangender");
        String dobStr = request.getParameter("humandob");
        String typeidStr = request.getParameter("typeid");

        // Parse the id and typeid to integers
        int id = Integer.parseInt(idStr);
        int typeid = Integer.parseInt(typeidStr);

        // Parse the gender to a boolean
        boolean gender = genderStr.equalsIgnoreCase("Male");

        // Parse the date
        Date dob = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dob = (Date) sdf.parse(dobStr);
        } catch (ParseException e) {
            response.getWriter().println("Error parsing date: " + e.getMessage());
            return;
        }

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlDob = new java.sql.Date(dob.getTime());

        // Connect to the database and insert the new human
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Human";
            String username = "sa";
            String password = "abc123";

            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO Human (humanid, humanname, humandob, humangender, typeid) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setDate(3, sqlDob);
            pstmt.setBoolean(4, gender);
            pstmt.setInt(5, typeid);

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            response.getWriter().println("Error inserting data: " + e.getMessage());
            return;
        }
        // Redirect to the list page
        response.sendRedirect("list.jsp");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
