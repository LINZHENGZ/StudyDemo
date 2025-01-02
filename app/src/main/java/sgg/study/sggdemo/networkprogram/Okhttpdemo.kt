package sgg.study.sggdemo.networkprogram

import android.app.DownloadManager.Request
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class Okhttpdemo {


    val client: OkHttpClient = OkHttpClient.Builder() //builder构造者设计模式
        //连接超时时间
        .connectTimeout(10, TimeUnit.SECONDS)
        //读取超时时间
        .readTimeout(10,TimeUnit.SECONDS)
        //写超时
        .writeTimeout(10,TimeUnit.SECONDS)
        .build()


    fun post(){

        val request:Request =


    }


}