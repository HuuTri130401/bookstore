/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1611.servlets;

import com.se1611.employees.CreateEmployeeError;
import com.se1611.employees.EmployeeDAO;
import com.se1611.employees.EmployeeDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class AdminCreateNewEmployeeServlet extends HttpServlet {

    private final String ADMIN_MANAGE_LIST_EMPLOYEE = "adminShowListEmployees";
    private final String CREATE_NEW_EMPLOYEE = "adminCreateEmployeePage";

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

        //GET SITEMAP
        Properties siteMap = (Properties) request.getServletContext().getAttribute("SITE_MAP");
        //getRequest Parameter
        String account_Id = request.getParameter("txtAccountID");
        String password = request.getParameter("txtPassword");
        String fullName = request.getParameter("txtFullName");
        String phone = request.getParameter("txtPhone");
        String address = request.getParameter("txtAddress");
        String gender = request.getParameter("radioGender");
        String role = request.getParameter("radioRole");
        boolean status_Employee = true;

        String confirmPassword = request.getParameter("txtConfirmPassword");

        String url = siteMap.getProperty(CREATE_NEW_EMPLOYEE);
        boolean errFound = true;

        CreateEmployeeError employeeErrors = new CreateEmployeeError();
        try {
//            if (account_Id.trim().length() < 5 || account_Id.trim().length() > 30) {
//                errFound = false;
//                employeeErrors.setAccount_IdError("Account ID length has [5..30] chars");
//            }
            if (password.trim().length() < 5 || password.trim().length() > 30) {
                errFound = false;
                employeeErrors.setPasswordError("Password length has [5..20] chars");
            } else if (!confirmPassword.trim().equals(password.trim())) {
                errFound = false;
                employeeErrors.setConfirmPasswordError("Confirm Password does not match Password");
            }
//            if (fullName.trim().length() < 2 || fullName.trim().length() > 30) {
//                errFound = false;
//                employeeErrors.setAccount_IdError("Full Name length has [2..30] chars");
//            }
            int count = String.valueOf(phone).length();
            if (count < 10 || count > 11) {
                errFound = false;
                employeeErrors.setPhoneError("Phone length has [10 or 11] chars");
            }
//            if (address.trim().length() < 2 || address.trim().length() > 20) {
//                errFound = false;
//                employeeErrors.setAddressError("Address length has [2..20] chars");
//            }
//            if (gender.trim().length() < 1 || gender.trim().length() > 7) {
//                errFound = false;
//                employeeErrors.setGenderError("Gender length has [1..7] chars");
//            }
//            if (role.trim().length() < 2 || role.trim().length() > 7) {
//                errFound = false;
//                employeeErrors.setRoleError("Role length has [2..7] chars");
//            }
            if (errFound) {
                EmployeeDAO employeeDAO = new EmployeeDAO();
                EmployeeDTO employeeDTO = new EmployeeDTO(account_Id, password, fullName, phone, address, gender, role, status_Employee);

                boolean checkDuplicateAccountId = employeeDAO.checkAcoountDuplicate(account_Id);
                if (checkDuplicateAccountId) {
                    employeeErrors.setAccount_IdError("Duplicate AccountID: " + account_Id + "!");
                    request.setAttribute("INSERT_EMPLOYEE_MSG", employeeErrors);
                    url = CREATE_NEW_EMPLOYEE;
                } else {
                    boolean createEmployee = employeeDAO.addEmployeeAccount(employeeDTO);
                    if (createEmployee) {
                        url = ADMIN_MANAGE_LIST_EMPLOYEE;
                        request.setAttribute("INSERT_EMPLOYEE_MSG", "Create New Employee Success !");
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    }
                }
            } else {
                url = CREATE_NEW_EMPLOYEE;
                employeeErrors.setEmployeeErrorMsg("Can not Create New Employee !");
                request.setAttribute("INSERT_EMPLOYEE_MSG", employeeErrors);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
        } catch (SQLException e) {
            log("Account Create New Employee Servlet _ SQLException_ " + e.getMessage());
        } catch (NamingException e) {
            log("Account Create New Employee Servlet _ NamingException_ " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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