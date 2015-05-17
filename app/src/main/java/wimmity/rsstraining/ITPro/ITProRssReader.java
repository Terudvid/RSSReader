package wimmity.rsstraining.ITPro;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import wimmity.rsstraining.R;
import wimmity.rsstraining.ShowFragment;
import wimmity.rsstraining.accounts.ITProItem;

/**
 * Created by teru123123 on 15/04/21.
 *
 * intentのタグ渡し
 */
public class ITProRssReader extends ListFragment {

    private static final String RSS_FEED_URL = "http://itpro.nikkeibp.co.jp/rss/ITpro.rdf";
    public static final int MENU_ITEM_RELOAD = Menu.FIRST;
    private ITProRssListAdapter mAdapter;
    private ArrayList<ITProItem> mITProItems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.itprofragmnet, container, false);

        mITProItems = new ArrayList<ITProItem>();
        mAdapter = new ITProRssListAdapter(getActivity().getApplicationContext(), mITProItems);

        ITProRssParserTask tasks = new ITProRssParserTask(ITProRssReader.this, mAdapter);
        tasks.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, RSS_FEED_URL);
//        tasks.execute(RSS_FEED_URL);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        //タスクを起動する。

    }

    //リスト項目を洗濯した時の処理
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ITProItem itProItem = mITProItems.get(position);
        ShowFragment fragment2 = new ShowFragment();
        Bundle bdl2 = new Bundle();
        bdl2.putString("ITProTitle", (String) itProItem.getmTitle());
        bdl2.putString("ITProDesc", (String)itProItem.getmDescription());
        fragment2.setArguments(bdl2);
        fragment2.show(getChildFragmentManager(), "tag");

    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        boolean result = super.onCreateOptionsMenu(menu);
//        //デフォルトではアイテムをつかした順番通りに表示する。
//        menu.add(0, MENU_ITEM_RELOAD, 0, "更新");
//        return result;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_ITEM_RELOAD:
                //アダプタを初期化し、タスクを起動する。
                mITProItems = new ArrayList<ITProItem>();
                mAdapter = new ITProRssListAdapter(getActivity().getApplicationContext(), mITProItems);
                //タスクはその都度生成する。
                ITProRssParserTask task = new ITProRssParserTask(ITProRssReader.this, mAdapter);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
