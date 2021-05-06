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
import tr.com.rah.types.MusteriContract;
import tr.com.rah.types.PersonelContract;

/**
 *
 * @author rahimgng
 */
public class MusteriDAL extends ObjectHelper implements DALInterfaces<MusteriContract> {

    @Override
    public void Insert(MusteriContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Musteri (AdiSoyadi, Telefon, Adres, SehirId) VALUES('" + entity.getAdiSoyadi() + "','" + entity.getTelefon() + "', '" + entity.getAdres() + "'," + entity.getSehirId() + " ) ");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<MusteriContract> GetAll() {
        List<MusteriContract> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        MusteriContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Musteri");
            while (resultSet.next()) {
                contract = new MusteriContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
                contract.setAdres(resultSet.getString("Adres"));
                contract.setSehirId(resultSet.getInt("SehirId"));
                contract.setTelefon(resultSet.getString("Telefon"));

                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }

    @Override
    public MusteriContract Delete(MusteriContract entity) {
        MusteriContract mc = new MusteriContract();
        
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM musteri WHERE Id = " + entity.getId() + "");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mc;
    }

    @Override
    public void Update(MusteriContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Musteri SET AdiSoyadi = '" + entity.getAdiSoyadi() + "' , Telefon = " + entity.getTelefon() + " , Adres = '" + entity.getAdres() + "' , SehirId = " + entity.getSehirId() + " WHERE id = " + entity.getId() + "");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
        }
    }

    @Override
    public List<MusteriContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
