package com.apu.basejava.Helper;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.RecyclerView;

import com.apu.basejava.R;
import com.apu.basejava.Supportive.RecyccleviewAnimators.animators.LandingAnimator;

public class AnimationHelper {
    public static void setGeneralItemAnimation(Context context, View viewToAnimate, int position, int lastPosition, int animeRAW) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, animeRAW == 0 ? R.anim.slide_in_bottom : animeRAW);
            viewToAnimate.startAnimation(animation);
        }
    }

    public static void ScrollToPositionAndAnimate(final RecyclerView rvTextTemplateTTA, final int finalFocusPosition) {
        LandingAnimator animator = new LandingAnimator();
        rvTextTemplateTTA.setItemAnimator(animator);
        rvTextTemplateTTA.smoothScrollToPosition(finalFocusPosition);
        rvTextTemplateTTA.postDelayed(new Runnable() {
            @Override
            public void run() {
                rvTextTemplateTTA.getAdapter().notifyItemChanged(finalFocusPosition);
            }
        }, 1000);
    }
}
