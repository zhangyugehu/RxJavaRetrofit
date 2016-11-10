package com.thssh.rxjavaretrofit.base;

/**
 * Created by zhang on 2016/11/10.
 */
public class BasePresenter<T extends BaseViewModel> {

    protected T view;

    public BasePresenter(T view) {
        this.view = view;
    }
}
