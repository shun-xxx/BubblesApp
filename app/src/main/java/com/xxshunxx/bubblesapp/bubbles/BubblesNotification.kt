package com.xxshunxx.bubblesapp.bubbles

import android.app.Notification
import android.app.PendingIntent
import android.app.Person
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import com.xxshunxx.bubblesapp.R
import java.util.*

class BubblesNotification(
    private val context: Context,
    private val isAutoExpand: Boolean = false,
    private val isCallCategory: Boolean = false,
    private val message: String = ""
) {

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "notification_channel_id"
        const val NOTIFICATION_CHANNEL_NAME = "Example Bubbles Channel"
    }

    fun createBubblesNotification(): Notification {
        val target = Intent(context, BubblesActivity::class.java).apply {
            putExtra(BubblesActivity.KEY_DISPLAY_IS_AUTO, "isAutoExpand: $isAutoExpand")
            putExtra(
                BubblesActivity.KEY_DISPLAY_IS_CALL_CATEGORY,
                "isCallCategory: $isCallCategory"
            )
            putExtra(BubblesActivity.KEY_DISPLAY_MESSAGE, "message: $message")
        }
        val bubblesIntent =
            PendingIntent.getActivity(context, 0, target, PendingIntent.FLAG_UPDATE_CURRENT)
        val icon = Icon.createWithResource(context, R.drawable.ic_launcher_foreground)

        val bubblesData = Notification.BubbleMetadata.Builder()
            .setDesiredHeight(600)
            .setIntent(bubblesIntent)
            .setIcon(icon)
            .setAutoExpandBubble(isAutoExpand)
            .setSuppressNotification(true)
            .build()

        val bot = Person.Builder()
            .setBot(true)
            .setName("Example")
            .setImportant(true)
            .build()

        val style = if (message.isNotEmpty()) {
            Notification.MessagingStyle(bot)
                .addMessage(message, Date().time, bot)
        } else null

        val notificationBuilder = Notification.Builder(context, NOTIFICATION_CHANNEL_ID)
            .addPerson(bot)
            .setStyle(style)
            .setBubbleMetadata(bubblesData)
            .setContentIntent(bubblesIntent)
            .setSmallIcon(icon)

        return if (isCallCategory) {
            notificationBuilder.setCategory(Notification.CATEGORY_CALL)
        } else {
            notificationBuilder
        }.build()
    }
}