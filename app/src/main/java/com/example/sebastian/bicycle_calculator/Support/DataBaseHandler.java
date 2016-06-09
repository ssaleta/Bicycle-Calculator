package com.example.sebastian.bicycle_calculator.Support;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sebastian.bicycle_calculator.Model.Bicycle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 2016-06-05.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    public static DataBaseHandler sInstance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bicycleManager";
    private static final String TABLE_BICYCLES = "bicycles";
    //column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CHAINRING = "chainring";
    private static final String KEY_COG = "cog";
    private static final String KEY_SKIDPATCH = "skidPatch";
    private static final String KEY_RATIO = "ratio";




public static synchronized DataBaseHandler getsInstance(Context context){
    if(sInstance == null){
        sInstance = new DataBaseHandler(context.getApplicationContext());
    }
    return sInstance;
}
    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     /*   String CREATE_BICYCLES_TABLE = "CREATE TABLE " + TABLE_BICYCLES + "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + "TEXT," + KEY_CHAINRING +"REAL," + KEY_COG + "INTEGER" + ")";*/
        String CREATE_BICYCLES_TABLE = "CREATE TABLE " + TABLE_BICYCLES +
                "("
                + KEY_ID + "  INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_CHAINRING + " REAL,"
                + KEY_COG + " REAL,"
                + KEY_SKIDPATCH + " REAL,"
                + KEY_RATIO + " REAL" +
                ")";

        db.execSQL(CREATE_BICYCLES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_BICYCLES);
        onCreate(db);
    }

    public void addBicycle (Bicycle bicycle){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bicycle.getName());
        values.put(KEY_CHAINRING, bicycle.getChainring());
        values.put(KEY_COG, bicycle.getCog());
        values.put(KEY_SKIDPATCH, bicycle.getSkidPatch());
        values.put(KEY_RATIO, bicycle.getRatio());



        //insterting row
        db.insert(TABLE_BICYCLES, null, values);
        db.close();

    }
    public Bicycle getBicycle(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.query(TABLE_BICYCLES, new String[]{KEY_ID, KEY_NAME, KEY_CHAINRING, KEY_COG, KEY_SKIDPATCH, KEY_RATIO}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Bicycle bicycle = new Bicycle(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Double.parseDouble(cursor.getString(2)), Double.parseDouble(cursor.getString(3)), Double.parseDouble(cursor.getString(4)), Double.parseDouble(cursor.getString(5)));
        return bicycle;

    }
    public List<Bicycle> getAllBicycles(){
        List<Bicycle> bicycleList = new ArrayList<Bicycle>();
            String selectQuery = "SELECT  * FROM " + TABLE_BICYCLES;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Bicycle bicycle = new Bicycle();
                bicycle.setName(cursor.getString(1));
                Log.d("getAllbicycles", " name" + cursor.getString(1));
                Log.d("getAllbicycles", " chainring" + cursor.getString(2));
                Log.d("getAllbicycles", " cog" + cursor.getString(3));
                Log.d("getAllbicycles", " skidpatch" + cursor.getString(4));
                Log.d("getAllbicycles", " ratio" + cursor.getString(5));
                bicycle.setChainring(Double.parseDouble(cursor.getString(2)));

                bicycle.setCog(Double.parseDouble(cursor.getString(3)));
                bicycle.setSkidPatch(Double.parseDouble(cursor.getString(4)));
                bicycle.setRatio(Double.parseDouble(cursor.getString(5)));
                bicycleList.add(bicycle);

            }while(cursor.moveToNext());
        }

        return bicycleList;
    }

    public int getBicyclesCount(){
        String countQuery = "SELECT * FROM" + TABLE_BICYCLES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();

    }
    public int updateBicycles (Bicycle bicycle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bicycle.getName());
        values.put(KEY_CHAINRING, bicycle.getChainring());
        values.put(KEY_COG,bicycle.getCog());
        values.put(KEY_SKIDPATCH,bicycle.getSkidPatch());
        values.put(KEY_RATIO,bicycle.getRatio());
        return db.update(TABLE_BICYCLES, values, KEY_ID + " = ?", new String[] {String.valueOf(bicycle.getItemId())});
    }
    public void deleteBicycle(Bicycle bicycle){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BICYCLES, KEY_ID + " = ?", new String[]{String.valueOf(bicycle.getItemId())});
        db.close();
    }
}
