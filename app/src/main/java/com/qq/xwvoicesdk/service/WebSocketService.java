package com.qq.xwvoicesdk.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.qq.xwvoicesdk.listener.OnRefreshTokenListener;
import com.qq.xwvoicesdk.manager.ParseDataManager;
import com.qq.xwvoicesdk.manager.WebSocketManager;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/17
 * Describe:
 */
public class WebSocketService extends Service implements OnRefreshTokenListener {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseDataManager.getInstance().setOnRefreshTokenListener(this);
        //建立websocket链接
        WebSocketManager.getInstance().webSocketConnect();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void refreshToken(String token) {
        WebSocketManager.getInstance().setToken(token);
        WebSocketManager.getInstance().sendHeartBeat();
    }
}
