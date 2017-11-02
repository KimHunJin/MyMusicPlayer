package dxmnd.com.mymusicplayer.views.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import dxmnd.com.mymusicplayer.R
import dxmnd.com.mymusicplayer.datas.media.MediaItem
import dxmnd.com.mymusicplayer.utils.ReadMusicFile
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

    override fun onStart() {
        super.onStart()
        readPermission()
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



    // TODO : change MVP
    private fun addItemLocalDataMusicList() {

        val mediaList: List<MediaItem> = ReadMusicFile(this).getMediaList()
        mediaList.forEach { it -> Log.e("main", it.title + " " + it.artist) }
    }

    private fun readPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 300)

            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 300)
            }
        } else {
            addItemLocalDataMusicList()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            300 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addItemLocalDataMusicList()
                } else {
                    Toast.makeText(applicationContext, "권한 동의 하세요.", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
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
