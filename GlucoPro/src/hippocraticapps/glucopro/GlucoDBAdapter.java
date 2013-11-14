package hippocraticapps.glucopro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class GlucoDBAdapter {
	public static final String ADPTR_LOGTAG = GlucoDBAdapter.class.getSimpleName() + "_TAG";
	public static final String QUERY_LOGTAG = "QUERY_TAG";
	
	public static final String DB_NAME            		= "gluco_pro.db";
	public static final int    DB_VERSION         		= 1;
	public static final String BLOOD_SUGAR_TABLE   		= "blood_sugar_table";
	public static final String INSULIN_CORRECTION_TABLE = "insulin_correction_table";
	public static final String MEAL_DATA_TABLE			= "meal_data_table";

	// ********** blood_sugar table constants ******************
	// constants for blood_sugar table column names
	public static final String SUGAR_ID_COL_NAME        = "id";
	public static final String SUGAR_SHIFT_COL_NAME     = "ShiftId";
	public static final String SUGAR_PRE_POST_COL_NAME 	= "PrePost";
	public static final String SUGAR_LEVEL_COL_NAME		= "Level";
	public static final String SUGAR_TIME_COL_NAME		= "Time";
	
	// constants for blood_sugar table column numbers
	public static final int SUGAR_ID_COL_NUM         	= 0;
	public static final int SUGAR_SHIFT_COL_NUM      	= 1;
	public static final int SUGAR_PRE_POST_COL_NUM    	= 2;
	public static final int SUGAR_LEVEL_COL_NUM			= 3;
	public static final int SUGAR_TIME_COL_NUM			= 4;
	
	// ********** insulin_correction table constants ******************
	// constants for insulin_correction table column names
	public static final String CORRECT_ID_COL_NAME      = "id";
	public static final String CORRECT_SHIFT_COL_NAME   = "ShiftId";
	public static final String CORRECT_DOSE_COL_NAME 	= "Dose";
	public static final String CORRECT_TIME_COL_NAME	= "Time";
	public static final String CORRECT_FAST_COL_NAME	= "Fast";
	
	// constants for insulin_correction table column numbers
	public static final int CORRECT_ID_COL_NUM         	= 0;
	public static final int CORRECT_SHIFT_COL_NUM      	= 1;
	public static final int CORRECT_DOSE_COL_NUM    	= 2;
	public static final int CORRECT_TIME_COL_NUM		= 3;
	public static final int CORRECT_FAST_COL_NUM		= 4;
	
	// ********** meal_data table constants ******************
	// constants for meal_data table column names
	public static final String MEAL_ID_COL_NAME      = "id";
	public static final String MEAL_SHIFT_COL_NAME   = "ShiftId";
	public static final String MEAL_CARB_COL_NAME 	= "CarbIntake";
	public static final String MEAL_TIME_COL_NAME	= "Time";
	
	// constants for meal_data table column numbers
	public static final int MEAL_ID_COL_NUM         	= 0;
	public static final int MEAL_SHIFT_COL_NUM      	= 1;
	public static final int MEAL_DOSE_COL_NUM    		= 2;
	public static final int MEAL_TIME_COL_NUM			= 3;
	
	private SQLiteDatabase   	 mDb = null;
	private DBOpenHelper mDbHelper = null;
	
	private static class DBOpenHelper extends SQLiteOpenHelper {
		static final String HELPER_LOGTAG = DBOpenHelper.class.getSimpleName() + "_TAG";
		
		public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}
		
		// table creation string constant
		static final String SUGAR_TABLE_CREATE =
				"create table " + BLOOD_SUGAR_TABLE + 
				" (" + 
				SUGAR_ID_COL_NAME         	+ " integer primary key autoincrement, " + 
				SUGAR_SHIFT_COL_NAME      	+ " text not null, " + 
				SUGAR_PRE_POST_COL_NAME    	+ " text not null, " +
				SUGAR_LEVEL_COL_NAME 		+ " blob not null " + 
				SUGAR_TIME_COL_NAME 		+ " blob not null " + 
				");";
		
		static final String INSULIN_CORRECTION_CREATE =
				"create table " + INSULIN_CORRECTION_TABLE + 
				" (" + 
				CORRECT_ID_COL_NAME         + " integer primary key autoincrement, " + 
				CORRECT_SHIFT_COL_NAME      + " text not null, " + 
				CORRECT_DOSE_COL_NAME    	+ " text not null, " +
				CORRECT_TIME_COL_NAME 		+ " blob not null " + 
				CORRECT_FAST_COL_NAME 		+ " blob not null " + 
				");";
		
		static final String MEAL_DATA_CREATE =
				"create table " + MEAL_DATA_TABLE + 
				" (" + 
				MEAL_ID_COL_NAME         	+ " integer primary key autoincrement, " + 
				MEAL_SHIFT_COL_NAME      	+ " text not null, " + 
				MEAL_CARB_COL_NAME    		+ " text not null, " +
				MEAL_TIME_COL_NUM 			+ " blob not null " + 
				");";
			
		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d(HELPER_LOGTAG, SUGAR_TABLE_CREATE);
			db.execSQL(SUGAR_TABLE_CREATE);
			db.execSQL(INSULIN_CORRECTION_CREATE);
			db.execSQL(MEAL_DATA_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, 
				int newVersion) {
			Log.d(ADPTR_LOGTAG, "Upgrading from version " +
					oldVersion + " to " +
					newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + SUGAR_TABLE_CREATE);
			db.execSQL("DROP TABLE IF EXISTS " + INSULIN_CORRECTION_CREATE);
			db.execSQL("DROP TABLE IF EXISTS " + MEAL_DATA_CREATE);
			onCreate(db);
		}
	} // end of DBOpenHelper class
	
	public GlucoDBAdapter(Context context) {
		mDbHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
	}
	
	public void open() throws SQLiteException {
		try {
			mDb = mDbHelper.getWritableDatabase();
			Log.d(ADPTR_LOGTAG, "WRITEABLE DB CREATED");
		}
		catch ( SQLiteException ex ) {
			Log.d(ADPTR_LOGTAG, "READABLE DB CREATED");
			mDb = mDbHelper.getReadableDatabase();
		}
	}
	
	public SQLiteDatabase getReadableDatabase() {
		try {
			return mDbHelper.getReadableDatabase();
		}
		catch ( SQLiteException ex ) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public void close() {
		mDb.close();
	}
	
	/*
	 * Insert your static queries here.
	 */
}
