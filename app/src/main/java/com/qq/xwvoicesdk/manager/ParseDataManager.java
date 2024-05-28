package com.qq.xwvoicesdk.manager;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.qq.xwvoicesdk.bean.HeartBeatBean;
import com.qq.xwvoicesdk.bean.ProductBean;
import com.qq.xwvoicesdk.bean.Response;
import com.qq.xwvoicesdk.bean.ResponseBean;
import com.qq.xwvoicesdk.bean.TTSBean;
import com.qq.xwvoicesdk.bean.WeatherBean;
import com.qq.xwvoicesdk.listener.OnBindDeviceListener;
import com.qq.xwvoicesdk.listener.OnRefreshTokenListener;
import com.qq.xwvoicesdk.listener.WebSocketResponseListener;
import com.qq.xwvoicesdk.util.ByteToStringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/15
 * Describe:
 */
public class ParseDataManager {

    private static ParseDataManager instance;

    private ParseDataManager() {
    }

    Handler webSocketHandler = WebSocketManager.getInstance().getWebSocketHandler();

    public static ParseDataManager getInstance() {
        if (instance == null) {
            synchronized (ParseDataManager.class) {
                if (instance == null) {
                    instance = new ParseDataManager();
                }
            }
        }
        return instance;
    }

    private WebSocketResponseListener webSocketResponseListener;
    private OnRefreshTokenListener onRefreshTokenListener;
    private OnBindDeviceListener onBindDeviceListener;

    public void setOnBindDeviceListener(OnBindDeviceListener onBindDeviceListener) {
        this.onBindDeviceListener = onBindDeviceListener;
    }

    public void setOnRefreshTokenListener(OnRefreshTokenListener onRefreshTokenListener) {
        this.onRefreshTokenListener = onRefreshTokenListener;
    }

    public void setWebSocketResponseListener(WebSocketResponseListener webSocketResponseListener) {
        this.webSocketResponseListener = webSocketResponseListener;
    }

    public void parseData(String text) {
        Log.d("ParseDataManager", "mWebSocket onMessage = " + text);
        webSocketHandler.post(() -> {
            try {
                ResponseBean responseBean = new Gson().fromJson(text, ResponseBean.class);

                bindDevice(responseBean);
                getDeviceInfo(responseBean);
                unBindDevice(responseBean);

                byte[] decode = Base64.decode(responseBean.getContent(), Base64.NO_WRAP);
                String parseByte = ByteToStringUtil.parseByte(decode);

                Log.d("ParseDataManager", "mWebSocket onMessage = " + parseByte);

                Response responseAuthDevice = new Gson().fromJson(parseByte, Response.class);
                if (responseAuthDevice == null) return;
                if (TextUtils.isEmpty(responseAuthDevice.getResponse())) return;
                String response = responseAuthDevice.getResponse();

                if (webSocketResponseListener != null) {
                    webSocketResponseListener.xw_response(response);
                }

                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.has("interval")) {
                    HeartBeatBean.getInstance().dataParse(jsonObject);
                }

                if (jsonObject.has("token")) {
                    String token = (String) jsonObject.get("token");
                    if (onRefreshTokenListener != null) {
                        onRefreshTokenListener.refreshToken(token);
                    }
                    Log.d("ParseDataManager", "mWebSocket token = " + token);
                }

                if (jsonObject.has("orig_question_text")) {
                    String ori_question_text = (String) jsonObject.get("orig_question_text");
                    Log.d("ParseDataManager", "ASR = " + ori_question_text);
                    if (webSocketResponseListener != null) {
                        webSocketResponseListener.ori_question_text(ori_question_text);
                    }
                }

                TTSBean ttsBean = new Gson().fromJson(response, TTSBean.class);
                if (ttsBean != null) {
                    if (ttsBean.getSkill_answer() != null) {
                        TTSBean.SkillAnswerBean.AudioInfoBean audio_info = ttsBean.getSkill_answer().getAudio_info();
                        if (audio_info != null) {
                            List<TTSBean.SkillAnswerBean.AudioInfoBean.AudioResourceGroupBean> audio_resource_group = audio_info.getAudio_resource_group();
                            if (audio_resource_group != null) {
                                TTSBean.SkillAnswerBean.AudioInfoBean.AudioResourceGroupBean audioResourceGroupBean = audio_resource_group.get(0);
                                if (audioResourceGroupBean != null) {
                                    String tts = audioResourceGroupBean.getContent();
                                    if (!TextUtils.isEmpty(tts)) {
                                        if (webSocketResponseListener != null) {
                                            webSocketResponseListener.tts(tts);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                WeatherBean weatherBean = new Gson().fromJson(response, WeatherBean.class);
                if (weatherBean != null) {
                    if (weatherBean.getSkill_answer() != null) {
                        WeatherBean.SkillAnswerBean.ComplexInfoBean complex_info = weatherBean.getSkill_answer().getComplex_info();
                        if (complex_info != null) {
                            if (complex_info.getCard_info() != null) {
                                WeatherBean.SkillAnswerBean.ComplexInfoBean.CardInfoBean.TtsBean ttsInfoBean = complex_info.getCard_info().getTts();
                                if (ttsInfoBean != null) {
                                    String tts = ttsInfoBean.getContent();
                                    if (!TextUtils.isEmpty(tts)) {
                                        if (webSocketResponseListener != null) {
                                            webSocketResponseListener.tts(tts);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 绑定设备
     * @param responseBean
     */
    public void bindDevice(ResponseBean responseBean) {
        if (responseBean != null) {
            if (responseBean.getCmd() == 1000011) {
                if (onBindDeviceListener != null) {
                    onBindDeviceListener.bindDevice();
                }
            }
        }
    }

    /**
     * 解绑设备
     * @param responseBean
     */
    public void unBindDevice(ResponseBean responseBean) {
        if (responseBean != null) {
            if (responseBean.getCmd() == 1000013) {
                if (webSocketResponseListener != null) {
                    webSocketResponseListener.un_bind_device();
                }
            }
        }
    }

    /**
     * 获取设备信息
     * @param responseBean
     */
    public void getDeviceInfo(ResponseBean responseBean) {
        if (responseBean != null) {
            if (responseBean.getCmd() == 1000012) {
                byte[] decode = Base64.decode(responseBean.getContent(), Base64.NO_WRAP);
                String parseByte = ByteToStringUtil.parseByte(decode);
                Log.d("v_zyuanxue",parseByte);
                Response responseProductInfo = new Gson().fromJson(parseByte, Response.class);
                if (responseProductInfo == null) return;
                if (TextUtils.isEmpty(responseProductInfo.getResponse())) return;
                String response = responseProductInfo.getResponse();
                if (webSocketResponseListener != null) {
                    webSocketResponseListener.productInfo(response);
                }
            }
        }
    }
}
