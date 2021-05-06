/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import tr.com.rah.interfaces.CoreInterfaces;

/**
 *
 * @author rahimgng
 */
public class ObjectHelper extends CoreFields implements CoreInterfaces {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
