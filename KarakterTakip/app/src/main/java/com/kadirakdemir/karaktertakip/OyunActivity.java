package com.kadirakdemir.karaktertakip;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class OyunActivity extends AppCompatActivity {

    veriKaynagi vk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun);
        TextView tv=(TextView)findViewById(R.id.sonucTV);
        Intent i=getIntent();

        final String isim=i.getStringExtra("isim");
        int skor=i.getIntExtra("puan",0);
        String sure=i.getStringExtra("sure");
        tv.setText("Tebrikler"+"  "+isim+" "+"\n" +"Hata Sayısı: "+skor+"\n" +"Süreniz: "+sure);
        findViewById(R.id.tekrarBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(OyunActivity.this,AnaActivity.class);
                k.putExtra("ad",isim);
                startActivity(k);
            }
        });
        ListView lw=(ListView)findViewById(R.id.skorListe);
        vk=new veriKaynagi(this);
        vk.ac();


        kullanici k=new kullanici(isim,skor,sure);
        int sonId=vk.kullaniciOlustur(k.getIsim(),k.getSkor(),k.getSure());
        List<kullanici> kullanicilar=vk.listele();
        ArrayAdapter<kullanici> adaptor=new ArrayAdapter<kullanici>(this,android.R.layout.simple_list_item_1,android.R.id.text1,kullanicilar );
        adaptor.add(k);
        lw.setAdapter(adaptor);
       /* Button button=(Button)findViewById(R.id.cikisBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });*/
    }


    protected void onResume(){
        vk.ac();
        super.onResume();
    }

    protected void pnPause(){
        vk.kapa();
        super.onPause();
    }

}
