package com.qq.xwvoicesdk.bean;


/**
 * Created by 阿飞の小蝴蝶 on 2023/2/15
 * Describe:
 */


public class ResponseBean  {

    /**
     * cmd : 0
     * seq : 0
     * http : {"statusCode":200,"header":{"websocket_uin":"0","websocket_productid":"6201","websocket_deviceid":""}}
     * content : eyJyZXF1ZXN0X2lkIjoiSEFMR0xDUFBLQkZBRURBREpMQlBQTEdMSUlQREpJUEciLCJ0eXBlIjozLCJyZXNwb25zZSI6IntcImNvZGVcIjoxMDAwMTAwMDIsXCJtc2dcIjpcImxpY2Vuc2UgZXJyb3JcIn0ifQ==
     */

    private int cmd;
    private int seq;
    private HttpBean http;
    private String content;

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public HttpBean getHttp() {
        return http;
    }

    public void setHttp(HttpBean http) {
        this.http = http;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class HttpBean {
        /**
         * statusCode : 200
         * header : {"websocket_uin":"0","websocket_productid":"6201","websocket_deviceid":""}
         */

        private int statusCode;
        private HeaderBean header;

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public HeaderBean getHeader() {
            return header;
        }

        public void setHeader(HeaderBean header) {
            this.header = header;
        }

        public static class HeaderBean {
            /**
             * websocket_uin : 0
             * websocket_productid : 6201
             * websocket_deviceid :
             */

            private String websocket_uin;
            private String websocket_productid;
            private String websocket_deviceid;

            public String getWebsocket_uin() {
                return websocket_uin;
            }

            public void setWebsocket_uin(String websocket_uin) {
                this.websocket_uin = websocket_uin;
            }

            public String getWebsocket_productid() {
                return websocket_productid;
            }

            public void setWebsocket_productid(String websocket_productid) {
                this.websocket_productid = websocket_productid;
            }

            public String getWebsocket_deviceid() {
                return websocket_deviceid;
            }

            public void setWebsocket_deviceid(String websocket_deviceid) {
                this.websocket_deviceid = websocket_deviceid;
            }
        }
    }
}
