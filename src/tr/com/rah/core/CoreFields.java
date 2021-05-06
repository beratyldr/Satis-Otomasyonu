/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rahimgng
 */
public class CoreFields {

    private String userName = "root";
    private String password = "";
    private String host = "127.0.0.1";

    private String db = "satisvestok";
    private int port = 3306;
    private String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.db;

    /* private Connection con = null;

    public void initialize() {
        String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.db;
        
        try {
            Class.forName("com.myssql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.con = DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }
}
