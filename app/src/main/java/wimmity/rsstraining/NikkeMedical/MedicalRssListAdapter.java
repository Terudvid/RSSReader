package wimmity.rsstraining.NikkeMedical;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import wimmity.rsstraining.accounts.MedicalItem;
import wimmity.rsstraining.R;

/**
 * Created by teru123123 on 15/05/12.
 */
public class MedicalRssListAdapter extends ArrayAdapter<MedicalItem>{

    private LayoutInflater mInfalter_Medical;
    private TextView mMedTitle;
    private TextView mMedDescr;

    public MedicalRssListAdapter(Context context, List<MedicalItem> object){
        super(context, 0, object);

        mInfalter_Medical = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //1行ごとにビューを生成する


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null){
            view = mInfalter_Medical.inflate(R.layout.item_row2, null);
        }

        MedicalItem mMedical = this.getItem(position);

        if (mMedical != null){
            String title = mMedical.getTitle_m().toString();
            mMedTitle = (TextView)view.findViewById(R.id.item_med_title);
            mMedTitle.setText(title);
            String desc = mMedical.getDiscription().toString();
            mMedDescr = (TextView)view.findViewById(R.id.item_med_desc);
            mMedDescr.setText(desc);


        }
        return view;
    }
}
