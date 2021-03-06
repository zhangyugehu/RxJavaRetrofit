package com.thssh.rxjavaretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.thssh.rxjavaretrofit.bean.json.MovieEntity;
import com.thssh.rxjavaretrofit.model.MovieService;
import com.thssh.rxjavaretrofit.net.HttpMthods;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_click)
    protected Button btnClick;
    @Bind(R.id.tv_content)
    protected TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_click)
    public void onClick(){
        getMovie2();
    }

    public void getMovie() {
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        MovieService moviewService = retrofit.create(MovieService.class);
//        Call<MovieEntity> call = moviewService.getTopMovie(0, 10);
//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
//                tvContent.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                tvContent.setText(t.getMessage());
//            }
//        });
        moviewService.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "onError -> " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        tvContent.setText(movieEntity.toString());
                    }
                });

    }
    public void getMovie2() {
        final StringBuilder sb = new StringBuilder();
        HttpMthods.getInstance().getTopMovies(0, 10, new Action1<String>() {
            @Override
            public void call(String s) {
                sb.append(s).append("\n");
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Toast.makeText(MainActivity.this, "error -> " + throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        }, new Action0() {
            @Override
            public void call() {
                tvContent.setText(sb.toString());
            }
        });
    }
}
