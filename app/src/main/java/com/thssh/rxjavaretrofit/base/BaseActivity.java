package com.thssh.rxjavaretrofit.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhang on 2016/11/10.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{
    protected Context mContext;
    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initView();
        initData();
    }

    protected abstract void initView() ;
    protected abstract void initData() ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    public Context getViewContext() {
        return mContext;
    }
}
