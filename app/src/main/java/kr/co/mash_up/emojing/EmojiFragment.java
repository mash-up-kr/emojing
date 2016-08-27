package kr.co.mash_up.emojing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;


public class EmojiFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    @BindView(R.id.rootView)
    View rootView;

    @BindView(R.id.recyclerView_emoji)
    RecyclerView rvEmoji;

    @BindView(R.id.emojicon_edit_text)
    EmojiconEditText mEmojiconEditText;

    @BindView(R.id.imageView_emoji)
    ImageView ivEmoji;

    EmojiRecyclerViewAdapter mEmojiRecyclerViewAdapter;

    EmojIconActions mEmojIconActions;

    Unbinder mUnbinder;

    public EmojiFragment() {
    }

    @SuppressWarnings("unused")
    public static EmojiFragment newInstance() {
        EmojiFragment fragment = new EmojiFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
//            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emoji, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvEmoji.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvEmoji.setHasFixedSize(true);
        mEmojiRecyclerViewAdapter = new EmojiRecyclerViewAdapter();
        rvEmoji.setAdapter(mEmojiRecyclerViewAdapter);

        mEmojIconActions = new EmojIconActions(getActivity(), rootView, mEmojiconEditText, ivEmoji);
        mEmojIconActions.ShowEmojIcon();
        mEmojIconActions.setKeyboardListener(new EmojIconActions.KeyboardListener() {
            @Override
            public void onKeyboardOpen() {
                Log.e("Keyboard", "open");
            }

            @Override
            public void onKeyboardClose() {
                Log.e("Keyboard", "close");
            }
        });
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }


    @OnClick(R.id.imageView_send)
    public void onClickSend() {
        String newText = mEmojiconEditText.getText().toString();
        Emoji emoji = new Emoji(newText, TimeUtils.getCurrentUnixTimeStamp());
        mEmojiRecyclerViewAdapter.addItem(0, emoji);

    }

    //Todo: 수정. 이미지로 만들고 공유
    public void share(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpg");
        final File photoFile = new File(getActivity().getFilesDir(), "foo.jpg");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(photoFile));

        String title = getResources().getString(R.string.app_name);
        Intent chooser = Intent.createChooser(intent, title);

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(DeleteEvent deleteEvent){
        mEmojiRecyclerViewAdapter.removeItem(deleteEvent.getPosition());
    }

    @Subscribe
    public void onEvent(ShareEvent shareEvent){
        share();
    }

}
