package com.example.pc.news_readerrss;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.widget.ListView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    ListView lv2;

    ArrayList<News> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        lv2 = (ListView) findViewById(R.id.lv2);

        String url = getIntent().getStringExtra("url");

        new NewsFeed().execute(url);

    }

    class NewsFeed extends AsyncTask<String,Void,Void>
    {
        ProgressDialog pd = new ProgressDialog(NewsActivity.this);
        @Override
        protected void onPreExecute() {

            pd.setMessage("please wait");
            pd.show();
        }

        @Override
        protected Void doInBackground(String... strings) {

                InputStream is = network(strings[0]);
                parsing(is);


            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            CustomAdapter adapter= new CustomAdapter(NewsActivity.this,
                    newsList);

            lv2.setAdapter(adapter);
        }
    }

    InputStream network(String url)
    {
        try {
            URL u = new URL(url);
            HttpURLConnection con = (HttpURLConnection)u.openConnection();
            return con.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;

    }

    void parsing(InputStream is)
    {
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(is,"utf-8");

            int event = parser.getEventType();

            News news=null;
            while(event!=XmlPullParser.END_DOCUMENT)
            {
                String tag = parser.getName();

                if(event == XmlPullParser.START_TAG)
                {
                    if (tag.equals("item")&& news==null) {
                        news = new News(null, null, null, null);
                    }else if (tag.equals("title") && news != null) {
                        String title = parser.nextText();
                        news.setTitle(title);
                    }else if (tag.equals("description") && news!= null) {
                        String description = parser.nextText();
                        try {
                            int startindex = description.lastIndexOf("https://");
                            int endindex = description.lastIndexOf(".cms")+4;

                            String image = description.substring(startindex, endindex);
                            news.setImage(image);

                            int ds = description.indexOf("</a>") + 4;
                            description = description.substring(ds);

                        } catch (Exception e) {

                        }
                        news.setDescription(description);
                    }   else if (tag.equals("pubDate") && news != null) {
                        String pubdate = parser.nextText();
                        news.setPubdates(pubdate);
                        newsList.add(news);
                        news = null;
                    }
                }
                event = parser.next();
            }
            is.close();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
