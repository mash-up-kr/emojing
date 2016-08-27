package kr.co.mash_up.emojing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class EmojiRecyclerViewAdapter extends RecyclerView.Adapter<EmojiViewHolder> {

    private final List<Emoji> mEmojies;


    public EmojiRecyclerViewAdapter() {
        mEmojies = new ArrayList<>();
        initData();
    }

    private void initData(){
        for(int i=0; i<20; i++){
            mEmojies.add(new Emoji());
        }
    }

    @Override
    public EmojiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_emoji, parent, false);
        return new EmojiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmojiViewHolder holder, int position) {
        Emoji emoji = mEmojies.get(position);
        holder.bindEmoji(emoji);
    }

    @Override
    public int getItemCount() {
        return mEmojies.size();
    }


}
