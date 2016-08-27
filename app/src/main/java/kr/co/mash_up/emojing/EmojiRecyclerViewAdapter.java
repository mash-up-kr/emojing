package kr.co.mash_up.emojing;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import kr.co.mash_up.emojing.base.BaseViewHolder;

public class EmojiRecyclerViewAdapter extends RecyclerSwipeAdapter<BaseViewHolder> {

    public static final int VIEW_TYPE_ITEM = 0;  //이모지 내용
    public static final int VIEW_TYPE_HEADER = 1;  //헤더

    private List<Emoji> mEmojies;  //DB에서 받아온 아이템 보관용(헤더 처리전)
    private final List<Object> mEmojiesHeaderExists;  //실제로 보이는 아이템(헤더 처리 후)

    public EmojiRecyclerViewAdapter() {
        mEmojies = new ArrayList<>();
        mEmojiesHeaderExists = new ArrayList<>();
//        initData();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mEmojies.add(new Emoji("이모지", 1472325985 + (i * 4000)));
        }

        sortHistoryList(mEmojies);
        insertHeaderView();
    }

    @Override
    public int getItemViewType(int position) {
        return mEmojiesHeaderExists.get(position) instanceof String ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
    }

    //헤더 처리전에 정렬
    private void sortHistoryList(List<Emoji> list) {
        mEmojies = list;

        //내림차순 정렬
        Collections.sort(mEmojies, new Comparator<Emoji>() {

            @Override
            public int compare(Emoji lhs, Emoji rhs) {
                return (lhs.getCreateAt() > rhs.getCreateAt()) ? -1 : (lhs.getCreateAt() > rhs.getCreateAt()) ? 1 : 0;
            }
        });
    }

    private void insertHeaderView() {
        mEmojiesHeaderExists.clear();

        String lastHeader = "";
        for (int i = 0; i < mEmojies.size(); i++) {
            Emoji emoji = mEmojies.get(i);

            String header = TimeUtils.timestampToYear(emoji.getCreateAt()) + "년 " + TimeUtils.timestampToMonth(emoji.getCreateAt()) + "월";
            if (!TextUtils.equals(lastHeader, header)) {
                mEmojiesHeaderExists.add(header);
                lastHeader = header;
            }
            mEmojiesHeaderExists.add(emoji);
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_HEADER:
                return EmojiHeaderViewHolder.newInstance(parent);
            case VIEW_TYPE_ITEM:
                return EmojiViewHolder.newInstance(parent, mItemManger);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        Object item = mEmojiesHeaderExists.get(position);

        if (holder instanceof EmojiViewHolder) {
            ((EmojiViewHolder) holder).bind((Emoji) item);
        } else {
            ((EmojiHeaderViewHolder) holder).bind((String) item);
        }
    }

    @Override
    public int getItemCount() {
        return mEmojiesHeaderExists.size();
    }

    public void addItem(int position, Emoji emoji) {
        if (position < 0) {
            position = 0;
        }
        mEmojies.add(position, emoji);
        notifyItemInserted(position);

        sortHistoryList(mEmojies);
        insertHeaderView();
    }

    //Todo: 수정, position문제
    public void removeItem(int position) {
        mEmojies.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mEmojies.size());
    }

    public void setItemList(List<Emoji> emojies) {

        mEmojies = emojies;

        sortHistoryList(mEmojies);

        insertHeaderView();
        notifyDataSetChanged();
    }


    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayout_item;
    }
}
