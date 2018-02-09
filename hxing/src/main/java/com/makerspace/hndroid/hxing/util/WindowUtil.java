package com.makerspace.hndroid.hxing.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Date： 18-2-6
 * Author： Hndroid
 * Description： 获取手机屏幕的宽高
 */

public class WindowUtil {

	public static Activity currentActivity = null;

	/**
	 * 获得屏幕宽度
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static int getWindowWidthPix() {
		int ver = Build.VERSION.SDK_INT;
		Display display = currentActivity.getWindowManager()
				.getDefaultDisplay();
		int width = 0;
		if (ver < 13) {
			DisplayMetrics dm = new DisplayMetrics();
			display.getMetrics(dm);
			width = dm.widthPixels;
		} else {
			Point point = new Point();
			display.getSize(point);
			width = point.x;
		}
		return width;
	}

	/**
	 * 获得屏幕高度
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static int getWindowHeightPix() {
		int ver = Build.VERSION.SDK_INT;
		Display display = currentActivity.getWindowManager()
				.getDefaultDisplay();
		int height = 0;
		if (ver < 13) {
			DisplayMetrics dm = new DisplayMetrics();
			display.getMetrics(dm);
			height = dm.heightPixels;
		} else {
			Point point = new Point();
			display.getSize(point);
			height = point.y;
		}
		return height;
	}
}
