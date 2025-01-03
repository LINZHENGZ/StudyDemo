package sgg.study.sggdemo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import sgg.study.sggdemo.keepalive.service.ForegroundService;
import sgg.study.sggdemo.networkprogram.GsonAccount;

public class MainActivity extends AppCompatActivity {

    String json = "{\"uid\":\"0001\",\"userName\":\"Freeman\",\"password\":\"password\",\"telNumber\":\"18597721939\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, ForegroundService.class));

        Gson gson = new Gson();
        GsonAccount account = gson.fromJson(json, GsonAccount.class);
        Log.e("MainActivity",account.toString());


    }




}