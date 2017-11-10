package dxmnd.com.mymusicplayer.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.support.annotation.StringRes
import dxmnd.com.mymusicplayer.R

/**
 * Created by HunJin on 2017-11-10.
 */
fun createSimpleOkErrorDialog(context: Context, title: String, message: String): Dialog
        = AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setNeutralButton(R.string.dialog_ok, null)
        .create()

fun createSimpleOkErrorDialog(context: Context, @StringRes titleResource: Int, @StringRes messageResource: Int): Dialog
        = createSimpleOkErrorDialog(context, context.getString(titleResource), context.getString(messageResource))

fun createSimpleOkErrorDialog(context: Context, message: String): Dialog
        = AlertDialog.Builder(context)
        .setTitle(context.getString(R.string.dialog_error_name))
        .setMessage(message)
        .setNeutralButton(R.string.dialog_ok, null)
        .create()

fun createSimpleOkErrorDialog(context: Context, @StringRes messageResource: Int): Dialog
        = createSimpleOkErrorDialog(context, context.getString(messageResource))
