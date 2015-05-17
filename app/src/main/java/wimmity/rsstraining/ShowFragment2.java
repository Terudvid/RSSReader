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
public class ShowFragment2 extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_detail1, container, false);
        String ItProTitle = getArguments().getString("MedicalTitle");
        String ITProDesc = getArguments().getString("medicalDesc");
        TextView title = (TextView)v.findViewById(R.id.item_detail_medical_title);
        TextView desc = (TextView)v.findViewById(R.id.item_detail_medical_desc);
        title.setText(ItProTitle);
        desc.setText(ITProDesc);

        return v;
    }
}
