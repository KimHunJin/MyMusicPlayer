package dxmnd.com.mymusicplayer.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dxmnd.com.mymusicplayer.R

import dxmnd.com.mymusicplayer.adapters.holders.MainRecyclerViewMusicViewHolder
import dxmnd.com.mymusicplayer.adapters.models.MainRecyclerViewModel
import dxmnd.com.mymusicplayer.datas.MainMusicItem
import dxmnd.com.mymusicplayer.listeners.OnItemClickListener

/**
 * Created by HunJin on 2017-08-23.
 */

class MainRecyclerViewMusicAdapter(private val mContext: Context) : RecyclerView.Adapter<MainRecyclerViewMusicViewHolder>(), MainRecyclerViewModel {

    private var mainItemList: MutableList<MainMusicItem> = ArrayList()

    var onItemClickListener : OnItemClickListener? = null
    private set

    fun setOnClickListener(listener: (Int) -> Unit) {
        this.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                listener(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainRecyclerViewMusicViewHolder(mContext, parent)

    override fun onBindViewHolder(holder: MainRecyclerViewMusicViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onItemClickListener?.onItemClick(position) }
        holder.bindView(getItem(position), position)
    }

    override fun getItemCount() = mainItemList.size


    fun getList() = mainItemList

    override fun addItem(mainMusicItem: MainMusicItem) {
        mainItemList.add(mainMusicItem)
    }

    override fun removeItem(mainMusicItem: MainMusicItem) {
        mainItemList.remove(mainMusicItem)
    }

    override fun getItem(position: Int) = mainItemList[position]

}
