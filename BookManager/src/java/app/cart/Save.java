/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.cart;
import app.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.Cart;
import main.CartList;
import Helper.Helper;
import java.util.ArrayList;

/**
 *
 * @author Asus ZenBook
 */
@WebServlet(name = "cart/save", urlPatterns = {"/cart/save"})
public class Save extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // CODE HRERE!
        PrintWriter out     = response.getWriter();
        int result          = -1;
        int id              = Helper.getId(request);
        String linkRedirect = getLinkRedirect(id);
        String message      = "";     
        
        try{
            CartList list   = CartList.getInstance();
            ArrayList<String> data       = Helper.createCartDataByRequest(request);
            // add
            if (id < 0){       
                Cart item   = new Cart(list.getNewId()+"", data.get(0), data.get(1), "7", "inactive");
                item.setCreated(Helper.getDateTimeString());
                result      = list.insert(item);
                if (result < 0) message = "insert-fail";
                else message = "insert-success";
            // edit
            }else{
                Cart item   = list.getItemById(id);
                item.setMultiValue(id+"", data.get(0), data.get(1), item.getUser().getId()+"", "inactive");
                item.setModified(Helper.getDateTimeString());
                result      = list.update(list.getPositionById(id), item);
                if (result < 0) message = "update-fail";
                else message = "update-success";
            }                   
        }catch (Exception e){            
            message     = "save-fail";
            out.println(e.getMessage());
        } 
        HttpSession session     = request.getSession();        
        session.setAttribute("message", message);
        response.sendRedirect(request.getContextPath()+app.prefix+"/"+linkRedirect);


    }
   
    public String getLinkRedirect(int id){
        String linkRedirect         = "form?action=add";
        if (id > 0) linkRedirect    = "form?action=view&id="+id; 
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
