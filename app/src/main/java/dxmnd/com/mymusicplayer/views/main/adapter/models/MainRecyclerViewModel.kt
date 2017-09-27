package dxmnd.com.mymusicplayer.views.main.adapter.models

import dxmnd.com.mymusicplayer.datas.main.MainMusicItem

/**
 * Created by HunJin on 2017-09-04.
 */

interface MainRecyclerViewModel {
    fun addItem(mainMusicItem: MainMusicItem)

    fun getItem(position: Int): MainMusicItem?

    fun getItemCount(): Int

    fun removeItem(mainMusicItem: MainMusicItem)
}