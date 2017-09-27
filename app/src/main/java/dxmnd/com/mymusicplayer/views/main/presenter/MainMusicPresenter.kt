package dxmnd.com.mymusicplayer.views.main.presenter

import dxmnd.com.mymusicplayer.views.main.adapter.models.MainRecyclerViewModel
import dxmnd.com.mymusicplayer.datas.main.MainMusicItem

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

    override fun loadDefaultItems() {
        for (position in 0..5) {
            mainModel?.addItem(MainMusicItem(count++, "알 수 없는 제목", "알 수 없는 아티스트", null))
        }

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