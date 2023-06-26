package com.example.mycasino5

import android.app.Application
import com.onesignal.OneSignal

class OneSignalApplication:Application() {

    val ONESIGNAL_APP_ID = "14ebba8f-48a4-4c9b-9049-89f00b09807c"

    override fun onCreate() {
        super.onCreate()

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

    }

}