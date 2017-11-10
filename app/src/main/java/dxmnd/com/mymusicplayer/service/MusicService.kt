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
        fun newIntent(context: Context): Intent = Intent(context, MusicService::class.java)
    }

    override fun onCreate() {
        super.onCreate()
        initMusicPlayer()
    }

    private val binder: IBinder = MusicServiceBinder()

    private var player: MediaPlayer? = MediaPlayer()
    private var audioManager: AudioManager? = null

    open class MusicServiceBinder : Binder() {
        fun getService(): MusicService = MusicService()
    }

    override fun onBind(intent: Intent?): IBinder = binder

    private fun initMusicPlayer() {

        if (player == null) {
            player = MediaPlayer()
        }

        player?.setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
        player?.setAudioStreamType(AudioManager.STREAM_MUSIC)

        player?.setOnCompletionListener(this)
        player?.setOnBufferingUpdateListener(this)
        player?.setOnErrorListener(this)
        player?.setOnInfoListener(this)
        player?.setOnPreparedListener(this)
        player?.setOnSeekCompleteListener(this)

        player?.reset()
    }

    private fun startMusic(id: String) {
        if (requestAudioFocus()) {
            stopSelf()
        } else {
            val musicUri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
            player?.setDataSource(applicationContext, musicUri)
            player?.prepareAsync()
        }
    }


    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        Log.e(TAG, "media player error : " + what + extra)
        return false
    }

    override fun onCompletion(mp: MediaPlayer?) {
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
        when (focusChange) {
            AudioManager.AUDIOFOCUS_GAIN -> {
                if (player == null) {
                    player = MediaPlayer()
                }
                player.let {
                    if (player!!.isPlaying) {
                        player?.stop()
                    }
                    player?.setVolume(1.0f, 1.0f)
                }

            }
            AudioManager.AUDIOFOCUS_LOSS -> {
                player?.let {
                    if (player!!.isPlaying) {
                        player?.stop()
                    }
                    player?.release()
                    player = null
                }
            }
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                player?.let {
                    if (player!!.isPlaying) {
                        player?.pause()
                    }
                }
            }
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                player?.let {
                    if (player!!.isPlaying) {
                        player?.setVolume(0.1f, 0.1f)
                    }
                }
            }
        }
    }

    private fun requestAudioFocus(): Boolean {
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        var result: Int? = audioManager?.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            return true
        }
        return false
    }

    private fun removeAudioFocus(): Boolean = AudioManager.AUDIOFOCUS_REQUEST_GRANTED == audioManager?.abandonAudioFocus(this)


}