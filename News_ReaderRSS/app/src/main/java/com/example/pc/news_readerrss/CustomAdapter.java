package com.example.pc.news_readerrss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by PC on 30-Jan-18.
 */

public class CustomAdapter extends BaseAdapter{
    Context context;
     ArrayList<News> news;
    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<News> news) {
        this.context = context;
        this.news = news;
         inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         View myView = inflater.inflate(R.layout.custom, null);

        TextView tvTitle = (TextView)myView.findViewById(R.id.tvTitle);
        ImageView imageView =(ImageView)myView.findViewById(R.id.imageView2);
        TextView tvDesc = (TextView) myView.findViewById(R.id.tvDescription);
        TextView tvpubDate = (TextView)myView.findViewById(R.id.tvPubdate);

        tvTitle.setText(news.get(position).getTitle());
        tvDesc.setText(news.get(position).getDescription());
        tvpubDate.setText(news.get(position).getPubdates());

        Picasso.with(context).load(news.get(position).getImage()).into(imageView);

        return myView;
    }
}
