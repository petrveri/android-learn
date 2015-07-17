package com.et4334.appofpetr.api.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.et4334.appofpetr.vo.River;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by et4334 on 7/13/15.
 */
public class RiverSqliteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "AppOfPetr";
    private static final String RIVER_TABLE_NAME = "rivers";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String RIVER_TABLE_CREATE =
            "CREATE TABLE " + RIVER_TABLE_NAME + " (" +
                    KEY_ID + " TEXT, " +
                    KEY_NAME + " TEXT);";

    RiverSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RIVER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public River getRecord(String id) {
        River river = new River();
        river.setId("*");
        river.setName("not found");

        Cursor cursor =
            getReadableDatabase().
                    query(RIVER_TABLE_NAME, new String[ ] {KEY_ID, KEY_NAME}, KEY_ID + "=?", new String[] {id}, null, null, null);
//                    rawQuery("select id, name from rivers where id = 1", new String[]{});
        int gotRecords = cursor.getCount();
        Log.d("getRecord", "got: " + gotRecords);
        if (gotRecords > 0) {
            if (cursor.moveToFirst()) {
                do {
                    river.setId(cursor.getString(0));
                    river.setName(cursor.getString(1));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return river;
    }

    public void insertRecord(String id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, id);
        contentValues.put(KEY_NAME, name);
        long inserted = getWritableDatabase().insert(RIVER_TABLE_NAME, null, contentValues);
        Log.d("insertRecord", "inserted: " + inserted);
    }

    public void updateRecord(String id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, id);
        contentValues.put(KEY_NAME, name);
        long updated = getWritableDatabase().update(RIVER_TABLE_NAME, contentValues, KEY_ID + "=?", new String[]{id});
        Log.d("updateRecord", "updated: " + updated);
    }

    public void deleteRecord(String id) {
        long deleted = getWritableDatabase().delete(RIVER_TABLE_NAME, KEY_ID + "=?", new String[]{id});
        Log.d("deleteRecord", "deleted: " + deleted);
    }

    public List<River> listRecords() {
        Cursor cursor =
                getReadableDatabase().
                        query(RIVER_TABLE_NAME, new String[ ] {KEY_ID, KEY_NAME}, null, null, null, null, null);
        int gotRecords = cursor.getCount();
        Log.d("listRecord", "got: " + gotRecords);
        ArrayList<River> rivers = new ArrayList<>();
        if (gotRecords > 0) {
            if (cursor.moveToFirst()) {
                do {
                    River river = new River();
                    river.setId(cursor.getString(0));
                    river.setName(cursor.getString(1));
                    rivers.add(river);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return rivers;
    }

}

