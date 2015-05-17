package wimmity.rsstraining.ITPro;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import wimmity.rsstraining.accounts.ITProItem;

/**
 * Created by teru123123 on 15/04/21.
 */
public class ITProRssParserTask extends AsyncTask<String, Integer, ITProRssListAdapter> {

    private ITProRssReader mActivity;
    private ITProRssListAdapter mAdapter;
    private ProgressDialog mProDia1;


    //コンストラクタ
    public ITProRssParserTask(ITProRssReader activity, ITProRssListAdapter adapter){
        this.mActivity = activity;
        this.mAdapter = adapter;
    }

    //タスクを実行した直後にコールされる

//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        mProDia1 = new ProgressDialog(mActivity.getActivity().getApplicationContext());
//        mProDia1.setProgress(ProgressDialog.STYLE_SPINNER);
//        mProDia1.setMessage("load");
//        mProDia1.show();
//    }

    //バックグラウンドにおける処理を担う。タスク実行時に渡された値を引数とする。
    @Override
    protected ITProRssListAdapter doInBackground(String... params) {
        ITProRssListAdapter result = null;

        try{
            //HTTP経由でアクセスし、InputStreamを取得する
            URL url = new URL(params[0]);
            InputStream is = url.openConnection().getInputStream();
            result = parseXML(is);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ITProRssListAdapter result) {
//        mProDia1.dismiss();
        mActivity.setListAdapter(result);
    }

    private ITProRssListAdapter parseXML(InputStream is) throws IOException, XmlPullParserException{
        XmlPullParser parser = Xml.newPullParser();
        try{
            parser.setInput(is, null);
            int eventType = parser.getEventType();
            ITProItem currentITProItem = null;

            while (eventType != XmlPullParser.END_DOCUMENT){
                String tag = null;
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.equals("item")){
                            currentITProItem = new ITProItem();
                        } else if (currentITProItem != null){
                            if(tag.equals("title")){
                                currentITProItem.setmTitle(parser.nextText());
                            } else if(tag.equals("description")){
                                currentITProItem.setmDescription(parser.nextText());
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        if (tag.equals("item")){
                            mAdapter.add(currentITProItem);
                        }

                        break;

                }
                eventType = parser.next();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return mAdapter;
    }
}
