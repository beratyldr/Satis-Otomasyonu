/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.com.rah.core.ObjectHelper;
import tr.com.rah.interfaces.DALInterfaces;
import tr.com.rah.types.SehirContract;

/**
 *
 * @author rahimgng
 */
public class SehirDAL extends ObjectHelper implements DALInterfaces<SehirContract> {

    @Override
    public void Insert(SehirContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Sehir(sehirAdi,sehirId) VALUES('" + entity.getAdi() + "' , " + entity.getId() + ")");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<SehirContract> GetAll() {
        List<SehirContract> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        SehirContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sehir");
            while (resultSet.next()) {
                contract = new SehirContract();
                contract.setId(resultSet.getInt("sehirId"));
                contract.setAdi(resultSet.getString("sehirAdi"));
                datacontract.add(contract);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return datacontract;
    }

    @Override
    public SehirContract Delete(SehirContract entity) {
        SehirContract sc = new SehirContract();

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Sehir WHERE sehirId = " + entity.getId() + "");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
        }

        return sc;
    }

    @Override
    public void Update(SehirContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Sehir SET sehirAdi = '" + entity.getAdi() + "' WHERE sehirId = " + entity.getId() + "");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<SehirContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
