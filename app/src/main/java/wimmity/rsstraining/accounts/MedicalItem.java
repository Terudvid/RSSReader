package wimmity.rsstraining.accounts;

/**
 * Created by teru123123 on 15/05/12.
 */
public class MedicalItem  {

    private CharSequence title_m;
    private CharSequence discription;

    public MedicalItem(){
        title_m = "";
        discription = "";
    }

    public CharSequence getDiscription() {
        return discription;
    }

    public void setDiscription(CharSequence discription) {
        this.discription = discription;
    }

    public CharSequence getTitle_m() {
        return title_m;
    }

    public void setTitle_m(CharSequence title_m) {
        this.title_m = title_m;
    }
}
