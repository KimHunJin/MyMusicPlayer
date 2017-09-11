package dxmnd.com.mymusicplayer.views

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast
import dxmnd.com.mymusicplayer.R
import dxmnd.com.mymusicplayer.adapters.MainRecyclerViewMusicAdapter
import dxmnd.com.mymusicplayer.views.presenter.MainMusicContract
import dxmnd.com.mymusicplayer.views.presenter.MainMusicPresenter

class MainActivity : AppCompatActivity(), MainMusicContract.View {


    private val rcvMainMusicList by lazy { findViewById(R.id.rcv_main_music_list) as RecyclerView }
    private val fabMainMusicAdd by lazy { findViewById(R.id.fab_main_music_add) as FloatingActionButton }
    private val toolbarMain by lazy { findViewById(R.id.toolbar) as Toolbar }

    private var mainMusicAdapter: MainRecyclerViewMusicAdapter? = null
    private var presenter: MainMusicContract.presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarMain.title = "Music Player"
        setSupportActionBar(toolbarMain)

        initialize()
    }

    private fun initialize() {

        presenter = MainMusicPresenter()
        presenter?.view = this

        mainMusicAdapter = MainRecyclerViewMusicAdapter(this)

        presenter?.mainModel = mainMusicAdapter

        rcvMainMusicList.setHasFixedSize(true)
        rcvMainMusicList.layoutManager = LinearLayoutManager(this)
        rcvMainMusicList.adapter = mainMusicAdapter

        mainMusicAdapter?.setOnClickListener {
            presenter?.mainAdapterItemClick(it)
        }

        fabMainMusicAdd.setOnClickListener {
            presenter?.mainAdapterAddItem()
        }

        presenter?.loadDefaultItems()
    }

    override fun mainAdapterNotify() {
        mainMusicAdapter?.notifyDataSetChanged()
    }

    override fun onSuccessAddItem(position: Int) {
        Toast.makeText(this, "Item Info : " + mainMusicAdapter?.getList()?.get(position).let { it?.title }, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessRemoveItem() {
    }
}
