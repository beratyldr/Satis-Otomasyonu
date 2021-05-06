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
import tr.com.rah.complex.types.HesapContractComplex;
import tr.com.rah.core.ObjectHelper;
import tr.com.rah.interfaces.DALInterfaces;
import tr.com.rah.types.HesapContract;

/**
 *
 * @author rahimgng
 */
public class HesapDAL extends ObjectHelper implements DALInterfaces<HesapContract> {

    @Override
    public void Insert(HesapContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Hesap(Adet,Fiyat,UrunId,masaNo,toplamFiyat,Tarih) VALUES(" + entity.getAdet() + "," + entity.getFiyat() + "," + entity.getUrunId() + "," + entity.getMasaId() + "," + entity.getFiyat() * entity.getAdet() + ",'" + entity.getTarih() + "')");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<HesapContractComplex> GetHesapNo(int no) {
        List<HesapContractComplex> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        HesapContractComplex contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT hesap.Id,Adi, hesap.Adet,hesap.Fiyat, hesap.Tarih,hesap.masaNo,hesap.toplamFiyat,urunler.Fiyat FROM hesap LEFT JOIN  urunler on hesap.UrunId = urunler.Id WHERE hesap.masaNo='" + no + "' ORDER BY hesap.Id DESC");
            while (resultSet.next()) {
                contract = new HesapContractComplex();

                contract.setId(resultSet.getInt("hesap.Id"));
                contract.setMasaNo(resultSet.getInt("hesap.masaNo"));
                contract.setTarih(resultSet.getString("hesap.Tarih"));
                contract.setUrunAdi(resultSet.getString("Adi"));
                contract.setAdet(resultSet.getInt("hesap.Adet"));
                contract.setFiyat(resultSet.getInt("urunler.Fiyat"));
                contract.setToplamFiyat(resultSet.getInt("hesap.toplamFiyat"));
                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }

    //generic list
    @Override
    public List<HesapContract> GetAll() {
        List<HesapContract> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        HesapContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hesap ");
            while (resultSet.next()) {
                contract = new HesapContract();
                contract.setId(resultSet.getInt("Id"));

                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }

    public int Toplam(int no) {
        int toplamm = 0;
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT SUM(toplamFiyat) as toplam FROM hesap WHERE masaNo=" + no + " ");
            while (resultSet.next()) {
                toplamm = resultSet.getInt("toplam");
            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toplamm;
    }

    public HesapContract Delete(int no) {
         HesapContract uc = new HesapContract();
        
        Connection connection = getConnection();
        
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM hesap WHERE masaNo = " + no + "");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UrunlerDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return uc;
    }

    @Override
    public void Update(HesapContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HesapContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HesapContract Delete(HesapContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
