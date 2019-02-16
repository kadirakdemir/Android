package com.kadirakdemir.karaktertakip;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MK on 25.10.2017.
 */

public class sqlLite_katmani extends SQLiteOpenHelper {
    public sqlLite_katmani(Context context) {
        super(context, "kullanici", null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table kullanici(Id integer primary key autoincrement,Isim text,Skor integer,Sure text,YorumYaptimi integer default 0) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int eski, int yeni) {
        db.execSQL("drop table if exists kullanici");
    }
}
