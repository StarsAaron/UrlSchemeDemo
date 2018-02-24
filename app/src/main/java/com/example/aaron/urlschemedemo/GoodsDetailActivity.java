package com.example.aaron.urlschemedemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.List;

public class GoodsDetailActivity extends AppCompatActivity {
    private static final String TAG = "GoodsDetailActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);

        TextView textView = (TextView)findViewById(R.id.tv_text);

        Uri uri = getIntent().getData();
        StringBuilder stringBuilder = new StringBuilder();

        if (uri != null) {
            // 完整的url信息
            String url = uri.toString();
            stringBuilder.append("url:"+url+'\n');
            Log.e(TAG, "url: " + uri);
            // scheme部分
            String scheme = uri.getScheme();
            stringBuilder.append("scheme:"+scheme+'\n');
            Log.e(TAG, "scheme: " + scheme);
            // host部分
            String host = uri.getHost();
            stringBuilder.append("host:"+host+'\n');
            Log.e(TAG, "host: " + host);
            //port部分
            int port = uri.getPort();
            stringBuilder.append("port:"+port+'\n');
            Log.e(TAG, "host: " + port);
            // 访问路劲
            String path = uri.getPath();
            stringBuilder.append("path:"+path+'\n');
            Log.e(TAG, "path: " + path);
            List<String> pathSegments = uri.getPathSegments();
            // Query部分
            String query = uri.getQuery();
            stringBuilder.append("query:"+query+'\n');
            Log.e(TAG, "query: " + query);
            //获取指定参数值
            String goodsId = uri.getQueryParameter("goodsId");
            stringBuilder.append("goodsId:"+goodsId+'\n');
            Log.e(TAG, "goodsId: " + goodsId);

            textView.setText(stringBuilder.toString());
        }
    }
}
