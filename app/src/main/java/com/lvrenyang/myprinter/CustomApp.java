package com.lvrenyang.myprinter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class CustomApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setLanguage();
    }

    public void setLanguage() {
        Resources resources=getResources();
        final SharedPreferences sharedPreferences=getSharedPreferences("language_choice"
                , Context.MODE_PRIVATE);

        int language_id = sharedPreferences.getInt("id", 2);
        // 获取应用内语言
        final Configuration configuration=resources.getConfiguration();

        switch (language_id){
            case 0:
                updateConfiguration(configuration, Locale.getDefault());
                break;
            case 1:
                updateConfiguration(configuration, Locale.SIMPLIFIED_CHINESE);
                break;
            case 2:
                updateConfiguration(configuration, Locale.ENGLISH);
                break;
            default:
                updateConfiguration(configuration, Locale.getDefault());
                break;
        }
        this.getResources().updateConfiguration( configuration,resources.getDisplayMetrics() );
    }

    public void updateConfiguration(Configuration conf, Locale locale) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            conf.setLocale(locale);
        }else {
            //noinspection deprecation
            conf.locale = locale;
        }
    }
}
