package sgg.study.sggdemo.networkprogram


//因为在macbook下没有安装 kotlin的环境变量所以 改到 主活动下打印
class GsonAccount {

    var uid: String = "0001"
    var userName:String = "Freeman"
    var password:String = "password"
    var telNumber:String = "18597721939"

    override fun toString(): String {
        return "GsonAccount(uid='$uid', userName='$userName', password='$password', telNumber='$telNumber')"
    }

}

