package com.tppa.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String productsList[];
    int images[];
    String details[];
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, String[] productsList, int[] images, String[] details) {
        this.context = applicationContext;
        this.productsList = productsList;
        this.images = images;
        this.details = details;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return productsList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public String getItemDetails(int i){
        return this.details[i];
    }

    public String getItemName(int i){
        return productsList[i];
    }

    public int getItemImage(int i){
        return images[i];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_list_view, null);
        TextView product = (TextView) view.findViewById(R.id.textView);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        product.setText(productsList[i]);
        icon.setImageResource(images[i]);
        return view;
    }
}