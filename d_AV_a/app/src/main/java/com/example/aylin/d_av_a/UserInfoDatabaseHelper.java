package com.example.aylin.d_av_a;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aylin on 05.05.2016.
 */
public class UserInfoDatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    public static String DATABASE_NAME = "dava_users_database";
    // Current version of database
    private static final int DATABASE_VERSION = 1;
    // Name of table
    private static final String TABLE = "davaUserInfo";
    // All Keys used in table
    private static final String KEY_ID = "id";
    private static final String KEY_TC = "tc";
    private static final String KEY_AVUKAT = "avukat";
    private static final String KEY_SİCİL = "sicil";
    private static final String KEY_TEL = "tel";
    public static String TAG = "tag";

    private static final String CREATE_USERINFO = "CREATE TABLE "
            + TABLE + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TC + " TEXT,"
            + KEY_AVUKAT + " TEXT,"  + KEY_SİCİL + " TEXT," + KEY_TEL + " TEXT );";

    public UserInfoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERINFO); // create  table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE); // drop table if exists
        onCreate(db);
    }

    public long addUserDetail(UserInfoModel userInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
// Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_TC, userInfo.tc);
        values.put(KEY_AVUKAT, userInfo.avukat);
        values.put(KEY_SİCİL, userInfo.sicil);
        values.put(KEY_TEL, userInfo.tel);
// insert row in  table
        long insert = db.insert(TABLE, null, values);
        return insert;
    }

    public int updateEntry(UserInfoModel userInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
// Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_AVUKAT, userInfo.avukat);
        values.put(KEY_TC, userInfo.tc);
        values.put(KEY_SİCİL, userInfo.sicil);
        values.put(KEY_TEL, userInfo.tel);
// update row in  table base on user.is value
        return db.update(TABLE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(userInfo.id) });
    }

    public void deleteEntry(long id) {
// delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    public UserInfoModel getUserInfo(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
// SELECT * FROM users WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE + " WHERE "
                + KEY_ID + " = " + id;
        Log.d(TAG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        UserInfoModel userInfo = new UserInfoModel();
        userInfo.id = c.getInt(c.getColumnIndex(KEY_ID));
        userInfo.tc = c.getString(c.getColumnIndex(KEY_TC));
        userInfo.avukat = c.getString(c.getColumnIndex(KEY_AVUKAT));
        userInfo.sicil = c.getString(c.getColumnIndex(KEY_SİCİL));
        userInfo.tel=c.getString(c.getColumnIndex(KEY_TEL));
        return userInfo;
    }

    public List<UserInfoModel> getAllUserInfoList() {
        List<UserInfoModel> userInfoArrayList = new ArrayList<UserInfoModel>();
        String selectQuery = "SELECT  * FROM " + TABLE;
        Log.d(TAG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                UserInfoModel userInfo = new UserInfoModel();
                userInfo.id = c.getInt(c.getColumnIndex(KEY_ID));
                userInfo.tc = c.getString(c.getColumnIndex(KEY_TC));
                userInfo.avukat = c.getString(c.getColumnIndex(KEY_AVUKAT));
                userInfo.sicil = c.getString(c.getColumnIndex(KEY_SİCİL));
                userInfo.tel = c.getString(c.getColumnIndex(KEY_TEL));
                userInfoArrayList.add(userInfo);
            } while (c.moveToNext());
        }
        return userInfoArrayList;
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
