package com.qq.xwvoicesdk.record;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.media.audiofx.AcousticEchoCanceler;
import android.util.Log;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/14
 * Describe:
 */
public class AudioRecorder {
    private static final String TAG = "AudioRecorder";
    private int mAudioSource = MediaRecorder.AudioSource.MIC;
    private int mSampleRateInHz = 16000;
    private int mChannelConfig = AudioFormat.CHANNEL_IN_MONO;
    private int mAudioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private int mBufferSizeInBytes;
    private AudioRecord mAudioRecorder;
    private boolean isRecording = false;
    private Context mContext;

    public AudioRecorder(Context context) {
        mContext = context;
        initRecorder();
    }

    public AudioRecorder(Context context, int sampleRate, int channel, int audioFormat) {
        mContext = context;
        mSampleRateInHz = sampleRate;
        mChannelConfig = channel;
        mAudioFormat = audioFormat;
        initRecorder();
    }

    private void initRecorder() {
        mBufferSizeInBytes = AudioRecord.getMinBufferSize(mSampleRateInHz, mChannelConfig, mAudioFormat);
        mAudioRecorder = new AudioRecord(mAudioSource, mSampleRateInHz, mChannelConfig, mAudioFormat, mBufferSizeInBytes);
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            if (AcousticEchoCanceler.isAvailable()) {
                AcousticEchoCanceler canceler = AcousticEchoCanceler.create(mAudioRecorder.getAudioSessionId());//回声消除
                if (canceler != null) {
                    canceler.setEnabled(true);
                }
            }
        }
    }

    public void startRecord() {
        Log.d(TAG, "startRecord");
        isRecording = true;
        // 开始录音
        try {
            mAudioRecorder.startRecording();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void stopRecord() {
        Log.d(TAG, "stopRecord");
        mAudioRecorder.stop();
        isRecording = false;
    }

    public void destroyRecord() {
        try {
            if (mAudioRecorder != null) {
                mAudioRecorder.stop();
                mAudioRecorder.release();
                mAudioRecorder = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getData() {
        byte buffer[] = new byte[mBufferSizeInBytes / 2];
        int read_len = mAudioRecorder.read(buffer, 0, buffer.length);
        if (read_len <= 0) {
            // 录音出错了,一般是被别的程序占用了麦克风
            //Log.d(TAG, "getData read_len = " + read_len);
            return null;
        }
        return buffer;
    }
}