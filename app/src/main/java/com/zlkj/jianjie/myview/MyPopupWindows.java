package com.zlkj.jianjie.myview;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;



public class MyPopupWindows extends PopupWindow {
	private View mview;
	private MyPopupListeners listener;
	private View AutoDismissView;

	public MyPopupWindows(Context context, View myview, MyPopupListeners listener) {
		super(context);
		this.listener = listener;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//mview = inflater.inflate(layoutId, null);
		mview=myview;
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setContentView(mview);
		//防止虚拟软键盘被弹出菜单遮住

		this.setFocusable(true);
		this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		this.setBackgroundDrawable(new BitmapDrawable());
		
		
	}

	public void setAutoDisMisss(int viewID) {
		try {
			AutoDismissView = mview.findViewById(viewID);
		}
		catch (Exception e) {
			AutoDismissView = null;
		}
		if (null != AutoDismissView) {
			mview.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int x = (int) event.getX();
					int y = (int) event.getY();
					if (event.getAction() == MotionEvent.ACTION_UP) {
						int top = AutoDismissView.getTop();
						int buttom = AutoDismissView.getBottom();
						int left = AutoDismissView.getLeft();
						int right = AutoDismissView.getRight();

						if (x < left || x > right || y < top || y > buttom) {
							dismiss();
						}
					}
					return true;
				}
			});
		}

	}

	public void setTextForTextView(int textviewID, String title) {
		TextView textview = null;
		try {
			textview = (TextView) mview.findViewById(textviewID);
		}
		catch (Exception e) {
			textview = null;
		}
		if (null != textview && null != title) {
			textview.setText(title);
		}
	}
	
	
	 EditText editview = null;
	public void setEditForTextView(int textviewID) {
       
        try {
            editview = (EditText) mview.findViewById(textviewID);
        }
        catch (Exception e) {
            editview = null;
        }
    }
	public String getEditForTextView() {
        return editview.getText().toString();
    }
	

	public void setTextForButton(int ButtonID, String title) {
		Button button = null;
		try {
			button = (Button) mview.findViewById(ButtonID);
		}
		catch (Exception e) {
			button = null;
		}
		if (null != button && null != title) {
			button.setText(title);
		}
	}
	

	public void setBackground(int bgcolor) {
		ColorDrawable dw = new ColorDrawable(bgcolor);
		
		this.setBackgroundDrawable(dw);
	}

	public void setListener(int viewid) {
		View view = null;
		try {
			view = mview.findViewById(viewid);
		}
		catch (Exception e) {
			view = null;
		}
		if (view != null) {
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					dismiss();
					if (null != listener) {
						listener.OnPopItemSelect(v);
					}
				}
			});
		}

	}

	/**
	 * 设置点击空白取消popuwindows
	 * @param viewid
	 */
	public void setListenerCancle(int viewid) {
		View view = null;
		try {
			view = mview.findViewById(viewid);
		}
		catch (Exception e) {
			view = null;
		}
		if (view != null) {
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					dismiss();
				}
			});
		}
	}
}
