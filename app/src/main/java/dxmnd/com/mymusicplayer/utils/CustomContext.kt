package dxmnd.com.mymusicplayer.utils

import android.content.Context

/**
 * Created by HunJin on 2017-11-22.
 */
class CustomContext {

    companion object {
        private var instance: CustomContext? = null

        open fun getInstance(context: Context): CustomContext {
            if (instance == null) {
                instance = CustomContext(context.applicationContext)
            }
            return instance as CustomContext
        }
    }

    private var context: Context? = null

    constructor(context: Context) {
        this.context = context
    }
}