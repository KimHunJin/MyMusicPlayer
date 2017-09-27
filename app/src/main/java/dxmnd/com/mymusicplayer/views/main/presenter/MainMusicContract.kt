package dxmnd.com.mymusicplayer.views.main.presenter

import dxmnd.com.mymusicplayer.views.main.adapter.models.MainRecyclerViewModel

/**
 * Created by HunJin on 2017-09-04.
 */

interface MainMusicContract {
    interface View {

        fun mainAdapterNotify()

        fun onSuccessAddItem(position: Int)

        fun onSuccessRemoveItem()

    }

    interface Presenter {

        var view: View?

        var mainModel : MainRecyclerViewModel?

        fun loadDefaultItems()

        fun mainAdapterAddItem()

        fun mainAdapterItemClick(position: Int)

        fun mainAdapterRemoveItem()

        fun mainAdapterMoreItemClick(position: Int, order: Int)

    }
}