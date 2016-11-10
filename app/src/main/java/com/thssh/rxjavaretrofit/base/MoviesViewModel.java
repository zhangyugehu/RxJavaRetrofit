package com.thssh.rxjavaretrofit.base;

import android.content.Context;

import java.util.List;

/**
 * Created by zhang on 2016/11/10.
 */
public interface MoviesViewModel extends BaseViewModel {
    void onResult(boolean append, List<String> data);

    void onError(String text);
}
