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
import javax.swing.JOptionPane;
import tr.com.rah.complex.types.HesapContractComplex;
import tr.com.rah.complex.types.StokContractComplex;
import tr.com.rah.complex.types.StokContractTotalComplex;
import tr.com.rah.core.ObjectHelper;
import tr.com.rah.interfaces.DALInterfaces;
import tr.com.rah.types.SehirContract;
import tr.com.rah.types.StokContract;

/**
 *
 * @author rahimgng
 */
public class StokDAL extends ObjectHelper implements DALInterfaces<StokContract> {
    public boolean a=true,b=true;
    @Override
    public void Insert(StokContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Stok(PersonelId,UrunId,Tarih,Adet) VALUES(" + entity.getPersonelId() + ","
                    + entity.getUrunId() + ", '"
                    + entity.getTarih() + "',"
                    + entity.getAdet() + ")");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
        }
    }
    
     
    public void Update(int no,int adet) {
        
        int vtadet=0;
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            vtadet=Adet(no);
            if(vtadet>0){
                b=true;
            if(vtadet-adet>=0){
            a=true;
            statement.executeUpdate("UPDATE stok SET Adet = Adet-'" +adet + "' WHERE UrunId = '"+no+"' ");
            }
            else{
                    a=false;
                    JOptionPane.showMessageDialog(null, "hata");
            }
                
            statement.close();
            connection.close();

        }
        else{
                b=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public int Adet(int no) {
        int adet = 0;
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Adet FROM stok WHERE UrunId="+no+"");
            while (resultSet.next()) {
                adet = resultSet.getInt("Adet");
            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adet;
    }
    public List<StokContractComplex> GetAllStok() {
        List<StokContractComplex> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        StokContractComplex contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT stok.Id, personel.AdiSoyadi,Adi, stok.Adet, stok.Tarih FROM stok LEFT JOIN urunler on stok.UrunId = urunler.Id LEFT JOIN personel on stok.PersonelId = personel.Id  ORDER BY stok.Id DESC");
            while (resultSet.next()) {
                contract = new StokContractComplex();
                contract.setId(resultSet.getInt("Id"));
                contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
                contract.setUrunAdi(resultSet.getString("urunler.Adi"));
                contract.setAdet(resultSet.getInt("Adet"));
                contract.setTarih(resultSet.getString("stok.Tarih"));

                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }
    

    public List<StokContractTotalComplex> GetTotalStok() {
        List<StokContractTotalComplex> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        StokContractTotalComplex contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT stok.Id, personel.AdiSoyadi,Adi, stok.Adet, stok.Tarih FROM stok LEFT JOIN urunler on stok.UrunId = urunler.Id LEFT JOIN personel on stok.PersonelId = personel.Id  GROUP BY UrunId");
            while (resultSet.next()) {
                contract = new StokContractTotalComplex();
                contract.setId(resultSet.getInt("Id"));
                contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
                contract.setUrunAdi(resultSet.getString("urunler.Adi"));
                contract.setAdet(resultSet.getInt("Adet"));
                contract.setTarih(resultSet.getString("stok.Tarih"));

                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }

    @Override
    public List<StokContract> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StokContract Delete(StokContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(StokContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StokContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
