/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.fe;

import static java.awt.Dialog.DEFAULT_MODALITY_TYPE;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
public class YetkiEkleFE extends JDialog implements FeInterfaces {

    public YetkiEkleFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Yetki Ekle"));

        add(panel);
        setTitle("Yetki Ekle");
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel adiLabel = new JLabel("Yetki Adı:", JLabel.RIGHT);
        panel.add(adiLabel);
        JTextField adiField = new JTextField(10);
        panel.add(adiField);

        JButton kaydetButton = new JButton("Kaydet");
        panel.add(kaydetButton);
        JButton iptalButton = new JButton("İptal");
        panel.add(iptalButton);

        kaydetButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                YetkilerContract contract = new YetkilerContract();
                contract.setAd(adiField.getText());

                new YetkilerDAL().Insert(contract);
                JOptionPane.showMessageDialog(null, contract.getAd() + " yetkisi eklendi!");

            });
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
