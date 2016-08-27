package kr.co.mash_up.emojing;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Beckham on 2016-08-27.
 */
public class EmojiViewHolder extends RecyclerView.ViewHolder {
    private Emoji mEmoji;

    @BindView(R.id.emoji_date)
    TextView tvDate;

    @BindView(R.id.emoji_day_of_week)
    TextView tvDayOfWeek;

    @BindView(R.id.emoji_diary)
    TextView tvDiary;

    public EmojiViewHolder(View itemView){
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bindEmoji(Emoji emoji){
        mEmoji = emoji;
        tvDate.setText("29 ");



    }

}
