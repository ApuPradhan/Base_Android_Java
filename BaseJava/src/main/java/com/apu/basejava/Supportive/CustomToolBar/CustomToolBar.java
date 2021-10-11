package com.apu.basejava.Supportive.CustomToolBar;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;

import com.apu.basejava.CoreHelper;
import com.apu.basejava.R;

public class CustomToolBar {
    protected static Activity ctx;
    protected static ActionBar actionBar;

    protected static ImageView btnBackXML, imgLogoXML;
    protected static TextView tvTitleXML;

    public CustomToolBar(Activity ctx) {
        CustomToolBar.ctx = ctx;
        actionBar = CoreHelper.getAppCompatActivityActivity(ctx).getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.layout_toolbar);
        View view = CoreHelper.getAppCompatActivityActivity(ctx).getSupportActionBar().getCustomView();

        btnBackXML = view.findViewById(R.id.btnBackXML);
        imgLogoXML = view.findViewById(R.id.imgLogoXML);
        tvTitleXML = view.findViewById(R.id.tvTitleXML);
    }

    public void toolBar(ToolBarModel model) {
        switch (model.getToolbarType()) {
            case Title:
                imgLogoXML.setVisibility(View.GONE);
                break;
            case TitleLogo:
                imgLogoXML.setVisibility(View.VISIBLE);
                imgLogoXML.setImageDrawable(ContextCompat.getDrawable(ctx, model.getLogo()));
                break;
            case NoToolbar:
                CoreHelper.getAppCompatActivityActivity(ctx).getSupportActionBar().hide();
                break;
            case DefaultToolbar:
                break;
        }

        if (model.getToolBarColor() != 0) {
            actionBar.setBackgroundDrawable(new ColorDrawable(model.getToolBarColor()));
        }
        if (model.getTitle() != null) {
            tvTitleXML.setText(model.getTitle());
            tvTitleXML.setTextColor(model.getTextTitleColor());
        }
        if (model.getBackButtonColor() != 0) {
            btnBackXML.getBackground().setTint(model.getBackButtonColor());
        }

        btnBackXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctx.onBackPressed();
            }
        });
    }


}
