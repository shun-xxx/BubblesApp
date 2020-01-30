package com.xxshunxx.bubblesapp.bubbles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xxshunxx.bubblesapp.R
import kotlinx.android.synthetic.main.activity_bubbles.*

class BubblesActivity : AppCompatActivity() {

    companion object {
        const val KEY_DISPLAY_IS_AUTO = "key_display_is_auto"
        const val KEY_DISPLAY_IS_CALL_CATEGORY = "key_display_is_call_category"
        const val KEY_DISPLAY_MESSAGE = "key_display_message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubbles)

        isAutoTextView.text = intent.getStringExtra(KEY_DISPLAY_IS_AUTO)
        isCallCategoryTextView.text = intent.getStringExtra(KEY_DISPLAY_IS_CALL_CATEGORY)
        messageTextView.text = intent.getStringExtra(KEY_DISPLAY_MESSAGE)
    }
}