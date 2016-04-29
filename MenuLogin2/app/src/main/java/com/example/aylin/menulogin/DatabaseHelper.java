package com.example.aylin.menulogin;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by aylin on 22.04.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    public static String DATABASE_NAME = "users_database";

    // Current version of database
    private static final int DATABASE_VERSION = 1;

    // Name of table
    private static final String TABLE_USERS = "users";

    // All Keys used in table
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    public static String TAG = "tag";


    private static final String CREATE_USERS = "CREATE TABLE "
            + TABLE_USERS + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME + " TEXT,"
            + KEY_PASSWORD + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    /**
     * This method is called by system if the database is accessed but not yet
     * created.
     */

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USERS); // create  table

    }

    /**
     * This method is called when any modifications in database are done like
     * version is updated or database schema is changed
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_USERS); // drop table if exists

        onCreate(db);
    }


    public long addUserDetail(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

// Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.username);
        values.put(KEY_PASSWORD, user.password);

// insert row in students table

        long insert = db.insert(TABLE_USERS, null, values);

        return insert;
    }


    public int updateEntry(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

// Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.username);
        values.put(KEY_PASSWORD, user.password);

// update row in  table base on user.is value
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.id) });
    }


    public void deleteEntry(long id) {

// delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }



    public UserModel getUser(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

// SELECT * FROM users WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE "
                + KEY_ID + " = " + id;
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        UserModel users = new UserModel();
        users.id = c.getInt(c.getColumnIndex(KEY_ID));
        users.password = c.getString(c.getColumnIndex(KEY_PASSWORD));
        users.username = c.getString(c.getColumnIndex(KEY_USERNAME));

        return users;
    }

    /**
     * Used to get detail of entire database and save in array list of data type
     * StudentsModel
     *
     * @return
     */
    public List<UserModel> getAllUsersList() {
        List<UserModel> usersArrayList = new ArrayList<UserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_USERS;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                UserModel users = new UserModel();
                users.id = c.getInt(c.getColumnIndex(KEY_ID));
                users.password = c.getString(c
                        .getColumnIndex(KEY_PASSWORD));
                users.username = c.getString(c.getColumnIndex(KEY_USERNAME));
// adding to Students list
                usersArrayList.add(users);
            } while (c.moveToNext());
        }

        return usersArrayList;
    }

    public int getRowCount() { //tabloda kaç satır kayıtlı olduğunu geri döner

        String countQuery = "SELECT  * FROM " + TABLE_USERS;
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
        db.delete(TABLE_USERS, null, null);
        db.close();
    }
}