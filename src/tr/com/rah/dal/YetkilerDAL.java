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
import tr.com.rah.types.YetkilerContract;

/**
 *
 * @author rahimgng
 */
public class YetkilerDAL extends ObjectHelper implements DALInterfaces<YetkilerContract> {

    @Override
    public void Insert(YetkilerContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Yetkiler(Adi, Id) VALUES('" + entity.getAd() + "'," + entity.getId() + " ) ");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<YetkilerContract> GetAll() {
        List<YetkilerContract> datacontract = new ArrayList<>();
        Connection connection = getConnection();
        YetkilerContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM yetkiler");
            while (resultSet.next()) {
                contract = new YetkilerContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAd(resultSet.getString("Adi"));
                datacontract.add(contract);

            }

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }

    @Override
    public YetkilerContract Delete(YetkilerContract entity) {
        YetkilerContract yc = new YetkilerContract();

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Yetkiler WHERE Id = " + entity.getId() + "");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
        }

        return yc;
    }

    @Override
    public void Update(YetkilerContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Yetkiler SET Adi = '" + entity.getAd() + "' WHERE id = " + entity.getId() + "");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<YetkilerContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
