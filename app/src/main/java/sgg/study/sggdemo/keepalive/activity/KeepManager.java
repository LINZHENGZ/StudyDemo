package sgg.study.sggdemo.keepalive.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

public class KeepManager {

    private static final KeepManager mInstance = new KeepManager();
    //广播
    private KeepReceiver mKeepReceiver;

    //弱引用 -- JVM进行垃圾回收时，无论内存是否充足，都会回收被弱应用关联的对象
    private WeakReference<Activity> mKeepActivity;

    public KeepManager() {

    }

    public static KeepManager getInstance(){
        return mInstance;
    }

    /*
    * 注册 开屏 关屏 广播
    * */

    public void registerKeep(Context context){

        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        mKeepReceiver = new KeepReceiver();
        context.registerReceiver(mKeepReceiver,filter);

    }

    /*
    * 注销 广播接收者
    * */

    public void unregisterKeep(Context context){
        if (mKeepReceiver != null){
            context.unregisterReceiver(mKeepReceiver);
        }
    }

    /*
    * 开启1像素 Activity
    * */
    public void startKeep(Context context){
        Intent intent = new Intent(context,Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    /*
    * 关闭1像素Activity
    * */
    public void finishKeep(){
        if (mKeepActivity != null){
            Activity activity = mKeepActivity.get();
            if (activity != null){
                activity.finish();
            }
            mKeepActivity = null;
        }
    }

    //设置软引用
    public void setKeep(KeepActivity keep){
        mKeepActivity = new WeakReference<Activity>(keep);
    }


}
