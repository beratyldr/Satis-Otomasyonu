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
import tr.com.rah.types.PersonelContract;

/**
 *
 * @author rahimgng
 */
public class PersonelDAL extends ObjectHelper implements DALInterfaces<PersonelContract> {

    @Override
    public void Insert(PersonelContract entity) {
        Connection connection = getConnection();
        try {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("INSERT INTO Personel(AdiSoyadi, Email) VALUES('" + entity.getAdiSoyadi() + "','" + entity.getEmail() + "' ) ");
            }
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<PersonelContract> GetAll() {
        List<PersonelContract> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        PersonelContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Personel");
            while (resultSet.next()) {
                contract = new PersonelContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
                contract.setEmail(resultSet.getString("Email"));
                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }
    public List<PersonelContract> GetAdi() {
        List<PersonelContract> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        PersonelContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT AdiSoyadi FROM Personel");
            while (resultSet.next()) {
                contract = new PersonelContract();
                contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
                
                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }
    
    public List<PersonelContract> GetMail() {
        List<PersonelContract> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        PersonelContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Email FROM Personel ");
            while (resultSet.next()) {
                contract = new PersonelContract();
                contract.setAdiSoyadi(resultSet.getString("Email"));
                
                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }
    
    @Override
    public PersonelContract Delete(PersonelContract entity) {
        PersonelContract pc = new PersonelContract();
        
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Personel WHERE Id = " + entity.getId() + "");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pc;
    }

    @Override
    public void Update(PersonelContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Personel SET AdiSoyadi = '" + entity.getAdiSoyadi() + "' , Email = '" + entity.getEmail() + "' WHERE id = " + entity.getId() + "");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<PersonelContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
