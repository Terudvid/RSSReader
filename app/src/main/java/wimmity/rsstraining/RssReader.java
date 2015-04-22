package wimmity.rsstraining;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by teru123123 on 15/04/21.
 */
public class RssReader extends ListActivity {

    private static final String RSS_FEED_URL = "http://itpro.nikkeibp.co.jp/rss/ITpro.rdf";
    public static final int MENU_ITEM_RELOAD = Menu.FIRST;
    private RssListAdapter mAdapter;
    private ArrayList<Item> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItems = new ArrayList<Item>();
        mAdapter = new RssListAdapter(this, mItems);

        //タスクを起動する。
        RssParserTask task = new RssParserTask(this, mAdapter);
        task.execute(RSS_FEED_URL);
    }

    //リスト項目を洗濯した時の処理
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Item item = mItems.get(position);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TITLE", item.getmTitle());
        intent.putExtra("DESCRIPTION", item.getmDescription());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        //デフォルトではアイテムをつかした順番通りに表示する。
        menu.add(0, MENU_ITEM_RELOAD, 0, "更新");
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_ITEM_RELOAD:
                //アダプタを初期化し、タスクを起動する。
                mItems = new ArrayList<Item>();
                mAdapter = new RssListAdapter(this, mItems);
                //タスクはその都度生成する。
                RssParserTask task = new RssParserTask(this, mAdapter);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
