package com.example.storagepart1.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.storagepart1.POJO.DataModel;

import static com.example.storagepart1.Constants.Constants.COLUMN_ADDRESS;
import static com.example.storagepart1.Constants.Constants.COLUMN_ID;
import static com.example.storagepart1.Constants.Constants.COLUMN_NAME;
import static com.example.storagepart1.Constants.Constants.COLUMN_PHONE;
import static com.example.storagepart1.Constants.Constants.DATA_BASE_NAME;
import static com.example.storagepart1.Constants.Constants.DATA_BASE_VERSION;
import static com.example.storagepart1.Constants.Constants.TABLE_NAME;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+COLUMN_NAME+" TEXT, "+COLUMN_PHONE+" INTEGER,"+COLUMN_ADDRESS+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int insertModel(SQLiteDatabase database, DataModel dataModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, dataModel.getName());
        contentValues.put(COLUMN_PHONE, dataModel.getMobileNumber());
        contentValues.put(COLUMN_ADDRESS, dataModel.getAddress());
        long i = database.insert(TABLE_NAME, null, contentValues);
        return (int)i;
    }

    public int updateModel(SQLiteDatabase database,DataModel dataModel) {
        Log.i("update",dataModel.getUniqueId()+"");
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,dataModel.getUniqueId());
        contentValues.put(COLUMN_NAME, dataModel.getName());
        contentValues.put(COLUMN_PHONE, dataModel.getMobileNumber());
        contentValues.put(COLUMN_ADDRESS, dataModel.getAddress());

        return database.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?",
                new String[]{String.valueOf(dataModel.getUniqueId())});
    }

    public Cursor fetchAllModel(SQLiteDatabase database) {
        String[] columns = new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_PHONE, COLUMN_ADDRESS};
        Cursor cursor = database.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public DataModel fetchLast (SQLiteDatabase database){

        DataModel dataModel = new DataModel();

        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_ID+" = (SELECT MAX("+COLUMN_ID+")  FROM "+TABLE_NAME+")",null);

        if (cursor != null) {
            cursor.moveToFirst();
            Log.i("fetchlast","done");
            dataModel.setUniqueId(cursor.getInt(0));
            dataModel.setName(cursor.getString(1));
            dataModel.setMobileNumber(cursor.getInt(2));
            dataModel.setAddress(cursor.getString(3));
        }
       return dataModel;
    }

    public void deleteModel(SQLiteDatabase database, int id) {

        database.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
