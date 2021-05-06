/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.utilities;

import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import tr.com.rah.dal.AccountDAL;
import tr.com.rah.fe.KategoriDuzenleFE;
import tr.com.rah.fe.KategoriEkleFe;
import tr.com.rah.fe.LoginFE;
import tr.com.rah.fe.MusteriEkleFE;
import tr.com.rah.fe.MüsteriDuzenleFE;
import tr.com.rah.fe.PersonelDuzenleFE;
import tr.com.rah.fe.PersonelEkleFE;
import tr.com.rah.fe.SehirDuzenleFE;
import tr.com.rah.fe.SehirEkleFE;
import tr.com.rah.fe.SifreBelirleFE;
import tr.com.rah.fe.UrunEkleFE;
import tr.com.rah.fe.UrunuDuzenleFE;
import tr.com.rah.fe.YetkiDuzenleFE;
import tr.com.rah.fe.YetkiEkleFE;
import tr.com.rah.types.PersonelContract;

/**
 *
 * @author rahimgng
 */
public class MenulerCom {

    public static JMenuBar initBar() {
        JMenuBar bar = new JMenuBar();

        //ürünler
        JMenu urunlerMenu = new JMenu("Ürünler");
        bar.add(urunlerMenu);
        JMenuItem urunlerEkleItem = new JMenuItem("Ürün Ekle");
        urunlerMenu.add(urunlerEkleItem);
        JMenuItem kategoriEkleItem = new JMenuItem("Kategori Ekle");
        urunlerMenu.add(kategoriEkleItem);
        urunlerMenu.addSeparator();
        JMenuItem urunuDuzenleItem = new JMenuItem("Ürünü Düzenle");
        urunlerMenu.add(urunuDuzenleItem);
        JMenuItem kategoriDuzenleItem = new JMenuItem("Kategoriyi Düzenle");
        urunlerMenu.add(kategoriDuzenleItem);

        //personeller menüsü
        JMenu personellerMenu = new JMenu("Personeller");
        bar.add(personellerMenu);
        JMenuItem personelEkleItem = new JMenuItem("Personel Ekle");
        personellerMenu.add(personelEkleItem);
        JMenuItem yetkiEkleItem = new JMenuItem("Yetki Ekle");
        personellerMenu.add(yetkiEkleItem);
        JMenuItem sifreBelirleItem = new JMenuItem("Şifre ve Yetki Belirleme");
        personellerMenu.add(sifreBelirleItem);
        personellerMenu.addSeparator();

        JMenuItem personelDuzenleItem = new JMenuItem("Personel Düzenle");
        personellerMenu.add(personelDuzenleItem);
        JMenuItem yetkiDuzenleItem = new JMenuItem("Yetki Düzenle");
        personellerMenu.add(yetkiDuzenleItem);

        //müşteri menü
        JMenu müsterilerMenu = new JMenu("Müşteriler");
        bar.add(müsterilerMenu);
        JMenuItem müsteriEkleItem = new JMenuItem("Müşteri Ekle");
        müsterilerMenu.add(müsteriEkleItem);
        JMenuItem sehirEkleItem = new JMenuItem("Şehir Ekle");
        müsterilerMenu.add(sehirEkleItem);
        müsterilerMenu.addSeparator();

        JMenuItem müsteriDuzenleItem = new JMenuItem("Müşteri Düzenle");
        müsterilerMenu.add(müsteriDuzenleItem);
        JMenuItem sehirDuzenleItem = new JMenuItem("Şehir Düzenle");
        müsterilerMenu.add(sehirDuzenleItem);
        PersonelContract contract = (PersonelContract) LoginFE.emailBox.getSelectedItem();

        if (new AccountDAL().GetYetkiId(contract.getId()).getYetkiId() == 2) {
            personellerMenu.hide();
        } else if (new AccountDAL().GetYetkiId(contract.getId()).getYetkiId() == 3) {
            müsterilerMenu.hide();
            personellerMenu.hide();
            urunlerMenu.hide();
        }

        /**
         * Ekleme İşlemleri
         */
        
        //ürün ekleme
        urunlerEkleItem.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                UrunEkleFE urunEkleFE = new UrunEkleFE();
            });
        });

        //kategori ekleme
        kategoriEkleItem.addActionListener((ActionEvent e) -> {
            KategoriEkleFe kategoriEkleFe = new KategoriEkleFe();
        });

        //personel ekleme
        personelEkleItem.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                PersonelEkleFE personelEkleFE = new PersonelEkleFE();
            });
        });

        //yetki ekleme
        yetkiEkleItem.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                YetkiEkleFE yetkiEkleFE = new YetkiEkleFE();
            });
        });

        //şifre ekleme
        sifreBelirleItem.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                SifreBelirleFE sifreBelirleFE = new SifreBelirleFE();
            });
        });

        //müşteri ekleme
        müsteriEkleItem.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                MusteriEkleFE müsteriEkleFE = new MusteriEkleFE();
            });
        });

        //şehir ekleme
        sehirEkleItem.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                SehirEkleFE setkiEkleFE = new SehirEkleFE();
            });
        });

        /**
         * Düzenleme İşlemleri
         */
        
        //kategori Düzenle
        kategoriDuzenleItem.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                KategoriDuzenleFE kategoriDuzenleFE = new KategoriDuzenleFE();
            });
        });

        //ürün Düzenle
        urunuDuzenleItem.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                UrunuDuzenleFE urunuDuzenleFE = new UrunuDuzenleFE();
            });
        });

        //personel Düzenle
        personelDuzenleItem.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                PersonelDuzenleFE personelDuzenleFE = new PersonelDuzenleFE();
            });
        });

        //yetki düzenle
        yetkiDuzenleItem.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                YetkiDuzenleFE yetkiDuzenleFE = new YetkiDuzenleFE();
            });
        });

        //Müşteri düzenle
        müsteriDuzenleItem.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                MüsteriDuzenleFE müsteriDuzenleFE = new MüsteriDuzenleFE();
            });
        });

        //şehir düzenle
        sehirDuzenleItem.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                SehirDuzenleFE sehirDuzenleFE = new SehirDuzenleFE();
            });
        });

        /**
         * Gösterim İşlemleri
         */
        
      
       
        return bar;
    }
}
