package dxmnd.com.mymusicplayer.current.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dxmnd.com.mymusicplayer.R

/**
 * Created by HunJin on 2017-09-13.
 */

class CurrentMusicInfoFragment : Fragment() {


    companion object {
        fun getInstance() = CurrentMusicInfoFragment()
        val TAG = CurrentMusicInfoFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = LayoutInflater.from(context).inflate(R.layout.fragment_current_music_info, container, false);

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get image url
        var url = ""


    }
}