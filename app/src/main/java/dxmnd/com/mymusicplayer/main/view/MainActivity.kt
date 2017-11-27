package dxmnd.com.mymusicplayer.main.view

import android.Manifest
import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast
import dxmnd.com.mymusicplayer.R
import dxmnd.com.mymusicplayer.main.MainContract
import dxmnd.com.mymusicplayer.main.adapter.MainRecyclerViewMusicAdapter
import dxmnd.com.mymusicplayer.main.presenter.MainMusicPresenter
import dxmnd.com.mymusicplayer.main.util.ReadMusicFile
import dxmnd.com.mymusicplayer.permission.BaseActivity
import dxmnd.com.mymusicplayer.permission.util.REQUEST_PERMISSION_READ_EXTERNAL_STORAGE
import dxmnd.com.mymusicplayer.service.MusicService
import dxmnd.com.mymusicplayer.service.model.MediaItem


class MainActivity : BaseActivity(), MainContract.View, ServiceConnection {


    private val rcvMainMusicList by lazy { findViewById(R.id.rcv_main_music_list) as RecyclerView }
    private val fabMainMusicAdd by lazy { findViewById(R.id.fab_main_music_add) as FloatingActionButton }
    private val toolbarMain by lazy { findViewById(R.id.toolbar_main) as Toolbar }

    private var mainMusicAdapter: MainRecyclerViewMusicAdapter? = null
    private var presenter: MainContract.Presenter? = null

    private var bindService: MusicService? = null

    private var isBind: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarMain.title = "Music Player"
        setSupportActionBar(toolbarMain)

        initialize()
    }


    override fun onStart() {
        super.onStart()
        val readExternalStoragePermission: String = Manifest.permission.READ_EXTERNAL_STORAGE
        if (hasPermission(readExternalStoragePermission)) {
            event()
        } else {
            requestPermissionSafely(arrayOf(readExternalStoragePermission), REQUEST_PERMISSION_READ_EXTERNAL_STORAGE)
        }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        isBind = false
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        if (isBind) {
            return
        }
        val binder: MusicService.MusicServiceBinder = service as MusicService.MusicServiceBinder
        bindService = binder.getService()
        isBind = true
    }

    private fun initialize() {

        presenter = MainMusicPresenter()
        presenter?.view = this

        mainMusicAdapter = MainRecyclerViewMusicAdapter(this)

        presenter?.mainModel = mainMusicAdapter

        rcvMainMusicList.setHasFixedSize(true)
        rcvMainMusicList.layoutManager = LinearLayoutManager(this)
        rcvMainMusicList.adapter = mainMusicAdapter

        presenter?.loadLocalItems(ReadMusicFile(this).getMediaList())

        mainMusicAdapter?.setOnClickListener {

        }

        fabMainMusicAdd.setOnClickListener {

        }
    }

    override fun onSuccessRemoveItem() {

    }

    override fun mainAdapterAddItem(mediaList: List<MediaItem>) {
        mainMusicAdapter?.addItem(mediaList)
    }

    override fun mainAdapterItemClick(position: Int) {
        Toast.makeText(this, mainMusicAdapter?.getList()?.get(position)?.title, Toast.LENGTH_SHORT).show()
    }

    override fun mainAdapterMoreItemClick(position: Int, order: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mainAdapterRemoveItem() {
        // remove
    }

    override fun mainAdapterNotify() {
        mainMusicAdapter?.notifyDataSetChanged()
    }

}
