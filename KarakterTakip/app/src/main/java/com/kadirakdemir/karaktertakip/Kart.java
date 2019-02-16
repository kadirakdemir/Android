package com.kadirakdemir.karaktertakip;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.widget.GridLayout;

/**
 * Created by MK on 18.10.2017.
 */

public class Kart extends android.support.v7.widget.AppCompatButton{
    boolean acikMi=false;
    boolean cevrilebilir=true;
    boolean secenek=true;
    int onPlanID=0;
    int arkaPlanID;
    static int _sayi;
    Drawable arka;
    Drawable on;
    public Kart(Context context){
        super(context);
    }
    public Kart(Context context,int id,int sayi) {
        super(context);
        setId(id);
        _sayi=sayi;
        Integer drw[]={R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,
                R.drawable.a6,R.drawable.a7,R.drawable.a8,R.drawable.a9,R.drawable.a10,R.drawable.a11,
                R.drawable.a12,R.drawable.a13,R.drawable.a14,R.drawable.a15,R.drawable.a16,R.drawable.a17,
                R.drawable.a18,R.drawable.a19,R.drawable.a20,R.drawable.a21};

        if(secenek==true) {
            arkaPlanID = R.drawable.back;
                if(id>sayi)
                    id=id-sayi;
                if(id%sayi==id)
                    onPlanID=drw[id];
                if(id%sayi==0)
                    onPlanID=drw[0];
        }
        if (sayi==8) {
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 240;
            layoutParams.height = 240;
            layoutParams.setMargins(10, 10, 10, 0);
            setLayoutParams(layoutParams);

        }
        if (sayi==15){
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 180;
            layoutParams.height = 180;
            layoutParams.setMargins(10, 10, 10, 10);
            setLayoutParams(layoutParams);
           // setPadding(50,20, 20, 0);
        }
        if (sayi==21){
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 150;
            layoutParams.height = 150;

            layoutParams.setMargins(10, 10, 10, 10);
            setLayoutParams(layoutParams);
            // setPadding(50,20, 20, 0);
        }
        arka= AppCompatDrawableManager.get().getDrawable(context,arkaPlanID);
        on=AppCompatDrawableManager.get().getDrawable(context,onPlanID);
        setBackground(on);
    }
    public void cevir() {
        if (cevrilebilir) {
            if (!acikMi) {
                setBackground(on);
                acikMi = true;
            } else {
                setBackground(arka);
                acikMi = false;
            }
        }
    }
    public void tumunuCevir(){
            setBackground(arka);
    }

}
