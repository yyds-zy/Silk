package com.qq.xwvoicesdk.listener;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/15
 * Describe:
 */
public interface WebSocketResponseListener {
    void ori_question_text(String ori_question_text);
    void xw_response(String response);
    void tts(String url);
    void productInfo(String productInfo);
    void un_bind_device();
}
