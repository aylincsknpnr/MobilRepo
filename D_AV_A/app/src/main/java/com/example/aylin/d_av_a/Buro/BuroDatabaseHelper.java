package com.example.aylin.d_av_a.Buro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aylin on 18.05.2016.
 */
public class BuroDatabaseHelper extends SQLiteOpenHelper {

        // Database Name
        public static String DATABASE_NAME = "dava_user_database";
        // Current version of database
        private static final int DATABASE_VERSION = 1;
        // Name of table
        private static final String TABLE = "buro";
        // All Keys used in table
        private static final String KEY_ID = "id";
        private static final String KEY_OFFICENAME = "officeName";
        private static final String KEY_ADDRESS = "address";
        private static final String KEY_TEL1 = "tel1";
        private static final String KEY_TEL2 = "tel2";
        private static final String KEY_FAX = "fax";
        private static final String KEY_TAXNO = "taxNo";
        private static final String KEY_TAXDEPARTMENT = "taxDepartment";
        public static String TAG = "tag";

        private static final String CREATE = "CREATE TABLE "
                + TABLE + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_OFFICENAME + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_TEL1 + " TEXT,"
                + KEY_TEL2 + " TEXT,"
                + KEY_FAX + " TEXT,"
                + KEY_TAXNO + " TEXT,"
                + KEY_TAXDEPARTMENT + " TEXT,"
                + " TEXT );";

        public BuroDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE); // create  table
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CREATE); // drop table if exists
            onCreate(db);
        }

        public long addUserDetail(BuroModel buroModel) {
            SQLiteDatabase db = this.getWritableDatabase();
// Creating content values
            ContentValues values = new ContentValues();
            values.put(KEY_OFFICENAME, buroModel.officeName);
            values.put(KEY_ADDRESS, buroModel.address);
            values.put(KEY_TEL1, buroModel.tel1);
            values.put(KEY_TEL2, buroModel.tel2);
            values.put(KEY_FAX, buroModel.fax);
            values.put(KEY_TAXNO, buroModel.taxNo);
            values.put(KEY_TAXDEPARTMENT, buroModel.taxDepartment);


// insert row in table
            long insert = db.insert(TABLE, null, values);
            return insert;
        }

        public int updateEntry(BuroModel buroModel) {
            SQLiteDatabase db = this.getWritableDatabase();
// Creating content values
            ContentValues values = new ContentValues();
            values.put(KEY_OFFICENAME, buroModel.officeName);
            values.put(KEY_ADDRESS, buroModel.address);
            values.put(KEY_TEL1, buroModel.tel1);
            values.put(KEY_TEL2, buroModel.tel2);
            values.put(KEY_FAX, buroModel.fax);
            values.put(KEY_TAXNO, buroModel.taxNo);
            values.put(KEY_TAXDEPARTMENT, buroModel.taxDepartment);
// update row in  table base on is value
            return db.update(TABLE, values, KEY_ID + " = ?",
                    new String[] { String.valueOf(buroModel.id) });
        }

        public void deleteEntry(long id) {
// delete row in students table based on id
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE, KEY_ID + " = ?",
                    new String[] { String.valueOf(id) });
        }

        public BuroModel getBuro(long id) {
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE + " WHERE "
                    + KEY_ID + " = " + id;
            Log.d(TAG, selectQuery);
            Cursor c = db.rawQuery(selectQuery, null);
            if (c != null)
                c.moveToFirst();
            BuroModel buroModel = new BuroModel();
            buroModel.id = c.getInt(c.getColumnIndex(KEY_ID));
            buroModel.officeName = c.getString(c.getColumnIndex(KEY_OFFICENAME));
            buroModel.address = c.getString(c.getColumnIndex(KEY_ADDRESS));
            buroModel.tel1 = c.getString(c.getColumnIndex(KEY_TEL1));
            buroModel.tel2 = c.getString(c.getColumnIndex(KEY_TEL2));
            buroModel.fax = c.getString(c.getColumnIndex(KEY_FAX));
            buroModel.taxNo = c.getString(c.getColumnIndex(KEY_TAXNO));
            buroModel.taxDepartment = c.getString(c.getColumnIndex(KEY_TAXDEPARTMENT));
            return buroModel;
        }

        public List<BuroModel> getAllBuroList() {
            List<BuroModel> buroArrayList = new ArrayList<BuroModel>();
            String selectQuery = "SELECT  * FROM " + TABLE;
            Log.d(TAG, selectQuery);
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    BuroModel buroModel = new BuroModel();
                    buroModel.id = c.getInt(c.getColumnIndex(KEY_ID));
                    buroModel.officeName = c.getString(c.getColumnIndex(KEY_OFFICENAME));
                    buroModel.address = c.getString(c.getColumnIndex(KEY_ADDRESS));
                    buroModel.tel1 = c.getString(c.getColumnIndex(KEY_TEL1));
                    buroModel.tel2 = c.getString(c.getColumnIndex(KEY_TEL2));
                    buroModel.fax = c.getString(c.getColumnIndex(KEY_FAX));
                    buroModel.taxNo = c.getString(c.getColumnIndex(KEY_TAXNO));
                    buroModel.taxDepartment = c.getString(c.getColumnIndex(KEY_TAXDEPARTMENT));
                    buroArrayList.add(buroModel);
                } while (c.moveToNext());
            }
            return buroArrayList;
        }

        public int getRowCount() { //tabloda kaç satır kayıtlı olduğunu geri döner
            String countQuery = "SELECT  * FROM " + TABLE;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            int rowCount = cursor.getCount();
            db.close();
            cursor.close();
// return row count
            return rowCount;
        }

        public void resetTables(){
// Tüm verileri siler. tabloyu resetler.
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE, null, null);
            db.close();
        }
    }
