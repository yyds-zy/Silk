package com.qq.xwvoicesdk.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qq.xwvoicesdk.Constant.ConstantParams;
import com.qq.xwvoicesdk.R;
import com.qq.xwvoicesdk.app.TencentDeviceApp;
import com.qq.xwvoicesdk.bean.ProductBean;
import com.qq.xwvoicesdk.listener.WebSocketResponseListener;
import com.qq.xwvoicesdk.manager.ParseDataManager;
import com.qq.xwvoicesdk.manager.RecordDataManager;
import com.qq.xwvoicesdk.manager.WebSocketManager;
import com.qq.xwvoicesdk.util.AudioFocusUtils;
import com.qq.xwvoicesdk.util.MD5;
import com.qq.xwvoicesdk.util.MediaHelper;
import com.qq.xwvoicesdk.util.SharedPreferenceUtil;
import com.tencent.xwappsdk.silk.Silk;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/14
 * Describe:
 */
public class RecorderActivity extends AppCompatActivity implements WebSocketResponseListener {

    public static final String TAG = "RecorderActivity";

    private static final int REQUEST_CODE = 1;
    private static String[] PERMISSIONS = {"android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE","android.permission.RECORD_AUDIO"};

    private RecordDataManager mRecordDataManager;
    private Object mRecordObject = new Object();
    private volatile boolean mXwRecording;   //正在录音
    private volatile boolean mXwRecordEnd;   //结束录音
    private volatile boolean mIsCancelRecord;//取消录音

    private boolean mIsSilkInit;
    private int mVoiceSeq = 0;
    private int mSeq;

    private Button mVoiceBtn, mUnbindDeviceBtn;
    private TextView mAsrTv, mResponseTv,mSnTv;

    private void checkPermission() {
        for(String permission:PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(TencentDeviceApp.getAppContent(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    Toast.makeText(TencentDeviceApp.getAppContent(), getString(R.string.please_open_permissions), Toast.LENGTH_SHORT).show();
                }
                ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE);
            } else {
                Toast.makeText(TencentDeviceApp.getAppContent(), getString(R.string.already_auth_success), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(TencentDeviceApp.getAppContent(), getString(R.string.auth_success), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TencentDeviceApp.getAppContent(), getString(R.string.auth_fail), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);
        checkPermission();
        initRecordAndVRL();
        initView();
        initData();
    }

    private void initData() {
        ParseDataManager.getInstance().setWebSocketResponseListener(this);
        WebSocketManager.getInstance().sendGetDeviceInfo();

        String productInfo = SharedPreferenceUtil.getInstance().getString(SharedPreferenceUtil.PRODUCT_INFO, SharedPreferenceUtil.ATTR);
        if (productInfo != null) {
            ProductBean productBean = new Gson().fromJson(productInfo, ProductBean.class);
            int app_uin = productBean.getData().getProduct_info().getApp_uin();
            if (app_uin == ConstantParams.app_uin) {
                mSnTv.setText("设备名: " + productBean.getData().getProduct_info().getDevice_name());
            }
        }
    }

    private void initView() {
        mVoiceBtn = findViewById(R.id.voiceBtn);
        mUnbindDeviceBtn = findViewById(R.id.unbind_device_btn);
        mAsrTv = findViewById(R.id.asr_tv);
        mResponseTv = findViewById(R.id.response_tv);
        mSnTv = findViewById(R.id.sn_tv);

        mVoiceBtn.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                mVoiceBtn.setText(getString(R.string.start_record));
                stopRecord();
                mVoiceBtn.setBackground(getDrawable(R.drawable.voice_btn_bg_normal));
            }
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                mVoiceBtn.setText(getString(R.string.recording));
                startRecord();
                mVoiceBtn.setBackground(getDrawable(R.drawable.voice_btn_bg_selected));
            }
            return false;
        });

        mVoiceBtn.setOnFocusChangeListener((view, hasFocus) -> {
            if (mVoiceBtn.isSelected()) {
                if (hasFocus) {
                    mVoiceBtn.setBackground(getDrawable(R.drawable.voice_btn_bg_selected));
                    mVoiceBtn.animate().setInterpolator(new OvershootInterpolator()).scaleX(1.1f).scaleY(1.1f).setDuration(300).start();
                } else {
                    mVoiceBtn.setBackground(getDrawable(R.drawable.voice_btn_bg_normal));
                    mVoiceBtn.animate().setInterpolator(new OvershootInterpolator()).scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                }
            } else {
                if (hasFocus) {
                    mVoiceBtn.setBackground(getDrawable(R.drawable.voice_btn_bg_selected));
                    mVoiceBtn.animate().setInterpolator(new OvershootInterpolator()).scaleX(1.1f).scaleY(1.1f).setDuration(300).start();
                } else {
                    mVoiceBtn.setBackground(getDrawable(R.drawable.voice_btn_bg_normal));
                    mVoiceBtn.animate().setInterpolator(new OvershootInterpolator()).scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                }
            }
        });

        mUnbindDeviceBtn.setOnClickListener(view -> {
            String productInfo = SharedPreferenceUtil.getInstance().getString(SharedPreferenceUtil.PRODUCT_INFO, SharedPreferenceUtil.ATTR);
            if (productInfo != null) {
                ProductBean productBean = new Gson().fromJson(productInfo, ProductBean.class);
                int app_uin = productBean.getData().getProduct_info().getApp_uin();
                if (app_uin == ConstantParams.app_uin) {
                    WebSocketManager.getInstance().sendUnBindDevice(productBean.getData().getDevice_info().getBinder().get(0).getBinder_id());
                }
            }
        });
    }

    public void initRecordAndVRL() {
        if (mRecordDataManager == null) {
            mRecordDataManager = new RecordDataManager(TencentDeviceApp.getAppContent(), mRecordDataListener);
        }
    }

    private RecordDataManager.RecordDataListener mRecordDataListener = new RecordDataManager.RecordDataListener() {
        @Override
        public void onData(byte[] data) {
            synchronized (mRecordObject) {
                if (mXwRecording || mXwRecordEnd) {
//                    Log.d(TAG,  "sendSpeech" + "onData mXwRecording " + mXwRecording + " mXwRecordEnd " + mXwRecordEnd);
                    sendSpeech(data, mXwRecordEnd);
                    if (mXwRecordEnd) {
                        mXwRecordEnd = false;
                        mRecordDataManager.stopRecord();
                    }
                }
            }
        }
    };

    private boolean startRecord() {
        MediaHelper.getInstance().pause();
        requestAudioFocus();
        mRecordDataManager.startRecord();
        synchronized (mRecordObject) {
            if (mXwRecording || mXwRecordEnd) return false;
            mXwRecording = true;
            mIsCancelRecord = false;
        }
        return true;
    }

    public boolean stopRecord() {
        Log.d(TAG, "stopRecord");
        synchronized (mRecordObject) {
            if (mXwRecording) {
                mXwRecording = false;
                mXwRecordEnd = true;
            }
        }
        return true;
    }

    public boolean cancelRecord() {
        Log.d(TAG, "cancelRecord");
        if (mRecordDataManager != null && mRecordDataManager.isRecording()) {
            mIsCancelRecord = true;
            return stopRecord();
        }

        return true;
    }

    private void requestAudioFocus() {
        AudioFocusUtils.initAudioFocus(TencentDeviceApp.getAppContent());
    }


    public void sendSpeech(byte[] speech, boolean end) {
        if (speech == null) return;

        if (!end && !mIsSilkInit) {
            Silk.init();
            mIsSilkInit = true;
            Log.d(TAG,"init Silk");
        }

        byte[] silkData = Silk.encode(speech);

        if (silkData != null) {
            Log.d(TAG,silkData.length+"");
        }


        boolean sendSpeechState = WebSocketManager.getInstance().sendSpeech(silkData, mVoiceSeq, end, speech.length, mSeq++);
        Log.d(TAG,"send result -----  " + sendSpeechState);
        mVoiceSeq = mVoiceSeq + speech.length;

        if (end) {
            Silk.unInit();
            mIsSilkInit = false;
            mVoiceSeq = 0;
            mSeq = 0;
            Log.d(TAG,"uninit Silk");
            WebSocketManager.getInstance().setRequestId(MD5.get32MD5(System.currentTimeMillis()));
        }
    }

    @Override
    public void ori_question_text(String ori_question_text) {
        Log.d(TAG,ori_question_text);
        mAsrTv.setText(ori_question_text);
    }

    @Override
    public void xw_response(String response) {
        Log.d(TAG,response);
        mResponseTv.setText(response);
    }

    @Override
    public void tts(String url) {
        Log.d(TAG, url);
        if (url.indexOf(ConstantParams.tts_im_url) != -1) {
            MediaHelper.getInstance().playSound(url, onCompletionListener);
        }
    }

    @Override
    public void productInfo(String productInfo) {
        SharedPreferenceUtil.getInstance().putString(SharedPreferenceUtil.PRODUCT_INFO,SharedPreferenceUtil.ATTR,productInfo);
        if (productInfo != null) {
            ProductBean productBean = new Gson().fromJson(productInfo, ProductBean.class);
            int app_uin = productBean.getData().getProduct_info().getApp_uin();
            if (app_uin == ConstantParams.app_uin) {
                mSnTv.setText("设备名: " + productBean.getData().getProduct_info().getDevice_name());
            }
        }
    }

    @Override
    public void un_bind_device() {
        SharedPreferenceUtil.getInstance().putString(SharedPreferenceUtil.PRODUCT_INFO,SharedPreferenceUtil.ATTR,null);
        Intent intent = new Intent(this,BindDeviceActivity.class);
        startActivity(intent);
        finish();
    }

    MediaPlayer.OnCompletionListener onCompletionListener = mediaPlayer -> {
        Log.d(TAG,"play Completion");
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                if (mVoiceBtn.isFocused()) {
                    mVoiceBtn.setText(getString(R.string.start_record));
                    stopRecord();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                if (mVoiceBtn.isFocused()) {
                    mVoiceBtn.setText(getString(R.string.recording));
                    startRecord();
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}