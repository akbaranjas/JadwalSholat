package com.akbaranjas.praytime.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.akbaranjas.praytime.R;
import com.akbaranjas.praytime.data.Constant;
import com.akbaranjas.praytime.data.DBHelper;

public class SplashActivity extends AppCompatActivity implements LocationListener {

    private TextView tv_status;
    LocationManager locationManager;
    String mprovider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        tv_status = (TextView) findViewById(R.id.tv_status_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Uri uriMethod = Uri.parse("content://"+ getResources().getString(R.string.content_authority) + "/"
                        + DBHelper.TBL_METHOD);

                Uri uriSetting = Uri.parse("content://"+ getResources().getString(R.string.content_authority) + "/"
                        + DBHelper.TBL_SETTING);

                Cursor cursor = getContentResolver().query(uriMethod ,
                        new String[]{
                                DBHelper.COL_METHOD_ID,
                                DBHelper.COL_METHOD_DESC
                        },
                        null,
                        null,
                        null
                );

                Cursor cursorSetting = getContentResolver().query(uriSetting ,
                        new String[]{
                                DBHelper.COL_SETTING_KEY,
                                DBHelper.COL_SETTING_VALUE
                        },
                        null,
                        null,
                        null
                );

                if(cursor.getCount() != 7){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            tv_status.setText(getResources().getString(R.string.status_preparing_setting_splash));
                        }
                    });
                    getContentResolver().delete(uriMethod,null,null);
                    int colSize = Constant.method_id.length;
                    ContentValues[] contentValues = new ContentValues[colSize];
                    for(int i = 0;i < colSize; i++){
                        ContentValues cv = new ContentValues();
                        cv.put(DBHelper.COL_METHOD_ID, Constant.method_id[i]);
                        cv.put(DBHelper.COL_METHOD_DESC, Constant.method_desc[i]);
                        contentValues[i] = cv;
                    }

                    getContentResolver().bulkInsert(
                            uriMethod,
                            contentValues);
                    getContentResolver().notifyChange(uriMethod, null);
                }

                if(cursorSetting.getCount() <= 0){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            tv_status.setText(getResources().getString(R.string.status_preparing_setting_splash));
                        }
                    });

                    int colSize = Constant.setting_key.length;
                    ContentValues[] contentValues = new ContentValues[colSize];
                    for(int i = 0;i < colSize; i++){
                        ContentValues cv = new ContentValues();
                        cv.put(DBHelper.COL_SETTING_KEY, Constant.setting_key[i]);
                        contentValues[i] = cv;
                    }

                    getContentResolver().bulkInsert(
                            uriSetting,
                            contentValues);
                    getContentResolver().notifyChange(uriSetting, null);

//                while (cursorSetting.moveToNext()){
//                    String key = cursorSetting.getString(0);
//                    String value = cursorSetting.getString(1);
//                    //if(key)
//                }
                }

                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, 2000);

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
