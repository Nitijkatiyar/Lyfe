package com.bst.Lyfe.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.bst.Lyfe.R;

public class OverviewViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Activity context;
    String[] textmiddle;
    String[] textbottom;
    int[] images;

    String[] texttop;
    LayoutInflater inflater;
    LayoutParams layoutParams;
    int width;

    @SuppressLint("NewApi")
    public OverviewViewPagerAdapter(Activity context, String[] title, String[] desc,
                                    String[] desctxt, int[] images) {
        this.context = context;
        this.textmiddle = desc;
        this.texttop = title;
        this.textbottom = desctxt;
        this.images = images;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        Log.e("size", "" + width);
        layoutParams = new LayoutParams(width - 80, width - 100);

    }

    @Override
    public int getCount() {
        return texttop.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @SuppressLint("NewApi")
    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        // Declare Variables
        TextView txttop, txtbottom, txtmiddle;
        ImageView imageView;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.overview_viewpager_item, container,
                false);

        txttop = (TextView) itemView.findViewById(R.id.textTop);
        txtmiddle = (TextView) itemView.findViewById(R.id.textmiddle);
        txtbottom = (TextView) itemView.findViewById(R.id.textbottom);
        imageView = (ImageView) itemView.findViewById(R.id.imageSlider);
        txttop.setText(texttop[position]);
        txtmiddle.setText(textmiddle[position]);
        txtbottom.setText(textbottom[position]);
        imageView.setImageResource(images[position]);


        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
