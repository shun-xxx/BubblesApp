package com.xxshunxx.bubblesapp.bubbles

import android.app.Service
import android.content.Intent
import android.os.IBinder

class BubblesService : Service() {

    companion object {
        const val KEY_IS_AUTO_EXPAND = "key_is_auto_expand"
        const val KEY_IS_CALL_CATEGORY = "key_is_call_category"
        const val KEY_BUBBLES_MESSAGE = "key_bubbles_message"
    }

    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return if (intent is Intent) {
            val notification = BubblesNotification(
                context = this,
                isAutoExpand = intent.getBooleanExtra(KEY_IS_AUTO_EXPAND, false),
                isCallCategory = intent.getBooleanExtra(KEY_IS_CALL_CATEGORY, false),
                message = intent.getStringExtra(KEY_BUBBLES_MESSAGE) ?: ""
            ).createBubblesNotification()

            startForeground(1, notification)

            START_STICKY
        } else START_NOT_STICKY
    }
}