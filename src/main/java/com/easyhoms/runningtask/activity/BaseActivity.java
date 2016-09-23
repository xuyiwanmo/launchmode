package com.easyhoms.runningtask.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.easyhoms.runningtask.BaseApp;

import org.xutils.x;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by 德医互联 on 2016/9/22.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }


    public String getTask(){
       StringBuilder str=new StringBuilder();
//        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> appTasks = activityManager.getRunningTasks(1);
//
//        for (ActivityManager.RunningTaskInfo task : appTasks) {
//             str.append(task.topActivity.getClassName());
//        }
//
//        getActivity();
        for (String s : BaseApp.getIntance().mList) {
            str.append(s+"\n");
        }
        return str.toString();

    }

    public static Activity getActivity() {
        StringBuilder sb=new StringBuilder();
        Class activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);

            Set key=activities.keySet();
            Iterator it=key.iterator();
            while (it.hasNext()) {
                Object o=it.next();
                Object activityRecord=activities.get(o);
                Class activityRecordClass = activityRecord.getClass();
//                Field pausedField = activityRecordClass.getDeclaredField("paused");
//                pausedField.setAccessible(true);
                //      if (pausedField.getBoolean(activityRecord)) {
                Field activityField = activityRecordClass.getDeclaredField("activity");
                activityField.setAccessible(true);
                Activity activity = (Activity) activityField.get(activityRecord);
                sb.append("栈  "+activity.getClass().getName()+"\n");
            }

//            for (Object activityRecord : activities.values()) {
//                Class activityRecordClass = activityRecord.getClass();
////                Field pausedField = activityRecordClass.getDeclaredField("paused");
////                pausedField.setAccessible(true);
//          //      if (pausedField.getBoolean(activityRecord)) {
//                    Field activityField = activityRecordClass.getDeclaredField("activity");
//                    activityField.setAccessible(true);
//                    Activity activity = (Activity) activityField.get(activityRecord);
//                    sb.append("栈  "+activity.getClass().getName()+"\n");
//                  //  return activity;
//            //    }
//            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        Log.i("zhang",sb.toString());
        return null;
    }

}
