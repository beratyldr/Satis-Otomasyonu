/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.fe;

import com.toedter.calendar.JDateChooser;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import tr.com.rah.dal.KategoriDAL;
import tr.com.rah.dal.UrunlerDAL;
import tr.com.rah.interfaces.FeInterfaces;
import tr.com.rah.types.UrunlerContract;

/**
 *
 * @author rahimgng
 */
public class UrunuDuzenleFE extends JDialog implements FeInterfaces {

    public UrunuDuzenleFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Ürün Düzenleme Sayfası"));

        add(panel);
        setTitle("Ürünü Düzenle");
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        //kategoribox, ürünlerbox, isim, fiyat
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel ustPanel = new JPanel(new GridLayout(6, 2));
        ustPanel.setBorder(BorderFactory.createTitledBorder("Ürünü Düzenle"));

        JLabel urunAdiLabel = new JLabel("Ürün Adı:", JLabel.RIGHT);
        ustPanel.add(urunAdiLabel);
        JTextField urunAdiField = new JTextField(10);
        ustPanel.add(urunAdiField);
        JLabel urunLabel = new JLabel("Ürünler:", JLabel.RIGHT);
        ustPanel.add(urunLabel);
        JComboBox urunBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
        ustPanel.add(urunBox);
        JLabel kategoriLabel = new JLabel("Ürün Kategori:", JLabel.RIGHT);
        ustPanel.add(kategoriLabel);
        JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAllParentId().toArray());
        ustPanel.add(kategoriBox);
        JLabel urunFiyatLabel = new JLabel("Ürün Fiyatı:", JLabel.RIGHT);
        ustPanel.add(urunFiyatLabel);
        JTextField urunFiyatField = new JTextField(10);
        ustPanel.add(urunFiyatField);
        JLabel tarihLabel = new JLabel("Tarih",JLabel.RIGHT);
        ustPanel.add(tarihLabel);
        JDateChooser tarihDate = new JDateChooser();
        ustPanel.add(tarihDate);

        JButton guncelleButton = new JButton("Güncelle");
        ustPanel.add(guncelleButton);
        JButton silButton = new JButton("Sil");
        ustPanel.add(silButton);
        
        //güncelle action
        guncelleButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                UrunlerContract contract = new UrunlerContract();
                //UrunlerContract kContract = (UrunlerContract) kategoriBox.getSelectedItem();
                UrunlerContract uContract = (UrunlerContract) urunBox.getSelectedItem();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String date = format.format(tarihDate.getDate());

                contract.setId(uContract.getId());
                contract.setAdi(urunAdiField.getText());
                contract.setKategoriId(uContract.getKategoriId());
                contract.setFiyat(Integer.parseInt(urunFiyatField.getText()));
                contract.setTarih(date);

                new UrunlerDAL().Update(contract);
                JOptionPane.showMessageDialog(null, "ürünü güncellendi!");
            });
        });
        
        //sil Action
        silButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                UrunlerContract contract = new UrunlerContract();
                UrunlerContract uContract = (UrunlerContract) urunBox.getSelectedItem();
                
                contract.setId(uContract.getId());
                
                new UrunlerDAL().Delete(contract);
                JOptionPane.showMessageDialog(null, "ürünü silindi!");
                
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
