package sgg.study.sggdemo.networkprogram

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class Okhttpdemo {


    private val client:OkHttpClient

    init {

//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        client=OkHttpClient.Builder() //builder构造者设计模式
            //连接超时时间
            .connectTimeout(10, TimeUnit.SECONDS)
            //读取超时时间
            .readTimeout(10,TimeUnit.SECONDS)
            //写超时
            .writeTimeout(10,TimeUnit.SECONDS)
            //添加拦截器
            .addInterceptor(LoggingInterceptor())
            .build()

    }

    fun get(url: String){
        Thread(Runnable {
            val request:Request = Request.Builder()
                .url("")
                .build()
            val call:Call = client.newCall(request)
            val response:Response=call.execute()
            val body:String?= response.body.toString()
            Log.e("okhttp","get response:${body}")
        }).start()
    }

    fun getAsync(){

        val request:Request = Request.Builder()
            .url("").build()

        val call:Call = client.newCall(request)
        call.enqueue(object :Callback{

            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
                Log.e("Okhttpdemo","get response onFailure:${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                TODO("Not yet implemented")
                val body:String? = response.body?.toString()
                Log.e("Okhttpdemo","get response :${body}")
            }

        })

    }


    val key:String = ""
    val value:String = ""
    val url:String = ""
    fun post(){

        val body:FormBody = FormBody.Builder()
            .add(key,value)
            .build()
        val request:Request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        val call:Call = client.newCall(request)

        Thread(Runnable {
            val response:Response = call.execute()
            Log.e("Okhttpdemo","post response:${response.body?.string()}")
        }).start()

    }

    fun postAsync(){

        val body:FormBody = FormBody.Builder().add(key,value).build()

        val request:Request = Request.Builder().url(url).post(body).build()

        val call:Call = client.newCall(request)

        Thread(Runnable {

            val response: Unit = call.enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {
                    TODO("Not yet implemented")
                    Log.e("Okhttpdemo","post response onFailure:${e.message}")

                }

                override fun onResponse(call: Call, response: Response) {
                    TODO("Not yet implemented")
                    Log.e("Okhttpdemo","post response:${response.body?.string()}")

                }

            })

        }).start()

    }

    /*
    * POST 异步请求【多表单文件上传】
    * */
    fun postAsyncMultipart(context: Context){

        //导入外部存储文件,android 6.0 以后都需要读取外部存储卡的文件都需要动态申请权限
        val file = File(Environment.getExternalStorageDirectory(),"1.png")
        if(!file.exists()){
            Toast.makeText(context,"文件不存在",Toast.LENGTH_SHORT).show()
            return
        }

        val body:MultipartBody = MultipartBody.Builder()
            .addFormDataPart("key","value")
            .addFormDataPart("file","file.png",RequestBody.create("application/octet-stream".toMediaType(),file))
            .build()

        val request:Request = Request.Builder().url(url)
            .post(body)
            .build()

        val call:Call = client.newCall(request)
        call.enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")

                Log.e("Okhttpdemo","postAsyncMultipart response onFailure:${e.message}")

            }

            override fun onResponse(call: Call, response: Response) {
                TODO("Not yet implemented")

                Log.e("Okhttpdemo","post response:${response.body?.string()}")

            }

        })



    }

    /*
    * 异步提交字符串
    * */

    fun postStringAsync(){

        //文本类型
        val textPlain = "text/plain;charset=utf-8".toMediaType()
        //JSON类型
        val application = "application/json;charset=utf-8".toMediaType()

        val jsonObj = JSONObject()
        jsonObj.put("key1","value1")
        jsonObj.put("key2",100)

        val body:RequestBody = RequestBody.create(application,jsonObj.toString())

        val request:Request = Request.Builder().url(url)
            .post(body)
            .build()

        val call:Call = client.newCall(request)

        call.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
                Log.e("Okhttpdemo","postStringAsync response onFailure:${e.message}")

            }

            override fun onResponse(call: Call, response: Response) {
                TODO("Not yet implemented")
                Log.e("Okhttpdemo","postStringAsync response:${response.body?.string()}")
            }

        })


    }




}