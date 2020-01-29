package br.com.improving.adjoechallenge

import android.app.job.JobParameters
import android.app.job.JobService
import androidx.core.app.NotificationManagerCompat

class CustomJobService : JobService() {

    companion object {
        const val CHANNEL_ID = "1989"
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        NotificationHelper.createNotificationChannel(
            this, NotificationManagerCompat.IMPORTANCE_HIGH, true,
            getString(R.string.app_name), "App notification channel."
        )


        NotificationHelper.showNotification(
            this,
            params?.extras?.getLong(CustomReceiver.TRIGGERED_TIME)?:0L
        )
        return true
    }

}