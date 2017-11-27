package dxmnd.com.mymusicplayer.current.adapter.models

import dxmnd.com.mymusicplayer.current.model.CurrentMusicItem

/**
 * Created by HunJin on 2017-09-27.
 */
interface CurrentRecyclerViewModel {
    fun addItem(currentMusicItem: CurrentMusicItem)

    fun getItem(position: Int): CurrentMusicItem?
}