package sgg.study.sggdemo.networkprogram

import android.accounts.Account
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


//因为在macbook下没有安装 kotlin的环境变量所以 改到 主活动下打印
class Account {

    var uid: String = "0001"
    var userName:String = "Freeman"
    var password:String = "password"
    var telNumber:String = "18597721939"

    override fun toString(): String {
        return "GsonAccount(uid='$uid', userName='$userName', password='$password', telNumber='$telNumber')"
    }

}


//数据类对象
data class Account2(
    val uid:String,
    val username:String,
    var password:String,
    var telNumber:String
)


fun main() {

    var json = "{\"uid\":\"0001\",\"userName\":\"Freeman\",\"password\":\"password\",\"telNumber\":\"18597721939\"}";
    val gson = Gson()

    //GSON 解析成对象
    val account:Account = gson.fromJson<Account>(json,Account2::class.java)

    //对象 解析成JSON
    val accountJson:String = gson.toJson(account)

    //将JSON 转换成集合
    var jsonList = "[{\"uid\":\"0001\",\"userName\":\"Freeman\",\"password\":\"password\",\"telNumber\":\"18597721939\"}]"
    val accountList:List<Account> = gson.fromJson(jsonList,object :TypeToken<List<Account>>() {}.type)

    //把集合转换成json
    val accountJsonList:String = gson.toJson(accountList)


}

