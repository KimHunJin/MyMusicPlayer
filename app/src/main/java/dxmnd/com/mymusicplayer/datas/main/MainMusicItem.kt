package dxmnd.com.mymusicplayer.datas.main

/**
 * Created by HunJin on 2017-08-24.
 */


data class MainMusicItem(val id: Int, val title: String, val artist: String, val thumbNail : String?) {
    var duration : Long ?= null
    var isItemSelected: Boolean = false
}