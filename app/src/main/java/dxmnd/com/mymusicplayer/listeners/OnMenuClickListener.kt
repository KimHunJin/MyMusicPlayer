package dxmnd.com.mymusicplayer.listeners

import android.view.MotionEvent

/**
 * Created by HunJin on 2017-09-06.
 */

interface OnMenuClickListener {
    fun onItemClick(position: Int, order: Int)
}