/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author Asus ZenBook
 */
public interface Config {
    String  levelArr[]       = new String[]{"member", "admin"};
    String  statusArr[]      = new String[]{"inactive", "active"};
    String  moneyFormat      = "#,###";
    int     cartIdLimit     = 5;
}
