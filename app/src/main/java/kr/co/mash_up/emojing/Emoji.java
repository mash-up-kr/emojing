package kr.co.mash_up.emojing;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Beckham on 2016-08-27.
 */
public class Emoji {
    private Date mDate;
    private String mDiary;
    private UUID mId;

    public Emoji(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getmDiary() {
        return mDiary;
    }

    public void setmDiary(String mDiary) {
        this.mDiary = mDiary;
    }
}
