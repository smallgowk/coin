package com.phanduy.store;

/**
 * Created by IT001 on 23-Jun-16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PortfolioDBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each tempCreatedDate if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "coinhub.db";

    public PortfolioDBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_TEMPLE = " CREATE TABLE " + PortfolioDAO.TABLE_PORT_FOLIO + " ("
                + PortfolioDAO.KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + PortfolioDAO.KEY_ID + " TEXT, "
                + PortfolioDAO.KEY_NAME + " TEXT, "
                + PortfolioDAO.KEY_SYMBOL + " TEXT, "
                + PortfolioDAO.KEY_RANK + " INTEGER, "
                + PortfolioDAO.KEY_PRICE_USD + " REAL, "
                + PortfolioDAO.KEY_PRICE_BTC + " REAL, "
                + PortfolioDAO.KEY_VOLUME_24H + " REAL, "
                + PortfolioDAO.KEY_PERCENT_CHANGE_1H + " REAL, "
                + PortfolioDAO.KEY_PERCENT_CHANGE_24H + " REAL, "
                + PortfolioDAO.KEY_PERCENT_CHANGE_7D + " REAL, "
                + PortfolioDAO.KEY_AMOUNT + " REAL )";

        Log.e("CREATE_TABLE_TEMPLE", "" + CREATE_TABLE_TEMPLE);

        db.execSQL(CREATE_TABLE_TEMPLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + PortfolioDAO.TABLE_PORT_FOLIO);

        // Create tables again
        onCreate(db);

    }

}