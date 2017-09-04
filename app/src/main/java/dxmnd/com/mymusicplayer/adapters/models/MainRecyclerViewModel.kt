package dxmnd.com.mymusicplayer.adapters.models

import dxmnd.com.mymusicplayer.datas.MainMusicItem

/**
 * Created by HunJin on 2017-09-04.
 */

interface MainRecyclerViewModel {
    fun addItem(mainMusicItem: MainMusicItem)

    fun getItem(position: Int): MainMusicItem?

    fun getItemCount(): Int

    fun removeItem(mainMusicItem: MainMusicItem)
}