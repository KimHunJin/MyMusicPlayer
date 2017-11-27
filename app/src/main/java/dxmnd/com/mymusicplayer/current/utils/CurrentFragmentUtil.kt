package dxmnd.com.mymusicplayer.current.utils

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by HunJin on 2017-09-13.
 */

fun AppCompatActivity.replaceFragmentToActivity(fragment: Fragment, frameId : Int) {
    val transaction = this.supportFragmentManager.beginTransaction()
    transaction.replace(frameId, fragment)
    transaction.commit()
}