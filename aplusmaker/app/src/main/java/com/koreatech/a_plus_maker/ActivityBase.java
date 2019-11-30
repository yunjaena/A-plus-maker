package com.koreatech.a_plus_maker;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityBase extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    public void showProgressDialog() {
        APlusMakerApplication.getInstance().showProgressDialog(this, null);
    }

    public void showProgressDialog(String message) {
        APlusMakerApplication.getInstance().showProgressDialog(this, message);
    }

    public void hideProgressDialog() {
        APlusMakerApplication.getInstance().hideProgressDialog();
    }


}
