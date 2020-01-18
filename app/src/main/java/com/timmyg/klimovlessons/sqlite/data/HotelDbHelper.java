package com.timmyg.klimovlessons.sqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HotelDbHelper extends SQLiteOpenHelper {


    public static final String LOG_TAG = HotelDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "hotel.db";

    private static final int DATABASE_VERSION = 1;

    public HotelDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_GUEST_TABLE = "CREATE TABLE " + HotelContract.GuestEntry.TABLE_NAME + " ("
                 + HotelContract.GuestEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                 + HotelContract.GuestEntry.COLUMN_NAME + " TEXT NOT NULL, "
                 + HotelContract.GuestEntry.COLUMN_CITY + " TEXT NOT NULL, "
                 + HotelContract.GuestEntry.COLUMN_GENDER + " INTEGER NOT NULL DEFAULT 3, "
                 + HotelContract.GuestEntry.COLUMN_AGE + " INTEGER NOT NULL DEFAULT 0);";

        sqLiteDatabase.execSQL(SQL_CREATE_GUEST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
