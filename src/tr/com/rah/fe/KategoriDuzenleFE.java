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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import tr.com.rah.dal.KategoriDAL;
import tr.com.rah.interfaces.FeInterfaces;
import tr.com.rah.types.KategoriContract;

/**
 *
 * @author rahimgng
 */
public class KategoriDuzenleFE extends JDialog implements FeInterfaces {

    public KategoriDuzenleFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Kategori Düzenleme Sayfası"));

        add(panel);
        setTitle("Kategori Düzenle");
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel ustPanel = new JPanel(new GridLayout(5, 2));
        ustPanel.setBorder(BorderFactory.createTitledBorder("Kategori Düzenle"));
        
        JLabel kategoriAdiLabel = new JLabel("Kategori Adı:", JLabel.RIGHT);
        ustPanel.add(kategoriAdiLabel);
        JTextField kategoriAdiField = new JTextField(10);
        ustPanel.add(kategoriAdiField);
        JLabel ustKategoriLabel = new JLabel("Üst Kategori:", JLabel.RIGHT);
        ustPanel.add(ustKategoriLabel);
        JComboBox ustKategoriBox = new JComboBox(new KategoriDAL().GetAllParentId().toArray());
        ustPanel.add(ustKategoriBox);
        /*JLabel altKategoriLabel = new JLabel("Alt Kategori:", JLabel.RIGHT);
        ustPanel.add(altKategoriLabel);
        JComboBox altKategoriBox = new JComboBox(new KategoriDAL().GetAllParentId().toArray());
        ustPanel.add(altKategoriBox);*/
        JLabel kategoriAdiDuzenleLabel = new JLabel("Kategori Adı Düzenle:", JLabel.RIGHT);
        ustPanel.add(kategoriAdiDuzenleLabel);
        JTextField kategoriAdiDuzenleField = new JTextField(10);
        ustPanel.add(kategoriAdiDuzenleField);

        JList kategoriList = new JList();
        kategoriList.setListData(new KategoriDAL().GetAll().toArray());
        JScrollPane pane = new JScrollPane(kategoriList);
        pane.setBorder(BorderFactory.createTitledBorder("Düzenlenecek Liste"));

        JButton duzenleButton = new JButton("Güncelle");
        ustPanel.add(duzenleButton);
        JButton silButton = new JButton("Sil");
        ustPanel.add(silButton);

        silButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {

                KategoriContract contract = new KategoriContract();
                KategoriContract kContract = (KategoriContract) ustKategoriBox.getSelectedItem();

                contract.setId(kContract.getId());

                new KategoriDAL().delete(contract);
                JOptionPane.showMessageDialog(null, " kategorisi silindi!");
                JOptionPane.getRootFrame().dispose();
            });
        });

        duzenleButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {

                KategoriContract contract = new KategoriContract();
                KategoriContract kContract = (KategoriContract) ustKategoriBox.getSelectedItem();
                KategoriContract lContract = (KategoriContract) kategoriList.getSelectedValue();
                /*if(contract.getId() == kContract.getParentId()){
                    
                }*/
                if (lContract != null) {
                    contract.setAdi(kategoriAdiDuzenleField.getText());
                    contract.setId(lContract.getId());
                    contract.setParentId(lContract.getParentId());
                } else {
                    contract.setAdi(kategoriAdiDuzenleField.getText());
                    contract.setId(kContract.getId());
                    contract.setParentId(kContract.getParentId());
                }

                new KategoriDAL().Update(contract);
                JOptionPane.showMessageDialog(null, " kategorisi güncellendi!");
                JOptionPane.getRootFrame().dispose();
            });
        });

        kategoriAdiField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                kategoriList.setListData(new KategoriDAL().GetSearchKategori(kategoriAdiField.getText()).toArray());
                kategoriList.getSelectedIndex();
            }
        });

        panel.add(ustPanel, BorderLayout.NORTH);
        panel.add(pane, BorderLayout.CENTER);

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
