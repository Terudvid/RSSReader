package wimmity.rsstraining;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import wimmity.rsstraining.View.SlidingTabLayout;


public class MainActivity extends ActionBarActivity {


    private SlidingTabLayout slidingTabLayout;
    private ViewPager mViewPager;
    private CustomFragmentAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       mAdapter = new CustomFragmentAdapter(getSupportFragmentManager());

       mViewPager = (ViewPager)findViewById(R.id.viewpager);
       mViewPager.setAdapter(mAdapter);


        //SlidingTabの実装
       slidingTabLayout = (SlidingTabLayout)findViewById(R.id.slidingtab);
       slidingTabLayout.setDistributeEvenly(true);
       slidingTabLayout.setViewPager(mViewPager);
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
