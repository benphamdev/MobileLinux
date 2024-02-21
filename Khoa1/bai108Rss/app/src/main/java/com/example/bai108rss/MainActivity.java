package com.example.bai108rss;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    public static String LINK = "LINK";
    ExecutorService executorService;
    Handler handler;
    ListView listViewTitle;
    ArrayList<HashMap<String, String>> hashMapArrayList;
    ArrayList<String> listTitle, listLink;
    ArrayAdapter adapter;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        setupProcess();
    }

    private void setupProcess() {
        String stringUrl = "https://vnexpress.net/rss/suc-khoe.rss";
        getContentBroswser(stringUrl);
        setUpListView();
    }

    private void getContentBroswser(String stringUrl) {
        final StringBuilder[] stringBuilders = {new StringBuilder()};
        executorService.execute(() -> {
            try {
                URL url = new URL(stringUrl);
                URLConnection urlConnection = url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.connect();

                BufferedReader bufferedReader =
                        new BufferedReader(
                                new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilders[0].append(line)
                                     .append('\n');
                }

                bufferedReader.close();

                runOnUiThread(() -> parseXML(stringBuilders[0].toString()));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })
        ;
    }

    public void setUpListView() {
        listViewTitle.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
            intent.putExtra(LINK, listLink.get(position));
            startActivity(intent);
        });
    }

    public void parseXML(String s) {
        XMLParser parser = new XMLParser();
        Document document = parser.getDomElement(s);
        NodeList nodeList = document.getElementsByTagName("item");

        String title = null;

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
//            HashMap<String, String> news = new HashMap<>();
            title = parser.getValue(element, "title");
//            news.put("title", title);
//            a.add(news);

            listTitle.add(title);
            listLink.add(parser.getValue(element, "link"));
        }
        adapter.notifyDataSetChanged();
//        Toast.makeText(this, title, Toast.LENGTH_SHORT)
//             .show();
    }

    private void setupUI() {
        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        listViewTitle = findViewById(R.id.list_view);
        hashMapArrayList = new ArrayList<>();
        listLink = new ArrayList<>();
        listTitle = new ArrayList<>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listTitle);
        listViewTitle.setAdapter(adapter);
    }
}