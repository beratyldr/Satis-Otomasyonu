/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.fe;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import tr.com.rah.dal.HesapDAL;
import tr.com.rah.dal.MusteriDAL;
import tr.com.rah.dal.SatisDAL;
import tr.com.rah.dal.StokDAL;
import tr.com.rah.dal.UrunlerDAL;
import tr.com.rah.interfaces.FeInterfaces;
import tr.com.rah.types.HesapContract;
import tr.com.rah.types.MusteriContract;
import tr.com.rah.types.PersonelContract;
import tr.com.rah.types.SatisContract;
import tr.com.rah.types.StokContract;
import tr.com.rah.types.UrunlerContract;
import tr.com.rah.utilities.MenulerCom;

/**
 *
 * @author rahimgng
 */
public class AnaPencereFE extends JFrame implements FeInterfaces {

    public int i = 0;
    public int j = 0;

    public AnaPencereFE() {
        initPencere();
    }

    @Override
    public final void initPencere() {
        JPanel panel = initPanel();
        JMenuBar bar = initBar();

        add(panel);
        setJMenuBar(bar);
        setTitle("Satış ve Stok Programı");
        pack();
        setSize(getMaximumSize());
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTabbedPane pane = initTabs();
        panel.add(pane, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public JMenuBar initBar() {
        JMenuBar bar = MenulerCom.initBar();
        return bar;
    }

    public JButton initButton(String name) {
        JButton button = new JButton(name);
        //button.setSize(250, 100);
        return button;
    }

    @Override
    public JTabbedPane initTabs() {

        JTabbedPane pane = new JTabbedPane();
        ImageIcon icon = new ImageIcon("icons/stock.png");

        JPanel stokPanel = new JPanel(new BorderLayout());
        JPanel satisPanel = new JPanel(new BorderLayout());
        JPanel hesaplarPanel = new JPanel();

        //stok tab
        JPanel stokSolPanel = new JPanel(new BorderLayout());
        JPanel stokSolUstPanel = new JPanel(new GridLayout(5, 2));
        JPanel stokSolAltPanel = new JPanel();

        stokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok İşlemleri"));
        Object[] stokKolonlar = {"Id", "Ürün Adı", "Personel Adı", "Adet", "Tarih"};
        DefaultTableModel model = new DefaultTableModel(stokKolonlar, 0);
        JTable table = new JTable(model);
        JScrollPane stokTablePane = new JScrollPane(table);

        new StokDAL().GetAllStok().forEach((contract) -> {
            model.addRow(contract.getVeriler());
        });

        JLabel stokUrunAdiLabel = new JLabel("Ürün Adı:", JLabel.LEFT);
        stokSolUstPanel.add(stokUrunAdiLabel);
        JComboBox stokUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
        stokSolUstPanel.add(stokUrunAdiBox);
        JLabel stokAdetLabel = new JLabel("Adet:", JLabel.LEFT);
        stokSolUstPanel.add(stokAdetLabel);
        JTextField stokAdetField = new JTextField(10);
        stokSolUstPanel.add(stokAdetField);
        JLabel stokTarihiLabel = new JLabel("Tarih:", JLabel.LEFT);
        stokSolUstPanel.add(stokTarihiLabel);
        JDateChooser stokTarihi = new JDateChooser();
        stokSolUstPanel.add(stokTarihi);

        JButton stokEkleButton = new JButton("Stok Ekle");
        stokSolUstPanel.add(stokEkleButton);
        JButton stokYenileButton = new JButton("Yenile");
        stokSolUstPanel.add(stokYenileButton);



        //stok yenile action
        stokYenileButton.addActionListener((ActionEvent e) -> {
            int satir = model.getRowCount();
            for (int i = 0; i < satir; i++) {
                model.removeRow(0);
            }
            new StokDAL().GetAllStok().forEach((compContract) -> {
                model.addRow(compContract.getVeriler());
            });
        });

        //stok ekle action
        stokEkleButton.addActionListener((ActionEvent e) -> {
            StokContract contract = new StokContract();
            UrunlerContract uContract = (UrunlerContract) stokUrunAdiBox.getSelectedItem();
            PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();

            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            String date = format.format(stokTarihi.getDate());

            contract.setPersonelId(pContract.getId());
            contract.setUrunId(uContract.getId());
            contract.setTarih(date);
            contract.setAdet(Integer.parseInt(stokAdetField.getText()));
            StokDAL stk=new StokDAL();
            stk.Update(contract.getUrunId(), -contract.getAdet());
            if(stk.b==true){
            JOptionPane.showMessageDialog(null, uContract.getAdi() + " adlı ürün stok içerisinden" + contract.getAdet() + " adet eklendi!");
            }
            else{
                stk.Insert(contract);
            }

            //aynı anda sağ tarafa ekleme
            int satir = model.getRowCount();
            for (int i = 0; i < satir; i++) {
                model.removeRow(0);
            }
            new StokDAL().GetAllStok().forEach((compContract) -> {
                model.addRow(compContract.getVeriler());
            });

        });

        //satış tab
        JPanel satisSagPanel = new JPanel(new BorderLayout());
        JPanel satisSagUstPanel = new JPanel(new GridLayout(6, 2));
        JPanel satisSagAltPanel = new JPanel();

        satisSagPanel.setBorder(BorderFactory.createTitledBorder("Satış İşlemleri"));
        Object[] satisKolonlar = {"Id", "Personel Adı", "Müşteri Adı", "Ürün Adı", "Adeti", "Tarihi"};
        DefaultTableModel satisModel = new DefaultTableModel(satisKolonlar, 0);
        JTable satisTable = new JTable(satisModel);
        JScrollPane satisTablePane = new JScrollPane(satisTable);

        JLabel musteriLabel = new JLabel("Müşteri Adı:", JLabel.LEFT);
        satisSagUstPanel.add(musteriLabel);
        JComboBox musteriAdiBox = new JComboBox(new MusteriDAL().GetAll().toArray());
        satisSagUstPanel.add(musteriAdiBox);
        JLabel satisUrunAdiLabel = new JLabel("Ürün Adı:", JLabel.LEFT);
        satisSagUstPanel.add(satisUrunAdiLabel);
        JComboBox satisUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
        JComboBox hesapNo = new JComboBox(new UrunlerDAL().GetAll().toArray());
        satisSagUstPanel.add(satisUrunAdiBox);
        JLabel satisAdetLabel = new JLabel("Adet:", JLabel.LEFT);
        satisSagUstPanel.add(satisAdetLabel);
        JTextField satisAdetField = new JTextField(10);
        satisSagUstPanel.add(satisAdetField);
        JLabel satisTarihiLabel = new JLabel("Tarih:", JLabel.LEFT);
        satisSagUstPanel.add(satisTarihiLabel);
        JDateChooser satisTarihi = new JDateChooser();
        satisSagUstPanel.add(satisTarihi);

        JButton satisEkleButton = new JButton("Satış Yap");
        satisSagUstPanel.add(satisEkleButton);
        JButton satisYenileButton = new JButton("Yenile");
        satisSagUstPanel.add(satisYenileButton);

        //sağda göstermek için
        new SatisDAL().GetAllSatis().forEach((compContract) -> {
            satisModel.addRow(compContract.getVeriler());
        });

        //satis ekle action
        satisEkleButton.addActionListener((ActionEvent e) -> {
            PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
            UrunlerContract uContract = (UrunlerContract) satisUrunAdiBox.getSelectedItem();
            MusteriContract mContract = (MusteriContract) musteriAdiBox.getSelectedItem();
            SatisContract contract = new SatisContract();

            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            String date = format.format(satisTarihi.getDate());

            contract.setMusteriId(mContract.getId());
            contract.setPersonelId(pContract.getId());
            contract.setUrunId(uContract.getId());
            contract.setAdet(Integer.parseInt(satisAdetField.getText()));
            contract.setTarih(date);

           

            //aynı anda sağa ekleme
            int satir = satisModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                satisModel.removeRow(0);
            }
            

            //stoktan eksiltme işlemi
            StokDAL stk=new StokDAL();
            stk.Update(contract.getUrunId(), contract.getAdet());
            if(stk.a==true){
            JOptionPane.showMessageDialog(null, uContract.getAdi() + " adlı ürün stok içerisinden" + contract.getAdet() + " adet eksiltildi!");
            new SatisDAL().Insert(contract);
            }
            new SatisDAL().GetAllSatis().forEach((compContract) -> {
                satisModel.addRow(compContract.getVeriler());
            });
        });

        //satis yenile action
        satisYenileButton.addActionListener((ActionEvent e) -> {
            int satir = satisModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                satisModel.removeRow(0);
            }
            new SatisDAL().GetAllSatis().forEach((compContract) -> {
                satisModel.addRow(compContract.getVeriler());
            });
        });

        //hesaplar tab
        JPanel hesaplarSolPanel = new JPanel();
        JPanel hesaplarOrtaPanel = new JPanel();
        JPanel hesaplarAltPanel = new JPanel();

        Object[] hesapSiparisleri = {"Id", "MASA NO", "Fiyat", "Ürün Adı", "Adeti", "TOPLAM FİYAT", "Tarihi"};
        DefaultTableModel hesapModel = new DefaultTableModel(hesapSiparisleri, 0);
        JTable hesapTable = new JTable(hesapModel);
        JScrollPane hesapTablePane = new JScrollPane(hesapTable);
        JButton toplam = new JButton("Toplam Hesap");
        hesaplarAltPanel.add(toplam);
        JButton ode = new JButton("ÖDE");
        hesaplarAltPanel.add(ode);
        hesaplarSolPanel.setBorder(BorderFactory.createTitledBorder("hesap İşlemleri"));
        JLabel hesapSiparis = new JLabel("Ürün Adı:", JLabel.LEFT);
        hesaplarSolPanel.add(hesapSiparis);
        JComboBox hesapUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
        hesaplarSolPanel.add(hesapUrunAdiBox);
        JLabel hesapAdetLabel = new JLabel("Adet:", JLabel.LEFT);
        hesaplarSolPanel.add(hesapAdetLabel);
        JTextField hesapAdetField = new JTextField(10);
        hesaplarSolPanel.add(hesapAdetField);
        /*JLabel hesapFiyatLabel = new JLabel("Fiyat:", JLabel.LEFT);
        hesaplarSolPanel.add(hesapFiyatLabel);
        JTextField hesapFiyatField = new JTextField(10);
        hesaplarSolPanel.add(hesapFiyatField);*/
        JLabel hesapTarihiLabel = new JLabel("Tarih:", JLabel.LEFT);
        hesaplarSolPanel.add(hesapTarihiLabel);
        JDateChooser hesapTarihi = new JDateChooser();
        hesaplarSolPanel.add(hesapTarihi);

        JButton hesapEkleButton = new JButton("Sipariş Ekle");
        hesaplarSolPanel.add(hesapEkleButton);
        JButton hesapYenileButton = new JButton("Yenile");
        hesaplarSolPanel.add(hesapYenileButton);

        /*JButton hesapTotalButton = new JButton("Stok Toplam Ürün");
        hesaplarSolPanel.add(hesapTotalButton);*/
        toplam.addActionListener((ActionEvent e) -> {
            int a = 0;
            HesapDAL tpl = new HesapDAL();
            a = tpl.Toplam(j);
            JOptionPane.showMessageDialog(null,"toplam ödencek tutar ="+a);
        });
        ode.addActionListener((ActionEvent e) -> {
            HesapDAL ab = new HesapDAL();
            ab.Delete(j);
            JOptionPane.showMessageDialog(null,"toplam ödencek tutar =");
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
        });
        JButton[] btn = new JButton[20];

        for (int i = 0; i < 20; i++) {
            btn[i] = new JButton("MASA" + (i + 1));
            hesaplarOrtaPanel.add(btn[i]);
        }
        btn[0].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 1;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });

        });
        btn[1].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 2;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });

        });
        btn[2].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 3;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[3].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 4;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[4].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 5;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[5].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 6;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[6].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 7;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[7].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 8;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[8].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 9;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[9].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 10;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[10].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 11;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[11].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 12;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[12].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 13;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[13].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 14;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[14].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 15;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[15].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 16;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[16].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 17;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[17].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 18;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[18].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 19;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });
        btn[19].addActionListener((ActionEvent e) -> {
            hesaplarAltPanel.setVisible(true);
            hesaplarSolPanel.setVisible(true);
            j = 20;
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });

        //hesap ekle action
        hesapEkleButton.addActionListener((ActionEvent e) -> {
            //PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
            UrunlerContract uContract = (UrunlerContract) hesapUrunAdiBox.getSelectedItem();
            HesapContract contract = new HesapContract();

            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            String date = format.format(hesapTarihi.getDate());

            contract.setMasaId(j);
            contract.setUrunId(uContract.getId());
            contract.setAdet(Integer.parseInt(hesapAdetField.getText()));
            contract.setFiyat(uContract.getFiyat());
            contract.setToplamFiyat( Integer.parseInt(hesapAdetField.getText()) * uContract.getFiyat());
            contract.setTarih(date);

            

            //aynı anda sağa ekleme
            int satir = hesapModel.getRowCount();
            for (int i = 0; i < satir; i++) {
                hesapModel.removeRow(0);
            }

            
            StokDAL stk=new StokDAL();
            stk.Update(contract.getUrunId(), contract.getAdet());
            if(stk.a==true){
            JOptionPane.showMessageDialog(null, uContract.getAdi() + " adlı ürün stok içerisinden" + contract.getAdet() + " adet eksiltildi!");
            new HesapDAL().Insert(contract);
            }
            new HesapDAL().GetHesapNo(j).forEach((compContract) -> {
                hesapModel.addRow(compContract.getVeriler());
            });
        });

        //panel işlemleri
        stokPanel.add(stokSolPanel, BorderLayout.WEST);
        stokPanel.add(stokTablePane, BorderLayout.CENTER);

        satisPanel.add(satisSagPanel, BorderLayout.WEST);
        satisPanel.add(satisTablePane, BorderLayout.CENTER);

        stokSolPanel.add(stokSolUstPanel, BorderLayout.NORTH);
        stokSolPanel.add(stokSolAltPanel, BorderLayout.SOUTH);

        satisSagPanel.add(satisSagUstPanel, BorderLayout.NORTH);
        satisSagPanel.add(satisSagAltPanel, BorderLayout.SOUTH);

        hesaplarPanel.add(hesaplarSolPanel);
        hesaplarPanel.add(hesaplarOrtaPanel);
        hesaplarPanel.add(hesaplarAltPanel);
        hesaplarAltPanel.add(hesapTablePane);
        hesaplarSolPanel.setVisible(false);
        hesaplarAltPanel.setVisible(false);

        pane.addTab("Stoklar ", icon, stokPanel, "does nothing");
        pane.addTab("Satışlar ", icon, satisPanel, "does nothing");
        pane.addTab("Hesaplar ", icon, hesaplarPanel, "does nothing");

        return pane;
    }
}
