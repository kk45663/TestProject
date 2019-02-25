package com.example.login.loginsighnupexample;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kundan6.Singh on 25-02-2019.
 */

public class Dialog_Product_Features extends DialogFragment {
    LinearLayout llFeatures;
    ArrayList<String> alFeatures;

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_product_fragment, container,
                false);
        getDialog().setTitle("Product Features");
        alFeatures = new ArrayList<>();
        alFeatures.clear();
        alFeatures.add("Unlimited Practice");
        alFeatures.add("Check Learner Status");
        alFeatures.add("Multiple Language");
        llFeatures = (LinearLayout) rootView.findViewById(R.id.llFeatures);
        for (int i = 0; i < alFeatures.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout llChildView = new LinearLayout(getActivity());
            llChildView.setOrientation(LinearLayout.HORIZONTAL);
            // Create ImageView
            AppCompatImageView image = new AppCompatImageView(getActivity());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                image.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.ic_done_black_24dp));
            }
            llChildView.addView(image, params);

            // Create TextView
            TextView msg = new TextView(getActivity());
            msg.setText(" "+alFeatures.get(i));
            llChildView.addView(msg);
            msg.setTextColor(R.color.background_color);
            llFeatures.addView(llChildView);
        }
        // Do something else
        return rootView;
    }
}
