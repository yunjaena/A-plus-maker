package com.koreatehc.a_plus_maker;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SaveFileSharedPrefernce extends BaseSharedPreferencesHelper {
    private final String TAG = SaveFileSharedPrefernce.class.getSimpleName();
    private final String A_PLUS_MAKER_SHARED_PREFERENCES = "com.koreatech.sharedpreferences";
    public static final String KEY_USER = ".files";

    private static volatile SaveFileSharedPrefernce instance = null;


    public static SaveFileSharedPrefernce getInstance() {
        if (instance == null) {
            synchronized (SaveFileSharedPrefernce.class) {
                instance = new SaveFileSharedPrefernce();
            }
        }
        return instance;
    }


    public void init(Context context) {
        super.init(context, context.getSharedPreferences(A_PLUS_MAKER_SHARED_PREFERENCES, Context.MODE_PRIVATE));
    }

    public void saveFileList(FileList fileList) {
        SharedPreferences.Editor editor = editor();
        editor.clear();
        editor.putString(KEY_USER, gsonHelper.objectToJSON(fileList).toString());
        editor.apply();
    }

    public FileList loadFileList() {
        String fileListString = sharedPreferences.getString(KEY_USER, "");
        FileList fileList = gsonHelper.jsonToObject(fileListString, FileList.class);
        Log.d(TAG, "fileList: " + fileList);
        if(fileList == null) {
            fileList = new FileList();
        }
        return fileList;
    }
}
