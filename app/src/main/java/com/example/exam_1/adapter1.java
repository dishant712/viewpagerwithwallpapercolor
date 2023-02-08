package com.example.exam_1;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class adapter1 extends PagerAdapter {
    MainActivity1 mainActivity1;
    ArrayList<String> templistimage;
    public adapter1(MainActivity1 mainActivity1, ArrayList<String> templistimage) {

        this.mainActivity1=mainActivity1;
        this.templistimage=templistimage;
    }

    @Override
    public int getCount() {
        return templistimage.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(mainActivity1).inflate(R.layout.item1,container,false);

        ImageView imageView =view.findViewById(R.id.im);

        InputStream inputstream= null;
        try
        {
            inputstream = mainActivity1.getAssets().open("" +templistimage.get(position));
            Drawable drawable = Drawable.createFromStream(inputstream, null);
            imageView.setImageDrawable(drawable);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
container.removeView((View) object);
    }
}
