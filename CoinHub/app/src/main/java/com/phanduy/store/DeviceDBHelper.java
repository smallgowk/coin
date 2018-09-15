package com.phanduy.store;

/**
 * Created by IT001 on 23-Jun-16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DeviceDBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each tempCreatedDate if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "idoctor.db";

    public DeviceDBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_DEVICE = "CREATE TABLE " + DeviceRepo.TABLE_NAME  + "("
                + DeviceRepo.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + DeviceRepo.KEY_TEMPLE + " REAL, "
                + DeviceRepo.KEY_TIME + " TEXT, "
                + DeviceRepo.KEY_STATUS + " INTEGER, "
                + DeviceRepo.KEY_BABY_ID + " INTEGER, "
                + DeviceRepo.KEY_PARENT_ID + " INTEGER, "
                + DeviceRepo.KEY_DEVICE_MAC + " TEXT )";

        Log.e("CREATE_TABLE_DEVICE", "" + CREATE_TABLE_DEVICE);

        db.execSQL(CREATE_TABLE_DEVICE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + PortfolioDAO.TABLE_PORT_FOLIO);

        // Create tables again
        onCreate(db);

    }

}