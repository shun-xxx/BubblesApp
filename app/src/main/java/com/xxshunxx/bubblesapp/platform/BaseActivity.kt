package com.xxshunxx.bubblesapp.platform

import android.app.*
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.xxshunxx.bubblesapp.R
import com.xxshunxx.bubblesapp.bubbles.BubblesActivity
import com.xxshunxx.bubblesapp.bubbles.BubblesNotification.Companion.NOTIFICATION_CHANNEL_ID
import com.xxshunxx.bubblesapp.bubbles.BubblesNotification.Companion.NOTIFICATION_CHANNEL_NAME
import com.xxshunxx.bubblesapp.bubbles.BubblesService
import kotlinx.android.synthetic.main.activity_base.*
import java.util.*
import kotlin.concurrent.schedule

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        createNotificationChannel()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, BaseFragment.createInstance())
            .commit()
    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NotificationManager::class.java) ?: return finish()

        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH)
        channel.setAllowBubbles(true)

        notificationManager.createNotificationChannel(channel)
    }
}