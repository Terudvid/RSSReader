package wimmity.rsstraining.NikkeMedical;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import wimmity.rsstraining.R;
import wimmity.rsstraining.ShowFragment2;
import wimmity.rsstraining.accounts.MedicalItem;

/**
 * Created by teru123123 on 15/05/12.
 *
 * ListFragmentの呼び出しの残りを書く *
 * おそらくこのままだと落ちる
 *
 * 追記：アプリ自体は起動するが、日経Medicalの呼び出しができない
 */
public class MedicalRssReader  extends ListFragment{

    private static final String MEDICAL_RSS_FEED_URL = "http://itpro.nikkeibp.co.jp/rss/ITpro.rdf";
    public static final int MENU_TIMERELOAD_MEDICAL = Menu.FIRST;
    private MedicalRssListAdapter mMedAdapter;
    private ArrayList<MedicalItem> mMedItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.medicalfragmnet, container, false);
        mMedItems = new ArrayList<MedicalItem>();
        mMedAdapter = new MedicalRssListAdapter(getActivity(), mMedItems);

        //open a tasks
        //エラーで落ちる

        MedicalRssParserTask tasks = new MedicalRssParserTask(MedicalRssReader.this, mMedAdapter);
        tasks.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, MEDICAL_RSS_FEED_URL);
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        MedicalItem medicalItem = mMedItems.get(position);
        ShowFragment2 fragment = new ShowFragment2();
        Bundle bdl = new Bundle();
        bdl.putString("MedicalTitle", (String) medicalItem.getTitle_m());
        bdl.putString("medicalDesc", (String) medicalItem.getDiscription());
        fragment.setArguments(bdl);
        fragment.show(getChildFragmentManager(), "tag");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(0, MENU_TIMERELOAD_MEDICAL, 0, "更新");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case MENU_TIMERELOAD_MEDICAL:
            mMedItems = new ArrayList<MedicalItem>();
            mMedAdapter = new MedicalRssListAdapter(getActivity().getApplicationContext(), mMedItems);
            MedicalRssParserTask task = new MedicalRssParserTask(MedicalRssReader.this,mMedAdapter);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
