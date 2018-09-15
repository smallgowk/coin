package com.phanduy.store;

/**
 * Created by IT001 on 23-Jun-16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.phanduy.model.MyCoin;

import java.util.ArrayList;

public class FavoriteDAO {

    public static final String KEY_ROW_ID = "ROW_ID";
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_SYMBOL = "SYMBOL";
    public static final String KEY_RANK = "RANK";
    public static final String KEY_PRICE_USD = "PRICE_USD";
    public static final String KEY_PRICE_BTC = "PRICE_BTC";
    public static final String KEY_VOLUME_24H= "VOLUME_24H";
    public static final String KEY_PERCENT_CHANGE_1H= "PERCENT_CHANGE_1H";
    public static final String KEY_PERCENT_CHANGE_24H= "PERCENT_CHANGE_24H";
    public static final String KEY_PERCENT_CHANGE_7D= "PERCENT_CHANGE_7D";
    public static final String KEY_AMOUNT= "AMOUT";

    public static final String TABLE_FAVORITE = "FAVORITE";

    private PortfolioDBHelper dbHelper;

    public FavoriteDAO(Context context) {
        dbHelper = new PortfolioDBHelper(context);
    }

    public long insert(MyCoin myCoin) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, myCoin.getId());
        values.put(KEY_NAME, myCoin.getName());
        values.put(KEY_SYMBOL, myCoin.getSymbol());
        values.put(KEY_RANK, myCoin.getRank());
        values.put(KEY_PRICE_USD, myCoin.getPrice_usd());
        values.put(KEY_PRICE_BTC, myCoin.getPrice_btc());
        values.put(KEY_VOLUME_24H, myCoin.getVolume_24h());
//        int random = new Random().nextInt(100);
        values.put(KEY_PERCENT_CHANGE_1H, myCoin.getPercent_change_1h());
        values.put(KEY_PERCENT_CHANGE_24H, myCoin.getPercent_change_24h());
        values.put(KEY_PERCENT_CHANGE_7D, myCoin.getPercent_change_7d());
        values.put(KEY_AMOUNT, myCoin.getAmmount());
        // Inserting Row
        long rowId = db.insert(TABLE_FAVORITE, null, values);

        myCoin.setRowId(rowId);

        db.close(); // Closing database connection
        return rowId;
    }
//    public long insert(float temple) {
//
//        //Open connection to write data
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_TEMPLE, temple);
//        values.put(KEY_TIME, GlobalInfo.simpleDateFormatDB.format(new java.util.Date()));
//        values.put(KEY_STATUS, 0);
//        // Inserting Row
//        long student_Id = db.insert(TABLE_FAVORITE, null, values);
//        db.close(); // Closing database connection
//        return student_Id;
//    }
//
    public void delete(String id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(TABLE_FAVORITE, KEY_ID + "= ?", new String[] { id });
        db.close(); // Closing database connection
    }
    public void delete(MyCoin myCoin) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(TABLE_FAVORITE, KEY_ID + "= ?", new String[] { myCoin.getId() });
        db.close(); // Closing database connection
    }
//
    public void clearData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(TABLE_FAVORITE, null, null);
        db.close(); // Closing database connection
    }
//
    public void update(MyCoin myCoin) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        update(db, myCoin);
        db.close(); // Closing database connection
    }

    public void update(SQLiteDatabase db, MyCoin myCoin) {

        ContentValues values = new ContentValues();

        values.put(KEY_RANK,myCoin.getRank());
        values.put(KEY_PRICE_USD,myCoin.getPrice_usd());
        values.put(KEY_PRICE_BTC, myCoin.getPrice_btc());
        values.put(KEY_VOLUME_24H, myCoin.getVolume_24h());
        values.put(KEY_PERCENT_CHANGE_7D, myCoin.getPercent_change_7d());
        values.put(KEY_PERCENT_CHANGE_1H, myCoin.getPercent_change_1h());
        values.put(KEY_PERCENT_CHANGE_24H, myCoin.getPercent_change_24h());
        values.put(KEY_AMOUNT, myCoin.getAmmount());

        db.update(TABLE_FAVORITE, values, KEY_ID + "= ?", new String[] { myCoin.getId() });
    }

    public void updateInfo(ArrayList<MyCoin> listCoins) {

        if(listCoins == null || listCoins.isEmpty()) {
            return;
        }

        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            for(MyCoin myCoin : listCoins) {
                update(db, myCoin);
            }
        } catch (Exception e) {

        } finally {
            if(db != null) {
                db.close(); // Closing database connection
            }
        }

    }
//
    public ArrayList<MyCoin> getMyCoinList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  * " +
                " FROM " + TABLE_FAVORITE;

        //Student student = new Student();
        ArrayList<MyCoin> listCoins = new ArrayList<MyCoin>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {

                MyCoin myCoin = new MyCoin();
                myCoin.setRowId(cursor.getLong(cursor.getColumnIndex(KEY_ROW_ID)));
                myCoin.setId(cursor.getString(cursor.getColumnIndex(KEY_ID)));
                myCoin.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                myCoin.setSymbol(cursor.getString(cursor.getColumnIndex(KEY_SYMBOL)));
                myCoin.setRank(cursor.getInt(cursor.getColumnIndex(KEY_RANK)));
                myCoin.setPrice_usd(cursor.getDouble(cursor.getColumnIndex(KEY_PRICE_USD)));
                myCoin.setPrice_btc(cursor.getDouble(cursor.getColumnIndex(KEY_PRICE_BTC)));
                myCoin.setVolume_24h(cursor.getDouble(cursor.getColumnIndex(KEY_VOLUME_24H)));
                myCoin.setPercent_change_1h(cursor.getDouble(cursor.getColumnIndex(KEY_PERCENT_CHANGE_1H)));
                myCoin.setPercent_change_24h(cursor.getDouble(cursor.getColumnIndex(KEY_PERCENT_CHANGE_24H)));
                myCoin.setPercent_change_7d(cursor.getDouble(cursor.getColumnIndex(KEY_PERCENT_CHANGE_7D)));
                myCoin.setAmmount(cursor.getDouble(cursor.getColumnIndex(KEY_AMOUNT)));

                listCoins.add(myCoin);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listCoins;

    }
//    public ArrayList<TemperatureObject>  getTempleList(String date) {
//        //Open connection to read only
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String selectQuery =  "SELECT  " +
//                KEY_ID + "," +
//                KEY_TEMPLE + "," +
//                KEY_TIME + "," +
//                KEY_BABY_ID + "," +
//                KEY_PARENT_ID + "," +
//                KEY_DEVICE_MAC + "," +
//                KEY_STATUS +
//                " FROM " + TABLE_FAVORITE
//                + " WHERE (Datetime(" + KEY_TIME + ") between Datetime('" + date + " 00:00:01') " +
//                " AND Datetime('" + date + " 23:59:59'))"
//                ;
//
//        //Student student = new Student();
//        ArrayList<TemperatureObject> studentList = new ArrayList<TemperatureObject>();
//
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        // looping through all rows and adding to list
//
//        if (cursor.moveToFirst()) {
//            do {
//                TemperatureObject temperatureObject = new TemperatureObject();
//                temperatureObject.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
//                temperatureObject.setTemperature(cursor.getFloat(cursor.getColumnIndex(KEY_TEMPLE)));
//                temperatureObject.setTimeDb(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
//                temperatureObject.setStatus(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
//                temperatureObject.setBabyId(cursor.getInt(cursor.getColumnIndex(KEY_BABY_ID)));
//                temperatureObject.setParentId(cursor.getInt(cursor.getColumnIndex(KEY_PARENT_ID)));
//                temperatureObject.setDeviceMac(cursor.getString(cursor.getColumnIndex(KEY_DEVICE_MAC)));
//
//                studentList.add(temperatureObject);
//
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//        return studentList;
//
//    }
//    public ArrayList<TemperatureObject>  getTempleList(String date, String time) {
//        //Open connection to read only
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String selectQuery =  "SELECT  " +
//                KEY_ID + "," +
//                KEY_TEMPLE + "," +
//                KEY_TIME + "," +
//                KEY_BABY_ID + "," +
//                KEY_PARENT_ID + "," +
//                KEY_DEVICE_MAC + "," +
//                KEY_STATUS +
//                " FROM " + TABLE_FAVORITE
//                + " WHERE (Datetime(" + KEY_TIME + ") between (Datetime('" + date + " " + time + "'))" +
//                " AND Datetime('" + date + " 23:59:59'))"
////                + " WHERE (Datetime(" + KEY_TIME + ") after Datetime('" + date + " " + tempCreatedDate + "') " +
////                " AND (Datetime(" + KEY_TIME + ") before Datetime('" + date + " 23:59:59'))"
//                ;
//
//        //Student student = new Student();
//        ArrayList<TemperatureObject> studentList = new ArrayList<TemperatureObject>();
//
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        // looping through all rows and adding to list
//
//        if (cursor.moveToFirst()) {
//            do {
//                TemperatureObject temperatureObject = new TemperatureObject();
//                temperatureObject.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
//                temperatureObject.setTemperature(cursor.getFloat(cursor.getColumnIndex(KEY_TEMPLE)));
//                temperatureObject.setTimeDb(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
//                temperatureObject.setStatus(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
//                temperatureObject.setBabyId(cursor.getInt(cursor.getColumnIndex(KEY_BABY_ID)));
//                temperatureObject.setParentId(cursor.getInt(cursor.getColumnIndex(KEY_PARENT_ID)));
//                temperatureObject.setDeviceMac(cursor.getString(cursor.getColumnIndex(KEY_DEVICE_MAC)));
//
//                studentList.add(temperatureObject);
//
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//        return studentList;
//
//    }
//
//    public TemperatureObject getTemple(int Id){
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String selectQuery =  "SELECT  " +
//                KEY_ID + "," +
//                KEY_TEMPLE + "," +
//                KEY_TIME + "," +
//                KEY_BABY_ID + "," +
//                KEY_PARENT_ID + "," +
//                KEY_DEVICE_MAC + "," +
//                KEY_STATUS +
//                " FROM " + TABLE_FAVORITE
//                + " WHERE " +
//                KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string
//
//        int iCount =0;
//        TemperatureObject temperatureObject = new TemperatureObject();
//
//        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );
//
//        if (cursor.moveToFirst()) {
//            do {
//                temperatureObject.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
//                temperatureObject.setTemperature(cursor.getFloat(cursor.getColumnIndex(KEY_TEMPLE)));
//                temperatureObject.setTimeDb(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
//                temperatureObject.setStatus(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
//                temperatureObject.setBabyId(cursor.getInt(cursor.getColumnIndex(KEY_BABY_ID)));
//                temperatureObject.setParentId(cursor.getInt(cursor.getColumnIndex(KEY_PARENT_ID)));
//                temperatureObject.setDeviceMac(cursor.getString(cursor.getColumnIndex(KEY_DEVICE_MAC)));
//
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//        return temperatureObject;
//    }



}