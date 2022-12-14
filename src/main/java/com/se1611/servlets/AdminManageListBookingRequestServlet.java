/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1611.servlets;

import com.se1611.bookingRequest.BookingRequestDAO;
import com.se1611.bookingRequest.BookingRequestDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class AdminManageListBookingRequestServlet extends HttpServlet {

    private final String RESULT_BOOKING_REQUEST = "adminManageListBookingRequestPage";

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
        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");
        String url = (String) siteMap.get((RESULT_BOOKING_REQUEST));

        try {
            String searchValue = request.getParameter("txtSearchBookRequest");
            BookingRequestDAO bookingRequestDAO = new BookingRequestDAO();

            List<BookingRequestDTO> listBookingRequest = bookingRequestDAO.getListBookingRequest();
            if (!listBookingRequest.isEmpty()) {
                request.setAttribute("LIST_BOOKING_REQUESTS", listBookingRequest);
            }
            if (searchValue != null && searchValue.trim().length() > 0) {
                listBookingRequest = bookingRequestDAO.searchBookRequest(searchValue);
                request.setAttribute("LIST_BOOKING_REQUESTS", listBookingRequest);
            }
        } catch (SQLException e) {
            log("AdminManageListBookingRequestServlet_SQL_ " + e.getMessage());
        } catch (NamingException e) {
            log("AdminManageListBookingRequestServlet_NamingExceptionNamingException " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

    }
    
//    public static void main(String[] args)
//            throws NamingException, SQLException {
//        BookingRequestDAO bookingRequestDAO = new BookingRequestDAO();
//        List<BookingRequestDTO> listBookingRequest = bookingRequestDAO.getListBookingRequest();
//        if (!listBookingRequest.isEmpty()) {
//            System.out.println(listBookingRequest);
//        }
//    }

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
