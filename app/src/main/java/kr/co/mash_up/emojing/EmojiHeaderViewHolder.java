package kr.co.mash_up.emojing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import kr.co.mash_up.emojing.base.BaseViewHolder;

/**
 * Created by Dong on 2016-08-28.
 */
public class EmojiHeaderViewHolder extends BaseViewHolder<String> {

    @BindView(R.id.textView_year_month)
    TextView tvYearMonth;

    public static EmojiHeaderViewHolder newInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_emoji_header, parent, false);
        return new EmojiHeaderViewHolder(itemView);
    }

    public EmojiHeaderViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(String s) {
        tvYearMonth.setText(s);
    }
}
