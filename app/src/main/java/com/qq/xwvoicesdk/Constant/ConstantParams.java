package com.qq.xwvoicesdk.Constant;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/15
 * Describe:
 */
public class ConstantParams {

    //域名
    public static final String HOST = "wss://xwcloudapi.weixin.qq.com";

    //设备认证
    public static final String authDevicePath = "/xwopenapi/authdevice";

    //微信扫码绑定
    public static final String bind_By_WX_Code = "/xwopenapi/device/bind_by_wxcode";

    //解绑设备
    public static final String unbind_Device = "/xwopenapi/device/unbind";

    //更新设备属性 修改设备的备注和头像
    public static final String update_Attr = "/xwopenapi/device/update_attr";

    //获取设备属性 修获取设备的备注和头像
    public static final String get_Attr = "/xwopenapi/device/get_attr";

    //语音请求
    public static final String speechPath = "/xwopenapi/speech";

    //文本请求
    public static final String textPath = "/xwopenapi/text";

    //心跳服务
    public static final String heartBeat = "/xwopenapi/heartbeat";

    //SN        TODO 请输入你的SN
    public static final String serialNumber = "";

    //license   TODO 请输入你的license
    public static final String license = "";

    //UIN       TODO 请输入你的UIN
    public static final int app_uin = 0;

    //微信登录二维码
    public static final String wx_login_code = "https://open.weixin.qq.com/connect/qrconnect?appid=wx592d6d57a4b08a91&scope=snsapi_login&redirect_uri=https://xiaowei.weixin.qq.com/loading&state=wx_login&login_type=jssdk&self_redirect=true&styletype=&sizetype=&bgcolor=&rst=";

    //TTS im Url
    public static final String tts_im_url = "https://cloudim.weixin.qq.com";

    //wxlogin front
    public static final String wx_login_front = "code=";

    //wxlogin behind
    public static final String wx_login_behind = "&state=wx_login";
}