package com.akbaranjas.praytime.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by akbaranjas on 14/12/16.
 */

public class DBHelper extends SQLiteOpenHelper {


    public static final String PRAY_DB = "praytime.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_SETTING = "tbl_setting";
    public static final String ID = "id";
    public static final String COL_SETTING_KEY = "SETTING_KEY";
    public static final String COL_SETTING_VALUE = "SETTING_VALUE";
    public static final String TBL_METHOD = "tbl_method";
    public static final String COL_METHOD_ID = "method_id";
    public static final String COL_METHOD_DESC = "desc";
    public static final String COL_PRAY_DATE = "date";
    public static final String COL_FAJR_TIME = "fajr_time";
    public static final String COL_SUNRISE_TIME = "sunrise_time";
    public static final String COL_DHUHUR_TIME = "dhuhur_time";
    public static final String COL_ASR_TIME = "asr_time";
    public static final String COL_SUNSET_TIME = "sunset_time";
    public static final String COL_MAGHRIB_TIME = "maghrib_time";
    public static final String COL_ISHA_TIME = "isha_time";
    public static final String COL_IMSHAK_TIME = "imshak_time";
    public static final String COL_MIDNIGHT_TIME = "midnight_time";
    public static final String COL_IS_ALARM = "is_alarm";
    public static final String TBL_PRAY_TIME_TODAY = "tbl_pray_time_today";
    public static final String TBL_PRAY_TIME_MONTHLY = "tbl_pray_time_monthly";
    public static final String COL_MONTH = "month";

    private static final String CREATE_TBL_SETTING ="create table " + TBL_SETTING + " (" +
            COL_SETTING_KEY + " text, " +
            COL_SETTING_VALUE + " text, " +
            "unique ("+ COL_SETTING_KEY+ ") on conflict replace);";


    private static final String CREATE_TBL_TIME_METHOD ="create table " + TBL_METHOD + " (" +
            COL_METHOD_ID + " int, " +
            COL_METHOD_DESC + " text, " +
            "unique ("+ COL_METHOD_ID + ") on conflict replace);";


    public static final String COL_IS_ALARM_FAJR = "is_alarm_fajr";
    public static final String COL_IS_ALARM_DHUHUR = "is_alarm_dhuhur";
    public static final String COL_IS_ALARM_ASR = "is_alarm_asr";
    public static final String COL_IS_ALARM_MAGHRIB = "is_alarm_maghrib";
    public static final String COL_IS_ALARM_ISYA = "is_alarm_isya";
    private static final String CREATE_TBL_PRAY_TIME ="create table " + TBL_PRAY_TIME_TODAY + " (" +
            ID + " int, " +
            COL_PRAY_DATE + " int, " +
            COL_FAJR_TIME + " text, " +
            COL_SUNRISE_TIME + " text, " +
            COL_DHUHUR_TIME + " text, " +
            COL_ASR_TIME + " text, " +
            COL_SUNSET_TIME + " text, " +
            COL_MAGHRIB_TIME + " text, " +
            COL_ISHA_TIME + " text, " +
            COL_IMSHAK_TIME + " text, " +
            COL_MIDNIGHT_TIME + " text, " +
            COL_IS_ALARM_FAJR + " int, " +
            COL_IS_ALARM_DHUHUR + " int, " +
            COL_IS_ALARM_ASR + " int, " +
            COL_IS_ALARM_MAGHRIB + " int, " +
            COL_IS_ALARM_ISYA + " int, " +
            "unique ("+ COL_PRAY_DATE + ") on conflict replace);";

    private static final String CREATE_TBL_PRAY_TIME_MONTHLY ="create table " + TBL_PRAY_TIME_MONTHLY + " (" +
            ID + " int, " +
            COL_PRAY_DATE + " int, " +
            COL_FAJR_TIME + " text, " +
            COL_SUNRISE_TIME + " text, " +
            COL_DHUHUR_TIME + " text, " +
            COL_ASR_TIME + " text, " +
            COL_SUNSET_TIME + " text, " +
            COL_MAGHRIB_TIME + " text, " +
            COL_ISHA_TIME + " text, " +
            COL_IMSHAK_TIME + " text, " +
            COL_MIDNIGHT_TIME + " text, " +
            COL_MONTH + " int, " +
            "unique ("+ COL_PRAY_DATE + ") on conflict replace);";


    public DBHelper(Context context) {
        super(context, PRAY_DB, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TBL_SETTING);
        sqLiteDatabase.execSQL(CREATE_TBL_TIME_METHOD);
        sqLiteDatabase.execSQL(CREATE_TBL_PRAY_TIME);
        sqLiteDatabase.execSQL(CREATE_TBL_PRAY_TIME_MONTHLY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        buatUlangDB(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        buatUlangDB(db);
    }

    private void buatUlangDB(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("drop table if exists " + TBL_SETTING);
        sqLiteDatabase.execSQL("drop table if exists " + TBL_METHOD);
        sqLiteDatabase.execSQL("drop table if exists " + TBL_PRAY_TIME_TODAY);
        sqLiteDatabase.execSQL("drop table if exists " + TBL_PRAY_TIME_MONTHLY);

        onCreate(sqLiteDatabase);
    }
}
