/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static Helper.Template.isStringEmpty;
import java.util.ArrayList;
import main.User;
import java.security.MessageDigest; 
import java.text.DecimalFormat;


/**
 *
 * @author Asus ZenBook
 */



public class Helper{    
    
    /*********** MAIN DIFFERENCE **************/
    public static String[] createBookDataByRequest(HttpServletRequest request) throws Exception{
        String data[]   = new String[10];
        try{
            data[0]         = ucfirst(request.getParameter("title").trim());
            data[1]         = ucfirst(request.getParameter("author").trim());
            data[2]         = ucfirst(request.getParameter("category").trim());
            data[3]         = request.getParameter("status").trim();
            data[4]         = request.getParameter("price").trim();
        }catch(Exception e){
            throw new Exception("Reading form error");
        }
        return data;
    }
    
    public static String[] createCategoryDataByRequest(HttpServletRequest request) throws Exception{
            String data[]   = new String[10];
        try{
            data[0]         = ucfirst(request.getParameter("name").trim());
            data[1]         = request.getParameter("status").trim();
        }catch(Exception e){
            throw new Exception("Reading form error");
        }
        return data;
    }
    
    public static ArrayList<String> createUserDataByRequest(HttpServletRequest request, int id) throws Exception{
        ArrayList<String> data  = new ArrayList<String>();
        try{
            data.add(request.getParameter("username").trim());
            data.add(request.getParameter("email").trim());
            data.add(ucfirst(request.getParameter("fullname").trim()));
            data.add(request.getParameter("phone").trim());
            data.add(request.getParameter("level").trim());
            data.add(request.getParameter("status").trim());
            validateForm(data);
        }catch(Exception e){
            throw new Exception("Reading form error");
        }
        return data;
    }
    
    public static ArrayList<String> createCartDataByRequest(HttpServletRequest request) throws Exception{
        ArrayList<String> data  = new ArrayList<String>();
        try{
            data.add(request.getParameter("booksString").trim());
            data.add(request.getParameter("qtysString").trim());
            validateForm(data);
        }catch(Exception e){
            throw new Exception("Reading form error");
        }
        return data;
    }
    /*********** MAIN DIFFERENCE **************/
    
    public static void validateForm(ArrayList<String> data) throws Exception{
        for (int i = 0; i<data.size(); i++){
            if (Template.isStringEmpty(data.get(i))) throw new Exception("Validate error");
        }
    }
    
    public static String solvePassword(HttpServletRequest request, int id) throws Exception{
        String password     = getParam(request, "password");
        if (id < 0 && Template.isStringEmpty(password)) throw new Exception("Password is null (add item)");
        return password;
    }
    
    public static int getId(HttpServletRequest request){
        int id          = -1;
        if (request.getParameter("id") != null) id = Integer.parseInt(request.getParameter("id"));
        return id;
    }
    
    public static String setIfEmpty(String str1, String str2){
        if (Template.isStringEmpty(str1)) str1 = str2;
        return str1;
    }
    
    public static String getLinkRedirect(int id){
        String linkRedirect     = "form?action=add";
        if (id > 0) linkRedirect = "form?action=edit&id="+id; 
        return linkRedirect;
    }
    
    public static String getParam(HttpServletRequest request, String name){
        if (request.getParameter(name) != null) return request.getParameter(name);
        else return null;
    }
    
    public static String ucfirst(String str){
        str     = str.substring(0,1).toUpperCase()+str.substring(1, str.length());
        return str;
    }
    
    public static String limitString(String title, int limit){
        String xhtml    = title;
        if (!isStringEmpty(title)){
            if (title.length() > limit){
                xhtml   = xhtml.substring(0,limit)+"...";
            }            
        }
        return xhtml;
    }
    
    public static String getDateTimeString(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDate = now.format(myFormatObj);
        return formattedDate;
    }
    
    public static LocalDateTime convertStringToDateTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime dateTime      = LocalDateTime.parse(time, formatter);
        return dateTime;
    }
    
    public static int convertStringToInt(String string){
        int result;
        try{
            result  = Integer.parseInt(string);
        }catch (Exception e){
            result  = 0;
        }
        return result;
    }
    
    public static String md5(String string){
        String passwordToHash = string;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (Exception e) 
        {
            e.getMessage();
        }
        return generatedPassword;
    }
    
    public static String strpad(String str, int limit, String letter){
        if (str.length() < limit){
            String temp = "";
            int rest    = limit - str.length();
            for (int i =0; i<rest; i++){
                temp    += letter;
            }
            str     = temp+str;
        }
        return str;
    }
    
    public static String number_format(int number, String format){
        DecimalFormat commaFormat;
        commaFormat = new DecimalFormat(format); 
        return commaFormat.format(number);
    }
}
