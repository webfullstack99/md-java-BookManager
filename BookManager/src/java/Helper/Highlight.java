/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

/**
 *
 * @author Asus ZenBook
 */
public class Highlight {
    public static String highlight(String searchingField, String searchValue, String field, String content){
        if (searchValue != null){
            if (searchingField.equals(field)){         
               content     =  content.replaceAll("((?i)"+searchValue+")", "<span style=\"background:yellow;\">$1</span>");                      
            }
        }
            
        return content;
    }
}
