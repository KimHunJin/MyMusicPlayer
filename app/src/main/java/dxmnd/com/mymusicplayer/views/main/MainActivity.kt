package dxmnd.com.mymusicplayer.views.main

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast
import dxmnd.com.mymusicplayer.R
import dxmnd.com.mymusicplayer.views.main.adapter.MainRecyclerViewMusicAdapter
import dxmnd.com.mymusicplayer.views.main.presenter.MainMusicContract
import dxmnd.com.mymusicplayer.views.main.presenter.MainMusicPresenter

class MainActivity : AppCompatActivity(), MainMusicContract.View {


    private val rcvMainMusicList by lazy { findViewById(R.id.rcv_main_music_list) as RecyclerView }
    private val fabMainMusicAdd by lazy { findViewById(R.id.fab_main_music_add) as FloatingActionButton }
    private val toolbarMain by lazy { findViewById(R.id.toolbar_main) as Toolbar }

    private var mainMusicAdapter: MainRecyclerViewMusicAdapter? = null
    private var Presenter: MainMusicContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarMain.title = "Music Player"
        setSupportActionBar(toolbarMain)

        initialize()
    }

    private fun initialize() {

        Presenter = MainMusicPresenter()
        Presenter?.view = this

        mainMusicAdapter = MainRecyclerViewMusicAdapter(this)

        Presenter?.mainModel = mainMusicAdapter

        rcvMainMusicList.setHasFixedSize(true)
        rcvMainMusicList.layoutManager = LinearLayoutManager(this)
        rcvMainMusicList.adapter = mainMusicAdapter

        mainMusicAdapter?.setOnClickListener {
            Presenter?.mainAdapterItemClick(it)
        }

        fabMainMusicAdd.setOnClickListener {
            Presenter?.mainAdapterAddItem()
        }

        Presenter?.loadDefaultItems()
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
