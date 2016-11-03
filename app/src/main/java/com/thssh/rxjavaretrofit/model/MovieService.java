package com.thssh.rxjavaretrofit.model;


import com.thssh.rxjavaretrofit.bean.json.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhang on 2016/11/3.
 */

public interface MovieService {
    @GET("top250")
//    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
