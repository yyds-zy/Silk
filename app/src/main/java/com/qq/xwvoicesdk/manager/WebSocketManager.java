package com.qq.xwvoicesdk.manager;

import static java.lang.Thread.sleep;

import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.qq.xwvoicesdk.Constant.ConstantParams;
import com.qq.xwvoicesdk.bean.AuthDeviceBean;
import com.qq.xwvoicesdk.bean.BindDeviceBean;
import com.qq.xwvoicesdk.bean.GetDeviceInfoBean;
import com.qq.xwvoicesdk.bean.HeartBeatBean;
import com.qq.xwvoicesdk.bean.ResponseBean;
import com.qq.xwvoicesdk.bean.SpeechBean;
import com.qq.xwvoicesdk.bean.UnBindDeviceBean;
import com.qq.xwvoicesdk.util.ByteToStringUtil;
import com.qq.xwvoicesdk.util.MD5;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/14
 * Describe:
 */
public class WebSocketManager {

    public static final String TAG = "WebSocketManager";
    private static WebSocketManager instance;
    private Handler mWebSocketHandler = new Handler();
    private WebSocketManager(){}
    private WebSocket mWebSocket;
    private OkHttpClient mOkHttpClient;
    private String mToken = "";
    private String mRequestId = MD5.get32MD5(System.currentTimeMillis());

    public Handler getWebSocketHandler() {
        if (mWebSocketHandler == null) {
            mWebSocketHandler = new Handler();
        }
        return mWebSocketHandler;
    }

    public static WebSocketManager getInstance() {
        if (instance == null) {
            synchronized (WebSocketManager.class) {
                if (instance == null) {
                    instance = new WebSocketManager();
                }
            }
        }
        return instance;
    }

    //创建WebSocket长链接 且 认证设备
    public void webSocketConnect() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(ConstantParams.HOST)
                .build();
        ClientWebSocketListener listener = new ClientWebSocketListener();
        mOkHttpClient.newWebSocket(request,listener);
        mOkHttpClient.dispatcher().executorService().shutdown();
    }

    private final class ClientWebSocketListener extends WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            mWebSocket = webSocket;
            sendAuthDevice();
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            Log.d(TAG,"mWebSocket onMessage = " + text);
            ParseDataManager.getInstance().parseData(text);
            Message message = Message.obtain();
            message.obj = text;
            mWebSocketHandler.sendMessage(message);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            Message message = Message.obtain();
            message.obj = bytes.utf8();
            mWebSocketHandler.sendMessage(message);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            Log.d(TAG,"mWebSocket onClosing = " + reason + " code := " + code);
            if(null != mWebSocket){
                mWebSocket.close(1000,"close web socket");
                mWebSocket=null;
            }
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            Log.d(TAG,"mWebSocket onClosed -- " + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t,  Response response) {
            if (response == null) return;
            Log.d(TAG,"mWebSocket onFailure -- " + response.toString());
        }
    }

    /**
     * 发送语音请求
     * @param bytes
     * @param voiceSeq
     * @param isEndVoice
     * @param voiceLength
     * @param seq
     * @return
     */
    public boolean sendSpeech(byte[] bytes,int voiceSeq,boolean isEndVoice,int voiceLength,int seq) {
        if (mWebSocket == null) {
            Log.d(TAG,"mWebSocket is null");
            return false;
        }
        String speechOrder = new SpeechBean(mRequestId).toSpeechJson(bytes, voiceSeq, isEndVoice, voiceLength, seq);
        return mWebSocket.send(speechOrder);
    }

    /**
     * 刷新Token
     * @return
     */
    public boolean sendAuthDevice() {
        if (mWebSocket == null) {
            Log.d(TAG,"mWebSocket is null");
            return false;
        }
        String authDeviceOrder = new AuthDeviceBean().toAuthDeviceJson();
        return mWebSocket.send(authDeviceOrder);
    }

    /**
     * 心跳服务
     * @return
     */
    public boolean sendHeartBeat() {
        if (mWebSocket == null) {
            Log.d(TAG,"mWebSocket is null");
            return false;
        }
        String heartBeatOrder = HeartBeatBean.getInstance().toHeartBeatJson();
        return mWebSocket.send(heartBeatOrder);
    }

    /**
     * 绑定设备
     * @param code
     * @return
     */
    public boolean sendBindDevice(String code) {
        if (mWebSocket == null) {
            Log.d(TAG,"mWebSocket is null");
            return false;
        }
        String bindDeviceOrder = new BindDeviceBean().toBindDeviceJson(code);
        return mWebSocket.send(bindDeviceOrder);
    }

    /**
     * 获取设备信息
     * @return
     */
    public boolean sendGetDeviceInfo() {
        if (mWebSocket == null) {
            Log.d(TAG,"mWebSocket is null");
            return false;
        }
        String getDeviceInfoJson = new GetDeviceInfoBean().toGetDeviceInfoJson();
        return mWebSocket.send(getDeviceInfoJson);
    }

    /**
     * 解绑设备
     * @param binderId
     * @return
     */
    public boolean sendUnBindDevice(String binderId) {
        if (mWebSocket == null) {
            Log.d(TAG,"mWebSocket is null");
            return false;
        }
        String toUnDeviceJson = new UnBindDeviceBean().toUnBindDeviceJson(binderId);
        return mWebSocket.send(toUnDeviceJson);
    }

    public void setToken(String token) {
        mToken = token;
    }

    public String getToken() {
        return mToken;
    }

    public String getRequestId() {
        return mRequestId;
    }

    public void setRequestId(String requestId) {
        mRequestId = requestId;
    }
}
