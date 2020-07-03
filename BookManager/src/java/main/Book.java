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

public class Book {
    int     id;
    String  title;
    String  author;
    String  category;
    String created      = " ";
    String modified     = " ";
    String status;
    int     price;
    
    
    // CONSTRUCTORS
    public Book(){}
    public Book(int id, String title, String author, String category, String created, String modified, String status, int price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.created = created;
        this.modified = modified;
        this.status = status;
        this.price = price;
    }    
    public Book(String id, String title, String author, String category, String status, String price){
        setId(id);
        setTitle(title);
        setAuthor(author);
        setCategory(category);
        setStatus(status);
        setPrice(price);
    }
    
    // SET MULTI VALUE
    public void setMultiValue(String id, String title, String author, String category, String status, String price){
        setId(id);
        setTitle(title);
        setAuthor(author);
        setCategory(category);
        setStatus(status);
        setPrice(price);
    }
    
    
    // SETTERS AND GETTERS
    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = Integer.parseInt(price);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
