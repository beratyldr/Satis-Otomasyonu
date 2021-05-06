/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.complex.types;

/**
 *
 * @author rahimgng
 */
public class HesapContractComplex {

    private int id;
    private int masaNo;
    private int adet;
    private int fiyat;
    private String urunAdi;
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

    public int getMasaNo() {
        return masaNo;
    }

    public void setMasaNo(int masaNo) {
        this.masaNo = masaNo;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
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
        Object[] veriler = {id,masaNo,fiyat,urunAdi,adet,toplamFiyat, tarih};

        return veriler;
    }
    
    @Override
    public String toString() {
        return masaNo+ " " + urunAdi + ""+fiyat+""+adet;
    }

}
