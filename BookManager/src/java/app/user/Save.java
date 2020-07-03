/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.user;

import app.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.User;
import main.UserList;
import Helper.Helper;
import Helper.Template;
import java.util.ArrayList;

/**
 *
 * @author Asus ZenBook
 */
@WebServlet(name = "user/save", urlPatterns = {"/user/save"})
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
            
            UserList list           = UserList.getInstance();     
            ArrayList<String> data  = Helper.createUserDataByRequest(request, id);
            String password         = Helper.md5(Helper.solvePassword(request,id)); 
            // add
            if (id < 0){                 
                User item   = new User(list.getNewId()+"", data.get(0), data.get(1), data.get(2), data.get(3), password, data.get(4), data.get(5));                
                item.setCreated(Helper.getDateTimeString());
                result      = list.insert(item);
                if (result < 0) message = "insert-fail";
                else message = "insert-success";
            // edit
            }else{
                User item   = list.getItemById(id);
                String pass = item.getPassword();
                item.setMultiValue(id+"", data.get(0), data.get(1), data.get(2), data.get(3), password, data.get(4), data.get(5));
                item.setPasswordIfEmpty(pass);
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
