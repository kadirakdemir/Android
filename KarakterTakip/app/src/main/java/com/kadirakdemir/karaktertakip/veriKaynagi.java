package com.kadirakdemir.karaktertakip;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MK on 26.10.2017.
 */

public class veriKaynagi extends AppCompatActivity {
    SQLiteDatabase db;
    sqlLite_katmani sqlLite_katmani;

    public veriKaynagi(Context c){
        sqlLite_katmani=new sqlLite_katmani(c);
    }
    public void ac(){
        db=sqlLite_katmani.getWritableDatabase();
    }
    public void kapa(){
        sqlLite_katmani.close();
    }

    public int kullaniciOlustur(String isim,int skor,String sure){

        ContentValues val=new ContentValues();
        val.put("Isim",isim);
        val.put("Skor",skor);
        val.put("Sure",sure);
        int sonId=(int) db.insert("kullanici",null,val);
        return sonId;
    }

    public List<kullanici> listele(){
        String kolonlar[]={"Isim","Skor","Sure"};
        List<kullanici> liste=new ArrayList<kullanici>();
        String sorgu="SELECT Isım,Skor,Sure * FROM"+"ORDER BY Skor DESC";
        Cursor c=db.query("kullanici",kolonlar,null,null,null,null,"Skor","4");
        c.moveToFirst();
        while (!c.isAfterLast()){

            String isim=c.getString(0);
            int puan=c.getInt(1);
            String sure=c.getString(2);

            kullanici k=new kullanici(isim,puan,sure);
            liste.add(k);
            c.moveToNext();
        }
        c.close();
        return liste;
    }
}
