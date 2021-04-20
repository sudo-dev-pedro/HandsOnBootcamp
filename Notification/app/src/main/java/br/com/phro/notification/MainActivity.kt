package br.com.phro.notification

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.app.NotificationManager.*
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.AudioAttributes.USAGE_NOTIFICATION
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import br.com.phro.notification.databinding.ActivityMainBinding
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var notificationManager: NotificationManager
    private lateinit var notifyIntent: Intent
    private lateinit var notifyPendingIntent: PendingIntent
    private lateinit var pendingIntentA: PendingIntent
    private lateinit var pendingIntentB: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createDefaultNotificationChannel()
        createNotificationChannelGroup()
        createBounceNotificationChannel()

        onClickTitleText()
        onClickTitleTextLargeIconButton()
        onClickTitleTextExpandableLargeIconButton()
        onClickTitleTextSoundVibration()
        onClickTitleTextIntent()
        onClickTitleTextActionIntent()

        checkIfIntentHasExtra()
    }

    private fun checkIfIntentHasExtra() {
        // Checando se tenho Extras na minha intent
        if (intent.hasExtra(NOTIFICATION_ACTION_ID)) {
            Toast.makeText(
                this,
                intent.getStringExtra(NOTIFICATION_ACTION_ID),
                Toast.LENGTH_LONG
            ).show()

            notificationManager.cancelAll()
        }
    }

    // Click Listeners
    private fun onClickTitleText() {
        mainBinding.btnTitleText.setOnClickListener {
            showTitleTextNotification()
        }
    }

    private fun onClickTitleTextLargeIconButton() {
        mainBinding.btnTitleTextLargeIcon.setOnClickListener {
            showTitleTextLargeIconNotification()
        }
    }

    private fun onClickTitleTextExpandableLargeIconButton() {
        mainBinding.btnTitleTextExpandableIcon.setOnClickListener {
            showTitleTextExpandableLargeIconNotification()
        }
    }

    private fun onClickTitleTextSoundVibration() {
        mainBinding.btnTitleTextSound.setOnClickListener {
            showTitleTextSoundNotification()
        }
    }

    private fun onClickTitleTextIntent() {
        mainBinding.btnTitleTextIntent.setOnClickListener {
            onClickIntentNotification()
        }
    }

    // Notificações onde o usuário pode tomar algumas ações. Como a que ocorre no WhatsApp
    private fun onClickTitleTextActionIntent() {
        mainBinding.btnTitleTextActionIntent.setOnClickListener {
            onClickIntentActionsNotifications()
        }
    }

    // Notification Builders
    private fun showTitleTextNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_DEFAULT_ID)
            .setContentTitle(DEFAULT_NOTIFICATION_TITLE)
            .setContentText(DEFAULT_NOTIFICATION_MESSAGE)
            .setSmallIcon(R.drawable.ic_notification_alert)

        showNotification(builder)
    }

    private fun showTitleTextLargeIconNotification() {
        val bitmapIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_stat_add_alert)

        val builder = NotificationCompat.Builder(this, CHANNEL_DEFAULT_ID)
            .setContentTitle(DEFAULT_NOTIFICATION_TITLE)
            .setContentText(DEFAULT_NOTIFICATION_MESSAGE)
            .setSmallIcon(R.drawable.ic_notification_alert)
            .setLargeIcon(bitmapIcon)

        showNotification(builder)

    }

    private fun showTitleTextExpandableLargeIconNotification() {
        val bitmapIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_stat_add_alert)

        val builder = NotificationCompat.Builder(this, CHANNEL_DEFAULT_ID)
            .setContentTitle(DEFAULT_NOTIFICATION_TITLE)
            .setContentText(DEFAULT_NOTIFICATION_MESSAGE)
            .setSmallIcon(R.drawable.ic_notification_alert)
            .setLargeIcon(bitmapIcon)
            .setStyle(
                NotificationCompat
                    .BigPictureStyle()
                    .bigPicture(bitmapIcon)
                    .bigLargeIcon(null)
            )

        showNotification(builder)
    }

    private fun showTitleTextSoundNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_BOUNCE_ID)
            .setContentTitle(DEFAULT_NOTIFICATION_TITLE)
            .setContentText(DEFAULT_NOTIFICATION_MESSAGE)
            .setSmallIcon(R.drawable.ic_notification_alert)
            //Sound, Vibrate e Priority só funciona em Android 7 e está depreciado.
            .setSound(buildSoundUrl())
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setPriority(NotificationCompat.PRIORITY_MAX)

        showNotification(builder)
    }

    private fun onClickIntentNotification() {
        notifyIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        notifyPendingIntent = PendingIntent.getActivity(
            this, 0, notifyIntent, 0
        )

        val builder = NotificationCompat
            .Builder(this, CHANNEL_DEFAULT_ID)
            .setContentTitle(DEFAULT_NOTIFICATION_TITLE)
            .setContentText(DEFAULT_NOTIFICATION_MESSAGE)
            .setSmallIcon(R.drawable.ic_stat_access_alarm)
            .setContentIntent(notifyPendingIntent)
            .setAutoCancel(true)

        showNotification(builder)
    }

    // Ações na minha notificação
    private fun onClickIntentActionsNotifications() {
        val builder = NotificationCompat.Builder(this, CHANNEL_DEFAULT_ID)
            .setContentTitle(DEFAULT_NOTIFICATION_TITLE)
            .setContentText(DEFAULT_NOTIFICATION_MESSAGE)
            .setSmallIcon(R.drawable.ic_stat_access_alarm)
            .setAutoCancel(true)
            // Adição das ações
            .addAction(
                NotificationCompat.Action.Builder(
                    null, NOTIFICATION_ACTION_A, prepareIntentActionA()
                ).build()
            )
            .addAction(
                NotificationCompat.Action.Builder(
                    null, NOTIFICATION_ACTION_B, prepareIntentActionB()
                ).build()
            )

        showNotification(builder)
    }

    private fun prepareIntentActionA(): PendingIntent {
        val intentA = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(NOTIFICATION_ACTION_ID, NOTIFICATION_ACTION_A)
        }

        pendingIntentA = PendingIntent.getActivity(this, 1, intentA, 0)

        return pendingIntentA
    }

    private fun prepareIntentActionB(): PendingIntent {
        val intentB = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(NOTIFICATION_ACTION_ID, NOTIFICATION_ACTION_B)
        }

        pendingIntentB = PendingIntent.getActivity(this, 2, intentB, 0)

        return pendingIntentB
    }

    private fun showNotification(builder: NotificationCompat.Builder) {
        val notification = builder.build()

        notificationManager.notify(nextInt(), notification)
    }

    // Creating Channels
    private fun createDefaultNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_DEFAULT_ID,
                CHANNEL_DEFAULT_NAME,
                IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DEFAULT_DESCRIPTION
                // Luz da notificação?
                enableLights(true)
                // Cor da luz da notificação?
                lightColor = Color.RED
                enableVibration(true)
            }

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createNotificationChannelGroup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val group = NotificationChannelGroup(CHANNEL_GROUP_ID, CHANNEL_GROUP_NAME)
            notificationManager.createNotificationChannelGroup(group)
        }
    }

    private fun createBounceNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(USAGE_NOTIFICATION)
                .build()

            val notificationChannel = NotificationChannel(
                CHANNEL_BOUNCE_ID,
                CHANNEL_BOUNCE_NAME,
                IMPORTANCE_HIGH
            ).apply {
                description = CHANNEL_BOUNCE_DESCRIPTION
                // Luz da notificação
                enableLights(true)
                enableVibration(true)
                group = CHANNEL_GROUP_ID
                shouldVibrate()

                vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

                setSound(buildSoundUrl(), audioAttributes)
            }

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun buildSoundUrl() = Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/" + R.raw.bounce
    )

    companion object {
        const val CHANNEL_DEFAULT_ID = "90"
        const val CHANNEL_DEFAULT_NAME = "DEFAULT NAME"
        const val CHANNEL_DEFAULT_DESCRIPTION = "DEFAULT DESCRIPTION"

        const val CHANNEL_GROUP_ID = "99"
        const val CHANNEL_GROUP_NAME = "GRUPO"

        const val CHANNEL_BOUNCE_ID = "B1"
        const val CHANNEL_BOUNCE_NAME = "BOUNCE"
        const val CHANNEL_BOUNCE_DESCRIPTION = "BOUNCE NOTIFICATION"

        const val DEFAULT_NOTIFICATION_TITLE = "Notificação"
        const val DEFAULT_NOTIFICATION_MESSAGE = "MESSAGE"

        const val NOTIFICATION_ACTION_ID = "ACTION"
        const val NOTIFICATION_ACTION_A = "ACTION A"
        const val NOTIFICATION_ACTION_B = "ACTION B"
    }
}