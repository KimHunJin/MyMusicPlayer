package dxmnd.com.mymusicplayer.main.adapter.models

import dxmnd.com.mymusicplayer.service.model.MediaItem

/**
 * Created by HunJin on 2017-09-04.
 */

interface MainRecyclerViewModel {
    fun addItem(mainMusicItem: List<MediaItem>)

    fun getItem(position: Int): MediaItem?

    fun getItemCount(): Int

    fun removeItem(mainMusicItem: MediaItem)
}