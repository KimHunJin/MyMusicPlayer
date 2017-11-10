package dxmnd.com.mymusicplayer.utils

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import dxmnd.com.mymusicplayer.datas.media.MediaItem

/**
 * Created by HunJin on 2017-11-02.
 */


class ReadMusicFile(private var context: Context) {

    private var cursorColumns: Array<String> = Array(7, init = { "" })


    fun getMediaList(): List<MediaItem> = getMedia()

    @SuppressLint("Recycle")
    private fun getMedia(): List<MediaItem> {

        cursorColumns[0] = MediaStore.Audio.Media._ID
        cursorColumns[1] = MediaStore.Audio.Media.ARTIST
        cursorColumns[2] = MediaStore.Audio.Media.TITLE
        cursorColumns[3] = MediaStore.Audio.Media.ALBUM_ID
        cursorColumns[4] = MediaStore.Audio.Media.MIME_TYPE
        cursorColumns[5] = MediaStore.Audio.Media.DURATION
        cursorColumns[6] = MediaStore.Audio.Media.DATA

        val musicList: MutableList<MediaItem> = mutableListOf()
        val map: Map<Int, String> = albumArt()

        val cursor: Cursor = context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                cursorColumns, null, null, null) ?: return musicList



        if (cursor.moveToFirst()) {
            val idColumn = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
            val artistColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val singColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val albumColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)
            val mimeColumn = cursor.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE)
            val durationColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)
            val dataColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA)


            do {
                val mime = cursor.getString(mimeColumn)

                if (mime == "audio/mpeg") {
                    val id = cursor.getInt(idColumn)
                    val artist = cursor.getString(artistColumn)
                    val sing = cursor.getString(singColumn)
                    val albumId: Int = cursor.getInt(albumColumn)
                    val duration = cursor.getInt(durationColumn)
                    val data = cursor.getString(dataColumn)
                    var albumThumbnail = ""
                    if (map.containsKey(albumId)) {
                        albumThumbnail = map[albumId].toString()
                    }
                    val playListItem = MediaItem(id, sing, artist, albumThumbnail, duration.toLong(), data)
                    musicList.add(playListItem)
                }

            } while (cursor.moveToNext())
        }
        cursor.close()

        return musicList

    }

    private fun albumArt(): Map<Int, String> {
        val map: MutableMap<Int, String> = HashMap()

        val list: Array<String> = Array(2, init = { "" })
        list[0] = MediaStore.Audio.Albums._ID
        list[1] = MediaStore.Audio.Albums.ALBUM_ART


        val cursor: Cursor = context.contentResolver.query(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                list, null, null, null
        )

        cursor.let {
            if (cursor.moveToFirst()) {
                val albumArt: Int = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART)
                val albumId: Int = cursor.getColumnIndex(MediaStore.Audio.Albums._ID)
                do {
                    val key: Int = cursor.getString(albumId).toInt()
                    val value: String = cursor.getString(albumArt)
                    if (!map.containsKey(key)) {
                        map.put(key, value)
                    }
                } while (cursor.moveToNext())
                cursor.close()
            }
        }

        return map
    }
}
