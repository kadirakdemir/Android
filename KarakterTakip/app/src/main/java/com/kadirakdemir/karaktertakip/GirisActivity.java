package com.kadirakdemir.karaktertakip;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GirisActivity extends AppCompatActivity {
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    int sonKart=0;
    int skor=0;
    int hata=0;
    int _kartSayisi=0;
    int _secim;
    int _dakika=0;
    int _saniye=0;
    String _zaman;
    int _sure=0;
    Handler handle=null;
    Runnable run=null;
    GridLayout gl;
    GridLayout.LayoutParams glp=new GridLayout.LayoutParams();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        mDrawerList = (ListView)findViewById(R.id.navList);
        Intent i=getIntent();
        final String s=i.getStringExtra("mesaj");
        final int secim=i.getIntExtra("secim",_secim);
        TextView tv=(TextView)findViewById(R.id.textView2);
        tv.setText(s);
        GridLayout.LayoutParams layoutParams=new GridLayout.LayoutParams();
        layoutParams.height=250;

        final TextView ztv=(TextView)findViewById(R.id.zamanTv);

        //Kart sayısı ve kartın boyutları ayarlanıyor

        if (secim==0){
            _kartSayisi=16;
            _sure=2000;
            gl=(GridLayout)findViewById(R.id.kartlarGrid);
            gl.setColumnCount(4);
            gl.setPadding(20,100,20,20);
        }
        if (secim==1) {
            _kartSayisi = 30;
            _sure=4000;
            gl = (GridLayout) findViewById(R.id.kartlarGrid);
            gl.setColumnCount(5);
            gl.setPadding(40,20,0,0);
        }
        if (secim==2) {
            _kartSayisi = 42;
            gl = (GridLayout) findViewById(R.id.kartlarGrid);
            gl.setColumnCount(6);
            _sure=6000;
            gl.setPadding(30,20,0,0);
        }

        // oyun işlemleri

        final Kart kartlar[]=new Kart[_kartSayisi];


        final Button button=new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0;i<_kartSayisi;i++){
                    Kart kart=(Kart)findViewById(kartlar[i].getId());
                    kart.tumunuCevir();
                }
               // Toast.makeText(getApplicationContext(),"Arkaplan Rengi Değişti.", Toast.LENGTH_LONG).show();
            }
        });

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               button.performClick();
            }
        },_sure);

        for (int j=1;j<=_kartSayisi;j++)
        {
            kartlar[j-1]=new Kart(this, j,_kartSayisi/2);
            kartlar[j-1].setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  final Kart k=(Kart)v;
                   k.cevir();
                   if (sonKart>0){
                      final Kart k2=(Kart)findViewById(sonKart);
                       if (k2.onPlanID==k.onPlanID&&k2.getId()!=k.getId()){
                           //Eşleştiler
                           k2.cevrilebilir=false;
                           k.cevrilebilir=false;
                           skor++;
                           sonKart=0;
                           TextView skortv=(TextView)findViewById(R.id.skorTV);
                           skortv.setText("Skorunuz:"+skor);
                           if (skor==_kartSayisi/2){
                               Intent i=new Intent(GirisActivity.this,OyunActivity.class);
                               i.putExtra("puan",hata);
                               i.putExtra("isim",s);
                               i.putExtra("sure",_zaman);
                               startActivity(i);
                                //oyun bitti
                           }
                       }
                       else {
                            //Eşleşmediler geriçevir 2 kartı
                           Handler h=new Handler();
                           h.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   k.cevir();
                                   k2.cevir();
                               }
                           },500);
                           hata++;
                           TextView hatatv=(TextView)findViewById(R.id.hataTV);
                           hatatv.setText("Hata sayınız:"+hata);
                           sonKart=0;
                       }
                   }else {
                       sonKart=k.getId();

                   }
               }
           });
        }

        //Karistir
        for (int k=0;k<_kartSayisi;k++){
            int rg=(int)(Math.random()*_kartSayisi);
            Kart kart = kartlar[rg];
            kartlar[rg]=kartlar[k];
            kartlar[k]=kart;
        }

       handle =new Handler();
        run =new Runnable() {
            @Override
            public void run() {
                handle.removeCallbacks(run);
                handle.postDelayed(run, 1000);
                _saniye++;
                if (_saniye==60){
                    _dakika++;
                    _saniye=0;
                    if(_dakika==60){
                        finish();
                    }
                }
                _zaman=Integer.toString(_dakika)+":"+Integer.toString(_saniye);
                ztv.setText("Süre: " + _dakika+":"+_saniye);
            }
        };
        run.run();

        //GridLayouta kartlar ekleniyor
        for (int j=0; j<_kartSayisi;j++) {
            gl.addView(kartlar[j]);
        }
    }


}
