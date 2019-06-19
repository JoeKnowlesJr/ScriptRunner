package com.danasoft.scriptrunner;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomPagerAdapter extends PagerAdapter {
    private ArrayList<View> views = new ArrayList<>();

    @Override
    public int getItemPosition (@NotNull Object object) {
        int index = views.indexOf (object);
        if (index == -1)
            return POSITION_NONE;
        else
            return index;
    }

    @NotNull
    @Override
    public Object instantiateItem (@NotNull ViewGroup container, int position) {
        View v = views.get (position);
        container.addView (v);
        return v;
    }

    @Override
    public void destroyItem (@NotNull ViewGroup container, int position, @NotNull Object object) {
        container.removeView (views.get (position));
    }

    @Override
    public int getCount () {
        return views.size();
    }

    @Override
    public boolean isViewFromObject (@NotNull View view, @NotNull Object object) {
        return view == object;
    }

    public int addView (View v) {
        return addView (v, views.size());
    }

    @Contract("_, _ -> param2")
    int addView(View v, int position) {
        views.add (position, v);
        return position;
    }

    public int removeView (ViewPager pager, View v) {
        return removeView (pager, views.indexOf (v));
    }

    @Contract("_, _ -> param2")
    private int removeView(@NotNull ViewPager pager, int position) {
        pager.setAdapter (null);
        views.remove (position);
        pager.setAdapter (this);

        return position;
    }

    public View getView (int position) {
        return views.get (position);
    }
}
