package com.malpo.northeast;

import android.os.Bundle;

import com.metova.slim.SlimActivity;
import com.metova.slim.annotation.Layout;

import butterknife.ButterKnife;

public class BaseActivity extends SlimActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
