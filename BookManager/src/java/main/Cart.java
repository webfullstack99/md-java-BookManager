/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.util.ArrayList;
import main.User;
import main.BookList;
import main.Book;
import main.UserList;
/**
 *
 * @author Asus ZenBook
 */
public class Cart {
    int     id;
    ArrayList<Book> bookArr;
    ArrayList<Integer> qtyArr;
    User    user;
    int     totalQty;
    int     totalPrice;
    String  status;
    String  created = " ";
    String  modified= " ";
    
    
    // CONSTRUCTORS
    public Cart(){}
    public Cart(int id, ArrayList<Book> bookArr, ArrayList<Integer> qtyArr, User user, String status) {
        this.id = id;
        this.bookArr = bookArr;
        this.user = user;
        this.status = status;
        computeQtyAndTotal();
    }
    public Cart(String id, String bookArr, String qtyArr, String user, String status) {
        setId(id);
        setBookArr(bookArr);
        setQtyArr(qtyArr);
        setUser(user);
        setStatus(status);
        computeQtyAndTotal();
    }
    
    // SET MULTI VALUE
    public void setMultiValue(String id, String bookArr, String qtyArr, String user, String status) {
        setId(id);
        setBookArr(bookArr);
        setQtyArr(qtyArr);
        setUser(user);
        setStatus(status);
        computeQtyAndTotal();
    }
    
    // SUPPORTED FUNCTION
    public static ArrayList<Book> convertStringToBookArr(String string){
        ArrayList<Book> arr = new ArrayList<Book>();
        String data[]           = string.split("\\-");         
        if (data.length > 0){
            BookList list   = BookList.getInstance();
            Book item;
            
            for (int i = 0; i<data.length; i++){
                item   = list.getItemById(Integer.parseInt(data[i]));
                if (item != null) arr.add(item);                
            }
        }
        return arr;
    }
    
    public static ArrayList<Integer> convertStringToQtyArr(String string){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        String data[]           = string.split("\\-");         
        if (data.length > 0){
            for (int i = 0; i<data.length; i++){
                arr.add(Integer.parseInt(data[i]));
            }
        }
        return arr;
    }
    
    public static String convertBookArrToString(ArrayList<Book> arr){
        String str  = "";
        for (int i=0; i<arr.size(); i++){
            str         += "-"+arr.get(i).getId();
        }
        str     = str.substring(1, str.length());
        return str;
    }
  
    public static String convertQtyArrToString(ArrayList<Integer> arr){
        String str  = "";
        for (int i=0; i<arr.size(); i++){
            str         += "-"+arr.get(i);
        }
        str     = str.substring(1, str.length());
        return str;
    }
    
    public User convertUserIdToUser(String userId){
        UserList userList   = UserList.getInstance();
        User user           = userList.getItemById(Integer.parseInt(userId));
        return user;
    }
    
    public void computeQtyAndTotal(){
        int quantity        = 0;
        int totalPrice      = 0;
        for (int i=0; i<this.bookArr.size(); i++){
            quantity    += this.qtyArr.get(i);
            totalPrice  += this.bookArr.get(i).getPrice() * this.qtyArr.get(i);
        }
        setTotalQty(quantity);
        setTotalPrice(totalPrice);
    }
    
    // SETTERS AND GETTERS
    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public ArrayList<Book> getBookArr() {
        return bookArr;
    }

    public void setBookArr(String booksString) {
        this.bookArr = convertStringToBookArr(booksString);
    }

    public ArrayList<Integer> getQtyArr() {
        return qtyArr;
    }

    public void setQtyArr(String qtyArr) {
        this.qtyArr = convertStringToQtyArr(qtyArr);
    }    
    
    public User getUser() {
        return user;
    }

    public void setUser(String userId) {
        this.user = convertUserIdToUser(userId);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }  

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
}
