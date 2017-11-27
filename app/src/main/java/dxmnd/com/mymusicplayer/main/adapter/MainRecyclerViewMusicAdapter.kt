package dxmnd.com.mymusicplayer.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import dxmnd.com.mymusicplayer.listeners.OnItemClickListener
import dxmnd.com.mymusicplayer.listeners.OnMenuClickListener
import dxmnd.com.mymusicplayer.main.adapter.holders.MainRecyclerViewMusicViewHolder
import dxmnd.com.mymusicplayer.main.adapter.models.MainRecyclerViewModel
import dxmnd.com.mymusicplayer.service.model.MediaItem
import dxmnd.com.mymusicplayer.utils.adapters.BaseViewHolder

/**
 * Created by HunJin on 2017-08-23.
 */

class MainRecyclerViewMusicAdapter(private val mContext: Context) : RecyclerView.Adapter<BaseViewHolder<MediaItem>>(), MainRecyclerViewModel {


    private var mainItemList: MutableList<MediaItem> = ArrayList()

    var onItemClickListener: OnItemClickListener? = null
        private set

    var onMoreClickListener: OnMenuClickListener? = null
        private set

    override fun onBindViewHolder(holder: BaseViewHolder<MediaItem>?, position: Int) {
        holder?.itemView?.setOnClickListener { onItemClickListener?.onItemClick(position) }
        holder?.bindView(getItem(position), position)
    }

    fun setOnClickListener(listener: (Int) -> Unit) {
        this.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                listener(position)
            }
        }
    }

    fun setOnMoreClickLisener(listener: (Int) -> Unit) {
        this.onMoreClickListener = object : OnMenuClickListener {
            override fun onItemClick(position: Int, order: Int) {
                listener(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainRecyclerViewMusicViewHolder(mContext, parent, onMoreClickListener)


    override fun getItemCount() = mainItemList.size


    fun getList() = mainItemList

    override fun addItem(mainMusicItem: List<MediaItem>) {
        mainItemList = mainMusicItem as MutableList<MediaItem>
    }

    override fun removeItem(mainMusicItem: MediaItem) {
        mainItemList.remove(mainMusicItem)
    }

    override fun getItem(position: Int) = mainItemList[position]

}
