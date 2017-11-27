package dxmnd.com.mymusicplayer.current.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import dxmnd.com.mymusicplayer.utils.adapters.BaseViewHolder
import dxmnd.com.mymusicplayer.current.model.CurrentMusicItem
import dxmnd.com.mymusicplayer.listeners.OnItemClickListener
import dxmnd.com.mymusicplayer.current.adapter.hoders.CurrentRecyclerViewPlayListViewHolder
import dxmnd.com.mymusicplayer.current.adapter.models.CurrentRecyclerViewModel

/**
 * Created by HunJin on 2017-09-27.
 */

class CurrentRecyclerViewPlayListAdapter(private val context: Context) : RecyclerView.Adapter<BaseViewHolder<CurrentMusicItem>>(), CurrentRecyclerViewModel {

    private var currentItemList: MutableList<CurrentMusicItem> = ArrayList()

    private var onItemClick: OnItemClickListener? = null
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CurrentMusicItem>
            = CurrentRecyclerViewPlayListViewHolder(context, parent, onItemClick)

    override fun onBindViewHolder(holder: BaseViewHolder<CurrentMusicItem>?, position: Int) {
        holder?.bindView(getItem(position), position)
        holder?.itemView?.setOnClickListener {
            onItemClick?.onItemClick(position)
        }
    }

    // current activity에서 listener을 달고 presenter에서 조작한다.
    fun setOnClickListener(listener: (Int) -> Unit) {
        this.onItemClick = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                listener(position)
            }
        }
    }

    override fun getItemCount(): Int = currentItemList.size

    override fun getItem(position: Int): CurrentMusicItem = currentItemList[position]

    override fun addItem(musicItem: CurrentMusicItem) {
        currentItemList.add(musicItem)
    }
}