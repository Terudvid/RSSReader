package wimmity.rsstraining;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by teru123123 on 15/05/13.
 */
public class ShowFragment extends DialogFragment {

    public ShowFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_detail, container, false);
        String title = getArguments().getString("Title");
        String descr = getArguments().getString("Desc");
        TextView listtitle = (TextView)v.findViewById(R.id.item_detail_itpro_title);
        TextView medDesc = (TextView)v.findViewById(R.id.item_detail_itpro_desc);
        listtitle.setText(title);
        medDesc.setText(descr);
        return v;
    }
}
