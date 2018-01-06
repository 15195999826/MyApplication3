package com.example.alienware.util;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by alienware on 2017/12/20
 */
public class Myadapter extends PagerAdapter
{
    private List<View> views;
    private Context context;

    public Myadapter(List<View> views, Context context)
    {
        this.views=views;
        this.context=context;
    }
    @Override
    public int getCount() {

        return views.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)  //instantiateItem用于创建position所在位置的视图
    {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) //重写destroyItem方法，用于删除position位置所制定的视图。
    {
        container.removeView(views.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object o) //isViewFromObject函数用于判断instantiateItem返回的对象是否与当前View代表的是同一个对象。
    {
        return (view == o);
    }
}