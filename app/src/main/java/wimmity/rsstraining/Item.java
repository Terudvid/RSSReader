package wimmity.rsstraining;

/**
 * Created by teru123123 on 15/04/21.
 */
public class Item {
    private CharSequence mTitle;
    private CharSequence mDescription;

    public Item(){
        mTitle = "";
        mDescription = "";
    }

    public CharSequence getmDescription() {
        return mDescription;
    }

    public void setmDescription(CharSequence mDescription) {
        this.mDescription = mDescription;
    }

    public CharSequence getmTitle() {
        return mTitle;
    }

    public void setmTitle(CharSequence mTitle) {
        this.mTitle = mTitle;
    }
}

