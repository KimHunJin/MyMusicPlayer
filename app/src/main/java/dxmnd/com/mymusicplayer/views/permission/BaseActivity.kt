package dxmnd.com.mymusicplayer.views.permission

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import dxmnd.com.mymusicplayer.R
import dxmnd.com.mymusicplayer.utils.createSimpleOkErrorDialog
import dxmnd.com.mymusicplayer.views.permission.utils.REQUEST_PERMISSION_READ_EXTERNAL_STORAGE

/**
 * Created by HunJin on 2017-11-07.
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
            checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION_READ_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    event()
                } else {
                    createSimpleOkErrorDialog(this, R.string.permission_title, R.string.permission_not_accept).show()
                }
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    open fun event() {}
}