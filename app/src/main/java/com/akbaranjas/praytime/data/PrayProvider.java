package com.akbaranjas.praytime.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.akbaranjas.praytime.R;

/**
 * Created by akbaranjas on 14/12/16.
 */

public class PrayProvider extends ContentProvider {

    public static final int URI_SETTING = 100;
    public static final int URI_METHOD = 200;
    public static final int URI_TODAY_PRAY = 300;
    public static final int URI_MONTHLY_PRAY= 400;
    DBHelper dbHelper;
    UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
        String content_authority = getContext().getResources().getString(R.string.content_authority);
        dbHelper = new DBHelper(getContext());
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(content_authority , DBHelper.TBL_SETTING, URI_SETTING);
        uriMatcher.addURI(content_authority, DBHelper.TBL_METHOD , URI_METHOD);
        uriMatcher.addURI(content_authority, DBHelper.TBL_PRAY_TIME_TODAY + "/#", URI_TODAY_PRAY);
        uriMatcher.addURI(content_authority, DBHelper.TBL_PRAY_TIME_MONTHLY + "/#", URI_MONTHLY_PRAY);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionargs, String sortorder) {
        Cursor retCursor = null;
        switch (uriMatcher.match(uri)){
            case URI_SETTING:
                retCursor = dbHelper.getReadableDatabase().query(
                        DBHelper.TBL_SETTING,
                        projection,
                        //DBHelper.TBL_SETTING + "." + DBHelper.COL_SETTING_KEY + " = ?",
                        selection,
                        //new String[] { uri.getLastPathSegment() },
                        selectionargs,
                        null,
                        null,
                        sortorder
                );
                retCursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;

            case URI_METHOD:
                retCursor = dbHelper.getReadableDatabase().query(
                        DBHelper.TBL_METHOD,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortorder
                );
                retCursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;

            case URI_TODAY_PRAY:
                retCursor = dbHelper.getReadableDatabase().query(
                        DBHelper.TBL_PRAY_TIME_TODAY,
                        projection,
                        DBHelper.TBL_PRAY_TIME_TODAY + "." + DBHelper.COL_PRAY_DATE + " = ?",
                        new String[] { uri.getLastPathSegment() },
                        null,
                        null,
                        sortorder
                );
                retCursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;

            case URI_MONTHLY_PRAY:
                retCursor = dbHelper.getReadableDatabase().query(
                        DBHelper.TBL_PRAY_TIME_MONTHLY,
                        projection,
                        DBHelper.TBL_PRAY_TIME_MONTHLY + "." + DBHelper.COL_MONTH + " = ?",
                        new String[] { uri.getLastPathSegment() },
                        null,
                        null,
                        sortorder
                );
                retCursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;
        }

        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case URI_SETTING:
                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + String.valueOf(R.string.content_authority) + "/"
                        + DBHelper.TBL_SETTING + "/";
            case URI_METHOD:
                return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + String.valueOf(R.string.content_authority) + "/"
                        + DBHelper.TBL_METHOD + "/";
            case URI_TODAY_PRAY:
                return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + String.valueOf(R.string.content_authority) + "/"
                        + DBHelper.TBL_PRAY_TIME_TODAY + "/";
            case URI_MONTHLY_PRAY:
                return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + String.valueOf(R.string.content_authority) + "/"
                        + DBHelper.TBL_PRAY_TIME_MONTHLY + "/";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        switch (uriMatcher.match(uri)) {

            case URI_TODAY_PRAY:
                long id = dbHelper.getWritableDatabase().insert(
                        DBHelper.TBL_PRAY_TIME_TODAY,
                        null,
                        contentValues
                );
                return Uri.parse ("content://" +String.valueOf(R.string.content_authority) + "/" + DBHelper.TBL_PRAY_TIME_TODAY +"/row/"+id );
        }
        return null;
    }

    @Override
    public int delete(Uri uri,String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {

            case URI_SETTING:
                int x = dbHelper.getWritableDatabase().delete(
                        DBHelper.TBL_SETTING,
                        selection,
                        selectionArgs
                );
                return x;

            case URI_METHOD:
                int i = dbHelper.getWritableDatabase().delete(
                        DBHelper.TBL_METHOD,
                        selection,
                        selectionArgs
                );
                return i;

            case URI_TODAY_PRAY:
                int z = dbHelper.getWritableDatabase().delete(
                        DBHelper.TBL_PRAY_TIME_TODAY,
                        selection,
                        selectionArgs
                );
                return z;

            case URI_MONTHLY_PRAY:
                int y = dbHelper.getWritableDatabase().delete(
                        DBHelper.TBL_PRAY_TIME_MONTHLY,
                        selection,
                        selectionArgs
                );
                return y;

        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {

            case URI_SETTING:
                int i = dbHelper.getWritableDatabase().update(
                        DBHelper.TBL_SETTING,
                        contentValues,
                        selection,
                        selectionArgs
                );
                return i;
            case URI_METHOD:
                int a = dbHelper.getWritableDatabase().update(
                        DBHelper.TBL_METHOD,
                        contentValues,
                        selection,
                        selectionArgs
                );
                return a;

            case URI_TODAY_PRAY:
                int z = dbHelper.getWritableDatabase().update(
                        DBHelper.TBL_PRAY_TIME_TODAY,
                        contentValues,
                        selection,
                        selectionArgs
                );
                return z;

            case URI_MONTHLY_PRAY:
                int y = dbHelper.getWritableDatabase().update(
                        DBHelper.TBL_PRAY_TIME_MONTHLY,
                        contentValues,
                        selection,
                        selectionArgs
                );
                return y;

        }
        return 0;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        SQLiteDatabase writableDatabase= dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {

            case URI_SETTING:
                int returnCount = 0;

                writableDatabase.beginTransaction();
                try {
                    for (ContentValues cv : values) {
                        writableDatabase.insert(
                                DBHelper.TBL_SETTING,
                                null,
                                cv
                        );
                        returnCount++;
                    }
                    writableDatabase.setTransactionSuccessful();
                }
                catch (Exception e) {
                    returnCount = 0;
                }
                finally {
                    writableDatabase.endTransaction();
                }
                return returnCount;


            case URI_METHOD:
                int count = 0;

                writableDatabase.beginTransaction();
                try {
                    for (ContentValues cv : values) {
                        writableDatabase.insert(
                                DBHelper.TBL_METHOD,
                                null,
                                cv
                        );
                        count++;
                    }
                    writableDatabase.setTransactionSuccessful();
                }
                catch (Exception e) {
                    count = 0;
                }
                finally {
                    writableDatabase.endTransaction();
                }
                return count;

            case URI_MONTHLY_PRAY:
                int counting = 0;

                writableDatabase.beginTransaction();
                try {
                    for (ContentValues cv : values) {
                        writableDatabase.insert(
                                DBHelper.TBL_PRAY_TIME_MONTHLY,
                                null,
                                cv
                        );
                        counting++;
                    }
                    writableDatabase.setTransactionSuccessful();
                }
                catch (Exception e) {
                    count = 0;
                }
                finally {
                    writableDatabase.endTransaction();
                }
                return counting;
        }
        return 0;
    }
}
