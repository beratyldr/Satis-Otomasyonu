/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.complex.types;

import java.sql.Date;

/**
 *
 * @author rahimgng
 */
public class SatisContractComplex {

    private int id;
    private String musteriAdi;
    private String personelAdi;
    private String urunAdi;
    private String tarih;
    private int adet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusteriAdi() {
        return musteriAdi;
    }

    public void setMusteriAdi(String musteriAdi) {
        this.musteriAdi = musteriAdi;
    }

    public String getPersonelAdi() {
        return personelAdi;
    }

    public void setPersonelAdi(String personelAdi) {
        this.personelAdi = personelAdi;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }
    
    public Object[] getVeriler() {
        Object[] veriler = {id, musteriAdi, personelAdi,urunAdi, adet, tarih};

        return veriler;
    }
    
    @Override
    public String toString() {
        return musteriAdi + " " + personelAdi + " " + urunAdi;
    }

}
