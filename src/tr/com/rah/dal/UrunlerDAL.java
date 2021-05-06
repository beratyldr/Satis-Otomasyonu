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
import tr.com.rah.types.UrunlerContract;

/**
 *
 * @author rahimgng
 */
public class UrunlerDAL extends ObjectHelper implements DALInterfaces<UrunlerContract> {

    @Override
    public void Insert(UrunlerContract entity) {
        Connection connection = getConnection();
        
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Urunler(Adi, KategoriId, Tarih, Fiyat)" + "VALUES ('" + entity.getAdi() + "' , " + entity.getKategoriId() + ",'" + entity.getTarih() + "' , " + entity.getFiyat() + ")");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UrunlerContract> GetAll() {
        List<UrunlerContract> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        UrunlerContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Urunler");
            while(resultSet.next()){
                contract = new UrunlerContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdi(resultSet.getString("Adi"));
                contract.setKategoriId(resultSet.getInt("KategoriId"));
                contract.setFiyat(resultSet.getInt("Fiyat"));
                contract.setTarih(resultSet.getString("Tarih"));
                
                datacontract.add(contract);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }

    @Override
    public UrunlerContract Delete(UrunlerContract entity) {
        UrunlerContract uc = new UrunlerContract();
        
        Connection connection = getConnection();
        
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Urunler WHERE Id = " + entity.getId() + "");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UrunlerDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return uc;
    }

    @Override
    public void Update(UrunlerContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Urunler SET Adi = '" + entity.getAdi()+ "' , KategoriId = " + entity.getKategoriId() + " , Fiyat = " + entity.getFiyat() + " , Tarih = '" + entity.getTarih() + "' WHERE id = " + entity.getId() + "");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<UrunlerContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
