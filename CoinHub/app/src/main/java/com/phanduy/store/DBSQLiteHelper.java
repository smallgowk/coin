package com.phanduy.store;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.phanduy.model.InboxModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Creates application database.
 * 
 * @author itcuties
 *
 */
public class DBSQLiteHelper extends SQLiteOpenHelper {
	
	private static String DB_NAME = "idoctor_db";
	
	public SQLiteDatabase myDataBase;
	
	private String DB_PATH = "";
	
	public Context mycontext;


//	public TodoSQLiteHelper(Context context) {
//		// Databse: todos_db, Version: 1
//		super(context, "todos_db", null, 1);
//	}
	
	public DBSQLiteHelper(Context context) throws IOException {
        super(context,DB_NAME,null,1);
        this.mycontext=context;
        
        DB_PATH = "/data/data/"
                + mycontext.getApplicationContext().getPackageName()
                + "/databases/";
        boolean dbexist = checkdatabase();
        if (dbexist) {
            //System.out.println("Database exists");
            opendatabase(); 
        } else {
            System.out.println("Database doesn't exist");
            createdatabase();
        }
    }
	
	public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if(dbexist) {
            //System.out.println(" Database exists.");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }   

    private boolean checkdatabase() {
        //SQLiteDatabase checkdb = null;
        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            //checkdb = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
            checkdb = dbfile.exists();
        } catch(SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if(myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

	/**
	 * Create simple table
	 * todos
	 * 		_id 	- key
	 * 		todo	- todo text
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Execute create table SQL
		db.execSQL("CREATE TABLE todos (_id INTEGER PRIMARY KEY AUTOINCREMENT, todo TEXT NOT NULL);");
	}

	/**
	 * Recreates table
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		// DROP table
		db.execSQL("DROP TABLE IF EXISTS todos");
		// Recreate table
		onCreate(db);
	}
	
	public void delete(SQLiteDatabase db, String tableName, InboxModel inboxModel) {
		db.delete(tableName, "id = ?",
				new String[] { String.valueOf(inboxModel.getId()) });
	}
	
}
