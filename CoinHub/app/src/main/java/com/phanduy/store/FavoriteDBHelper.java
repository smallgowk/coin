package com.phanduy.store;

/**
 * Created by IT001 on 23-Jun-16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FavoriteDBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each tempCreatedDate if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "coinhub.db";

    public FavoriteDBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_TEMPLE = " CREATE TABLE " + FavoriteDAO.TABLE_FAVORITE + " ("
                + FavoriteDAO.KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + FavoriteDAO.KEY_ID + " TEXT, "
                + FavoriteDAO.KEY_NAME + " TEXT, "
                + FavoriteDAO.KEY_SYMBOL + " TEXT, "
                + FavoriteDAO.KEY_RANK + " INTEGER, "
                + FavoriteDAO.KEY_PRICE_USD + " REAL, "
                + FavoriteDAO.KEY_PRICE_BTC + " REAL, "
                + FavoriteDAO.KEY_VOLUME_24H + " REAL, "
                + FavoriteDAO.KEY_PERCENT_CHANGE_1H + " REAL, "
                + FavoriteDAO.KEY_PERCENT_CHANGE_24H + " REAL, "
                + FavoriteDAO.KEY_PERCENT_CHANGE_7D + " REAL, "
                + FavoriteDAO.KEY_AMOUNT + " REAL )";

        Log.e("CREATE_TABLE_TEMPLE", "" + CREATE_TABLE_TEMPLE);

        db.execSQL(CREATE_TABLE_TEMPLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteDAO.TABLE_FAVORITE);

        // Create tables again
        onCreate(db);

    }

}