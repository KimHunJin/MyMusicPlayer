package dxmnd.com.mymusicplayer.current.presenter

import dxmnd.com.mymusicplayer.current.CurrentContract
import dxmnd.com.mymusicplayer.current.adapter.models.CurrentRecyclerViewModel

/**
 * Created by HunJin on 2017-09-27.
 */

class CurrentMusicPresenter : CurrentContract.Presenter {
    override var view: CurrentContract.View? = null

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