package com.zlkj.jianjie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlkj.jianjie.R;

import java.util.List;

/**
 * Created by gulong on 2016/9/8.
 */
public class MySpinnerAdapter extends BaseAdapter {

    private List<String> mylist;
    private Context mContext;

    public MySpinnerAdapter(Context context,List<String> list){
        mContext=context;
        mylist=list;

    }

    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public String getItem(int position) {
        return mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.sprinner_item,null);
            holder=new ViewHolder();
            holder.mytedtt= (TextView) convertView.findViewById(R.id.mysprinner_textview);

            convertView.setTag(holder);

        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.mytedtt.setText(""+getItem(position));


        return convertView;
    }


    private class ViewHolder{
        TextView mytedtt;
    }

}
