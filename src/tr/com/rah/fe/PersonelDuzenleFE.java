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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import tr.com.rah.dal.PersonelDAL;
import tr.com.rah.interfaces.FeInterfaces;
import tr.com.rah.types.PersonelContract;

/**
 *
 * @author rahimgng
 */
public class PersonelDuzenleFE extends JDialog implements FeInterfaces{

    public PersonelDuzenleFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Personel Düzenleme Sayfası"));

        add(panel);
        setTitle("Personeli Düzenle");
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel ustPanel = new JPanel(new GridLayout(4, 2));
        ustPanel.setBorder(BorderFactory.createTitledBorder("Personeli Düzenle"));
        
        JLabel personelLabel = new JLabel("Personel Seç:", JLabel.RIGHT);
        ustPanel.add(personelLabel);
        JComboBox personelBox = new JComboBox(new PersonelDAL().GetAll().toArray());
        ustPanel.add(personelBox);
        
        JLabel adiLabel = new JLabel("Personel Adı Düzenle:", JLabel.RIGHT);
        ustPanel.add(adiLabel);
        JTextField adiField = new JTextField(10);
        ustPanel.add(adiField);
        JLabel emailLabel = new JLabel("Personel Maili Düzenle:", JLabel.RIGHT);
        ustPanel.add(emailLabel);
        JTextField emailField = new JTextField(10);//new PersonelDAL().GetAll().toString()
        ustPanel.add(emailField);
        
        JButton guncelleButton = new JButton("Güncelle");
        ustPanel.add(guncelleButton);
        JButton silButton = new JButton("Sil");
        ustPanel.add(silButton);
        
        //güncelle action
        guncelleButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                PersonelContract contract = new PersonelContract();
                PersonelContract pContract = (PersonelContract) personelBox.getSelectedItem();
                
                contract.setId(pContract.getId());
                contract.setAdiSoyadi(adiField.getText());
                contract.setEmail(emailField.getText());
                
                new PersonelDAL().Update(contract);
                JOptionPane.showMessageDialog(null, contract.getAdiSoyadi() + " adlı personel güncellendi!");
                
            });
        });
        
        //sil action
        silButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                PersonelContract contract = new PersonelContract();
                PersonelContract pContract = (PersonelContract) personelBox.getSelectedItem();
                
                contract.setId(pContract.getId());
                
                new PersonelDAL().Delete(contract);
                JOptionPane.showMessageDialog(null, contract.getAdiSoyadi() + " adlı kişi silindi!");
            });
        });
        
        panel.add(ustPanel);
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
