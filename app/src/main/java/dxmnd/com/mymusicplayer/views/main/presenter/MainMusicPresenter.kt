package dxmnd.com.mymusicplayer.views.main.presenter

import dxmnd.com.mymusicplayer.datas.main.MainMusicItem
import dxmnd.com.mymusicplayer.datas.media.MediaItem
import dxmnd.com.mymusicplayer.views.main.adapter.models.MainRecyclerViewModel

/**
 * Created by HunJin on 2017-09-04.
 */

class MainMusicPresenter : MainMusicContract.Presenter {

    companion object {
        private val TAG = MainMusicPresenter::class.java.simpleName
    }

    override var view: MainMusicContract.View? = null

    override var mainModel: MainRecyclerViewModel? = null

    private var count = 0

    override fun loadDefaultItems(mediaList: List<MediaItem>) {

        mediaList.forEach { it -> mainModel?.addItem(MainMusicItem(count++, it.title, it.artist, it.thumbnailImagePath)) }

        view?.mainAdapterNotify()
    }

    override fun mainAdapterAddItem() {
        mainModel?.addItem(MainMusicItem(count++, "알 수 없는 제목", "알 수 없는 아티스트", null))
        view?.onSuccessAddItem(mainModel?.getItemCount()?.let { it - 1 } ?: 0)
        view?.mainAdapterNotify()
    }

    override fun mainAdapterItemClick(position: Int) {
        mainModel?.getItem(position)?.let {
            it.isItemSelected = !it.isItemSelected
            view?.mainAdapterNotify()

            view?.onBindService(it.id.toString())
        }
    }


    override fun mainAdapterMoreItemClick(position: Int, order: Int) {
        mainModel?.getItem(position)?.let {
            when (order) {
                1 -> {
                    //ToDo add current list
                }
                2 -> {
                    //ToDo close
                }
                else -> {

                }
            }
        }
    }

    override fun mainAdapterRemoveItem() {

    }

}