package com.utaliiguides.activity

import android.app.Application
import com.sendbird.android.SendBird
import com.utaliiguides.R

class UtaliiGuideApplication : Application(){


    override fun onCreate() {
        super.onCreate()

        SendBird.init(getString(R.string.sendbird_app_id), this)
    }

}