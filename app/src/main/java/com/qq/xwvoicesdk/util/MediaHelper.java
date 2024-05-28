package com.qq.xwvoicesdk.util;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/16
 * Describe:
 */
public class MediaHelper {
    private static MediaPlayer mPlayer;
    private static MediaHelper mInstance;
    private static boolean isPause = false;
    private MediaHelper(){}

    public static MediaHelper getInstance() {
        if (mInstance == null) {
            synchronized (MediaHelper.class){
                if (mInstance == null) {
                    mInstance = new MediaHelper();
                }
            }
        }
        return mInstance;
    }

    public void playSound(String filePath, MediaPlayer.OnCompletionListener listener) {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        } else {
            mPlayer.reset();
        }
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnCompletionListener(listener);
        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mPlayer.reset();
                return false;
            }
        });
        try {
            mPlayer.setDataSource(filePath);
            mPlayer.prepare();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException("read file exception：" + e.getMessage());
        }
        mPlayer.start();
        isPause = false;
    }
    public void pause() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            isPause = true;
        }
    }
    // 继续
    public void resume() {
        if (mPlayer != null && isPause) {
            mPlayer.start();
            isPause = false;
        }
    }
    public void release() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
