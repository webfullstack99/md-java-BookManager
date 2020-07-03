/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.UserList;
import Helper.Helper;
import main.User;
import Helper.Template;
import java.util.ArrayList;

/**
 *
 * @author Asus ZenBook
 */
@WebServlet(name = "user/manager", urlPatterns = {"/user/index"})
public class Manager extends HttpServlet implements app{    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // set params
        request.setAttribute("controller", app.controller);
        PrintWriter out     = response.getWriter();
        
        try{
            
            UserList list   = UserList.getInstance();
            list.setTempList(list.getList());
            
            solveSearch(request);
            solveSort(request);
            request.setAttribute("list", list);
            
            // check
            if (!Template.isStringEmpty(list.str)) throw new Exception(list.str);
            
            request.getRequestDispatcher(app.viewPrefix+"/index.jsp").forward(request, response);            
        }catch (Exception e){
            out.println(e.getMessage());
        }
    }
    
    
    // search
    public void solveSearch(HttpServletRequest request){    
        UserList list   = UserList.getInstance();
        String searchField  = Helper.getParam(request, "search_field");
        String searchValue  = Helper.getParam(request, "search_value");
        list.search(searchField, searchValue);       
    }
    
    // sort
    public void solveSort(HttpServletRequest request){
        UserList list   = UserList.getInstance();
        String sortField  = (Helper.getParam(request, "sort_field") != null) ? Helper.getParam(request, "sort_field") : "id";
        String sortOrder  = (Helper.getParam(request, "sort_order") != null) ? Helper.getParam(request, "sort_order") : "desc";
        list.sort(sortField, sortOrder);           
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
