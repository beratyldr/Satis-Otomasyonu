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
import tr.com.rah.complex.types.SatisContractComplex;
import tr.com.rah.core.ObjectHelper;
import tr.com.rah.interfaces.DALInterfaces;
import tr.com.rah.types.SatisContract;

/**
 *
 * @author rahimgng
 */
public class SatisDAL extends ObjectHelper implements DALInterfaces<SatisContract> {

    @Override
    public void Insert(SatisContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Satis(UrunId, MusteriId,PersonelId,Adet,Tarih) VALUES(" + entity.getUrunId() +"," + entity.getMusteriId() + "," + entity.getPersonelId() + "," + entity.getAdet() + ",'" + entity.getTarih() +"')");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public List<SatisContractComplex> GetAllSatis() {
        List<SatisContractComplex> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        SatisContractComplex contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT satis.Id, personel.AdiSoyadi,musteri.AdiSoyadi,Adi, satis.Adet, satis.Tarih FROM satis LEFT JOIN musteri on satis.MusteriId = musteri.Id LEFT JOIN urunler on satis.UrunId = urunler.Id LEFT JOIN personel on satis.PersonelId = personel.Id  ORDER BY satis.Id DESC");
            while (resultSet.next()) {
                contract = new SatisContractComplex();
                
                contract.setId(resultSet.getInt("satis.Id"));
                contract.setMusteriAdi(resultSet.getString("musteri.AdiSoyadi"));
                contract.setPersonelAdi(resultSet.getString("personel.AdiSoyadi"));
                contract.setTarih(resultSet.getString("satis.Tarih"));
                contract.setUrunAdi(resultSet.getString("Adi"));
                contract.setAdet(resultSet.getInt("satis.Adet"));
                
                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }
    //generic list
    @Override
    public List<SatisContract> GetAll() {
        List<SatisContract> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        SatisContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM satis ");
            while (resultSet.next()) {
                contract = new SatisContract();
                contract.setId(resultSet.getInt("Id"));
                

                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }

    @Override
    public SatisContract Delete(SatisContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(SatisContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SatisContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
