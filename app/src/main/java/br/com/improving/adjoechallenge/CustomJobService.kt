package br.com.improving.adjoechallenge

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.os.PersistableBundle
import androidx.core.app.NotificationManagerCompat

class CustomJobService : JobService() {

    companion object {
        const val CHANNEL_ID = "1989"

        fun triggerJob(context: Context, triggeredTime: Long) {
            val serviceComponent = ComponentName(context, CustomJobService::class.java)

            val bundle = PersistableBundle()
            bundle.putLong(CustomReceiver.TRIGGERED_TIME, triggeredTime)

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

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        NotificationHelper.createNotificationChannel(
            this, NotificationManagerCompat.IMPORTANCE_HIGH, true,
            getString(R.string.app_name), "App notification channel."
        )

        val timeElapsed = params?.extras?.getLong(CustomReceiver.TRIGGERED_TIME) ?: 0L


        NotificationHelper.showNotification(
            this,
            timeElapsed
        )

        triggerJob(this, timeElapsed)
        return true
    }

}