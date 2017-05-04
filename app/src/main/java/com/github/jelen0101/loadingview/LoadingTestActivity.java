package com.github.jelen0101.loadingview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.github.jelen0101.library.widget.LoadingView;

public class LoadingTestActivity extends AppCompatActivity {
    RelativeLayout mRootRl;
    Button mShowBtn;

    LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_test);

        init();
    }

    private void init() {
        mRootRl = (RelativeLayout) findViewById(R.id.test_root_rl);
        mShowBtn = (Button) findViewById(R.id.test_show_btn);
        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingView.showInCenter();
            }
        });

        mLoadingView = new LoadingView.Builder(this)
                .text("加载中")
                .build();
    }
}
