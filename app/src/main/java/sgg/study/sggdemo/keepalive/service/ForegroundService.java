package sgg.study.sggdemo.keepalive.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class ForegroundService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        startForeground(1,new Notification());
        startService(new Intent(this,InnnerService.class));


    }

    //Android 8.0 之前 需要在本地服务中多增加一个服务
    public static class InnnerService extends Service{
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
        //启动
        @Override
        public void onCreate() {
            super.onCreate();
            startForeground(1,new Notification());
            stopSelf();
        }
    }



}
