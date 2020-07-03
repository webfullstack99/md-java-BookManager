/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.cart;
import app.book.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import main.*;
import Helper.*;

/**
 *
 * @author Asus ZenBook
 */
@WebServlet(name = "cart/ajaxsave", urlPatterns = {"/cart/ajaxsave"})
public class AjaxSave extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        PrintWriter out     = response.getWriter();
        
        // CODE HRERE!
        String action   = Helper.getParam(request, "action");
        String type     = Helper.getParam(request, "type");
        int id          = Helper.convertStringToInt(Helper.getParam(request, "id"));

        int result      = -1;
        String newType  = "";
        String xhtml    = "";
   
        try{
            if (action != null && type != null && id > 0){
                   switch(action){
                    case "changeStatus":                        
                        if (type.equals("active")) newType = "inactive";
                        else if (type.equals("inactive")) newType = "active";
                        else newType="inative";          
                        
                        CartList list   = CartList.getInstance();                        
                        Cart item       = list.getItemById(id);
                        item.setStatus(newType);                       
                        item.setModified(Helper.getDateTimeString());
                        result  = list.update(list.getPositionById(id), item);
                        xhtml   = Template.showItemStatus(newType, id);
                        break;
                        
                    case "getBookRowInCartHtml":    
                        String totalStr        = "";
                        String totalPrice;
                        int price;
                        int qty             = Integer.parseInt(Helper.getParam(request, "qty"));
                        int order           = Integer.parseInt(Helper.getParam(request, "lastOrder"))+1;
                        
                        BookList bookList   = BookList.getInstance();
                        Book book           = bookList.getItemById(id);
                        xhtml               = Template.showBookRowInCart(order, qty, book); 
                        if (!Template.isStringEmpty(xhtml) && order == 1){
                            totalStr        = Template.showTotalPriceInCart(qty * book.getPrice());
                            xhtml           += totalStr;
                        }
                        if (!Template.isStringEmpty(xhtml)) result = 1;
                        break;
                } 
            }
            JSONObject json = new JSONObject();
            if (result > 0){
                json.put("message", "success");
                json.put("xhtml", xhtml);
            }else{
                json.put("message", "fail");
            }
            out.print(json);
        }catch (Exception e){
            out.print("error");
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
