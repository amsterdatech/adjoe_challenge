package br.com.improving.adjoechallenge

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import br.com.improving.adjoechallenge.CustomJobService.Companion.triggerJob
import java.util.*


class CustomReceiver : BroadcastReceiver() {
    companion object {
        const val TRIGGERED_TIME = "TRIGGERED_TIME"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_USER_PRESENT) {
            triggerJob(context, Calendar.getInstance().timeInMillis)
        }
    }
}