package wimmity.rsstraining.NikkeMedical;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import wimmity.rsstraining.accounts.MedicalItem;

/**
 * Created by teru123123 on 15/05/12.
 */
public class MedicalRssParserTask extends AsyncTask<String, Integer, MedicalRssListAdapter> {
    private MedicalRssReader mActivity;
    private MedicalRssListAdapter mAdapter;
    private ProgressDialog mDialog;
    //defalut
    public MedicalRssParserTask(MedicalRssReader activity, MedicalRssListAdapter adapter){
        this.mActivity = activity;
        this.mAdapter = adapter;
    }

    // this cann't solved because 'transaction' can happen runtime
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
////         mDialog = new ProgressDialog(mActivity.getActivity().getApplicationContext());
////         mDialog.setMessage("nowLooding");
//         mDialog.show();
//    }

    @Override
    protected MedicalRssListAdapter doInBackground(String... params) {
        MedicalRssListAdapter result = null;

        try{
            URL url = new URL(params[0]);
            InputStream is = url.openConnection().getInputStream();
            result = parseXML(is);

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    @Override
    protected void onPostExecute(MedicalRssListAdapter result) {
//        mProgressDialog.dismiss();
//        mDialog = mProgressDialog.get();
//        mDialog.dismiss();
        mActivity.setListAdapter(result);
    }

    private MedicalRssListAdapter parseXML(InputStream is) throws IOException, XmlPullParserException{
        XmlPullParser parser = Xml.newPullParser();

        try{
            parser.setInput(is, null);
            int eventType = parser.getEventType();
            MedicalItem currentMedical = null;

            while (eventType != XmlPullParser.END_DOCUMENT){
                String tag = null;
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.equals("item")){
                            currentMedical = new MedicalItem();
                        } else  if(currentMedical != null){
                            if (tag.equals("title")){
                                currentMedical.setTitle_m(parser.nextText());
                            }else if (tag.equals("description")){
                                currentMedical.setDiscription(parser.nextText());
                            }
                        }
                     break;
                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        if (tag.equals("item")){
                            mAdapter.add(currentMedical);
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
