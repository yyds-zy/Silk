package com.qq.xwvoicesdk.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.qq.xwvoicesdk.Constant.ConstantParams;
import com.qq.xwvoicesdk.R;
import com.qq.xwvoicesdk.bean.ProductBean;
import com.qq.xwvoicesdk.listener.OnBindDeviceListener;
import com.qq.xwvoicesdk.manager.ParseDataManager;
import com.qq.xwvoicesdk.manager.WebSocketManager;
import com.qq.xwvoicesdk.util.SharedPreferenceUtil;

/**
 * Created by 阿飞の小蝴蝶 on 2023/2/17
 * Describe:
 */
public class BindDeviceActivity extends AppCompatActivity implements OnBindDeviceListener {

    public static final String TAG = "BindDeviceActivity";
    private WebView webview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_device);
        initWebView();
        initListener();
        initData();
    }

    private void initData() {
        String productInfo = SharedPreferenceUtil.getInstance().getString(SharedPreferenceUtil.PRODUCT_INFO, SharedPreferenceUtil.ATTR);
        if (productInfo == null) return;
        ProductBean productBean = new Gson().fromJson(productInfo, ProductBean.class);
        int app_uin = productBean.getData().getProduct_info().getApp_uin();
        if (app_uin == ConstantParams.app_uin) {
            bindDevice();
        }
    }

    private void initListener() {
        ParseDataManager.getInstance().setOnBindDeviceListener(this);
    }

    private void initWebView() {
        webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webview.loadUrl(ConstantParams.wx_login_code);
        //设置Web视图
        webview.setWebViewClient(new WxWebViewClient());
    }

    @Override
    public void bindDevice() {
        Intent intent = new Intent(this,RecorderActivity.class);
        startActivity(intent);
        finish();
    }

    private class WxWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String path = request.getUrl().toString();
            Log.d(TAG,path);
            if (ConstantParams.wx_login_code.equals(path)) {
                view.loadUrl(path);
                return true;
            } else {
                String code = path.substring(path.indexOf(ConstantParams.wx_login_front), path.indexOf(ConstantParams.wx_login_behind)).substring(5);
                WebSocketManager.getInstance().sendBindDevice(code);
                Log.d(TAG, code);
            }
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        finish();
        return false;
    }
}
