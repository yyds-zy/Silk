package com.qq.xwvoicesdk.manager;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/14
 * Describe:
 */
import android.content.Context;
import android.util.Log;

import com.qq.xwvoicesdk.record.AudioRecorder;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecordDataManager {

    private static final String TAG = "RecordDataManager";
    private Context mContext;
    private AudioRecorder mAudioRecorder;

    private ArrayList<byte[]> dataList = new ArrayList<>();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private RecordRunnable mRecordRunnable;
    private boolean isRecording = false;
    private boolean mBleRecording = false;
    private RecordDataListener mListener;

    public RecordDataManager(Context context, RecordDataListener listener) {
        mContext = context;
        mListener = listener;
        mRecordRunnable = new RecordRunnable();
    }

    public interface RecordDataListener {
        void onData(byte[] data);
    }

    public void startRecord() {
        if (!isRecording) {
            Log.d(TAG, "startRecord");
            isRecording = true;
            if (mAudioRecorder == null) {
                mAudioRecorder = new AudioRecorder(mContext);
            }
            mAudioRecorder.startRecord();
            executorService.execute(mRecordRunnable);
        }
    }

    public void stopRecord() {
        if (isRecording) {
            Log.d(TAG, "stopRecord");
            if (mAudioRecorder != null) {
                mAudioRecorder.stopRecord();
                mAudioRecorder.destroyRecord();
                mAudioRecorder = null;
            }
            isRecording = false;
        }
    }

    public void destoryRecord() {
        if (mAudioRecorder != null) {
            mAudioRecorder.destroyRecord();
            mAudioRecorder = null;
        }
        executorService.shutdownNow();
        isRecording = false;
    }

    public boolean isRecording(){
        return isRecording || mBleRecording;
    }

    public boolean isBleRecording() { return mBleRecording;}

    public void startBleHeadSetRecord() {
        mBleRecording = true;
        synchronized (dataList) {
            dataList.clear();
        }
    }

    public void stopBleHeadSetRecord() {
        synchronized (dataList) {
            byte[] fakeData = new byte[720];
            dataList.add(fakeData);
            notifyData();
        }
        mBleRecording = false;
    }


    public void recordBleDataNotify(byte[] decodedByteData) {
        if (decodedByteData != null) {
            synchronized (dataList) {
                dataList.add(decodedByteData);
            }
        }
        synchronized (dataList) {
            if (dataList.size() >= 5) {
                notifyData();
            }
        }
    }

    private void notifyData() {
        if (mListener != null) {
            synchronized (dataList) {
                int len = 0;
                for (byte[] srcArray : dataList) {
                    len += srcArray.length;
                }
                byte[] destArray = new byte[len];
                int destLen = 0;
                for (byte[] srcArray : dataList) {
                    System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
                    destLen += srcArray.length;
                }
                dataList.clear();
                mListener.onData(destArray);
            }
        }
    }

    private class RecordRunnable implements Runnable {
        @Override
        public void run() {
            while (isRecording) {
                //when ble Recording skip AudioRecorder read
                if (!mBleRecording) {
                    byte[] data = mAudioRecorder.getData();
                    synchronized (dataList) {
                        if (data != null) {
                            dataList.add(data);
                        }
                        if (dataList.size() >= 5) {
                            notifyData();
                        }
                    }
                }
            }
        }
    }
}

