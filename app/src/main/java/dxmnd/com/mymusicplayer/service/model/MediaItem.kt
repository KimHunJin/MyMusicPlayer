package dxmnd.com.mymusicplayer.service.model

import dxmnd.com.mymusicplayer.models.MediaItemMarker

/**
 * Created by HunJin on 2017-11-02.
 */

data class MediaItem(val id: Int, val title: String, val artist: String, val thumbnailImagePath: String, val duration: Long, val data: String) : MediaItemMarker {
    var isItemSelected : Boolean = false
}
