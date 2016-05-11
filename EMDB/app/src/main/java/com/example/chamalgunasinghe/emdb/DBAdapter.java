package com.example.chamalgunasinghe.emdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBAdapter {

    //COLUMNS
    static final String ROWID = "id";
    static final String NAME = "name";
    static final String POSITION = "age";
    static final String RETPRICE = "retprice";
    static final String SELLPRICE = "sellprice";
    static final String TAG = "DBAdapter";

    //DB Properties
    static final String DBNAME = "mydb";
    static final String TBNAME = "mytb";
    static final int DBVERSION = '1' ;

    static final String CREATE_TB = "CREATE TABLE mytb(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, age TEXT NOT NULL, retprice TEXT NOT NULL, sellprice TEXT NOT NULL);";

    final Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context ctx) {
        this.c = ctx;
        helper = new DBHelper(c);
    }


    String TBLID = "id";
    static final String DEVNAME = "user";
    static final String LATITUDE = "lat";
    static final String LONGITUDE = "long";
    static final String DATE = "date";


    //Inner helper class
    private static class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context) {
            super(context, DBNAME, null, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {

                db.execSQL(CREATE_TB);

            } catch (android.database.SQLException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(TAG, "Updating db table");
            db.execSQL("DROP TABLE IF EXISTS mytb");
            onCreate(db);
        }
    }

    //Open the DB
    public DBAdapter openDB() {
        try {
            db=helper.getWritableDatabase();

        }
        catch (android.database.SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    //Close the db
    public void close(){
        helper.close();
    }

    //Insert into table
    public long add(String name, String age, String retprice, String sellprice){
        try {
            ContentValues cv = new ContentValues();
            cv.put(NAME, name);
            cv.put(POSITION, age);
            cv.put(RETPRICE, retprice);
            cv.put(SELLPRICE, sellprice);
            return db.insert(TBNAME, ROWID, cv);
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    //Get all values
    public Cursor getAllPlayers(){
        String[] columns = {ROWID,NAME, POSITION, RETPRICE, SELLPRICE};
        return db.query(TBNAME, columns, null, null, null, null, null);

    }

}
