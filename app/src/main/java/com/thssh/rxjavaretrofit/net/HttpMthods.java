package com.thssh.rxjavaretrofit.net;

import com.thssh.rxjavaretrofit.bean.json.MovieEntity;
import com.thssh.rxjavaretrofit.bean.json.subclass.Subject;
import com.thssh.rxjavaretrofit.model.MovieService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhang on 2016/11/3.
 */
public class HttpMthods {

    private static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private MovieService movieService;

    public static HttpMthods getInstance(){
        return SingletomHolder.INSTANCE;
    }
    public static class SingletomHolder{
        private static final HttpMthods INSTANCE = new HttpMthods();
    }
    private HttpMthods() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        movieService = retrofit.create(MovieService.class);
    }

    public void getTopMovies(int start, int count, Observer<String> observer){
        movieService.getTopMovie(start, count)
                .flatMap(new Func1<MovieEntity, Observable<Subject>>() {
                    @Override
                    public Observable<Subject> call(MovieEntity movieEntity) {
                        return Observable.from(movieEntity.getSubjects());
                    }
                })
                .map(new Func1<Subject, String>() {
                    @Override
                    public String call(Subject subject) {
                        return subject.getTitle();
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
