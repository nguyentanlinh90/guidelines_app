package com.ntl.guidelinesapp.modules.list;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ntl.guidelinesapp.R;

public class TranslateAnimationUtil implements View.OnTouchListener {

    private GestureDetector gestureDetector;

    public TranslateAnimationUtil(Context context, View view) {
        gestureDetector = new GestureDetector(context, new SimpleGestureDetector(view));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public class SimpleGestureDetector extends GestureDetector.SimpleOnGestureListener {
        private View mViewAnimation;
        private boolean isFinished = true;

        public SimpleGestureDetector(View mViewAnimation) {
            this.mViewAnimation = mViewAnimation;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (distanceY > 0) {
                hiddenView();
            } else {
                showView();
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        private void hiddenView() {
            if (mViewAnimation == null || mViewAnimation.getVisibility() == View.GONE) {
                return;
            }
            Animation animationDown = AnimationUtils.loadAnimation(mViewAnimation.getContext(), R.anim.bottom_down);
            animationDown.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mViewAnimation.setVisibility(View.VISIBLE);
                    isFinished = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mViewAnimation.setVisibility(View.GONE);
                    isFinished = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            if (isFinished) {
                mViewAnimation.startAnimation(animationDown);
            }
        }

        private void showView() {
            if (mViewAnimation == null || mViewAnimation.getVisibility() == View.VISIBLE) {
                return;
            }
            Animation animationUp = AnimationUtils.loadAnimation(mViewAnimation.getContext(), R.anim.bottom_up);
            animationUp.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mViewAnimation.setVisibility(View.VISIBLE);
                    isFinished = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isFinished = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            if (isFinished) {
                mViewAnimation.startAnimation(animationUp);
            }
        }
    }
}
