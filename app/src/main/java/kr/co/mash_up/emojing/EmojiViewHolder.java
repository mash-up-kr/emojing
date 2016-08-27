package kr.co.mash_up.emojing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;
import kr.co.mash_up.emojing.base.BaseViewHolder;

/**
 * Created by Beckham on 2016-08-27.
 */
public class EmojiViewHolder extends BaseViewHolder<Emoji> {

    @BindView(R.id.swipeLayout_item)
    SwipeLayout itemSwipeLayout;

    @BindView(R.id.tv_emoji_date)
    TextView tvDate;  //날짜(일)

    @BindView(R.id.tv_emoji_day_of_week)
    TextView tvDayOfWeek;  //요일

    @BindView(R.id.tv_emoji_diary)
    EmojiconTextView tvDiary;  //이모지 내용

    private Emoji mEmoji;

    SwipeItemRecyclerMangerImpl mItemManger;  //swipeLayout controller

    public static EmojiViewHolder newInstance(ViewGroup parent, SwipeItemRecyclerMangerImpl itemManger) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_emoji, parent, false);
        return new EmojiViewHolder(itemView, itemManger);
    }

    public EmojiViewHolder(View itemView, SwipeItemRecyclerMangerImpl itemManger) {
        super(itemView);

        mItemManger = itemManger;
    }

    @Override
    public void bind(Emoji emoji) {
        mEmoji = emoji;
        tvDate.setText(String.format(Locale.KOREAN, "%d", TimeUtils.timestampToDay(emoji.getCreateAt())));
        tvDayOfWeek.setText(TimeUtils.timestampToDayOfWeek(emoji.getCreateAt()));
        tvDiary.setText(emoji.getDiary());
    }

    @OnClick(R.id.iv_emoji_delete)
    public void onDelete() {
        EventBus.getDefault().post(new DeleteEvent(getAdapterPosition()-1));
    }

    @OnClick(R.id.iv_emoji_edit)
    public void onEdit() {

    }

    @OnClick(R.id.iv_emoji_share)
    public void onShare() {
        EventBus.getDefault().post(new ShareEvent());
    }
}
