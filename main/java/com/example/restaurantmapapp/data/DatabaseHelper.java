package com.example.restaurantmapapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.restaurantmapapp.model.Locations;
import com.example.restaurantmapapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Util.LOCATION_NAME + " TEXT ," + Util.LOCATION_LAT + " REAL," + Util.LOCATION_LON + " REAL)";

        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROPTABLE = "DROP TABLE IF EXISTS";
        db.execSQL(DROPTABLE, new String[]{Util.TABLE_NAME});

        onCreate(db);
    }

    public long insertLocation(Locations location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Util.LOCATION_NAME, location.getLocation_name());
        cv.put(Util.LOCATION_LAT, location.getLat());
        cv.put(Util.LOCATION_LON, location.getLon());
        long newrowid = db.insert(Util.TABLE_NAME, null,cv);
        db.close();
        return newrowid;

    }

    public List<Locations> listAllLocations(){
        List<Locations> locList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String SelectAll = " SELECT * FROM "+ Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(SelectAll, null);
        if (cursor.moveToFirst()){
            do {
                Locations loc = new Locations();

                loc.setLocation_name(cursor.getString(1));
                loc.setLat(cursor.getDouble(2));
                loc.setLon(cursor.getDouble(3));
                System.out.println( "INSIDE THE CURSOR = "+cursor.getString(1) +", "+cursor.getDouble(2) + ", "+ cursor.getDouble(3) );
                locList.add(loc);

            }while (cursor.moveToNext());
        }
        return locList;
    }
}
