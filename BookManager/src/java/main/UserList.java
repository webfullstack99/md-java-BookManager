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

public class UserList extends List <User>{
    private static UserList instance;

    private UserList() {
        this.list       = new ArrayList<User>();
        this.filepath   = "D:\\Java-project\\user_input.txt";
        readFile();
    }    
    public static UserList getInstance(){
        if (instance == null){
            instance    = new UserList();
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
                    if (sortField.equals("username")){
                        sortByUsername(sortOrder);
                    }else if (sortField.equals("status")){
                       sortByStatus(sortOrder);  
                    }else if (sortField.equals("id")){
                       sortById(sortOrder);  
                    }else if (sortField.equals("level")){
                       sortByLevel(sortOrder);  
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
            ArrayList<User> temp = getEmptyList();
            searchValue     = searchValue.toLowerCase();
            int j=0;
            for (int i =0; i<this.tempList.size(); i++){
                if (searchField.equals("username")){
                    if (this.tempList.get(i).getUsername().trim().toLowerCase().indexOf(searchValue) >= 0){
                        temp.add(this.tempList.get(i));
                    }
                }else if (searchField.equals("email")){
                    if (this.tempList.get(i).getEmail().trim().toLowerCase().indexOf(searchValue) >= 0){
                        temp.add(this.tempList.get(i));
                    }
                }else if (searchField.equals("fullname")){
                    if (this.tempList.get(i).getFullname().trim().toLowerCase().indexOf(searchValue) >= 0){
                        temp.add(this.tempList.get(i));
                    }
                }else if (searchField.equals("phone")){
                    if ((this.tempList.get(i).getPhone()+"").trim().toLowerCase().indexOf(searchValue) >= 0){
                        temp.add(this.tempList.get(i));
                    }
                }
            }
            setTempList(temp);        
        }  
    }
    
    @Override
    public User createItemByStringArray(String data[]){
        User item   = new User();
        item = new User(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);                              
        item.setCreated(data[8]);                
        item.setModified(data[9]);
        return item;
    }    
    
    @Override
    public String convertObjectToString(User item) throws Exception{
        String str  = "";
        try{
            str         += item.getId();
            str         += "|"+item.getUsername();
            str         += "|"+item.getEmail();
            str         += "|"+item.getFullname();
            str         += "|"+item.getPhone();
            str         += "|"+item.getPassword();
            str         += "|"+item.getLevel();
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
        Collections.sort(this.tempList, new Comparator<User>(){
            public int compare(User t1, User t2){
                if (order.equals("asc")) return (t1.getId()-t2.getId());
                else return -(t1.getId()-t2.getId());
            }
        });
    }
    
    public void sortByUsername(String order){
        Collections.sort(this.tempList, new Comparator<User>(){
            public int compare(User t1, User t2){
                if (order.equals("asc")) return t1.getUsername().compareTo(t2.getUsername());
                else return -t1.getUsername().compareTo(t2.getUsername());
            }
        });
    }
    
    public void sortByLevel(String order){
        Collections.sort(this.tempList, new Comparator<User>(){
            public int compare(User t1, User t2){
                if (order.equals("asc")) return t1.getLevel().compareTo(t2.getLevel());
                else return -t1.getLevel().compareTo(t2.getLevel());
            }
        });
    }
    
    public void sortByStatus(String order){
        Collections.sort(this.tempList, new Comparator<User>(){
            public int compare(User t1, User t2){
                if (order.equals("asc")) return t1.getStatus().compareTo(t2.getStatus());
                else return -t1.getStatus().compareTo(t2.getStatus());
            }
        });
    }
    
    public void sortByCreated(String order){        
        Collections.sort(this.tempList, new Comparator<User>(){
            public int compare(User t1, User t2){
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
        Collections.sort(this.tempList, new Comparator<User>(){
            public int compare(User t1, User t2){
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
    public int getNewId(){
        int result   = 1;
        if (this.list.size() > 0){
            User item   = Collections.max(this.list, new Comparator<User>(){
                public int compare(User t1, User t2){
                    return (t1.getId()-t2.getId());
                }
            });
            result = item.getId()+1;
        }        
        return result;
    }
    
    @Override
    public void fillActiveItems(){
        ArrayList<User> temp = getEmptyList();
        for (int i=0; i<this.tempList.size();i++){
            if (this.tempList.get(i).getStatus().equals("active"));
            temp.add(this.tempList.get(i));
        }
        setTempList(temp);
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
