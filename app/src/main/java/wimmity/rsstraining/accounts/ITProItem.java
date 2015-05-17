package wimmity.rsstraining.accounts;

/**
 * Created by teru123123 on 15/04/21.
 */
public class ITProItem {
    private CharSequence mTitle;
    private CharSequence mDescription;

    public ITProItem(){
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

