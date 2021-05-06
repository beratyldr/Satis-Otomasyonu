/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.types;

import tr.com.rah.dal.SehirDAL;

/**
 *
 * @author rahimgng
 */
public class MusteriContract {

    private int id;
    private String adiSoyadi;
    private String telefon;
    private String adres;
    private int sehirId;

    SehirContract contract = new SehirContract();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdiSoyadi() {
        return adiSoyadi;
    }

    public void setAdiSoyadi(String adiSoyadi) {
        this.adiSoyadi = adiSoyadi;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getSehirId() {
        return sehirId;
    }

    public void setSehirId(int sehirId) {
        this.sehirId = sehirId;
        //this.sehirId = contract.getId();
    }

    @Override
    public String toString() {
        return  sehirId + " , " + adiSoyadi + " , " + telefon + " , " + adres ;
    }

}
