package br.com.standup

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import br.com.standup.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var view: View
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var toastMessage: String
    private lateinit var notifyIntent: Intent
    private lateinit var notifyPendingIntent: PendingIntent
    private lateinit var alarmManager: AlarmManager
    private lateinit var notificationManager: NotificationManager


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        view = mainBinding.root
        setContentView(view)

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        notifyIntent = Intent(this, AlarmReceiver::class.java)
        notifyPendingIntent = PendingIntent.getBroadcast(
            this,
            NOTIFICATION_ID,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        mainBinding.btnToggleAlarm.setOnCheckedChangeListener { _, isChecked ->

            toastMessage = if (isChecked) {
                alarmManager.setInexactRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime(),
                    AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                    notifyPendingIntent
                )
                "Stand Up Alarm On!"
            } else {
                notificationManager.cancelAll()
                alarmManager.cancel(notifyPendingIntent)
                "Stand Up Alarm Off!"
            }

            Toast.makeText(
                this,
                toastMessage,
                Toast.LENGTH_SHORT
            ).show()
        }

        createNotificationChannel(notificationManager)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {

        val notificationChannel = NotificationChannel(
            PRIMARY_CHANNEL_ID,
            "Stand up notification",
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = "Notifies every 15 minutes to stand up and walk"
        notificationManager.createNotificationChannel(notificationChannel)
    }

    companion object {
        const val NOTIFICATION_ID = 0
        const val PRIMARY_CHANNEL_ID = "PRIMARY_NOTIFICATION_CHANNEL"
    }

}