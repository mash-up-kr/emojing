package kr.co.mash_up.emojing;

/**
 * Created by Beckham on 2016-08-27.
 */
public class Emoji {

    private String mDiary;  //이모지 내용

    private long createAt;

    public Emoji(String diary, long createAt) {
        mDiary = diary;
        this.createAt = createAt;
    }

    public String getDiary() {
        return mDiary;
    }

    public void setDiary(String diary) {
        mDiary = diary;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }
}
