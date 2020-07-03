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
import javax.servlet.http.HttpServletRequest;
import main.List;

public class BookList extends List <Book>{
    private static BookList instance;

    // SINGLETON DESIGN PATTERN
    private BookList() {
        this.list       = new ArrayList<Book>();
        this.filepath   = "D:\\Java-project\\book_input.txt";
        readFile();
    }
    public static BookList getInstance(){
        if (instance == null){
            instance    = new BookList();
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
                    if (sortField.equals("title")){
                        sortByTitle(sortOrder);
                    }else if (sortField.equals("author")){
                        sortByAuthor(sortOrder);
                    }else if (sortField.equals("category")){
                        sortByCategory(sortOrder);                   
                    }else if (sortField.equals("status")){
                       sortByStatus(sortOrder);  
                    }else if (sortField.equals("price")){
                        sortByPrice(sortOrder);  
                    }else if (sortField.equals("id")){                        
                        sortById(sortOrder);  
                    }else if (sortField.equals("created")){
                        sortByCreated(sortOrder);  
                    }else if (sortField.equals("modified")){
                        sortByModified(sortOrder);  
                    }
                }
            }
        } 
    }
    @Override
    public void search(String searchField, String searchValue){
        if (searchField != null && searchValue != null){       
            ArrayList<Book> temp = getEmptyList();
            searchValue     = searchValue.toLowerCase();
            int j=0;
            for (int i =0; i<this.tempList.size(); i++){
                if (searchField.equals("title")){
                    if (this.tempList.get(i).getTitle().trim().toLowerCase().indexOf(searchValue) >= 0){
                        temp.add(this.tempList.get(i));
                    }
                }else if (searchField.equals("author")){
                   if (this.tempList.get(i).getAuthor().trim().toLowerCase().indexOf(searchValue) >= 0){
                        temp.add(this.tempList.get(i));
                    }
                }
            }
            setTempList(temp);        }  
    }
    
    public void fillCat(int id){
        if (id > 0){
            ArrayList<Book> temp    = getEmptyList();
            CategoryList catList    = CategoryList.getInstance(); 
            Category cat            = catList.getItemById(id);
            if (cat != null){                
                for (int i=0; i<this.tempList.size(); i++){
                    if (this.tempList.get(i).getCategory().trim().toLowerCase().equals(cat.getName().toLowerCase())){
                        temp.add(this.tempList.get(i));
                    } 
                }
            }    
            setTempList(temp);
        }
    }
    
    @Override
    public void sortByCreated(String order){        
        Collections.sort(this.tempList, new Comparator<Book>(){
            public int compare(Book t1, Book t2){
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
    
    @Override
    public void sortByModified(String order){        
        Collections.sort(this.tempList, new Comparator<Book>(){
            public int compare(Book t1, Book t2){
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
    
    @Override
    public Book createItemByStringArray(String data[]){
        Book item   = new Book();
        item = new Book(data[0], data[1], data[2], data[3], data[4], data[5]);                              
        item.setCreated(data[6]);                
        item.setModified(data[7]);
        return item;
    } 
    
    @Override
    public String convertObjectToString(Book item) throws Exception{
        String str  = "";
        try{
            str         += item.getId();
            str         += "|"+item.getTitle();
            str         += "|"+item.getAuthor();
            str         += "|"+item.getCategory();
            str         += "|"+item.getStatus();
            str         += "|"+item.getPrice();
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
        Collections.sort(this.tempList, new Comparator<Book>(){
            public int compare(Book t1, Book t2){
                if (order.equals("asc")) return (t1.getId()-t2.getId());
                else{
                    return -(t1.getId()-t2.getId());
                }
            }
        });
    }
    
    public void sortByTitle(String order){
        Collections.sort(this.tempList, new Comparator<Book>(){
            public int compare(Book t1, Book t2){
                if (order.equals("asc")) return t1.getTitle().compareTo(t2.getTitle());
                else return -t1.getTitle().compareTo(t2.getTitle());
            }
        });
    }
    
    public void sortByAuthor(String order){
        Collections.sort(this.tempList, new Comparator<Book>(){
            public int compare(Book t1, Book t2){
                if (order.equals("asc")) return t1.getAuthor().compareTo(t2.getAuthor());
                else return -t1.getAuthor().compareTo(t2.getAuthor());
            }
        });
    }
    
    public void sortByCategory(String order){
        Collections.sort(this.tempList, new Comparator<Book>(){
            public int compare(Book t1, Book t2){
                if (order.equals("asc")) return t1.getCategory().compareTo(t2.getCategory());
                else return -t1.getCategory().compareTo(t2.getCategory());
            }
        });
    }
    
    public void sortByStatus(String order){
        Collections.sort(this.tempList, new Comparator<Book>(){
            public int compare(Book t1, Book t2){
                if (order.equals("asc")) return t1.getStatus().compareTo(t2.getStatus());
                else return -t1.getStatus().compareTo(t2.getStatus());
            }
        });
    }
    
    public void sortByPrice(String order){
        Collections.sort(this.tempList, new Comparator<Book>(){
            public int compare(Book t1, Book t2){
                if (order.equals("asc")) return (t1.getPrice() - t2.getPrice());
                else return -(t1.getPrice() - t2.getPrice());
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
        ArrayList<Book> temp = getEmptyList();
        for (int i=0; i<this.tempList.size();i++){
            if (this.tempList.get(i).getStatus().equals("active")){
                temp.add(this.tempList.get(i));
            }
            
        }
        setTempList(temp);
    }
    
    @Override
    public int getNewId(){
        int result   = 1;
        if (this.list.size() > 0){
            Book item   = Collections.max(this.list, new Comparator<Book>(){
                public int compare(Book t1, Book t2){
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
