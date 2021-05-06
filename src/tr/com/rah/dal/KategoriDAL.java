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
import tr.com.rah.types.KategoriContract;

/**
 *
 * @author rahimgng
 */
/*import java.util.Map;

final class MyEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }
}*/
public class KategoriDAL extends ObjectHelper implements DALInterfaces<KategoriContract> {

    //veritabanı bağlantısı
    @Override
    public void Insert(KategoriContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Kategori(Adi, ParentId) VALUES('" + entity.getAdi() + "'," + entity.getParentId() + " ) ");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<KategoriContract> GetAll() {
        List<KategoriContract> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        KategoriContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori");
            while (resultSet.next()) {
                contract = new KategoriContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdi(resultSet.getString("Adi"));
                contract.setParentId(resultSet.getInt("ParentId"));

                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }

    public List<KategoriContract> GetAllParentId() {
        List<KategoriContract> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        KategoriContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori");// WHERE parentId = 0
            while (resultSet.next()) {
                contract = new KategoriContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdi(resultSet.getString("Adi"));
                contract.setParentId(resultSet.getInt("ParentId"));

                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }

    @Override
    public KategoriContract Delete(KategoriContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void delete(KategoriContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Kategori WHERE Id = '" + entity.getId() + "'");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void Update(KategoriContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Kategori SET Adi = '" + entity.getAdi() + "',ParentId = " + entity.getParentId() + " WHERE id = " + entity.getId() + "");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<KategoriContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //kategoride arama
    public List<KategoriContract> GetSearchKategori(String kategoriAdi) {
        List<KategoriContract> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        KategoriContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori WHERE Adi LIKE '" + "%" + kategoriAdi + "%" + "'");
            while (resultSet.next()) {
                contract = new KategoriContract();
                contract.setAdi(resultSet.getString("Adi"));
                contract.setParentId(resultSet.getInt("ParentId"));

                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }
    //statement.executeQuery("SELECT * FROM Kategori WHERE Adi LIKE '" + "%" + kategoriAdi + "%" + "'");
}
