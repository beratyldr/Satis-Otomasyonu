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
import tr.com.rah.dal.YetkilerDAL;
import tr.com.rah.interfaces.FeInterfaces;
import tr.com.rah.types.YetkilerContract;

/**
 *
 * @author rahimgng
 */
public class YetkiDuzenleFE extends JDialog implements FeInterfaces {

    public YetkiDuzenleFE() {
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

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel ustPanel = new JPanel(new GridLayout(3, 2));
        ustPanel.setBorder(BorderFactory.createTitledBorder("Yetki Düzenle"));

        JLabel adiLabel = new JLabel("Yetki Adı:", JLabel.RIGHT);
        ustPanel.add(adiLabel);
        JTextField adiField = new JTextField(10);
        ustPanel.add(adiField);

        JLabel yetkiLabel = new JLabel("Yetki Seç:", JLabel.RIGHT);
        ustPanel.add(yetkiLabel);
        JComboBox yetkiBox = new JComboBox(new YetkilerDAL().GetAll().toArray());
        ustPanel.add(yetkiBox);

        JButton guncelleButton = new JButton("Güncelle");
        ustPanel.add(guncelleButton);
        JButton silButton = new JButton("Sil");
        ustPanel.add(silButton);

        //güncelle action
        guncelleButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                YetkilerContract contract = new YetkilerContract();
                YetkilerContract yContract = (YetkilerContract) yetkiBox.getSelectedItem();

                contract.setId(yContract.getId());
                contract.setAd(adiField.getText());

                new YetkilerDAL().Update(contract);
                JOptionPane.showMessageDialog(null, contract.getAd() + " adlı yetki güncellendi!");

            });
        });

        //sil action
        silButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                YetkilerContract contract = new YetkilerContract();
                YetkilerContract yContract = (YetkilerContract) yetkiBox.getSelectedItem();

                contract.setId(yContract.getId());

                new YetkilerDAL().Delete(contract);
                JOptionPane.showMessageDialog(null, contract.getAd() + " adlı yetki silindi!");
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
