package com.fnspl.hiplaretails.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 12-08-2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hipla_retail.db";

    // for product table
    private static final String TABLE_NAME = "product";
    private static final String KEY_ID = "s_id";
    private static final String KEY_PRODUCT_ID = "id";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_CATEGORY_NAME = "category_name";
    private static final String KEY_PRODUCT_TITLE = "title";
    private static final String KEY_PRODUCT_PICTURE = "picture";
    private static final String KEY_PRODUCT_DESC = "desc";
    private static final String KEY_BAR_CODE = "bar_code";
    private static final String KEY_UNIT = "unit";
    private static final String KEY_PRICE = "price";
    private static final String KEY_C_DATE = "c_dt";





    //private final String TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PRODUCT_ID + " INTEGER," + KEY_USER_ID + " TEXT," + KEY_CATEGORY_NAME + " TEXT," + KEY_PRODUCT_TITLE + " TEXT," + KEY_PRODUCT_DESC + " TEXT," + KEY_PRICE + " TEXT,"+ KEY_BAR_CODE + " TEXT,"+ KEY_PRODUCT_PICTURE + " TEXT,"+ KEY_C_DATE + " TEXT" + ")";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL(TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }



    public void setTruncateDatabaseAll(){
        Log.e("nur delete", "setTruncateDatabaseAll" );
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try{
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

            onCreate(sqLiteDatabase);

        }catch (Exception e){
            e.printStackTrace();
        }

        Log.e("nur delete", "setTruncateDatabase" );

    }

    public void setTruncateDatabase(){
        Log.e("nur delete", "setTruncateDatabase" );
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try{

            sqLiteDatabase.delete(TABLE_NAME, null, null);


        }catch (Exception e){
            e.printStackTrace();
        }


    }


   /* public long AddChildList(String id, String U_id, String name, String dob,String c_date, String picture) {
        Log.e("nur insert", "" + id + U_id + name + dob + picture);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_CHILD_ID, Integer.parseInt(id));
        cv.put(KEY_USER_ID, U_id);
        cv.put(KEY_CHILD_NAME, name);
        cv.put(KEY_CHILD_DOB, dob);
        cv.put(KEY_CHILD_C_DATE, c_date);
        cv.put(KEY_CHILD_PICTURE, picture);

        return db.insert(TABLE_NAME, null, cv);
    }

    public ArrayList<Child> getChildList() {
        ArrayList<Child> nodeLists = new ArrayList<Child>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] table_column = new String[]{
                KEY_CHILD_ID, KEY_USER_ID, KEY_CHILD_NAME, KEY_CHILD_DOB, KEY_CHILD_C_DATE,KEY_CHILD_PICTURE
        };
        Cursor cursor = db.query(TABLE_NAME, table_column, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Child nodeList = new Child();
            // Take values from the DB
            nodeList.setId(cursor.getString(0));
            nodeList.setUser_id(cursor.getString(1));
            nodeList.setName(cursor.getString(2));
            nodeList.setDob(cursor.getString(3));
            nodeList.setCreate_date(cursor.getString(4));
            nodeList.setImage(cursor.getString(5));
            nodeLists.add(nodeList);
            cursor.moveToNext();
        }

        return nodeLists;
    }


    public int editChild(int id, String name, String dob,String img) {
        Log.e("nur update child", "" + id + "-" + dob+"...."+img);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_CHILD_NAME, name);
        cv.put(KEY_CHILD_DOB, dob);
        cv.put(KEY_CHILD_PICTURE, img);

        return db.update(TABLE_NAME, cv, KEY_CHILD_ID + "= " + id, null);
    }

    public void deleteChildList(int user_id) {
        Log.e("delete", "delete");
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        //db.delete(TABLE_NAME, null, null);
        db.execSQL("delete from " + TABLE_NAME + " WHERE " + KEY_USER_ID + "= '" + user_id + "'");
        db.close();

    }


    public void deleteAllChildList() {
        Log.e("delete", "delete");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        db.close();

    }*/



}
