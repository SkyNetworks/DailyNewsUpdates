package com.example.pc.news_readerrss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lv;

    String[] catagory= {"Top Stories", "India", "Sports", "Entertainment", "Science"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lv);

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                catagory);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this,NewsActivity.class);
                switch (position)
                {
                    case 0 :
                        i.putExtra("url","https://timesofindia.indiatimes.com/rssfeedstopstories.cms");
                        break;
                    case 1 :
                        i.putExtra("url", "https://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms");
                        break;

                    case 2 :
                        i.putExtra("url", "https://timesofindia.indiatimes.com/rssfeeds/4719148.cms");
                        //i.putExtra("url", "https://timesofindia.indiatimes.com/rssfeeds/4719148.cms");
                        break;
                    case 3 :
                        i.putExtra("url", "https://timesofindia.indiatimes.com/rssfeeds/1081479906.cms");
                        break;
                    case 4 :
                        i.putExtra("url", "https://timesofindia.indiatimes.com/rssfeeds/-2128672765.cms");
                        break;
                }

                startActivity(i);
            }
        });
    }
}
