/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import tr.com.rah.dal.KategoriDAL;
import tr.com.rah.interfaces.FeInterfaces;
import tr.com.rah.types.KategoriContract;

/**
 *
 * @author rahimgng
 */
public class KategoriEkleFe extends JDialog implements FeInterfaces {
    
    public KategoriEkleFe() {
        initPencere();
    }
    
    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("KategoriEkle"));
        
        add(panel);
        setTitle("Kategori Ekle");
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    
    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new GridLayout(3,2));
        
        JLabel adiLabel = new JLabel("Kategori Adı:",JLabel.RIGHT);
        panel.add(adiLabel);
        JTextField adiField = new JTextField(10);
        panel.add(adiField);
        JLabel kategoriLabel = new JLabel("Kategori:",JLabel.RIGHT);
        panel.add(kategoriLabel);
        JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAllParentId().toArray());
        panel.add(kategoriBox);
        kategoriBox.insertItemAt("--Kategori Seçiniz--", 0);
        kategoriBox.setSelectedIndex(0);
        JButton kaydetButton = new JButton("Kaydet");
        panel.add(kaydetButton);
        kaydetButton.addActionListener((ActionEvent e) -> {
            KategoriContract contract = new KategoriContract();
            
            if(kategoriBox.getSelectedIndex() != 0){
                KategoriContract casContract = (KategoriContract) kategoriBox.getSelectedItem();
                contract.setAdi(adiField.getText());
                contract.setParentId(casContract.getId());
                
                new KategoriDAL().Insert(contract);
                JOptionPane.showMessageDialog(null,contract.getAdi() + " kategorisi başarıyla eklendi!");
            }else{
                contract.setAdi(adiField.getText());
                contract.setParentId(kategoriBox.getSelectedIndex());
                
                new KategoriDAL().Insert(contract);
                JOptionPane.showMessageDialog(null,contract.getAdi() + " kategorisi başarıyla eklendi!");
                kategoriBox.addItem(new KategoriDAL().GetAllParentId());
            }
        });
        
        JButton iptalButton = new JButton("İptal");
        panel.add(iptalButton);
        
        iptalButton.addActionListener((ActionEvent e) -> {
            iptalButton.setVisible(false);
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
