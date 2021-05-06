/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import tr.com.rah.dal.AccountDAL;
import tr.com.rah.dal.PersonelDAL;
import tr.com.rah.interfaces.FeInterfaces;
import tr.com.rah.types.PersonelContract;

/**
 *
 * @author rahimgng
 */
public class LoginFE extends JDialog implements FeInterfaces {

    public static JComboBox emailBox;

    public LoginFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        
        add(panel);
        panel.setBorder(BorderFactory.createTitledBorder("Giriş Yap"));
        setTitle("Lütfen Giriş Yapınız.");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:", JLabel.RIGHT);
        panel.add(emailLabel);
        emailBox = new JComboBox(new PersonelDAL().GetAll().toArray());
        panel.add(emailBox);
        JLabel passwordLabel = new JLabel("Şifre:", JLabel.RIGHT);
        panel.add(passwordLabel);
        JPasswordField passwordField = new JPasswordField(15);
        panel.add(passwordField);
        
        JButton loginButton = new JButton("Giriş Yap");
        panel.add(loginButton);
        JButton iptalButton = new JButton("İptal");
        panel.add(iptalButton);
        
        loginButton.addActionListener((ActionEvent e) -> {
            PersonelContract contract = (PersonelContract) emailBox.getSelectedItem();
            String sifre = passwordField.getText();
            if (new AccountDAL().GetPersonelIdVeSifre(contract.getId(), sifre).getId() != 0 ) {      
                AnaPencereFE anaPencereFE = new AnaPencereFE();
                setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Giriş Başarısız!");
            }
            
        });
        
        return panel;
    }

    @Override
    public JMenuBar initBar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JTabbedPane initTabs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
