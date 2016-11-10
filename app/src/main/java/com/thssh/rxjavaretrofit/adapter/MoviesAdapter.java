package com.thssh.rxjavaretrofit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thssh.rxjavaretrofit.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhang on 2016/11/10.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder>{
    private List<String> mDatas;
    private MoviesAdapter.OnItemClickListener listener;

    public MoviesAdapter(List<String> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new MoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesHolder holder, final int position) {
        if(mDatas == null || mDatas.size() < position || position < 0) return;
        String posItem = mDatas.get(position);
        holder.tvTitle.setText(posItem);
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0: mDatas.size();
    }

    public void setOnItemClickListener(MoviesAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    protected final static class MoviesHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;

        public MoviesHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    public static interface OnItemClickListener {
        public void onClick(View v, int position);
    }
}
