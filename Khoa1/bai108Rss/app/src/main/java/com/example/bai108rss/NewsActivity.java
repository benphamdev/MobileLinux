package com.example.bai108rss;

import static com.example.bai108rss.MainActivity.LINK;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class NewsActivity extends AppCompatActivity {

    WebView webViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        setupUI();
        setupProcess();
    }

    private void setupUI() {
        webViewDisplay = findViewById(R.id.web_view_display);
    }

    public void setupProcess() {
        setupIntent();
    }

    public void setupIntent() {
        Intent intent = getIntent();
        String link = intent.getStringExtra(LINK);
//        Toast.makeText(this, link, Toast.LENGTH_SHORT)
//             .show();
        webViewDisplay.loadUrl(Objects.requireNonNull(link));
        webViewDisplay.setWebViewClient(new WebViewClient());
    }
}