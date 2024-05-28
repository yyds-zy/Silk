package com.qq.xwvoicesdk.util;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/14
 * Describe:
 */
public class AudioFocusUtils {
    private static String TAG = "AudioFocusUtils";
    //音频焦点管理
    private static AudioManager audioManager;
    private static AudioFocusRequest audioFocusRequest;
    private static volatile Boolean audioGain = false;

    /**
     * 初始化音频焦点
     *
     * @param context 上下文
     */
    public static void initAudioFocus(Context context) {
        if (audioManager == null && !audioGain) {
            int r = 0;
            //音频焦点
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            //android8.0之后申请焦点方式
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //AudioAttributes 配置(多媒体场景，申请的是音乐流)
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build();
                // 初始化AudioFocusRequest
                audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                        .setAudioAttributes(audioAttributes)
                        //设置是否允许延迟获取焦点
                        .setAcceptsDelayedFocusGain(true)
                        //设置AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK会暂停，系统不会压低声音
                        .setWillPauseWhenDucked(true)
                        //设置焦点监听回调
                        .setOnAudioFocusChangeListener(onAudioFocusChangeListener)
                        .build();
                //申请焦点
                r = audioManager.requestAudioFocus(audioFocusRequest);
            } else {//android8.0之前申请焦点方式
                r = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            }
            if (r == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                audioGain = true;
                Log.d(TAG, "initAudioFocus = " + audioGain);
            }
        }
    }

    /**
     * 注销音频焦点
     */
    public static void abandonAudioFocus() {
        if (audioManager != null && audioGain) {
            int r = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // 针对于Android 8.0+
                if (audioFocusRequest != null) {
                    r = audioManager.abandonAudioFocusRequest(audioFocusRequest);
                }
            } else {
                // 小于Android 8.0
                r = audioManager.abandonAudioFocus(onAudioFocusChangeListener);
            }

            if (r == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                audioGain = false;
                audioFocusRequest = null;
                audioManager = null;
                Log.d(TAG, "abandonAudioFocus audioGain = " + audioGain);
            }
        }
    }

    /**
     * 音频焦点监听
     */
    private static AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                //失去音频焦点
                case AudioManager.AUDIOFOCUS_LOSS:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    audioGain = false;
                    audioFocusRequest = null;
                    audioManager = null;
                    if (onAudioFocusListener != null) {
                        onAudioFocusListener.onLossAudioFocus(focusChange);
                    }
                    Log.d(TAG, "OnAudioFocusChange audioGain = " + audioGain);
                    break;
                //重新获取音频焦点
                case AudioManager.AUDIOFOCUS_GAIN:
                    audioGain = true;
                    if (onAudioFocusListener != null) {
                        onAudioFocusListener.onGainAudioFocus();
                    }
                    Log.d(TAG, "OnAudioFocusChange audioGain = " + audioGain);
                    break;
            }
        }
    };

    public static Boolean getAudioGainState() {
        return audioGain;
    }

    private static OnAudioFocusListener onAudioFocusListener;

    public static void setOnAudioFocusListener(OnAudioFocusListener onAudioFocusListener) {
        AudioFocusUtils.onAudioFocusListener = onAudioFocusListener;
    }

    public interface OnAudioFocusListener {
        //失去焦点
        void onLossAudioFocus(int focusChange);

        //获取焦点
        void onGainAudioFocus();
    }
}
