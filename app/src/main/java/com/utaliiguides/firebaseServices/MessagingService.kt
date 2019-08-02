package com.utalli

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.utalli.helpers.AppPreference
import com.utalli.helpers.Utils
import org.json.JSONObject
import java.lang.Exception


class MessagingService : FirebaseMessagingService() {


    var preference: AppPreference? = null


    override fun onNewToken(token: String?) {
        super.onNewToken(token)

        Utils.showLog(token!!)


    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        Utils.showLog("From: " + remoteMessage!!.getFrom())

        try {




        } catch (e: Exception) {
            e.printStackTrace()
        }




    }
}