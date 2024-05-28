package com.qq.xwvoicesdk.bean;

import android.util.Base64;
import android.util.Log;

import com.qq.xwvoicesdk.Constant.ConstantParams;
import com.qq.xwvoicesdk.listener.WebSocketResponseListener;
import com.qq.xwvoicesdk.manager.WebSocketManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/15
 * Describe: 语音请求体
 */
public class SpeechBean extends BaseJson {

    public static final String TAG = "SpeechBean";

    public SpeechBean(String requestID){
        super(ConstantParams.speechPath, WebSocketManager.getInstance().getToken(),requestID);
    }

    /**
     * 语音请求结构体数据
     * @param bytes 语音数据
     * @param voiceSeq 压缩前原始数据的偏移量
     * @param isEndVoice 标记是否是尾包数据
     * @param voiceLength 原始语音数据长度
     * @return
     */
    public String toSpeechJson(byte[] bytes,int voiceSeq,boolean isEndVoice,int voiceLength,int seq) {
        setSeq(seq);
        super.toJson();
        JSONObject jsonContent = new JSONObject();
        JSONObject jsonSpeech = new JSONObject();
        JSONObject jsonSpeechConfig = new JSONObject();

        try {
            jsonSpeech.put("voice_seq",voiceSeq);
            jsonSpeech.put("is_voice_end", isEndVoice);
            jsonSpeech.put("voice_len",voiceLength);
            jsonSpeech.put("samples_per_sec",16000);
            jsonSpeech.put("bits_per_sample", 16);
            jsonSpeech.put("voice_file_type", 1);
            jsonSpeech.put("voice_encode_type", 6);
            if (bytes != null) {
                jsonSpeech.put("voice_data", Base64.encodeToString(bytes, Base64.NO_WRAP));
            }

            jsonSpeechConfig.put("awake_cloud", false);
            jsonSpeechConfig.put("profile_type", 0);
            jsonSpeechConfig.put("wakeup_mode", 3);

            jsonContent.put("speech_config", jsonSpeechConfig);
            jsonContent.put("speech", jsonSpeech);

            String contentBase64 = Base64.encodeToString(jsonContent.toString().getBytes(), Base64.NO_WRAP);
            jsonObject.put("content", contentBase64);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"SpeechBean: = " + jsonObject.toString());
        return jsonObject.toString();
    }
}
