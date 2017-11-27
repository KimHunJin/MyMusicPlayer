package dxmnd.com.mymusicplayer.main

import dxmnd.com.mymusicplayer.main.adapter.models.MainRecyclerViewModel
import dxmnd.com.mymusicplayer.service.model.MediaItem

/**
 * Created by HunJin on 2017-09-04.
 */

interface MainContract {
    interface View {

        fun mainAdapterAddItem(mediaList: List<MediaItem>)

        fun mainAdapterItemClick(position: Int)

        fun mainAdapterMoreItemClick(position: Int, order: Int)

        fun mainAdapterRemoveItem()

        fun mainAdapterNotify()

        fun onSuccessRemoveItem()

    }

    interface Presenter {

        var view: View?

        var mainModel: MainRecyclerViewModel?

        fun loadLocalItems(mediaList: List<MediaItem>)

    }
}