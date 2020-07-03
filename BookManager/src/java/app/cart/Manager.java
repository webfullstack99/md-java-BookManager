/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.cart;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.CartList;
import main.Cart;
import Helper.Helper;
import Helper.Template;
import java.util.ArrayList;

/**
 *
 * @author Asus ZenBook
 */
@WebServlet(name = "cart/manager", urlPatterns = {"/cart/index"})
public class Manager extends HttpServlet implements app{    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // set params
        request.setAttribute("controller", app.controller);
        PrintWriter out     = response.getWriter();
        
        try{            
            CartList list   = CartList.getInstance();
            list.setTempList(list.getList());
//            list.str    = "";
//            for (int i=0; i<list.getTempList().size(); i++){
//                for (int j =0; j< list.getTempList().get(i).getQtyArr().size(); j++){                    
//                    list.str   += list.getTempList().get(i).getQtyArr().get(j)+"\n";
//                }                
//                list.str    +="\n";
//            }
            solveSearch(request);
            solveSort(request);            
            request.setAttribute("list", list);
            
            // check error
            if (!Template.isStringEmpty(list.str)) throw new Exception(list.str);
            request.getRequestDispatcher(app.viewPrefix+"/index.jsp").forward(request, response);            
        }catch (Exception e){
            out.println(e.getMessage());
        }
    }
    
    
    // search
    public void solveSearch(HttpServletRequest request){    
        CartList list   = CartList.getInstance();
        String searchField  = Helper.getParam(request, "search_field");
        String searchValue  = Helper.getParam(request, "search_value");
        list.search(searchField, searchValue);       
    }
    
    // sort
    public void solveSort(HttpServletRequest request){
        CartList list   = CartList.getInstance();
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
