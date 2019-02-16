package com.kadirakdemir.karaktertakip;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AnaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana);
        Intent ıntent=getIntent();
        RadioButton rb= (RadioButton)findViewById(R.id.kolayRBtn);
        final RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup1);
        final EditText et=(EditText)findViewById(R.id.editText);
        final String isminiz=et.getText().toString();



        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString().equals("İsminizi Giriniz"))
                    et.setText("");
            }
        });


           /* InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
            */


        String isim=ıntent.getStringExtra("ad");

        if (isim!=null&&isim!=""){
            et.setText(isim);
        }


        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int secim=0;
                RadioButton rb1=(RadioButton) findViewById(R.id.kolayRBtn);
                RadioButton rb2=(RadioButton) findViewById(R.id.ortaRBtn);
                RadioButton rb3=(RadioButton) findViewById(R.id.zorRBtn);
                Intent i=new Intent(AnaActivity.this,GirisActivity.class);
                int seviyeSecim=rg.getCheckedRadioButtonId();
                if (et.getText().toString()=="İsminizi Giriniz"||et.getText()==null) {
                    et.setText("MK");
                }
              if (seviyeSecim==rb1.getId())
                  secim=0;
              if(seviyeSecim==rb2.getId())
                  secim=1;
              if(seviyeSecim==rb3.getId())
                  secim=2;
                i.putExtra("secim",secim);
                i.putExtra("mesaj",et.getText().toString());
                startActivity(i);

            }
        });
    }
}
