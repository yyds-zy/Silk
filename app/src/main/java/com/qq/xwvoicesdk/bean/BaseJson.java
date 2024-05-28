package com.qq.xwvoicesdk.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/14
 * Describe:
 */
public class BaseJson {

    protected int cmd = 0;
    protected int product = 6201;
    protected int seq = 0;
    protected String method = "post";
    protected String path;
    protected String query = "";
    protected String Host= "xwcloudapi.weixin.qq.com";
    protected String Ticket;
    protected String RequestID;

    JSONObject jsonObject = new JSONObject();
    JSONObject jsonHttp = new JSONObject();
    JSONObject jsonHeader = new JSONObject();

    public BaseJson(String mPath, String ticket, String requestID) {
        path = mPath;
        Ticket = ticket;
        RequestID = requestID;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public void toJson() {
        try {
            jsonObject.put("cmd", cmd);
            jsonObject.put("product", product);
            jsonObject.put("seq", seq);

            jsonHttp.put("method", method);
            jsonHttp.put("path", path);
            jsonHttp.put("query", query);

            jsonHeader.put("Host", Host);
            jsonHeader.put("Ticket", Ticket);
            jsonHeader.put("RequestID", RequestID);

            jsonHttp.put("header", jsonHeader);

            jsonObject.put("http", jsonHttp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //响应
    protected String msg;
    protected int code;

    public boolean isRequest(String requestId){
        return requestId.equals(RequestID);
    }

    public void dataParse(JSONObject jsonObjectResp){
        try {
            code = jsonObjectResp.getInt("code");
            msg = jsonObjectResp.getString("msg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isSuccess(){
        return msg.equals("success");
    }
}
