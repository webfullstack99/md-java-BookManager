/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.book;
import app.*;
import main.BookList;
import main.Book;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.CategoryList;
import Helper.Helper;

/**
 *
 * @author Asus ZenBook
 */
@WebServlet(name = "book/form", urlPatterns = {"/book/form"})
public class Form extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // CODE HRERE!
        PrintWriter out     = response.getWriter();
        int id              = Helper.getId(request);
        
        try{
            BookList list   = BookList.getInstance();     
            CategoryList catList = CategoryList.getInstance();
            String action   = request.getParameter("action");
            if (action.equals("add")){      
                
            }else if (action.equals("edit")){
                Book item   = list.getItemById(id);   
                if (item == null) throw new Exception();
                request.setAttribute("item", item);     
            }else throw new RuntimeException();        
            request.setAttribute("catList", catList);
            request.setAttribute("prefix", app.prefix);
            request.getRequestDispatcher(app.viewPrefix+"/form.jsp").forward(request, response);
        }catch (Exception e){
            out.print(e.getMessage());
            //response.sendRedirect(request.getContextPath()+app.prefix+"/index");
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
