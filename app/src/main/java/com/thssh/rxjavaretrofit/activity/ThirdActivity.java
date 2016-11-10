package com.thssh.rxjavaretrofit.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.thssh.rxjavaretrofit.R;

/**
 * Created by zhang on 2016/11/10.
 */

public class ThirdActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        System.out.println("fasdfasddfsadfsdfsddfdsaf");
        super.onCreate(savedInstanceState, persistentState);
        getActionBar().setTitle("sdfsfsfsd");
        setContentView(R.layout.activity_main);
    }
}
