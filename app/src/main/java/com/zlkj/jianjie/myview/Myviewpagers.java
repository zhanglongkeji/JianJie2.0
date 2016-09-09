package com.zlkj.jianjie.myview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

import java.util.Timer;
import java.util.TimerTask;

public class Myviewpagers extends Gallery {

	private int a = 0;
	private static final int timerAnimation = 1;
	private final Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case timerAnimation:
				int position = getSelectedItemPosition();

				if (a >= (getCount() - 1)) {
					onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
					a++;
					if (a == (getCount() - 1) * 2) {
						a = 0;
					}
				} else {
					onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
					a++;
					break;
				}
				break;

			default:
				break;
			}
		};
	};

	private final Timer timer = new Timer();
	private final TimerTask task = new TimerTask() {
		public void run() {
			mHandler.sendEmptyMessage(timerAnimation);
		}
	};

	public Myviewpagers(Context paramContext) {
		super(paramContext);
		timer.schedule(task, 3000, 3000);
	}

	public Myviewpagers(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		timer.schedule(task, 3000, 3000);

	}

	public Myviewpagers(Context paramContext, AttributeSet paramAttributeSet,
						int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		timer.schedule(task, 3000, 3000);

	}

	private boolean isScrollingLeft(MotionEvent paramMotionEvent1,
			MotionEvent paramMotionEvent2) {
		float f2 = paramMotionEvent2.getX();
		float f1 = paramMotionEvent1.getX();
		if (f2 > f1)
			return true;
		return false;
	}

	public boolean onFling(MotionEvent paramMotionEvent1,
			MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2) {
		int keyCode;
		if (isScrollingLeft(paramMotionEvent1, paramMotionEvent2)) {
			keyCode = KeyEvent.KEYCODE_DPAD_LEFT;
		} else {
			keyCode = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(keyCode, null);
		return true;
	}

	public void destroy() {
		timer.cancel();
	}
}