package com.example.app;

import android.content.Intent;
import android.net.Uri;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewDatabase;

import java.util.Objects;

class MyWebViewClient extends WebViewClient {
    String mHost;
    String mLogin;
    String mPass;

    public MyWebViewClient(String host, String login, String pass) {
        mHost = host;
        mLogin = login;
        mPass = pass;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view,
                                          HttpAuthHandler handler,
                                          String host,
                                          String realm) {

        // maybe WebViewDatabase.getHttpAuthUsernamePassword? huh?
        if (Objects.equals(mHost, host)) {
            handler.proceed(mLogin, mPass);
        }
    }
}
