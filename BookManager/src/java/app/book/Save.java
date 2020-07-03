/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.book;

import app.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Book;
import main.BookList;
import Helper.Helper;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus ZenBook
 */
@WebServlet(name = "book/save", urlPatterns = {"/book/save"})
public class Save extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // CODE HRERE!
        PrintWriter out     = response.getWriter();
        int result          = -1;
        int id              = Helper.getId(request);
        String linkRedirect = Helper.getLinkRedirect(id);
        String message      = "";     
        
        try{
            BookList list   = BookList.getInstance();
            String data[]   = Helper.createBookDataByRequest(request);
            // add
            if (id < 0){            
                //throw new Exception(list.getList().size()+"");
                Book item   = new Book(list.getNewId()+"", data[0], data[1], data[2], data[3], data[4]);                
                item.setCreated(Helper.getDateTimeString());
                
                result      = list.insert(item);
                if (result < 0) message = "insert-fail";
                else message = "insert-success";
            // edit
            }else{
                Book item   = list.getItemById(id);
                item.setMultiValue(id+"", data[0], data[1], data[2], data[3], data[4]);
                item.setModified(Helper.getDateTimeString());              
                
                result      = list.update(list.getPositionById(id), item);
                if (result < 0) message = "update-fail";
                else message = "update-success";
            }                                
            HttpSession session     = request.getSession();        
            session.setAttribute("message", message);
            response.sendRedirect(request.getContextPath()+app.prefix+"/"+linkRedirect);
        }catch (Exception e){            
            message     = "save-fail";
            out.println(e.getMessage());
        } 

    }
   
    public String getLinkRedirect(int id){
        String linkRedirect         = "form?action=add";
        if (id > 0) linkRedirect    = "form?action=edit&id="+id; 
        return linkRedirect;
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
