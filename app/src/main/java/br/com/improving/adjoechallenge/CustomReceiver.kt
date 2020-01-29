package br.com.improving.adjoechallenge

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.PersistableBundle
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

    private fun triggerJob(context: Context, triggeredTime: Long) {
        val serviceComponent = ComponentName(context, CustomJobService::class.java)

        val bundle = PersistableBundle()
        bundle.putLong(TRIGGERED_TIME, triggeredTime)

        val builder = JobInfo.Builder(0, serviceComponent)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setMinimumLatency(5000)
            .setOverrideDeadline(1000 * 5)
            .setPersisted(true)
            .setExtras(bundle)


        val jobScheduler =
            App.instance.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        jobScheduler.schedule(builder.build())
    }
}