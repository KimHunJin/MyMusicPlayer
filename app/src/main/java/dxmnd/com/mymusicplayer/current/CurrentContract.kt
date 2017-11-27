package dxmnd.com.mymusicplayer.current

import dxmnd.com.mymusicplayer.current.adapter.models.CurrentRecyclerViewModel

/**
 * Created by HunJin on 2017-09-27.
 */


interface CurrentContract {
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