package dxmnd.com.mymusicplayer.views.current.presenter

import dxmnd.com.mymusicplayer.views.current.adapter.models.CurrentRecyclerViewModel

/**
 * Created by HunJin on 2017-09-27.
 */

class CurrentMusicPresenter : CurrentPlayMusicContract.Presenter {
    override var view: CurrentPlayMusicContract.View? = null

    override var currentModel: CurrentRecyclerViewModel? = null

    override fun currentAdapterAddItem() {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun currentAdapterItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun currentAdapterRemoveItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun currentAdapterMoreItemClick(position: Int, order: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}