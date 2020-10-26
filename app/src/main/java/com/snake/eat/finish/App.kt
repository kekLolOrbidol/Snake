package com.snake.eat.finish

import android.app.Application
import androidx.room.Room
import com.onesignal.OneSignal
import com.snake.eat.finish.model.ResultDb

class App: Application() {

    companion object {
        var resultDb: ResultDb? = null
    }

    override fun onCreate() {
        super.onCreate()

        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()

        resultDb = Room.databaseBuilder(this, ResultDb::class.java, "Results").allowMainThreadQueries().build()
    }
}