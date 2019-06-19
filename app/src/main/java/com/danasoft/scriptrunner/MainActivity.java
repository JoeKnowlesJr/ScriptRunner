package com.danasoft.scriptrunner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String HTML_start = "<html><head></head><body id=\"body\"><h1>Script Running</h1><script>";
    private static final String HTML_end = "</script></body></html>";
    Button btn_run, btn_clear, btn_edit;
    ViewPager viewPager;
    WebView wv_main;
    EditText et_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupPager();
        btn_run = findViewById(R.id.btn_run);
        btn_run.setOnClickListener(this);
        btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(this);
        btn_edit = findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(this);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resolveTitle(0);
    }

    @Override
    public void onClick(final View v) {
        switch(v.getId()) {
            case R.id.btn_run:
                runScript();
                break;
            case R.id.btn_clear:
                clear();
                break;
            case R.id.btn_edit:
                edit();
                break;
        }
    }

    private void edit() {
        viewPager.setCurrentItem(0);
    }

    private void clear() {

    }

    private void runScript() {
        String js = et_main.getText().toString();
        viewPager.setCurrentItem(1);
        String html_content = HTML_start + js + HTML_end;
        wv_main.loadData(html_content, "text/html", "utf-8");
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupPager() {
        viewPager = findViewById(R.id.viewPager);
        if (viewPager != null) {
            CustomPagerAdapter adapter = new CustomPagerAdapter();
            viewPager.setAdapter(adapter);
            LayoutInflater inflater = getLayoutInflater();
            FrameLayout v0 = (FrameLayout) inflater.inflate (R.layout.edit_layout, null);
            et_main = v0.findViewById(R.id.et_main);
            adapter.addView (v0, 0);
            FrameLayout v1 = (FrameLayout) inflater.inflate (R.layout.run_layout, null);
            wv_main = v1.findViewById(R.id.wv_main);
            wv_main.getSettings().setJavaScriptEnabled(true);
            wv_main.getSettings().setDomStorageEnabled(true);
            adapter.addView (v1, 1);
            adapter.notifyDataSetChanged();
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override public void onPageSelected(int i) { resolveTitle(i); }
                @Override public void onPageScrollStateChanged(int i) {}
                @Override public void onPageScrolled(int i, float v, int i1) {}
            });
        }
    }

    private void resolveTitle(int which) {
        ((TextView)findViewById(R.id.tv_title))
                .setText(String.format(Locale.getDefault(),
                        "%s", (which == 0) ? "Edit" : "Run"));
    }
}










