/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.book;

import app.*;
import com.sun.javafx.util.TempState;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.BookList;
import main.CategoryList;
import Helper.Helper;
import main.Book;
import main.Category;
import java.util.ArrayList;
import Helper.Template;

/**
 *
 * @author Asus ZenBook
 */
@WebServlet(name = "book/manager", urlPatterns = {"/book/index"})
public class Manager extends HttpServlet implements app{    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // set params
        request.setAttribute("controller", app.controller);
        
        PrintWriter out     = response.getWriter();
        try{
            BookList list   = BookList.getInstance();
            list.setTempList(list.getList()); 
           
            CategoryList catList = CategoryList.getInstance();
         
            solveSearch(request);
            solveSort(request);
            solveFilter(request);
            
            request.setAttribute("list", list);
            request.setAttribute("catList", catList); 
            
            // check error
            if (!Template.isStringEmpty(list.str)) throw new Exception(list.str);
            request.getRequestDispatcher(app.viewPrefix+"/index.jsp").forward(request, response);
        }catch (Exception e){
            out.println(e.getMessage());
        }
    }
    
    // search
    public void solveSearch(HttpServletRequest request){    
        BookList list   = BookList.getInstance();
        String searchField  = Helper.getParam(request, "search_field");
        String searchValue  = Helper.getParam(request, "search_value");
        list.search(searchField, searchValue);       
    }
    
    // sort
    public void solveSort(HttpServletRequest request){
        BookList list   = BookList.getInstance();
        String sortField  = (Helper.getParam(request, "sort_field") != null) ? Helper.getParam(request, "sort_field") : "id";
        String sortOrder  = (Helper.getParam(request, "sort_order") != null) ? Helper.getParam(request, "sort_order") : "desc";
        list.sort(sortField, sortOrder);           
    }
    
    // filter
    public void solveFilter(HttpServletRequest request){
        BookList list   = BookList.getInstance();     
        int catId;
        try{
            catId   = Integer.parseInt(Helper.getParam(request, "cat_filter"));
        }catch(Exception e){
            catId   = -1;
        }            
        list.fillCat(catId);
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
