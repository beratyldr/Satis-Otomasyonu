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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import tr.com.rah.dal.PersonelDAL;
import tr.com.rah.interfaces.FeInterfaces;
import tr.com.rah.types.PersonelContract;

/**
 *
 * @author rahimgng
 */
public class PersonelEkleFE extends JDialog implements FeInterfaces {

    public PersonelEkleFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Personel Ekle"));
        add(panel);
        setTitle("Personel Ekle");
        pack();
        setLocationRelativeTo(null);
        setModalityType(DEFAULT_MODALITY_TYPE);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new GridLayout(3,2));
        
        JLabel adiSoyadiLabel = new JLabel("Adı Soyadı:",JLabel.RIGHT);
        panel.add(adiSoyadiLabel);
        JTextField adiSoyadiField = new JTextField(10);
        panel.add(adiSoyadiField);
        JLabel eMailLabel = new JLabel("E-mail:",JLabel.RIGHT);
        panel.add(eMailLabel);
        JTextField eMailField = new JTextField(10);
        panel.add(eMailField);
        
        JButton kaydetButton = new JButton("Kaydet");
        panel.add(kaydetButton);
        JButton iptalButton = new JButton("İptal");
        panel.add(iptalButton);
        
        kaydetButton.addActionListener((ActionEvent e) -> {
            PersonelContract contract = new PersonelContract();
            contract.setAdiSoyadi(adiSoyadiField.getText());
            contract.setEmail(eMailField.getText());
            
            
            new PersonelDAL().Insert(contract);
            JOptionPane.showMessageDialog(null, contract.getAdiSoyadi() + " personeli eklendi!");
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
