/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import main.List;

public class CartList extends List <Cart>{
    private static CartList instance;

    private CartList() {
        this.list       = new ArrayList<Cart>();
        this.filepath   = "D:\\Java-project\\cart_input.txt";
        readFile();
    }    
    public static CartList getInstance(){
        if (instance == null){
            instance    = new CartList();
        }
        return instance;
    }
    
    // ******************************************************** MAIN DIFFERENCE BETWEEN LISTS ********************************************************
    // ******************************************************** MAIN DIFFERENCE BETWEEN LISTS ********************************************************
    // ******************************************************** MAIN DIFFERENCE BETWEEN LISTS ********************************************************   
    @Override
    public void sort(String sortField, String sortOrder){          
        if (sortField != null && sortOrder != null){ 
            for (int i =0; i< this.tempList.size() - 1; i++){
                for (int j =0; j < this.tempList.size() - i - 1; j ++){
                    if (sortField.equals("status")){
                       sortByStatus(sortOrder);  
                    }else if (sortField.equals("id")){
                       sortById(sortOrder);  
                    }else if (sortField.equals("totalQty")){
                       sortByTotalQty(sortOrder);  
                    }else if (sortField.equals("totalPrice")){
                       sortByTotalPrice(sortOrder);  
                    }else if (sortField.equals("username")){
                       sortByUsername(sortOrder);  
                    }else if (sortField.equals("created")){
                        sortByCreated(sortOrder);  
                    }else if (sortField.equals("modified")){
                        sortByModified(sortOrder);  
                    }
                }
            }
        } 
    }
    
    // SEARC
    @Override
    public void search(String searchField, String searchValue){
        if (searchField != null && searchValue != null){       
            ArrayList<Cart> temp = getEmptyList();
            searchValue     = searchValue.toLowerCase();
            int j=0;
            for (int i =0; i<this.tempList.size(); i++){
                if (searchField.equals("username")){
                    if (this.tempList.get(i).getUser().getUsername().trim().toLowerCase().indexOf(searchValue) >= 0){
                        temp.add(this.tempList.get(i));
                    }
                }
            }
            setTempList(temp);        
        }  
    }
    
    @Override
    public Cart createItemByStringArray(String data[]){
        Cart item   = new Cart();
        item = new Cart(data[0], data[1], data[2], data[3], data[4]);                              
        item.setCreated(data[5]);                
        item.setModified(data[6]);
        return item;
    }    
    
    @Override
    public String convertObjectToString(Cart item) throws Exception{
        String str  = "";
        try{
            str         += item.getId();
            str         += "|"+item.convertBookArrToString(item.getBookArr());
            str         += "|"+item.convertQtyArrToString(item.getQtyArr());
            str         += "|"+item.getUser().getId();
            str         += "|"+item.getStatus();
            str         += "|"+item.getCreated();
            str         += "|"+item.getModified();
        }catch (Exception e){
            throw new Exception("error convert to String");
        }
            
        return str;
    }
    // ******************************************************** MAIN DIFFERENCE BETWEEN LISTS ********************************************************
    // ******************************************************** MAIN DIFFERENCE BETWEEN LISTS ********************************************************
    // ******************************************************** MAIN DIFFERENCE BETWEEN LISTS ********************************************************
   
    // SORT
    @Override
    public void sortById(String order){
        Collections.sort(this.tempList, new Comparator<Cart>(){
            public int compare(Cart t1, Cart t2){
                if (order.equals("asc")) return (t1.getId()-t2.getId());
                else return -(t1.getId()-t2.getId());
            }
        });
    }
    
    public void sortByTotalQty(String order){
        Collections.sort(this.tempList, new Comparator<Cart>(){
            public int compare(Cart t1, Cart t2){
                if (order.equals("asc")) return (t1.getTotalQty()-t2.getTotalQty());
                else return -(t1.getTotalQty()-t2.getTotalQty());
            }
        });
    }
    
    public void sortByTotalPrice(String order){
        Collections.sort(this.tempList, new Comparator<Cart>(){
            public int compare(Cart t1, Cart t2){
                if (order.equals("asc")) return (t1.getTotalPrice()-t2.getTotalPrice());
                else return -(t1.getTotalPrice()-t2.getTotalPrice());
            }
        });
    }
    
    public void sortByUsername(String order){
        Collections.sort(this.tempList, new Comparator<Cart>(){
            public int compare(Cart t1, Cart t2){
                if (order.equals("asc")) return t1.getUser().getUsername().compareTo(t2.getUser().getUsername());
                else return -t1.getUser().getUsername().compareTo(t2.getUser().getUsername());
            }
        });
    }   
    
    public void sortByStatus(String order){
        Collections.sort(this.tempList, new Comparator<Cart>(){
            public int compare(Cart t1, Cart t2){
                if (order.equals("asc")) return t1.getStatus().compareTo(t2.getStatus());
                else return -t1.getStatus().compareTo(t2.getStatus());
            }
        });
    }
    
    public void sortByCreated(String order){        
        Collections.sort(this.tempList, new Comparator<Cart>(){
            public int compare(Cart t1, Cart t2){
                long v1, v2;
                try{
                    v1      = Long.parseLong(t1.getCreated());
                }catch (Exception e){
                    v1      = 0;
                }
                
                try{
                    v2      = Long.parseLong(t2.getCreated());
                }catch (Exception e){
                    v2      = 0;
                }
                if (order.equals("asc")) return compareLong(v1, v2);
                return -compareLong(v1, v2);
            }
        });
    }
    
    public void sortByModified(String order){        
        Collections.sort(this.tempList, new Comparator<Cart>(){
            public int compare(Cart t1, Cart t2){
                long v1, v2;
                try{
                    v1      = Long.parseLong(t1.getModified());
                }catch (Exception e){
                    v1      = 0;
                }
                
                try{
                    v2      = Long.parseLong(t2.getModified());
                }catch (Exception e){
                    v2      = 0;
                }
                if (order.equals("asc")) return compareLong(v1, v2);
                return -compareLong(v1, v2);
            }
        });
    } 


    // SUPPORTED FUNCTIONS    
    @Override
    public int getPositionById(int id) {
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
        
    @Override
    public void fillActiveItems(){
        ArrayList<Cart> temp = getEmptyList();
        for (int i=0; i<this.tempList.size();i++){
            if (this.tempList.get(i).getStatus().equals("active"));
            temp.add(this.tempList.get(i));
        }
        setTempList(temp);
    }
    @Override
    public int getNewId(){
        int result   = 1;
        if (this.list.size() > 0){
            Cart item   = Collections.max(this.list, new Comparator<Cart>(){
                public int compare(Cart t1, Cart t2){
                    return (t1.getId()-t2.getId());
                }
            });
            result = item.getId()+1;
        }        
        return result;
    }

    // MANIPULATE FILE FUNCTIONS
    @Override
    public void readFile(){
        ArrayList<String> data   = file(this.filepath);
        if (data.size() > 0){
            for (int i = 0; i < data.size(); i++) {
                String temp[] = temp = data.get(i).split("\\|");
                this.list.add(createItemByStringArray(temp));        
            }
        }
    }
}
