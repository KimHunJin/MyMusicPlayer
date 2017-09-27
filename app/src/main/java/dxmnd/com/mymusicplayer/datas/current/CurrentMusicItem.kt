package dxmnd.com.mymusicplayer.datas.current

/**
 * Created by HunJin on 2017-09-27.
 */

data class CurrentMusicItem(val id: Int, val title: String, val artist: String, val thumbnail: String?) {
    var duration: Long? = null
}