package com.qq.xwvoicesdk.bean;

import android.util.Base64;
import android.util.Log;

import com.qq.xwvoicesdk.Constant.ConstantParams;
import com.qq.xwvoicesdk.util.MD5;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/15
 * Describe:
 */
public class AuthDeviceBean extends BaseJson {

    public static final String TAG = "AuthDeviceBean";

    public AuthDeviceBean() {
        super(ConstantParams.authDevicePath, "", MD5.get32MD5(System.currentTimeMillis()));
    }

    /**
     * 认证设备请求结构体数据
     * @return
     */
    public String toAuthDeviceJson() {
        super.toJson();
        try {
            JSONObject jsonContent = new JSONObject();
            jsonContent.put("app_uin",ConstantParams.app_uin);
            jsonContent.put("sn",ConstantParams.serialNumber);
            jsonContent.put("license",ConstantParams.license);

            String contentBase64 = Base64.encodeToString(jsonContent.toString().getBytes(), Base64.NO_WRAP);
            jsonObject.put("content", contentBase64);
        } catch (JSONException e){
            e.printStackTrace();
        }
        Log.d(TAG,jsonObject.toString());
        return jsonObject.toString();
    }
}
