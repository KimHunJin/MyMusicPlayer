package dxmnd.com.mymusicplayer.adapters.holders

import android.content.Context
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import dxmnd.com.mymusicplayer.R
import dxmnd.com.mymusicplayer.datas.MainMusicItem
import dxmnd.com.mymusicplayer.listeners.OnMenuClickListener

/**
 * Created by HunJin on 2017-08-23.
 */

class MainRecyclerViewMusicViewHolder(val context: Context, parent: ViewGroup?, val onMoreClickListener: OnMenuClickListener?)
    : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rcv_main_music_info, parent, false)) {

    private val txtTitle by lazy { itemView?.findViewById(R.id.txt_rcv_item_main_info_music_title) as TextView }
    private val txtArtist by lazy { itemView?.findViewById(R.id.txt_rcv_item_main_info_music_artist) as TextView }
    private val imgThumbnail by lazy { itemView?.findViewById(R.id.img_rcv_item_main_info_thumbnail) as ImageView }
    private val imgMore by lazy { itemView?.findViewById(R.id.img_rcv_item_main_info_more) as ImageView }

    private val rlView by lazy { itemView?.findViewById(R.id.rl_item_view) as RelativeLayout }

    fun bindView(item: MainMusicItem, position: Int) {

        if (item.isItemSelected) {
            rlView.setBackgroundResource(R.color.colorHighlight)
        } else {
            rlView.setBackgroundResource(R.color.colorWhite)
        }

        imgMore.setOnClickListener {
            var popMenu = PopupMenu(context, imgMore)
            popMenu.inflate(R.menu.main_more_menu)
            popMenu.setOnMenuItemClickListener {
                onMoreClickListener?.onItemClick(position, it.order)
                false
            }
            popMenu.show()
        }

        txtTitle.text = item.title
        txtArtist.text = item.artist

    }

}
