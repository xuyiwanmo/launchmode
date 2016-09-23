package com.easyhoms.runningtask;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by 德医互联 on 2016/9/22.
 */

public class BaseApp extends Application {
    public static BaseApp sApp;
    public static StringBuilder result;

    public static BaseApp getIntance(){
        return sApp;
    }

    public static ArrayList<String> mList=new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        sApp=this;
        result=new StringBuilder("");
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                mList.add(activity.toString());
                Log.i("zhang","栈:"+activity.getTaskId()+" "+activity.getClass().getName()+" "+activity.toString());
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
              //  Log.i("zhang","栈:"+activity.getTaskId()+" "+activity.getClass().getName());
                mList.remove(activity.toString());
            }
        });
    }




}
