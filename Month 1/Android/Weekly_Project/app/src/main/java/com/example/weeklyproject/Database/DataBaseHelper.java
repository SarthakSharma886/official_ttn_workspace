package com.example.weeklyproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.weeklyproject.POJO.DataModel;

import static com.example.weeklyproject.constants.Constants.*;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db == null) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + COLUMN_FIRST_NAME + " TEXT, " + COLUMN_LAST_NAME + " TEXT," + COLUMN_AVATAR + " TEXT);");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int insertModel(SQLiteDatabase database, DataModel dataModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, dataModel.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, dataModel.getLastName());
        contentValues.put(COLUMN_AVATAR, dataModel.getAvatar());
        long i = database.insert(TABLE_NAME, null, contentValues);
        return (int) i;
    }

    public int updateModel(SQLiteDatabase database, DataModel dataModel) {
        Log.i("update", dataModel.getId() + "");
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, dataModel.getId());
        contentValues.put(COLUMN_FIRST_NAME, dataModel.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, dataModel.getLastName());
        contentValues.put(COLUMN_AVATAR, dataModel.getAvatar());

        return database.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?",
                new String[]{String.valueOf(dataModel.getId())});
    }

    public Cursor fetchAllModel(SQLiteDatabase database) {
        String[] columns = new String[]{COLUMN_ID, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_AVATAR};
        Cursor cursor = database.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public void deleteModel(SQLiteDatabase database, int id) {

        database.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
