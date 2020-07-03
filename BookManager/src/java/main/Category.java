/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Asus ZenBook
 */
public class Category {
    int     id;
    String  name;
    String  status;
    String  created = " ";
    String  modified= " ";
    
    
    // CONSTRUCTORS
    public Category(){}
    public Category(int id, String name, String status){
        this.id = id;
        this.name = name;
        this.status = status;
    }
    public Category(String id, String name, String status){
        setId(id);
        setName(name);
        setStatus(status);
    }
    
    // SET MULTI VALUE
    public void setMultiValue(String id, String name, String status){
        setId(id);
        setName(name);
        setStatus(status);
    }
    
    // SETTERS AND GETTERS

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    
}
