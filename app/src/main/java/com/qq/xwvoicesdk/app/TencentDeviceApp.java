package com.qq.xwvoicesdk.app;

import android.app.Application;
import android.content.Intent;

import com.qq.xwvoicesdk.service.WebSocketService;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/14
 * Describe:
 */
public class TencentDeviceApp extends Application {

    private static TencentDeviceApp mInstance;
    private Intent service;

    public static TencentDeviceApp getAppContent() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        service = new Intent(mInstance, WebSocketService.class);
        startService();
    }

    private void startService() {
        startService(service);
    }

    private void stopService() {
        stopService(service);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        stopService();
    }
}
