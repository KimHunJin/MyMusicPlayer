package dxmnd.com.mymusicplayer.views.main

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import dxmnd.com.mymusicplayer.R
import dxmnd.com.mymusicplayer.datas.media.MediaItem
import dxmnd.com.mymusicplayer.service.MusicService
import dxmnd.com.mymusicplayer.utils.ReadMusicFile
import dxmnd.com.mymusicplayer.views.main.adapter.MainRecyclerViewMusicAdapter
import dxmnd.com.mymusicplayer.views.main.presenter.MainMusicContract
import dxmnd.com.mymusicplayer.views.main.presenter.MainMusicPresenter
import dxmnd.com.mymusicplayer.views.permission.BaseActivity
import dxmnd.com.mymusicplayer.views.permission.utils.REQUEST_PERMISSION_READ_EXTERNAL_STORAGE


class MainActivity : BaseActivity(), MainMusicContract.View, ServiceConnection {


    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

    private val rcvMainMusicList by lazy { findViewById(R.id.rcv_main_music_list) as RecyclerView }
    private val fabMainMusicAdd by lazy { findViewById(R.id.fab_main_music_add) as FloatingActionButton }
    private val toolbarMain by lazy { findViewById(R.id.toolbar_main) as Toolbar }

    private var mainMusicAdapter: MainRecyclerViewMusicAdapter? = null
    private var presenter: MainMusicContract.Presenter? = null

    private var isBind: Boolean = false

    private lateinit var bindService: MusicService

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


    private var id: String = ""
    override fun onBindService(id: String) {
        val intent = MusicService.newIntent(this)
        bindService(intent, this, Context.BIND_AUTO_CREATE)
        this.id = id
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
        Log.e(TAG, bindService.toString())
        bindService.startMusic(id)
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

        }
    }

    override fun event() {
        super.event()
        val mediaList: List<MediaItem> = ReadMusicFile(this).getMediaList()
        presenter?.loadDefaultItems(mediaList)
    }


    override fun mainAdapterNotify() {
        mainMusicAdapter?.notifyDataSetChanged()
    }

    override fun onSuccessAddItem(position: Int) {
        Toast.makeText(this, "Item Info : " + mainMusicAdapter?.getList()?.get(position).let { it?.title }, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessRemoveItem() {
    }

    override fun onStop() {
        if (isBind) {
            unbindService(this)
            isBind = false
        }
        super.onStop()
    }

}
