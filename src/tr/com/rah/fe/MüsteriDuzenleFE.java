/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.fe;

import java.awt.BorderLayout;
import static java.awt.Dialog.DEFAULT_MODALITY_TYPE;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import tr.com.rah.dal.MusteriDAL;
import tr.com.rah.dal.PersonelDAL;
import tr.com.rah.dal.SehirDAL;
import tr.com.rah.dal.UrunlerDAL;
import tr.com.rah.interfaces.FeInterfaces;
import tr.com.rah.types.MusteriContract;
import tr.com.rah.types.PersonelContract;
import tr.com.rah.types.SehirContract;
import tr.com.rah.types.UrunlerContract;

/**
 *
 * @author rahimgng
 */
public class MüsteriDuzenleFE extends JDialog implements FeInterfaces {
    
    public MüsteriDuzenleFE() {
        initPencere();
    }
    
    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Yetki Düzenleme Sayfası"));
        
        add(panel);
        setTitle("Yetki Düzenle");
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    //id AdiSoyadi Telefon Adres SehirId
    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel ustPanel = new JPanel(new GridLayout(5, 2));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        ustPanel.setBorder(BorderFactory.createTitledBorder("Müşteri Düzenle"));
        
        JLabel musteriLabel = new JLabel("Müşteri Bilgileri:", JLabel.RIGHT);
        ustPanel.add(musteriLabel);
        JComboBox musteriBox = new JComboBox(new MusteriDAL().GetAll().toArray());
        ustPanel.add(musteriBox);
        JLabel sehirLabel = new JLabel("Şehir Bilgileri:", JLabel.RIGHT);
        ustPanel.add(sehirLabel);
        JComboBox sehirBox = new JComboBox(new SehirDAL().GetAll().toArray());
        ustPanel.add(sehirBox);
        sehirBox.setSelectedIndex(musteriBox.getSelectedIndex());
        
        JLabel adiLabel = new JLabel("Müşteri Adı Soyadı:", JLabel.RIGHT);
        ustPanel.add(adiLabel);
        JTextField adiField = new JTextField(10);
        ustPanel.add(adiField);
        JLabel telLabel = new JLabel("Müşteri Telefon:", JLabel.RIGHT);
        ustPanel.add(telLabel);
        JTextField telField = new JTextField(10);
        ustPanel.add(telField);
        
        JLabel adresLabel = new JLabel("Adres:", JLabel.RIGHT);
        ustPanel.add(adresLabel);
        
        JTextArea adresArea = new JTextArea(5, 1);
        JScrollPane pane = new JScrollPane(adresArea);
        pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));
        
        JButton guncelleButton = new JButton("Güncelle");
        buttonPanel.add(guncelleButton);
        JButton silButton = new JButton("Sil");
        buttonPanel.add(silButton);
        
        //güncelle action
        guncelleButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                MusteriContract contract = new MusteriContract();
                SehirContract sContract = (SehirContract) sehirBox.getSelectedItem();
                MusteriContract mContract = (MusteriContract) musteriBox.getSelectedItem();
                
                contract.setId(mContract.getId());
                contract.setAdiSoyadi(adiField.getText());
                contract.setTelefon(telField.getText());
                contract.setAdres(adresArea.getText());
                contract.setSehirId(sContract.getId());
                
                new MusteriDAL().Update(contract);
                JOptionPane.showMessageDialog(null, "müşteri güncellendi!");
            });
        });
        
        //sil action
        silButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                MusteriContract contract = new MusteriContract();
                 MusteriContract mContract = (MusteriContract) musteriBox.getSelectedItem();
                
                contract.setId(mContract.getId());
                
                new MusteriDAL().Delete(contract);
                JOptionPane.showMessageDialog(null, contract.getAdiSoyadi() + " adlı kişi silindi!");
            });
        });
        
        panel.add(ustPanel, BorderLayout.NORTH);
        panel.add(pane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
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
