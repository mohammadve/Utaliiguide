package com.utaliiguides.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.provider.Settings
import androidx.core.app.NotificationCompat

import com.utaliiguides.R
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.activity.SplashActivity
import com.utaliiguides.models.UserModel
import com.utalli.helpers.AppPreference

import java.io.IOException
import java.io.InputStream
import java.net.URL



class NotificationHelper(var mContext: Context) {
    private var mNotificationManager: NotificationManager? = null
    private var mBuilder: NotificationCompat.Builder? = null
    val NOTIFICATION_CHANNEL_ID = "10001"


    /**
     * Create and push the notification
     */
    fun createNotification(title: String, message: String, image: String?) {
        /**Creates an explicit intent for an Activity in your app */
        val resultIntent = Intent(mContext, HomeActivity::class.java)
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val rawBitmap = BitmapFactory.decodeResource(mContext.getResources(), com.utaliiguides.R.mipmap.ic_launcher)

        val resultPendingIntent = PendingIntent.getActivity(mContext, 0 /* Request code */, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        var bmp: Bitmap? = null
        if (image != null && !image.equals("", ignoreCase = true)) {

            try {
                val `in` = URL(image).openStream()
                bmp = BitmapFactory.decodeStream(`in`)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        mBuilder = NotificationCompat.Builder(mContext)
        mBuilder!!.setSmallIcon(R.mipmap.ic_bell)
            .setLargeIcon(rawBitmap)

        mBuilder!!.setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
            .setContentIntent(resultPendingIntent)

            .setAutoCancel(true)

        if (bmp != null) {
            mBuilder!!.setLargeIcon(bmp)
            mBuilder!!.setStyle(NotificationCompat.BigPictureStyle().bigPicture(bmp))
        }
        mNotificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel =
                NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(100, 100, 100, 100)
            assert(mNotificationManager != null)
            mBuilder!!.setChannelId(NOTIFICATION_CHANNEL_ID)
            mNotificationManager!!.createNotificationChannel(notificationChannel)
        }
        assert(mNotificationManager != null)
        mNotificationManager!!.notify(0 /* Request Code */, mBuilder!!.build())
    }
}
