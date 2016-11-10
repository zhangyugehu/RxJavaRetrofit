package com.thssh.rxjavaretrofit.base;

import com.thssh.rxjavaretrofit.net.HttpMthods;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

/**
 * Created by zhang on 2016/11/10.
 */
public class MoviesPresenter extends BasePresenter<MoviesViewModel>{

    private final int DEFAULT_COUNT = 10;
    private HttpMthods http;

    private int offset = DEFAULT_COUNT;

    protected List<String> mData;

    public MoviesPresenter(MoviesViewModel view) {
        super(view);
        mData = new ArrayList<>();
        http = HttpMthods.getInstance();
    }

    public void refresh() {
        getMovies(0, offset, true);
    }

    public void getMovies(final int start, final int count, final boolean isRefresh) {
        final List<String> data = new ArrayList<>();
        http.getTopMovies(start, count, new Observer<String>() {
            @Override
            public void onCompleted() {
                mData.clear();
                mData.addAll(data);
                data.clear();
                view.onResult(!isRefresh, mData);
                offset = start + count;
            }

            @Override
            public void onError(Throwable e) {
                data.clear();
                view.onError(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                data.add(s);
            }
        });
    }

    public void loadMore() {
        getMovies(offset, DEFAULT_COUNT, false);
    }
}
