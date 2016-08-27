package kr.co.mash_up.emojing.base;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Dong on 2016-08-28.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    /**
     * 뷰홀더에 데이터 바인딩
     *
     * @param t 데이터를 담는 POJO 객체
     */
    @UiThread
    public abstract void bind(T t);
}

