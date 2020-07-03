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
public class User {
    int     id;
    String  username;
    String  email;
    String  fullname;
    long     phone;
    String  password;
    String  level;
    String  status;    
    String  created     = " ";
    String  modified    = " ";
    
    
    // CONSTRUCTORS
    public User(){}
    public User(int id, String username, String email, String fullname, int phone, String password, String level, String status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.password = password;
        this.level = level;
        this.status = status;
    }
    public User(String id, String username, String email, String fullname, String phone, String password, String level, String status) {
        setId(id);
        setUsername(username);
        setEmail(email);
        setFullname(fullname);
        setPhone(phone);
        setPassword(password);
        setLevel(level);
        setStatus(status);               
    }
    
    // SET MULTI VALUE
    public void setMultiValue(String id, String username, String email, String fullname, String phone, String password, String level, String status){
        setId(id);
        setUsername(username);
        setEmail(email);
        setPhone(phone);
        setPassword(password);
        setLevel(level);
        setStatus(status);    
    }
    
    // SUPPORTED FUNCTION
    public void setPasswordIfEmpty(String string){
        if (this.password.trim().equals("")) setPassword(string);        
    }
    
    // SETTERS AND GETTERS
    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = Long.parseLong(phone);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
    
    
}
