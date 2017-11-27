package dxmnd.com.mymusicplayer.current.model

/**
 * Created by HunJin on 2017-09-27.
 */

data class CurrentMusicItem(val id: Int, val title: String, val artist: String, val thumbnail: String?) {
    var duration: Long? = null
}