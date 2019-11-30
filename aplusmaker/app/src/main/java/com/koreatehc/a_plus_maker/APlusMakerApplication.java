package com.koreatehc.a_plus_maker;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialog;

public class APlusMakerApplication extends Application {
    private Context applicationContext;
    private static APlusMakerApplication aPlusMakerApplication;
    private AppCompatDialog progressDialog;


    public static APlusMakerApplication getInstance() {
        return aPlusMakerApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        aPlusMakerApplication = this;
        SaveFileSharedPrefernce.getInstance().init(applicationContext);
    }

    public void showProgressDialog(Activity activity, String message) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        if (progressDialog != null && progressDialog.isShowing()) {
            setProgressDialog(message);
        } else {
            progressDialog = new AppCompatDialog(activity);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.progress_loading);
            progressDialog.show();
        }
        final ImageView imgLoading_frame = progressDialog.findViewById(R.id.iv_frame_loading);
        final AnimationDrawable frameAnimation = (AnimationDrawable) imgLoading_frame.getBackground();
        imgLoading_frame.post(new Runnable() {
            @Override
            public void run() {
                frameAnimation.start();
            }
        });
        TextView tv_progress_message = progressDialog.findViewById(R.id.tv_progress_message);
        if (!TextUtils.isEmpty(message)) {
            tv_progress_message.setText(message);
        }
    }

    private void setProgressDialog(String message) {
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        TextView tvProgressMessage = progressDialog.findViewById(R.id.tv_progress_message);
        if (!TextUtils.isEmpty(message)) {
            tvProgressMessage.setText(message);
        }
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


}
