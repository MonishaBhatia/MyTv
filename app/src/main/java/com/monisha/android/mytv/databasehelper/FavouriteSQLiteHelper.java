package com.monisha.android.mytv.databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.monisha.android.mytv.model.Channels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monisha on 18/04/17.
 */

public class FavouriteSQLiteHelper extends SQLiteOpenHelper {

    private SQLiteDatabase mDatabase;
    private Context mContext;
    private static FavouriteSQLiteHelper sSingleton;

    // Database Name
    private static final String DATABASE_NAME = "favouriteDB";

    public static final String TABLE_NAME= "favourite";
    public static final int SCHEMA_VERSION= 1;


    public static final String COLUMN_CHANNEL_ID = "channel_id";
    public static final String COLUMN_CHANNEL_NUMBER = "channel_number";
    public static final String COLUMN_CHANNEL_TITLE = "channel_title";
    public static final String COLUMN_CHANNEL_FAVOURITE = "channel_favourite";


    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NAME
            + "("
            + COLUMN_CHANNEL_ID + " INTEGER, "
            + COLUMN_CHANNEL_NUMBER + " INTEGER, "
            + COLUMN_CHANNEL_TITLE + " TEXT NOT NULL, "
            + COLUMN_CHANNEL_FAVOURITE + " TEXT NOT NULL "
            + ");";

    public synchronized static FavouriteSQLiteHelper getInstance(Context context) {
        if (sSingleton == null) {
            sSingleton = new FavouriteSQLiteHelper(context);
        }
        return sSingleton;
    }

    private FavouriteSQLiteHelper(Context context) {
        super(context, TABLE_NAME, null, SCHEMA_VERSION);
        mContext = context;
    }

    public FavouriteSQLiteHelper openConnection() throws SQLException {
        if (mDatabase == null) {
            mDatabase = sSingleton.getWritableDatabase();
        }
        return this;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cart");
        onCreate(sqLiteDatabase);
    }

    public void addItemToFavourite(Channels channels) {

        mDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CHANNEL_ID, channels.getChannelId());
        values.put(COLUMN_CHANNEL_NUMBER, channels.getChannelStbNumber());
        values.put(COLUMN_CHANNEL_TITLE, channels.getChannelTitle());
        values.put(COLUMN_CHANNEL_FAVOURITE, channels.isChannelFavourite());

        mDatabase.insert(TABLE_NAME, null, values);
    }

    public void deleteByChannelId(int channelId) {
        mDatabase = this.getWritableDatabase();

        mDatabase.delete(TABLE_NAME, COLUMN_CHANNEL_ID + " =\"" + channelId + "\" ",
                null);
    }

    public List<Channels> getAllFavorites() {
        List<Channels> channelsList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Channels channels = new Channels();
                channels.setChannelId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHANNEL_ID))));
                channels.setChannelStbNumber(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHANNEL_NUMBER))));
                channels.setChannelTitle(cursor.getString(cursor.getColumnIndex(COLUMN_CHANNEL_TITLE)));
                channels.setChannelFavourite(cursor.getInt(cursor.getColumnIndex(COLUMN_CHANNEL_FAVOURITE))>0);

                channelsList.add(channels);
            } while (cursor.moveToNext());
        }

        // return contact list
        return channelsList;
    }
}
