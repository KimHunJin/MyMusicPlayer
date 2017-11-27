package dxmnd.com.mymusicplayer.utils.logger

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


/**
 * Created by HunJin on 2017-11-24.
 */

fun log(any: Any?){
    Logger.addLogAdapter(AndroidLogAdapter())

    if (any == null) {
        log("null")
        return
    }

    when (any) {
        is Throwable -> Logger.e(any.message)
        else -> Logger.d(any.toString())
    }

}