package com.github.jelen0101.library.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.github.jelen0101.library.R;


/**
 * Created by Jelen on 2017/5/3.
 */

public class LoadingView {
    private Context mContext;
    private PopupWindow mPopupWindow;
    private TextView mTextTv;

    private float mBgAlpha;
    private int mWidth;
    private int mHeight;
    private boolean outsideTouchable;
    private String mText;

    private LoadingView(LoadingView.Builder builder) {
        mContext = builder.context;
        mBgAlpha = builder.alpha;
        mWidth = builder.width;
        mHeight = builder.height;
        outsideTouchable = builder.outsideTouchable;
        mText = builder.text;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        View popupView = LayoutInflater.from(mContext).inflate(R.layout.layout_loadingview, null);
        mTextTv = (TextView) popupView.findViewById(R.id.loading_text_tv);
        mTextTv.setText(mText);
        mPopupWindow = new PopupWindow(popupView, mWidth, mHeight);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(outsideTouchable);
//        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mPopupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_roundcorner));

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ((Activity)mContext).getWindow().getAttributes();
                lp.alpha = 1f;
                ((Activity)mContext).getWindow().setAttributes(lp);
            }
        });
    }

    /**
     * 显示
     * @param parent
     * @param gravity
     * @param x
     * @param y
     */
    public void showAtLocation(View parent, int gravity, int x, int y) {
        //设置背景变暗
        WindowManager.LayoutParams lp = ((Activity)mContext).getWindow().getAttributes();
        lp.alpha = mBgAlpha;
        ((Activity)mContext).getWindow().setAttributes(lp);
        mPopupWindow.showAtLocation(parent, gravity, x, y);
    }

    /**
     * 显示在Activity中间
     */
    public void showInCenter() {
        //设置背景变暗
        WindowManager.LayoutParams lp = ((Activity)mContext).getWindow().getAttributes();
        lp.alpha = mBgAlpha;
        ((Activity)mContext).getWindow().setAttributes(lp);
        View view = ((Activity)mContext).getWindow().getDecorView().findViewById(android.R.id.content);
        mPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    @TargetApi(19)
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        //设置背景变暗
        WindowManager.LayoutParams lp = ((Activity)mContext).getWindow().getAttributes();
        lp.alpha = mBgAlpha;
        ((Activity)mContext).getWindow().setAttributes(lp);
        mPopupWindow.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    /**
     * 消失
     */
    public void dismiss() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * 设置加载文字
     * @param text
     */
    public void setText(String text) {
        if (mTextTv != null) {
            mText = text;
            mTextTv.setText(mText);
        }
    }

    /**
     * 设置背景暗度
     * @param alpha
     */
    public void setBgAlpha(float alpha) {
        mBgAlpha = alpha;
    }

    public static class Builder {
        private Context context;
        private float alpha = 0.7f;
        private int width = 300;
        private int height = 300;
        private boolean outsideTouchable = false;
        private String text = "loading";

        public Builder(Context context) {
            this.context = context;
        }

        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder alpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        public Builder outsideTouchable(boolean outsideTouchable) {
            this.outsideTouchable = outsideTouchable;
            return this;
        }

        public LoadingView build() {
            return new LoadingView(this);
        }
    }
}
