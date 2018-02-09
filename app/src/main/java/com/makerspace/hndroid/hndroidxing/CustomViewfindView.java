package com.makerspace.hndroid.hndroidxing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.transition.Slide;
import android.util.AttributeSet;

import com.makerspace.hndroid.hxing.camera.CameraManager;
import com.makerspace.hndroid.hxing.view.ViewfinderView;

/**
 * Date： 18-2-7
 * Author： null
 * Description：
 */

public class CustomViewfindView extends ViewfinderView {

    private final int CORNER_WIDTH;
    private Paint mPaint;
    private static float density;
    private static final int CORNER_HEIGH = 6;
    private boolean isFirst;
    private int slideTop;
    private int slideBottom;
    private static final int HEARTBEAT = 5;


    public CustomViewfindView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();

        density = context.getResources().getDisplayMetrics().density;
        CORNER_WIDTH = (int) (15 * density);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect frame = CameraManager.get().getFramingRect();

        if (frame == null) {
            return;
        }

        if (!isFirst) {
            isFirst = true;
            slideTop = frame.top;
            slideBottom = frame.bottom;
        }
        mPaint.setColor(Color.BLUE);

        canvas.drawRect(frame.left, frame.top, frame.left + CORNER_WIDTH, frame.top + CORNER_HEIGH, mPaint);
        canvas.drawRect(frame.left, frame.top, frame.left + CORNER_HEIGH, frame.top + CORNER_WIDTH, mPaint);
        canvas.drawRect(frame.right - CORNER_HEIGH, frame.top, frame.right, frame.top + CORNER_WIDTH, mPaint);
        canvas.drawRect(frame.right - CORNER_WIDTH, frame.top, frame.right, frame.top + CORNER_HEIGH, mPaint);
        canvas.drawRect(frame.left, frame.bottom, frame.left + CORNER_WIDTH, frame.bottom + CORNER_HEIGH, mPaint);
        canvas.drawRect(frame.left, frame.bottom - CORNER_WIDTH, frame.left + CORNER_HEIGH, frame.bottom, mPaint);
        canvas.drawRect(frame.right - CORNER_WIDTH, frame.bottom - CORNER_HEIGH, frame.right, frame.bottom, mPaint);
        canvas.drawRect(frame.right - CORNER_HEIGH, frame.bottom - CORNER_WIDTH, frame.right, frame.bottom, mPaint);


        slideTop += HEARTBEAT;
        if (slideTop >= slideBottom) {
            slideTop = frame.top;
        }

        Rect lineRect = new Rect();
        lineRect.left = frame.left;
        lineRect.right = frame.right;
        lineRect.top = slideTop;
        lineRect.bottom = slideTop + 18;
        canvas.drawBitmap(((BitmapDrawable) (getResources()
                        .getDrawable(R.drawable.fle))).getBitmap(), null, lineRect,
                mPaint);
    }


}
