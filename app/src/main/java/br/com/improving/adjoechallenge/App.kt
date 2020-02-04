package br.com.improving.adjoechallenge

import android.app.Application
import org.json.JSONArray
import org.json.JSONObject

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}