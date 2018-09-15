package com.phanduy.store;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.phanduy.model.InboxModel;
import com.phanduy.utils.StringUtility;

import java.io.IOException;
import java.util.ArrayList;

/**
 * TODOs DAO object.
 * 
 * @author itcuties
 * 
 */
public class DAOSQlite {

	private SQLiteDatabase db;
	private DBSQLiteHelper dbHelper;

	public DAOSQlite(Context context) {
		try {
			dbHelper = new DBSQLiteHelper(context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db = dbHelper.getWritableDatabase();
	}

	// Close the db
	public void close() {
		db.close();
	}

	/**
	 * Create new TODO object
	 * 
	 * @param todoText
	 */
	public void createTodo(String dbName, String field, String value) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(field, value);
		// Insert into DB
		db.insert(dbName, null, contentValues);
	}

	/**
	 * Delete TODO object
	 * 
	 * @param todoId
	 */
	public void deleteTodo(String dbName, String field, int id) {
		// Delete from DB where id match
		db.delete(dbName, field + " = " + id, null);

	}

	/**
	 * Get all TODOs.
	 * 
	 * @return
	 */
	public ArrayList<InboxModel> getListInbox() {
		ArrayList<InboxModel> listInbox = new ArrayList<InboxModel>();

		// Name of the columns we want to select
		String[] tableColumns = new String[] { "ID", "TITLE",
				"CONTENT", "TIME", "SENDER_ID", "SENDER_NAME", "SENDER_PHONE" };

		// Query the database
		Cursor cursor = db.query("Ship_inbox", tableColumns, null, null, "TIME", null,
				null);
		cursor.moveToFirst();

		// Iterate the results
		while (!cursor.isAfterLast()) {
			InboxModel inboxModel = new InboxModel();
			
			inboxModel.setId(cursor.getInt(0));
			inboxModel.setTitle(cursor.getString(1));
			inboxModel.setContent(cursor.getString(2));
			inboxModel.setTime(cursor.getString(3));
			inboxModel.setSenderId(cursor.getInt(4));
			inboxModel.setSenderName(cursor.getString(5));
			inboxModel.setSenderPhone(cursor.getString(6));
			listInbox.add(inboxModel);
			cursor.moveToNext();
		}

		return listInbox;
	}
	
	public void insertInbox(InboxModel inboxModel) {
		ContentValues values = new ContentValues();
//		values.put("ID", inboxModel.getId());
		values.put("TITLE", inboxModel.getTitle());
		values.put("CONTENT", inboxModel.getContent());
		values.put("TIME", inboxModel.getTime());
		values.put("SENDER_ID", inboxModel.getSenderId());
		values.put("SENDER_NAME", inboxModel.getSenderName());
		values.put("SENDER_PHONE", inboxModel.getSenderPhone());
		values.put("CREATE_TIME", StringUtility.convertNowToFullDateString());
		
		db.insert("ship_inbox", null, values);
	}
	
	public void deleteInbox(InboxModel inboxModel) {
		db.delete("ship_inbox", "id = ?",
				new String[] { String.valueOf(inboxModel.getId()) });
	}
	
	public void deleteAllInbox() {
		db.delete("ship_inbox", "1 = 1",null);
	}

}
