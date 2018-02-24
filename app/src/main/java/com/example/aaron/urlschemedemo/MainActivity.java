package com.example.aaron.urlschemedemo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


/**
 * URL Scheme使用
 * <p>
 * 1.）在AndroidManifest.xml中对<activity />标签增加<intent-filter />设置Scheme
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webview);
        WebSettings webSetting = webView.getSettings();
        //启用js代码支持
        webSetting.setJavaScriptEnabled(true);
        //使用内置的缩放控制功能按钮,有些网页可能会禁用掉,所以不一定看的到效果
        //另外,官网建议启用缩放控制的时候,webView的宽/高不要设置成wrap_content
        webSetting.setBuiltInZoomControls(true);
        //指定页面默认编码,不过即使这么设置了,loadData()仍有可能出现中文乱码的请款
        webSetting.setDefaultTextEncodingName("utf-8");

        //优先使用缓存,然后才是网络加载
        webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                // title 是获取到的网页title,可以将之设置为webView所在页面的标题
                MainActivity.this.setTitle(title);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String httpurl) {
                if (httpurl.startsWith("xl:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(httpurl));
                    startActivity(intent);
                }
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        webView.loadUrl("file:///android_asset/index.html");


        Button btn_jump = (Button) findViewById(R.id.btn_jump);
        btn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });


    }

    /**
     * 如何判断一个Scheme是否有效
     *
     * @return
     */
    private void check() {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("xl://goods:8888/goodsDetail?goodsId=10011002"));
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isValid = !activities.isEmpty();
        if (isValid) {
            startActivity(intent);
        }
    }
}
