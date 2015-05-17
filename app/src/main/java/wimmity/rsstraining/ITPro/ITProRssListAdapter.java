package wimmity.rsstraining.ITPro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import wimmity.rsstraining.accounts.ITProItem;
import wimmity.rsstraining.R;

/**
 * Created by teru123123 on 15/04/21.
 */
public class ITProRssListAdapter extends ArrayAdapter<ITProItem> {

    private LayoutInflater mInflater;
    private TextView mTitle;
    private TextView mDescr;

    public ITProRssListAdapter(Context context, List<ITProItem> objects){
        super(context, 0, objects);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //1行ごとにビューを生成する

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null){
            view = mInflater.inflate(R.layout.item_row, null);
        }

        ITProItem ITProItem = this.getItem(position);

        if (ITProItem != null){
            String title = ITProItem.getmTitle().toString();
            mTitle = (TextView)view.findViewById(R.id.item_title);
            mTitle.setText(title);
            String decr = ITProItem.getmDescription().toString();
            mDescr = (TextView)view.findViewById(R.id.item_descr);
            mDescr.setText(decr);
        }

        return view;
    }
}
