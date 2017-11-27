package dxmnd.com.mymusicplayer.current.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import dxmnd.com.mymusicplayer.R
import dxmnd.com.mymusicplayer.current.utils.replaceFragmentToActivity
import dxmnd.com.mymusicplayer.current.view.fragments.CurrentMusicInfoFragment
import dxmnd.com.mymusicplayer.current.view.fragments.CurrentMusicInfoListFragment

class CurrentPlayMusicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_play_music)

        replaceFragmentToActivity(CurrentMusicInfoFragment.getInstance(), R.id.fragment_container_current_play_music)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.current_play_music_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_current_show_list -> {
                if(item.title.equals("재생목록")) {
                    item.title = "현재음악"
                    item.setIcon(R.drawable.ic_dehaze_red_24dp)
                    Toast.makeText(this,"Change 재생목록 -> 현재음악",Toast.LENGTH_SHORT).show()
                    replaceFragmentToActivity(CurrentMusicInfoListFragment.getInstance(), R.id.fragment_container_current_play_music)
                } else{
                    item.title = "재생목록"
                    item.setIcon(R.drawable.ic_dehaze_black_24dp)
                    Toast.makeText(this,"Change 현재음악 -> 재생목록",Toast.LENGTH_SHORT).show()
                    replaceFragmentToActivity(CurrentMusicInfoFragment.getInstance(),R.id.fragment_container_current_play_music)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
