/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.fe;

//import tr.com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import tr.com.rah.dal.KategoriDAL;
import tr.com.rah.dal.UrunlerDAL;
import tr.com.rah.interfaces.FeInterfaces;
import tr.com.rah.types.KategoriContract;
import tr.com.rah.types.UrunlerContract;

/**
 *
 * @author rahimgng
 */
public class UrunEkleFE extends JDialog implements FeInterfaces {

    public UrunEkleFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Ürün Kayıt Alanı"));
        add(panel);
        setTitle("Ürün Ekleyin");
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JLabel adiLabel = new JLabel("Ürün Adı:", JLabel.RIGHT);
        panel.add(adiLabel);
        JTextField adiField = new JTextField(10);
        panel.add(adiField);
        JLabel kategoriLabel = new JLabel("Kategori:", JLabel.RIGHT);
        panel.add(kategoriLabel);
        JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAllParentId().toArray());
        panel.add(kategoriBox);
        JLabel fiyatLabel = new JLabel("Fiyat:", JLabel.RIGHT);
        panel.add(fiyatLabel);
        JTextField fiyatField = new JTextField(10);
        panel.add(fiyatField);
        JLabel tarihLabel = new JLabel("Tarih:", JLabel.RIGHT);
        panel.add(tarihLabel);
        JDateChooser tarihDate = new JDateChooser();
        panel.add(tarihDate);

        JButton kaydetButton = new JButton("Kaydet");
        panel.add(kaydetButton);
        kaydetButton.addActionListener((ActionEvent e) -> {
            UrunlerContract contract = new UrunlerContract();
            KategoriContract casContract = (KategoriContract) kategoriBox.getSelectedItem();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            String date = format.format(tarihDate.getDate());
            
            contract.setAdi(adiField.getText());
            contract.setKategoriId(casContract.getId());
            contract.setTarih(date);
            contract.setFiyat(Integer.parseInt(fiyatField.getText()));
            new UrunlerDAL().Insert(contract);
            JOptionPane.showMessageDialog(null, contract.getAdi() + " ürünü başarılı bir şekilde eklendi!");
        });

        JButton iptalButton = new JButton("İptal");
        panel.add(iptalButton);

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
