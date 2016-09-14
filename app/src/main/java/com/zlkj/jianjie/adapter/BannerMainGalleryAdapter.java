package com.zlkj.jianjie.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zlkj.jianjie.R;
import com.zlkj.jianjie.imageload.ImageLoader;

import java.util.ArrayList;

public class BannerMainGalleryAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<String> arraylist;

	private ImageLoader loader;


	public BannerMainGalleryAdapter(Context conn, ArrayList<String> arrlist) {
		context=conn;
		arraylist=arrlist;
		loader=new ImageLoader(conn);
	}

	@Override
	public int getCount() {

		return arraylist.size();
	}

	@Override
	public String getItem(int arg0) {

		return arraylist.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {

		return arg0;
	}	

	@Override
	public View getView(int positon, View contentView, ViewGroup arg2) {
		if(contentView==null){
			contentView=LayoutInflater.from(context).inflate(R.layout.homepage_fragment_viewpage_item, null);
		}

		SimpleDraweeView imageView = (SimpleDraweeView) contentView.findViewById(R.id.viewpage_item_img);
		
		//loader.DisplayImage(getItem(positon), imageView, R.mipmap.my_touming_ceng, false);
		Uri imageuri=Uri.parse(getItem(positon));
		imageView.setImageURI(imageuri);

		return contentView;
	}

}
