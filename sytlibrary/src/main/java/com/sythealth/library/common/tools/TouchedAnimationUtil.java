package com.sythealth.library.common.tools;

import android.annotation.SuppressLint;
import android.graphics.ColorMatrixColorFilter;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import coach.beautyonline.sythealth.com.sytlibrary.R;


/**
 * view按下效果
 *
 * @since4.0
 */
@SuppressLint("ClickableViewAccessibility")
public class TouchedAnimationUtil {

    public final static float[] BT_DRAK_SELECTED = new float[]{1, 0, 0, 0, -40, 0, 1, 0, 0, -40, 0, 0, 1, 0, -40, 0, 0, 0, 1, 0};
    public final static float[] BT_LIGHT_SELECTED = new float[]{1, 0, 0, 0, 50, 0, 1, 0, 0, 50, 0, 0, 1, 0, 50, 0, 0, 0, 1, 0};

    public static OnTouchListener getTouchDarkListener(){
        return new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_DRAK_SELECTED));
                        setBacground(v);
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        v.getBackground().clearColorFilter();
                        setBacground(v);
                        v.postInvalidate();
                        break;
                }
                return false;
            }
        };
    }

    /**
     * 获取view变暗Touch监听
     * @return
     */
    public static OnTouchListener getToucDarkListenerWithAnimation() {
        return new OnTouchListener() {
            Animation animation;

            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_DRAK_SELECTED));
                        setBacground(v);
                        animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.button_scale_in);
                        v.startAnimation(animation);
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        v.getBackground().clearColorFilter();
                        setBacground(v);
                        animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.button_scale_out);
                        v.startAnimation(animation);
                        animation.setAnimationListener(new AnimationListener() {

                            @Override
                            public void onAnimationStart(Animation animation) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                // TODO Auto-generated method stub
                                v.clearAnimation();
                            }
                        });
                        v.postInvalidate();
                        break;
                }
                return false;
            }
        };
    }

//    /**
//     * 给试图添加点击效果,让背景变深
//     */
//
//    public static void addTouchDrak(View view, boolean hasAnima) {
//        view.setOnTouchListener(hasAnima ? TouchDarkWithAnimation : TouchDark);
//    }
//
//    /**
//     * 给试图添加点击效果,让背景变暗
//     */
//    public static void addTouchLight(View view, boolean hasAnima) {
//        view.setOnTouchListener(hasAnima ? TouchLightWithAnimation : TouchLight);
//    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    private static void setBacground(View v) {
        if (VERSION.SDK_INT > 15) {
            v.setBackground(v.getBackground());
        } else {
            v.setBackgroundDrawable(v.getBackground());
        }
    }

    public static final OnTouchListener TouchLightWithAnimation = new OnTouchListener() {
        Animation animation;

        @Override
        public boolean onTouch(final View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_LIGHT_SELECTED));
                    setBacground(v);
                    animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.button_scale_in);
                    v.startAnimation(animation);
                    break;
                case MotionEvent.ACTION_OUTSIDE:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    v.getBackground().clearColorFilter();
                    setBacground(v);
                    animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.button_scale_out);
                    v.startAnimation(animation);
                    animation.setAnimationListener(new AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            // TODO Auto-generated method stub
                            v.clearAnimation();
                        }
                    });
                    v.postInvalidate();
                    break;
            }
            return false;
        }
    };

    public static final OnTouchListener TouchLight = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_LIGHT_SELECTED));
                    setBacground(v);
                    break;
                case MotionEvent.ACTION_OUTSIDE:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    v.getBackground().clearColorFilter();
                    setBacground(v);
                    v.postInvalidate();
                    break;
            }
            return false;
        }
    };

    public static final OnTouchListener TouchDarkWithAnimation = new OnTouchListener() {
        Animation animation;

        @Override
        public boolean onTouch(final View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_DRAK_SELECTED));
                    setBacground(v);
                    animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.button_scale_in);
                    v.startAnimation(animation);
                    break;
                case MotionEvent.ACTION_OUTSIDE:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    v.getBackground().clearColorFilter();
                    setBacground(v);
                    animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.button_scale_out);
                    v.startAnimation(animation);
                    animation.setAnimationListener(new AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            // TODO Auto-generated method stub
                            v.clearAnimation();
                        }
                    });
                    v.postInvalidate();
                    break;
            }
            return false;
        }
    };

    public static final OnTouchListener TouchDark = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_DRAK_SELECTED));
                    setBacground(v);
                    break;
                case MotionEvent.ACTION_OUTSIDE:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    v.getBackground().clearColorFilter();
                    setBacground(v);
                    v.postInvalidate();
                    break;
            }
            return false;
        }
    };

}
