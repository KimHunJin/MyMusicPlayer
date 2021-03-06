package dxmnd.com.mymusicplayer.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.os.PowerManager
import android.provider.MediaStore
import android.util.Log

/**
 * Created by HunJin on 2017-11-10.
 */

class MusicService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener,
        MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener, AudioManager.OnAudioFocusChangeListener {

    companion object {
        val TAG: String = MusicService::class.java.simpleName
    }

    private var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        initMusicPlayer()
    }

    private val binder: IBinder = MusicServiceBinder()

    private var player: MediaPlayer? = null
    private var audioManager: AudioManager? = null

    inner class MusicServiceBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    override fun onBind(intent: Intent?): IBinder = binder

    private fun initMusicPlayer() {

        context = applicationContext

        audioManager = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        player = MediaPlayer()

        player?.setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
        player?.setAudioStreamType(AudioManager.STREAM_MUSIC)

        player?.setOnCompletionListener(this)
        player?.setOnBufferingUpdateListener(this)
        player?.setOnErrorListener(this)
        player?.setOnInfoListener(this)
        player?.setOnPreparedListener(this)
        player?.setOnSeekCompleteListener(this)

        player?.reset()

        Log.e(TAG, "init")
        Log.e(TAG, "player : " + player.toString())
    }

    fun startMusic(id: String) {
        if (!requestAudioFocus()) {
            Log.e(TAG, "requestAudioFocus is " + requestAudioFocus())
            player?.release()
            stopSelf()
        } else {
            if(player==null) {
                player = MediaPlayer()
            }
            val musicUri: Uri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
            Log.e(TAG, "music start : " + musicUri.toString())
            try {
                Log.e(TAG, player.toString())
                player?.setDataSource(context, musicUri)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            player?.prepareAsync()
        }
    }


    override fun onPrepared(mp: MediaPlayer?) {
        Log.e(TAG, "start")
        player?.start()
//        mp?.start()
//        player?.start()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        Log.e(TAG, "media player error : " + what + extra)
        return false
    }

    override fun onCompletion(mp: MediaPlayer?) {
        Log.e(TAG, "music complete")
        mp?.stop()
    }

    override fun onSeekComplete(mp: MediaPlayer?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInfo(mp: MediaPlayer?, what: Int, extra: Int): Boolean = false

    override fun onBufferingUpdate(mp: MediaPlayer?, percent: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAudioFocusChange(focusChange: Int) {
        Log.e(TAG, focusChange.toString())
        when (focusChange) {
            AudioManager.AUDIOFOCUS_GAIN -> {
                player = MediaPlayer()
                player?.let {
                    if (player!!.isPlaying) {
                        player!!.stop()
                    }
                    player!!.setVolume(1.0f, 1.0f)
                }

            }
            AudioManager.AUDIOFOCUS_LOSS -> {
                player?.let {
                    if (player!!.isPlaying) {
                        player!!.stop()
                    }
                    player!!.release()
                }
            }
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                player.let {
                    if (player!!.isPlaying) {
                        player!!.pause()
                    }
                }
            }
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                player.let {
                    if (player!!.isPlaying) {
                        player!!.setVolume(0.1f, 0.1f)
                    }
                }
            }
        }
    }

    private fun requestAudioFocus(): Boolean {
        // 정상 작동
        Log.e(TAG, "requestFocus Context : " + this.toString())
        // 펑
//        audioManager = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val result: Int? = audioManager?.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            return true
        }
        return false
    }

    private fun removeAudioFocus(): Boolean = AudioManager.AUDIOFOCUS_REQUEST_GRANTED == audioManager?.abandonAudioFocus(this)

}