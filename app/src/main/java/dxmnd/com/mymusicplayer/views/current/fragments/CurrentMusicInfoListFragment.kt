package dxmnd.com.mymusicplayer.views.current.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dxmnd.com.mymusicplayer.R

/**
 * Created by HunJin on 2017-09-27.
 */

class CurrentMusicInfoListFragment : Fragment() {

    companion object {
        fun getInstance() = CurrentMusicInfoListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = LayoutInflater.from(context).inflate(R.layout.fragment_current_music_info_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}