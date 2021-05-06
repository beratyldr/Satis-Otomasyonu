/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.types;

/**
 *
 * @author rahimgng
 */
public class HesapContract {

    private int id;
    private int masaId;
    private int urunId;
    private int adet;
    private int fiyat;
    private String tarih;
    private int toplamFiyat;

    public int getToplamFiyat() {
        return toplamFiyat;
    }

    public void setToplamFiyat(int toplamFiyat) {
        this.toplamFiyat = toplamFiyat;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMasaId() {
        return masaId;
    }

    public void setMasaId(int masaId) {
        this.masaId = masaId;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

  

    public int getUrunId() {
        return urunId;
    }

    public void setUrunId(int urunId) {
        this.urunId = urunId;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    @Override
    public String toString() {
        return id + " " +" "+fiyat+""+masaId + urunId + " " + adet + " "+ tarih;
    }

}
