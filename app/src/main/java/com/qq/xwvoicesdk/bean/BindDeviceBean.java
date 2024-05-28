package com.qq.xwvoicesdk.bean;

import android.util.Base64;
import android.util.Log;

import com.qq.xwvoicesdk.Constant.ConstantParams;
import com.qq.xwvoicesdk.manager.WebSocketManager;
import com.qq.xwvoicesdk.util.MD5;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/17
 * Describe:
 */
public class BindDeviceBean extends BaseJson{

    public BindDeviceBean() {
        super(ConstantParams.bind_By_WX_Code, WebSocketManager.getInstance().getToken(), MD5.get32MD5(System.currentTimeMillis()));
    }

    public String toBindDeviceJson(String code) {
        super.toJson();
        try {
            JSONObject jsonContent = new JSONObject();
            jsonContent.put("wx_code", code);
            String contentBase64 = Base64.encodeToString(jsonContent.toString().getBytes(), Base64.NO_WRAP);
            jsonObject.put("content", contentBase64);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
