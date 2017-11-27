package dxmnd.com.mymusicplayer.main.presenter

import dxmnd.com.mymusicplayer.main.MainContract
import dxmnd.com.mymusicplayer.main.adapter.models.MainRecyclerViewModel
import dxmnd.com.mymusicplayer.service.model.MediaItem

/**
 * Created by HunJin on 2017-09-04.
 */

class MainMusicPresenter : MainContract.Presenter {


    override var view: MainContract.View? = null

    override var mainModel: MainRecyclerViewModel? = null

    override fun loadLocalItems(mediaList: List<MediaItem>) {
        mainModel?.addItem(mediaList)
        view?.mainAdapterNotify()
    }

}