package com.thssh.rxjavaretrofit.base;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.thssh.rxjavaretrofit.R;
import com.thssh.rxjavaretrofit.adapter.MoviesAdapter;
import com.thssh.rxjavaretrofit.utils.RecyclerViewUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhang on 2016/11/10.
 */

public class MoviesActivity extends BaseActivity<MoviesPresenter> implements MoviesViewModel{

    @Bind(R.id.srl_container)
    SwipeRefreshLayout srlContainer;
    @Bind(R.id.rv_list)
    RecyclerView rvList;

    private MoviesAdapter adapter;
    private List<String> mDatas;

    @Override
    protected void initView() {
        System.out.println("initView");
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        srlContainer.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.darker_gray);
        srlContainer.setOnRefreshListener(listener);

    }
    SwipeRefreshLayout.OnRefreshListener listener =new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            presenter.refresh();
        }
    };

    @Override
    protected void initData() {
        presenter = new MoviesPresenter(this);
        mDatas = new ArrayList<>();
        adapter = new MoviesAdapter(mDatas);
        adapter.setOnItemClickListener(new MoviesAdapter.OnItemClickListener(){
            @Override
            public void onClick(View v, int position) {
                Snackbar.make(v, mDatas.get(position), Snackbar.LENGTH_SHORT).show();
            }
        });

        rvList.setAdapter(adapter);
        rvList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(Color.LTGRAY)
                .size(20)
                .build());
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(RecyclerViewUtils.isSlideToBottom(recyclerView)){
                    presenter.loadMore();
                    Snackbar.make(recyclerView, "加载更多...", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        listener.onRefresh();
        srlContainer.setRefreshing(true);
    }

    @Override
    public void onResult(boolean append, List<String> data) {
        if(!append) mDatas.clear();
        mDatas.addAll(data);
        adapter.notifyDataSetChanged();
        srlContainer.setRefreshing(false);
    }

    @Override
    public void onError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        srlContainer.setRefreshing(false);
    }
}
