package com.kadirakdemir.karaktertakip;

/**
 * Created by MK on 25.10.2017.
 */

public class kullanici {

    int Id;
    String Isim;
    int Skor;
    String Sure;
    boolean YorumYaptimi;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getIsim() {
        return Isim;
    }

    public void setIsim(String isim) {
        Isim = isim;
    }

    public int getSkor() {
        return Skor;
    }

    public void setSkor(int skor) {
        Skor = skor;
    }

    public String getSure() {
        return Sure;
    }

    public void setSure(String sure) {
        Sure = sure;
    }

    public boolean isYorumYaptimi() {
        return YorumYaptimi;
    }

    public void setYorumYaptimi(boolean yorumYaptimi) {
        YorumYaptimi = yorumYaptimi;
    }

    public kullanici(String isim, int skor, String sure) {
        Isim = isim;
        Skor = skor;
        Sure = sure;
    }

    public kullanici(int id, String isim, int skor, String sure) {
        Id = id;
        Isim = isim;
        Skor = skor;
        Sure = sure;
    }

    public  String toString(){
        return Id+" "+Isim+" "+Skor+" "+Sure;
    }
}
