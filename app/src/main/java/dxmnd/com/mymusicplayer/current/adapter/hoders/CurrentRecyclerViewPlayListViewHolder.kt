package dxmnd.com.mymusicplayer.current.adapter.hoders

import android.content.Context
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dxmnd.com.mymusicplayer.R
import dxmnd.com.mymusicplayer.utils.adapters.BaseViewHolder
import dxmnd.com.mymusicplayer.current.model.CurrentMusicItem
import dxmnd.com.mymusicplayer.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.item_rcv_current_music_info.view.*

/**
 * Created by HunJin on 2017-09-27.
 */
class CurrentRecyclerViewPlayListViewHolder(context: Context, val parent: ViewGroup, private val itemClickListener: OnItemClickListener?)
    : BaseViewHolder<CurrentMusicItem>(R.layout.item_rcv_current_music_info, context, parent) {

    override fun bindView(item: CurrentMusicItem, position: Int) {
        itemView?.let {
            itemView.txt_rcv_item_current_info_music_title.text = item.title
            itemView.txt_rcv_item_current_info_music_artist.text = item.artist
            Glide.with(context)
                    .load(item.thumbnail)
                    .into(itemView.img_rcv_item_current_info_thumbnail)
        }
        itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
    }

}