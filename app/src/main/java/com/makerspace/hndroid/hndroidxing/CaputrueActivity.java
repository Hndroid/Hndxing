package com.makerspace.hndroid.hndroidxing;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.widget.Toast;

import com.makerspace.hndroid.hxing.camera.CameraManager;
import com.makerspace.hndroid.hxing.hxingActivity.BarCodeActivity;
import com.makerspace.hndroid.hxing.listener.OnBarcodeImageChoseListener;
import com.makerspace.hndroid.hxing.listener.OnResultListener;
import com.makerspace.hndroid.hxing.view.ViewfinderView;

public class CaputrueActivity extends BarCodeActivity {

    private Boolean isLight = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setOnBarcodeImageChoseListener(new OnBarcodeImageChoseListener() {
            @Override
            public void onFail() {

            }

            @Override
            public void onSuccess(String resultContext, String barcodeFormatName) {
                Toast.makeText(CaputrueActivity.this, resultContext, Toast.LENGTH_SHORT).show();
            }
        });

        this.setOnBarCodeResultListener(new OnResultListener() {
            @Override
            public void onResult(String resultContext, String barcodeFormatName) {
                Toast.makeText(CaputrueActivity.this, resultContext, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_select_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_front_light_mode:
                if (!isLight) {
                    CameraManager.get().turnLightOn();
                    isLight = true;
                } else {
                    CameraManager.get().turnLightOff();
                    isLight = false;
                }
                return true;
            case R.id.menu_image:
                getBarcodeImage("选择二维码图片");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int provideContentView() {
        return R.layout.activity_caputrue_layout;
    }

    @Override
    protected SurfaceView setSurfaceViewInstance() {
        return findViewById(R.id.preview_view);
    }

    @Override
    protected ViewfinderView setViewfinderViewInstance() {
        return findViewById(R.id.viewfinder_view);
    }

}
