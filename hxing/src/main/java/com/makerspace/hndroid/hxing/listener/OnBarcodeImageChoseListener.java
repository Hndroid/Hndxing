package com.makerspace.hndroid.hxing.listener;

/**
 * Date： 18-2-7
 * Author： null
 * Description：相册选择图片监听类
 */

public interface OnBarcodeImageChoseListener {
    void onFail();
    void onSuccess(String resultContext, String barcodeFormatName);
}
