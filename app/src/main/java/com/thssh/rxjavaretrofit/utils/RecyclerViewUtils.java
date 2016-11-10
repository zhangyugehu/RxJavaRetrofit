package com.thssh.rxjavaretrofit.utils;

import android.support.v7.widget.RecyclerView;

/**
 * Created by zhang on 2016/11/10.
 */
public class RecyclerViewUtils {
    public static boolean isSlideToBottom(RecyclerView rv) {
        if(rv==null) return false;
        if((rv.computeVerticalScrollExtent() + rv.computeVerticalScrollOffset())
                >= rv.computeVerticalScrollRange()){
            return true;
        }
        return false;
    }
}
