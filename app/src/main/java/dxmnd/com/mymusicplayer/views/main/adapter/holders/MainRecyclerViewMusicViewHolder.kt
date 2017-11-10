package dxmnd.com.mymusicplayer.views.main.adapter.holders

import android.content.Context
import android.support.v7.widget.PopupMenu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import dxmnd.com.mymusicplayer.R
import dxmnd.com.mymusicplayer.adapters.BaseViewHolder
import dxmnd.com.mymusicplayer.datas.main.MainMusicItem
import dxmnd.com.mymusicplayer.listeners.OnMenuClickListener

import kotlinx.android.synthetic.main.item_rcv_main_music_info.view.*

/**
 * Created by HunJin on 2017-08-23.
 */

class MainRecyclerViewMusicViewHolder(context: Context, parent: ViewGroup?, private val onMoreClickListener: OnMenuClickListener?)
    : BaseViewHolder<MainMusicItem>(R.layout.item_rcv_main_music_info, context, parent) {

    override fun bindView(item: MainMusicItem, position: Int) {

        // 아이템이 한 개일 경우 it을 활용해도 된다.
        // 아이템이 여러개이기에 각 아이템을 불러 활용했다.
        // 일단 아이템이 널이 아니면 돌아간다.
        itemView?.let {
            if(item.isItemSelected) {
                itemView.rl_item_view.setBackgroundResource(R.color.colorHighlight)
            } else{
                itemView.rl_item_view.setBackgroundResource(R.color.colorWhite)
            }

            itemView.img_rcv_item_main_info_more.setOnClickListener {
                var popMenu = PopupMenu(context, itemView.img_rcv_item_main_info_more)
                popMenu.setOnMenuItemClickListener {
                    onMoreClickListener?.onItemClick(position, it.order)
                    false
                }
                popMenu.show()
            }

            itemView.txt_rcv_item_main_info_music_title.text = item.title
            itemView.txt_rcv_item_main_info_music_artist.text = item.artist
            Glide.with(context).load(item.thumbNail).into(itemView.img_rcv_item_main_info_thumbnail)
        }
    }
}
