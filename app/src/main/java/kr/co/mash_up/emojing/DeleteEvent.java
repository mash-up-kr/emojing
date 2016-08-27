package kr.co.mash_up.emojing;

/**
 * Created by Dong on 2016-08-28.
 */
public class DeleteEvent {
    private int position;

    public DeleteEvent(int position){this.position = position;}

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
