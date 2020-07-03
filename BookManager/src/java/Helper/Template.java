/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;
import java.text.DecimalFormat;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import main.Book;
import main.BookList;
import main.Category;
import main.CategoryList;
import main.Cart;
import main.CartList;
import main.User;
import main.UserList;
import Helper.Helper;
import config.Config;
import com.oracle.wls.shaded.org.apache.bcel.generic.CASTORE;

/**
 *
 * @author Asus ZenBook
 */

    
public class Template {
    
    // SHOW TABLE BODY 
    public static String showBookTableBody(HttpServletRequest request, BookList list) throws Exception{
        String xhtml    = "";
        if (!isListEmpty(list)){            
            int order   = 1;
            for (int i=0; i<list.getTempList().size(); i++){    
                Book item     = list.getTempList().get(i);
                int id          = item.getId();
                String title    = Highlight.highlight(Helper.getParam(request, "search_field"), Helper.getParam(request, "search_value"),"title", item.getTitle());
                String author   = Highlight.highlight(Helper.getParam(request, "search_field"), Helper.getParam(request, "search_value"),"author", item.getAuthor());
                String category = item.getCategory();
                String created      = showItemHistory(item.getCreated());
                String modified     = showItemHistory(item.getModified());
                String status       = showItemStatus(item.getStatus(), item.getId());
                String price       = Helper.number_format(item.getPrice(), Config.moneyFormat);
                
                xhtml   += "<tr>\n" +
"                                <th scope=\"row\">"+order+"</th>\n" +
"                                <td>"+title+"</td>\n" +
"                                <td>"+author+"</td>\n" +
"                                <td>"+category+"</td>\n" +
"                                <td class=\"\">"+price+"</td>\n" +
"                                <td>"+status+"</td>\n" +
"                                <td>"+created+"</td>\n" +                        
"                                <td>"+modified+"</td>\n" +
"                                <td>\n" +
"                                    <a  href=\"form?action=edit&id="+id+"\" class=\"text-success edit-action\"><i class=\"fas fa-edit fa-fw\"></i></a>\n" +
                                    "<a  href=\"delete?id="+id+"\" class=\"text-danger delete-action\"><i class=\"fas fa-trash-alt fa-fw\"></i></a>\n" +
                        
"                                </td>\n" +
//"                                <td>"+id+"</td>\n" +
"                            </tr>";
                order++;
            }
        }
        return xhtml;
    }
    
    public static String showCategoryTableBody(HttpServletRequest request, CategoryList list) throws Exception{
        String xhtml    = "";
        if (list.getList().size() > 0){            
            int order   = 1;
            for (int i=0; i<list.getTempList().size(); i++){
                Category item       = list.getTempList().get(i);
                int id              = item.getId();
                String name     = Highlight.highlight(Helper.getParam(request, "search_field"), Helper.getParam(request, "search_value"),"name", item.getName());
                String status   = Template.showItemStatus(item.getStatus(), item.getId());      
                String created      = showItemHistory(item.getCreated());
                String modified     = showItemHistory(item.getModified());
                
             
                xhtml   += "<tr>\n" +
"                                <th scope=\"row\">"+order+"</th>\n" +
"                                <td>"+name+"</td>\n" +
"                                <td>"+status+"</td>\n" +
"                                <td>"+created+"</td>\n" +
"                                <td>"+modified+"</td>\n" +
"                                <td>\n" +
"                                    <a  href=\"form?action=edit&id="+id+"\" class=\"text-success edit-action\"><i class=\"fas fa-edit fa-fw\"></i></a>\n" +
                                    "<a  href=\"delete?id="+id+"\" class=\"text-danger delete-action\"><i class=\"fas fa-trash-alt fa-fw\"></i></a>\n" +                        
"                                </td>\n" +
//"                                <td>"+id+"</td>\n" +
"                            </tr>";
                order++;
            }
        }
        return xhtml;
    }
    
    public static String showUserTableBody(HttpServletRequest request, UserList list) throws Exception{
        String xhtml    = "";
        if (list.getList().size() > 0){            
            int order   = 1;
            for (int i=0; i<list.getTempList().size(); i++){
                User item           = list.getTempList().get(i);
                int id              = item.getId();
                String username     = Highlight.highlight(Helper.getParam(request, "search_field"), Helper.getParam(request, "search_value"),"username", item.getUsername());
                String email        = Highlight.highlight(Helper.getParam(request, "search_field"), Helper.getParam(request, "search_value"),"email", item.getEmail());
                String fullname     = Highlight.highlight(Helper.getParam(request, "search_field"), Helper.getParam(request, "search_value"),"fullname", item.getFullname());
                String phone        = Highlight.highlight(Helper.getParam(request, "search_field"), Helper.getParam(request, "search_value"),"phone", item.getPhone()+"");
                String levelArr[]   = Config.levelArr;
                String level        = Template.showSelect("level", item.getId(), levelArr, item.getLevel());
                String status       = Template.showItemStatus(item.getStatus(), item.getId());      
                String created      = showItemHistory(item.getCreated());
                String modified     = showItemHistory(item.getModified());
                
             
                xhtml   += "<tr>\n" +
"                                <th scope=\"row\">"+order+"</th>\n" +
"                                <td>\n" +
"                                       <strong>Username:&nbsp;</strong>"+username+"<br/>\n" +
"                                       <strong>Email:&nbsp;</strong>"+email+"</strong><br/>\n" +
"                                       <strong>Fullname:&nbsp;</strong>"+fullname+"<br/>\n" +
"                                </td>  \n" +
"                                <td>"+phone+"</td>\n" +
"                                <td>"+level+"</td>\n" +
"                                <td>"+status+"</td>\n" +
"                                <td>"+created+"</td>\n" +
"                                <td>"+modified+"</td>\n" +
"                                <td>\n" +
"                                    <a  href=\"form?action=edit&id="+id+"\" class=\"text-success edit-action\"><i class=\"fas fa-edit fa-fw\"></i></a>\n" +
                                    "<a  href=\"delete?id="+id+"\" class=\"text-danger delete-action\"><i class=\"fas fa-trash-alt fa-fw\"></i></a>\n" +                        
"                                </td>\n" +
//"                                <td>"+id+"</td>\n" +
"                            </tr>";
                order++;
            }
        }
        return xhtml;
    }
    
    public static String showCartTableBody(HttpServletRequest request, CartList list) throws Exception{
        String xhtml    = "";
        if (list.getList().size() > 0){            
            int order   = 1;
            for (int i=0; i<list.getTempList().size(); i++){
                Cart item       = list.getTempList().get(i);
                int id          = item.getId();
                String cartId   = Helper.strpad(id+"", Config.cartIdLimit, "0");
                int totalQty    = item.getTotalQty();
                String username = Highlight.highlight(Helper.getParam(request, "search_field"), Helper.getParam(request, "search_value"),"username", item.getUser().getUsername());
                String totalPrice  = Helper.number_format(item.getTotalPrice(), Config.moneyFormat);
                String status   = Template.showItemStatus(item.getStatus(), item.getId());      
                String created      = showItemHistory(item.getCreated());
                String modified     = showItemHistory(item.getModified());
                
             
                xhtml   += "<tr>\n" +
"                                <th scope=\"row\">"+order+"</th>\n" +
"                                <td><span class=\"text-primary\"><strong>#"+cartId+"</strong></span></td>\n" +
"                                <td>"+totalQty+"</td>\n" +
"                                <td>"+totalPrice+"</td>\n" +
"                                <td>"+username+"</td>\n" +
"                                <td>"+status+"</td>\n" +
"                                <td>"+created+"</td>\n" +
"                                <td>"+modified+"</td>\n" +
"                                <td>\n" +
"                                    <a  href=\"form?action=view&id="+id+"\" class=\"text-info edit-action\"><i class=\"fas fa-info-circle fa-lg fa-fw\"></i></a>\n" +
                                    "<a  href=\"delete?id="+id+"\" class=\"text-danger delete-action\"><i class=\"fas fa-trash-alt fa-fw\"></i></a>\n" +                        
"                                </td>\n" +
//"                                <td>"+id+"</td>\n" +
"                            </tr>";
                order++;
            }
        }
        return xhtml;
    }
    
    // CART
    public static String showBookRowInCart(int order, int qty, Book item){
        String xhtml    = "";
        if (item != null){
            String title    = item.getTitle();
            String price    = Helper.number_format(item.getPrice(), Config.moneyFormat);

            xhtml   =  "<tr book_id=\""+item.getId()+"\">\n" +
"                               <th field=\"order\" scope=\"row\">"+order+"</th>\n" +
"                               <td>"+title+"</td>\n" +
"                               <td field=\"quantity\"><input class=\"cart-qty-input form-control\" type=\"number\" min=\"1\" max=\"10\" value=\""+qty+"\"/></td>\n" +
"                               <td field=\"price\" value=\""+item.getPrice()+"\">"+price+"</td>\n" +
"                               <td class=\"text-center\">\n" +
"                                   <a item-id=\""+item.getId()+"\" class=\"delete-action text-danger\" href=\"#\"><i class=\"fas fa-trash-alt\"></i></a>\n" +
"                               </td>\n" +
"                           </tr>";
        }
        return xhtml;
    }
    
    // show total price
    public static String showTotalPriceInCart(int totalPrice){
        String xhtml            = "";
        String totalPriceStr    = Helper.number_format(totalPrice, Config.moneyFormat);
        xhtml   =          "<tr id=\"total-price-row\">\n" +
"                               <td colspan=\"3\" class=\"text-right\">Total price</td>\n" +
"                               <td colspan=\"2\" class=\"text-center\"><strong id=\"total-price\" value=\""+totalPrice+"\">"+totalPriceStr+"</strong></strong><strong> vnd</strong></td>\n" +
"                           </tr>";
        return xhtml;
    }
   
    // SHOW ITEM THINGS    
    public static String showItemStatus(String status, int id){
        String xhtml    = "";
        String btnClass    = "";
        String content  = "";
        if (status.equals("active")){
            btnClass    = "btn btn-success";
            content     = "Active";
        }else if (status.equals("inactive")){
            btnClass    = "btn btn-warning text-light";
            content     = "In active";
            xhtml   = "<a href=\"#\" class=\"btn btn-warning text-light\">In active</a>";
        }else{
            btnClass    = "btn btn-secondary";
            content     = "Undefined";
        }        
        xhtml   = "<a id=\""+id+"\" value=\""+status+"\" href=\"#\" class=\"status-btn "+btnClass+"\">"+content+"</a>";
        return xhtml;
    }
    
    public static String showHeading(String heading){
        String xhtml    = "";
        if (!heading.trim().equals("")){
            xhtml       = "<div class=\"row\">\n" +
                "            <div class=\"col-12 text-primary\">\n" +
                "                <h1 class=\"text-center mt-5\">"+heading+"</h1>\n" +
                "            </div>\n" +
                "        </div>";
        }
        return xhtml;
    }
    
    public static String showItemHistory(String time){
        String xhtml = "";
        try{
            if (!isStringEmpty(time)){
                LocalDateTime dateTime   = Helper.convertStringToDateTime(time);

                // display on screen
                String dateStr = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String timeStr = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")); 
                // long time
                //xhtml   = dateStr+"<br/>"+"<small text=\"muted\">"+timeStr+"</small>";
                // short time
                xhtml   = "<div class=\"d-flex align-items-center\"><i class=\"fal fa-clock\"></i>&nbsp;"+dateStr+"</div>";

            }
        }catch (Exception e){
            xhtml   = "";
        }   
            
        return xhtml;
    }
    
    public static String showCatFilter(HttpServletRequest request, CategoryList list){
        String xhtml    = "";
        if (list.getList().size() > 0){
            String selected     = "";
            int catValue;
            try{
                catValue    = Integer.parseInt(Helper.getParam(request, "cat_filter"));
            }catch (Exception e){
                catValue    = -1;
            }
            
            xhtml   = "<select id=\"cat-filter\" class=\"custom-select\"><option value=\"default\">All category</option>";            
            for (int i=0;  i< list.getList().size(); i++){
                if (list.getList().get(i).getId() == catValue) selected = "selected=\"selected\"";
                else selected = "";
                
                xhtml   += "<option "+selected+" value=\""+list.getList().get(i).getId()+"\">"+list.getList().get(i).getName()+"</option>";
            }            
            xhtml   +="</select>";
        }
        return xhtml;
    }
    
    public static String showCatSelect(CategoryList list, String selectedValue){
        String xhtml    = "";
        String selected = "";
        int catValue;
        xhtml   = "<select name=\"category\" class=\"custom-select form-group col-lg-10\">";            
        for (int i=0; i< list.getList().size(); i++){
            if (list.getList().get(i).getName().trim().toLowerCase().equals(selectedValue.trim().toLowerCase())) selected = "selected=\"selected\"";
            else selected = "";
            xhtml   += "<option "+selected+" value=\""+list.getList().get(i).getName()+"\">"+list.getList().get(i).getName()+"</option>";
        }            
        xhtml   +="</select>";
        return xhtml;
    }
    
    public static String showSelect(String name, int id, String data[], String selected){
        String xhtml    = "";
        if (data.length > 0){
            String selectedXhtml    = "";
            xhtml   = "<select id=\""+id+"\" class=\""+name+"-select form-control\">";
            for (int i=0; i< data.length; i++){
                if (data[i].equals(selected)) selectedXhtml = "selected=\"selected\"";
                else selectedXhtml = "";
                xhtml   += "<option "+selectedXhtml+" value=\""+data[i]+"\">"+Helper.ucfirst(data[i])+"</option>";
            }
            xhtml   += "</select>";
        }
        return xhtml;
    }       
    
    public static String showFormSelect(String name, String data[], String selectedValue){
        String xhtml    = "";
        String selected = "";    
        xhtml   += "<select name=\""+name+"\" class=\"custom-select form-group col-lg-10\">\n";
            for (int i=0; i < data.length; i++){
                if (data[i].equals(selectedValue)) selected = "selected=\"selected\"";
                else selected = "";
                xhtml   += "<option "+selected+" value=\""+data[i]+"\">"+Helper.ucfirst(data[i])+"</option>";
            }
        xhtml   += "</select>";
        return xhtml;
    }
    
    public static String showMessage(String message){
        String xhtml    = "";
        if (!message.trim().equals("")){
            String content  = "";
            String classes  = "alert alert-success";
            switch(message){
                case "update-success":
                    content     = "Update item successfully";
                    break;
                case "insert-success":
                    content     = "Insert item successfully";
                    break;
                case "save-success":
                    content     = "Save item successfully";
                    break;
                case "delete-success":
                    content     = "Delete item successfully";
                    break;
                case "insert-fail": case "save-fail": case "delete-fail": case "update-fail":
                    content     = "Something went wrong";
                    classes     = "alert alert-danger";
                    break;             
            }
            if (!content.trim().equals("")){
                xhtml   = "<div id=\"alert\" class=\"row\">\n" +
                "            <div class=\"col-12\">\n" +
                "                <div class=\""+classes+" collapse show active\" role=\"alert\">\n" +
                "                  <strong>" + content + "</strong>"+
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";
            } 
        }
        return xhtml;
    }
    
    // SUPPORTED FUNCTIONS ==============    
    public static boolean isListEmpty(BookList list){
        if (list.getList().size() <1) return true;
        return false;
    }
    
    public static boolean isStringEmpty(String string){
        if (string.trim().equals("")) return true;
        return false;
    }
}
