package com.qq.xwvoicesdk.bean;

import android.util.Base64;
import android.util.Log;

import com.qq.xwvoicesdk.Constant.ConstantParams;
import com.qq.xwvoicesdk.manager.WebSocketManager;
import com.qq.xwvoicesdk.util.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/15
 * Describe:
 */
public class HeartBeatBean extends BaseJson{

    public static final String TAG = "HeartBeatBean";

    private static HeartBeatBean mInstance;
    private Timer hearTimer;

    public static HeartBeatBean getInstance() {
        if (mInstance == null) {
            synchronized (HeartBeatBean.class) {
                if (mInstance == null) {
                    mInstance = new HeartBeatBean();
                }
            }
        }
        return mInstance;
    }

    private HeartBeatBean(){
        super(ConstantParams.heartBeat, WebSocketManager.getInstance().getToken(), MD5.get32MD5(System.currentTimeMillis()));
    }

    public String toHeartBeatJson() {
        super.toJson();
        try {
            JSONObject jsonContent = new JSONObject();
            jsonContent.put("mstimestamp",System.currentTimeMillis());
            String contentBase64 = Base64.encodeToString(jsonContent.toString().getBytes(), Base64.NO_WRAP);
            jsonObject.put("content", contentBase64);
        } catch (JSONException e){
            e.printStackTrace();
        }
        Log.d(TAG,jsonObject.toString());
        return jsonObject.toString();
    }

    public void dataParse(JSONObject response){
        try {
            if (response.has("interval")) {
                if (hearTimer == null)
                    hearTimer = new Timer();
                hearTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        WebSocketManager.getInstance().sendHeartBeat();
                    }
                }, response.getInt("interval") * 1000L);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
