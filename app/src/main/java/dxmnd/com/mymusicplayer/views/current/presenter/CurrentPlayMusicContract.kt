package dxmnd.com.mymusicplayer.views.current.presenter

import dxmnd.com.mymusicplayer.views.current.adapter.models.CurrentRecyclerViewModel

/**
 * Created by HunJin on 2017-09-27.
 */


interface CurrentPlayMusicContract {
    interface View {
        fun currentAdapterNotify()
    }

    interface Presenter {
        var view: View?
        var currentModel: CurrentRecyclerViewModel?

        fun currentAdapterAddItem()

        fun currentAdapterItemClick(position: Int)

        fun currentAdapterRemoveItem()

        fun currentAdapterMoreItemClick(position: Int, order: Int)
    }
}