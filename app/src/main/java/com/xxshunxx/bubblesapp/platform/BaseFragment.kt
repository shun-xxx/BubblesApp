package com.xxshunxx.bubblesapp.platform

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xxshunxx.bubblesapp.R
import com.xxshunxx.bubblesapp.bubbles.BubblesService
import kotlinx.android.synthetic.main.fragment_base.*

class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_base, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BubblesSendForegroundButton.setOnClickListener {
            context?.startForegroundService(createBubblesIntent())
        }

        BubblesSendBackgroundButton.setOnClickListener {
            activity?.finish()
            context?.startForegroundService(createBubblesIntent())
        }
    }

    private fun createBubblesIntent(): Intent {
        val intent = Intent(context, BubblesService::class.java).apply {
            putExtra(BubblesService.KEY_IS_AUTO_EXPAND, bubblesAutoExpandSwitch.isChecked)
            putExtra(BubblesService.KEY_IS_CALL_CATEGORY, bubblesCallCategorySwitch.isChecked)
            putExtra(BubblesService.KEY_BUBBLES_MESSAGE, bubblesMessageEditText.text?.toString())
        }
        context?.stopService(intent)
        return intent
    }

    companion object {
        fun createInstance() = BaseFragment()
    }
}