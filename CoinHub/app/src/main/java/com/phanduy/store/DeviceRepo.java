package com.phanduy.store;

/**
 * Created by IT001 on 23-Jun-16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.phanduy.GlobalInfo;
import com.phanduy.model.BabyInfo;

public class DeviceRepo {

    public static final String KEY_ID = "ID";
    public static final String KEY_TEMPLE = "TEMPLE";
    public static final String KEY_TIME = "TIME";
    public static final String KEY_STATUS = "STATUS";
    public static final String KEY_BABY_ID = "BABY_ID";
    public static final String KEY_PARENT_ID = "PARENT_ID";
    public static final String KEY_DEVICE_MAC = "DEVICE_MAC";

    public static final String TABLE_NAME = "DEVICE";

    private PortfolioDBHelper dbHelper;

    public DeviceRepo(Context context) {
        dbHelper = new PortfolioDBHelper(context);
    }

    public long insert(float temple, BabyInfo babyInfo, String macAddress) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TEMPLE, temple);
        values.put(KEY_TIME, GlobalInfo.simpleDateFormatDB.format(new java.util.Date()));
        values.put(KEY_STATUS, 0);
        if(babyInfo != null) {
            values.put(KEY_BABY_ID, babyInfo.getId());
            values.put(KEY_PARENT_ID, babyInfo.getParent_id());
        } else {
            values.put(KEY_BABY_ID, 0);
            values.put(KEY_PARENT_ID, 0);
        }
        values.put(KEY_DEVICE_MAC, macAddress);


        // Inserting Row
        long student_Id = db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
        return student_Id;
    }
    public long insert(float temple) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TEMPLE, temple);
        values.put(KEY_TIME, GlobalInfo.simpleDateFormatDB.format(new java.util.Date()));
        values.put(KEY_STATUS, 0);
        // Inserting Row
        long student_Id = db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
        return student_Id;
    }

    public void delete(long student_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(TABLE_NAME, KEY_ID + "= ?", new String[] { String.valueOf(student_Id) });
        db.close(); // Closing database connection
    }

    public void clearData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(TABLE_NAME, null, null);
        db.close(); // Closing database connection
    }

//    public void update(TemperatureObject temperatureObject) {
//
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(KEY_TEMPLE, temperatureObject.getTemperature());
//        values.put(KEY_TIME,temperatureObject.getTimeSaveDb());
//        values.put(KEY_STATUS, temperatureObject.getStatus());
//
//        // It's a good practice to use parameter ?, instead of concatenate string
//        db.update(TABLE_FAVORITE, values, KEY_ID + "= ?", new String[] { String.valueOf(temperatureObject.getId()) });
//        db.close(); // Closing database connection
//    }
//    public void updateStatus(ArrayList<TemperatureObject> listTemper, int status) {
//
//        if(listTemper == null || listTemper.isEmpty()) {
//            return;
//        }
//        String[] listIds = new String[listTemper.size()];
//        String query = "(";
//        for(int i = 0, size = listTemper.size(); i < size; i++) {
//            listIds[i] = String.valueOf(listTemper.get(i).getId());
//            if(i == 0) {
//                query += "?";
//            } else {
//                query += ",?";
//            }
//        }
//        query += ")";
//
//        SQLiteDatabase db = null;
//        try {
//            db = dbHelper.getWritableDatabase();
//            ContentValues values = new ContentValues();
//
//            values.put(KEY_STATUS, status);
//
//            // It's a good practice to use parameter ?, instead of concatenate string
//            db.update(TABLE_FAVORITE, values, KEY_ID + " IN " + query, listIds);
//
//        } catch (Exception e) {
//
//        } finally {
//            if(db != null) {
//                db.close(); // Closing database connection
//            }
//        }
//
//    }
//
//    public ArrayList<TemperatureObject>  getTempleList(int status, int parentId) {
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
//                " FROM " + TABLE_FAVORITE +
//                " WHERE " + KEY_STATUS + "=" + status;
//
//        //Student student = new Student();
//        ArrayList<TemperatureObject> studentList = new ArrayList<TemperatureObject>();
//
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        // looping through all rows and adding to list
//
//        if (cursor.moveToFirst()) {
//            do {
//                if(parentId == cursor.getInt(cursor.getColumnIndex(KEY_PARENT_ID))) {
//                    TemperatureObject temperatureObject = new TemperatureObject();
//                    temperatureObject.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
//                    temperatureObject.setTemperature(cursor.getFloat(cursor.getColumnIndex(KEY_TEMPLE)));
//                    temperatureObject.setTimeDb(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
//                    temperatureObject.setStatus(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
//                    temperatureObject.setBabyId(cursor.getInt(cursor.getColumnIndex(KEY_BABY_ID)));
//                    temperatureObject.setParentId(cursor.getInt(cursor.getColumnIndex(KEY_PARENT_ID)));
//                    temperatureObject.setDeviceMac(cursor.getString(cursor.getColumnIndex(KEY_DEVICE_MAC)));
//
//                    studentList.add(temperatureObject);
//                }
//
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//        return studentList;
//
//    }
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