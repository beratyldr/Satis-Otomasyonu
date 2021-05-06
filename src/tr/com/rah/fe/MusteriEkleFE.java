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
import tr.com.rah.dal.SehirDAL;
import tr.com.rah.interfaces.FeInterfaces;
import tr.com.rah.types.MusteriContract;

/**
 *
 * @author rahimgng
 */
public class MusteriEkleFE extends JDialog implements FeInterfaces {

    public MusteriEkleFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Müşteri Ekle"));
        add(panel);
        setTitle("Müşteri Ekle");
        pack();
        setLocationRelativeTo(null);
        setModalityType(DEFAULT_MODALITY_TYPE);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel fieldPanel = new JPanel(new GridLayout(5, 2));
        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        
        JLabel adiSoyadiLabel = new JLabel("Adı Soyadı:", JLabel.RIGHT);
        fieldPanel.add(adiSoyadiLabel);
        JTextField adiSoyadiField = new JTextField(15);
        fieldPanel.add(adiSoyadiField);
        
        JLabel telefonLabel = new JLabel("Telefon:", JLabel.RIGHT);
        fieldPanel.add(telefonLabel);
        JTextField telefonField = new JTextField(15);
        fieldPanel.add(telefonField);
        
        JLabel sehirLabel = new JLabel("Şehir:", JLabel.RIGHT);
        fieldPanel.add(sehirLabel);
        JComboBox sehirBox = new JComboBox(new SehirDAL().GetAll().toArray());
        fieldPanel.add(sehirBox);
        
        JLabel adresLabel = new JLabel("Adres:", JLabel.RIGHT);
        fieldPanel.add(adresLabel);
        
        JTextArea adresArea = new JTextArea(5,1);
        JScrollPane pane = new JScrollPane(adresArea);
        pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));
        //fieldPanel.add(adresArea);
        
        JButton kaydetButton = new JButton("Kaydet");
        buttonPanel.add(kaydetButton);
        JButton iptalButton = new JButton("İptal");
        buttonPanel.add(iptalButton);
        
        kaydetButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                MusteriContract contract = new MusteriContract();
                contract.setAdiSoyadi(adiSoyadiField.getText());
                contract.setTelefon(telefonField.getText());
                contract.setAdres(adresArea.getText());
                contract.setSehirId(sehirBox.getSelectedIndex()+1);
                
                new MusteriDAL().Insert(contract);
                JOptionPane.showMessageDialog(null, contract.getAdiSoyadi()+ " müşterisi eklendi!");
            });
        });
        
        panel.add(fieldPanel,BorderLayout.NORTH);
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
