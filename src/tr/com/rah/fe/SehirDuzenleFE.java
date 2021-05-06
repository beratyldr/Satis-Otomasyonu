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
import tr.com.rah.dal.SehirDAL;
import tr.com.rah.dal.YetkilerDAL;
import tr.com.rah.interfaces.FeInterfaces;
import tr.com.rah.types.SehirContract;
import tr.com.rah.types.YetkilerContract;

/**
 *
 * @author rahimgng
 */
public class SehirDuzenleFE extends JDialog implements FeInterfaces {

    public SehirDuzenleFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
       JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Şehir Düzenleme Sayfası"));

        add(panel);
        setTitle("Şehir Düzenle");
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
         JPanel panel = new JPanel(new BorderLayout());

        JPanel ustPanel = new JPanel(new GridLayout(3, 2));
        ustPanel.setBorder(BorderFactory.createTitledBorder("Şehir Düzenle"));

        JLabel adiLabel = new JLabel("Şehir Adı:", JLabel.RIGHT);
        ustPanel.add(adiLabel);
        JTextField adiField = new JTextField(10);
        ustPanel.add(adiField);

        JLabel sehirLabel = new JLabel("Şehir Seç:", JLabel.RIGHT);
        ustPanel.add(sehirLabel);
        JComboBox sehirBox = new JComboBox(new SehirDAL().GetAll().toArray());
        ustPanel.add(sehirBox);

        JButton guncelleButton = new JButton("Güncelle");
        ustPanel.add(guncelleButton);
        JButton silButton = new JButton("Sil");
        ustPanel.add(silButton);

        //güncelle action
        guncelleButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                SehirContract contract = new SehirContract();
                SehirContract sContract = (SehirContract) sehirBox.getSelectedItem();

                contract.setId(sContract.getId());
                contract.setAdi(adiField.getText());

                new SehirDAL().Update(contract);
                JOptionPane.showMessageDialog(null, contract.getAdi()+ " adlı şehir güncellendi!");

            });
        });

        //sil action
        silButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                SehirContract contract = new SehirContract();
                SehirContract yContract = (SehirContract) sehirBox.getSelectedItem();

                contract.setId(yContract.getId());

                new SehirDAL().Delete(contract);
                JOptionPane.showMessageDialog(null, contract.getAdi()+ " adlı şehir silindi!");
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
