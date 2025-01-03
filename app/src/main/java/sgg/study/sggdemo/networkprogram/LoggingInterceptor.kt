package sgg.study.sggdemo.networkprogram

import android.util.Log
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer

/*
* 拦截器 -> 可以监视、重写和重试调用请求
* */

class LoggingInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")

        val time_start:Long = System.nanoTime()
        val request:Request = chain.request()
        val response:Response = chain.proceed(request)

        val buffer = Buffer()

        request.body?.writeTo(buffer)

        val requestBodyStr:String = buffer.readUtf8()

        Log.e("OKHTTP",String.format("Sending request %s with params %s",request.url,requestBodyStr))

        //response 响应流只能调用一次
        val bussinessData:String = response.body?.string()?:"response body null"

        val mediaType:MediaType?= response.body?.contentType()
        val newBody = ResponseBody.create(mediaType,bussinessData)
        val newResponse: Response = response.newBuilder().body(newBody).build()

        val time_end:Long = System.nanoTime()
        Log.e("OKHTTP",String.format("Received response for %s in %.1fms >>> %s",request.url,(time_end - time_start) /1e6,bussinessData))

        return newResponse
    }


}