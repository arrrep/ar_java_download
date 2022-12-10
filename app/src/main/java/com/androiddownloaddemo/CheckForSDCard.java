package com.androiddownloaddemo;

import android.os.Environment;

public class CheckForSDCard {
    //проверка сд карты
    public boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
