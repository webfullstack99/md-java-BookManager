/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Asus ZenBook
 */
public abstract class List <T>{
    ArrayList<T> list;
    ArrayList<T> tempList;
    String filepath;
    public String str  = "";
    
    // MANIPULATE DATA
    public int insert(T item) throws Exception{
        if (item != null){
            this.list.add(item);
            saveFile();
            return 1;
        }
        return -1;
         
    };
    
    public int update(int index, T item) throws Exception{
        if (item != null){
            this.list.set(index, item);
            saveFile();
            return 1;
        }
        return -1;
    }
    
    public int delete(int index) throws Exception{
        if (index >= 0){
            this.list.remove(index);
            saveFile();
            return 1;
            
        }
        return -1;
    }
    
    public void refresh() throws Exception{
        this.list   = new ArrayList<T>();
        readFile();
    }
    
    // SORT
    abstract public void sortById(String order);
    
    abstract public void sortByCreated(String order);
    
    abstract public void sortByModified(String order);
    
    abstract public void sort(String sortField, String sortOrder);
    
    // SEARCH
    abstract public void search(String searchField, String searchValue);
    
    // FILE
    abstract public void readFile() throws Exception;
    
    public void saveFile() throws Exception{
        String listData[]       = createStringList();
        write(this.filepath, listData);
    }    

    public ArrayList<String> file(String filePath) {
        ArrayList<String> data  = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"))) {
            String cLine;
            while ((cLine = br.readLine()) != null) {
                if (!cLine.trim().equals("null")) {
                    if (!cLine.trim().equals("")) {
                        data.add(cLine);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void write(String filename, String[] x) throws Exception {
        try{
            BufferedWriter outputWriter = null;
            outputWriter = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < this.list.size(); i++) {
                outputWriter.write(x[i]);
                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();
        }catch (Exception e){
            throw new Exception ("write file error");
        }        
    }
    
    // SUPPORTED FUNCTIONS 
    abstract public int getNewId();
    
    abstract public int getPositionById(int id);
    
    abstract public String convertObjectToString(T item) throws Exception;
    
    abstract public T createItemByStringArray(String data[]);
    
    public T getItemById(int id) {
        int pos     = getPositionById(id);
        if (pos >= 0) return this.list.get(pos);
        else return null;
    }
    
    public String[] createStringList() throws Exception{
        String listData[]   = new String[this.list.size()];
        int j =0;
        try{            
            for (int i =0; i<this.list.size(); i++){
                if (this.list.get(i) != null){
                    listData[j] = convertObjectToString(this.list.get(i));
                    j++;
                };
            }
        }catch (Exception e){
            throw new Exception ("error create string");
        }            
        return listData;        
    }    
    
    public int compareLong(long v1, long v2){
        if (v1 > v2) return 1;
        else if (v1 == v2) return 0;
        else return -1;
    }
    
    abstract public void fillActiveItems();
    
    // SETTER AND GETTERS
    public ArrayList<T> getEmptyList (){
        return new ArrayList<T>();
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public ArrayList<T> getTempList() {
        return tempList;
    }

    public void setTempList(ArrayList<T> tempList) {
        this.tempList = tempList;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
