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
import tr.com.rah.types.AccountContract;

/**
 *
 * @author rahimgng
 */
public class AccountDAL extends ObjectHelper implements DALInterfaces<AccountContract> {

    @Override
    public void Insert(AccountContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Accounts(PersonelId,YetkiId,Sifre) VALUES(" + entity.getPersonelId() + "," + entity.getYetkiId() + ",'" + entity.getSifre() + "')");
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AccountContract GetPersonelIdVeSifre(int personelId, String sifre) {
        AccountContract contract = new AccountContract();
        List<AccountContract> listele = new ArrayList<>();
        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM accounts WHERE PersonelId = " + personelId + " AND Sifre = '" + sifre.trim() + "'");

            while (rs.next()) {
                contract.setId(rs.getInt("Id"));
                contract.setPersonelId(rs.getInt("PersonelId"));
                contract.setSifre((rs.getString("Sifre")));
                contract.setYetkiId(rs.getInt("YetkiId"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
        }
        return contract;
    }

    public AccountContract GetYetkiId(int personelId) {
        AccountContract contract = new AccountContract();
        List<AccountContract> listele = new ArrayList<>();
        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM accounts WHERE PersonelId = " + personelId + " ");

            while (rs.next()) {
                contract.setId(rs.getInt("Id"));
                contract.setPersonelId(rs.getInt("PersonelId"));
                contract.setYetkiId(rs.getInt("YetkiId"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
        }
        return contract;
    }

    @Override
    public List<AccountContract> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountContract Delete(AccountContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(AccountContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE Accounts SET Sifre = '" + entity.getSifre() +"' WHERE id = " + entity.getId() + ",YetkiId = " + entity.getYetkiId()+ ",PersonelId = " + entity.getPersonelId()+ " ");

            statement.close();
            connection.close();
        } catch (SQLException e) {
        }

    }

    @Override
    public List<AccountContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
