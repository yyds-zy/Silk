package com.qq.xwvoicesdk.bean;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/15
 * Describe:
 */
public class Response extends BaseJson{


    /**
     * request_id : JDBDAOOAEPBNGKJOIBAMLPLPABBAENCA
     * type : 3
     * response : {"code":0,"msg":"success","token":"CgwQwOO-hMCYg1EYoR4SCEJLWUJVMlpGGJS0JA.2NWAJHmwtUTG7Me8L0es-qjuu1Mo5OYTR9T0gpnTPmY","expire_time":1676453673}
     */

    private String request_id;
    private int type;
    private String response;

    public Response(String mPath, String ticket, String requestID) {
        super(mPath, ticket, requestID);
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public boolean isRequest(String requestId) {
        return super.isRequest(requestId);
    }
}
