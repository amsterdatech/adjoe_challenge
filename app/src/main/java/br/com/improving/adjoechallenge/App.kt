package br.com.improving.adjoechallenge

import android.app.Application
import org.json.JSONArray
import org.json.JSONObject

class App : Application() {

    companion object {
        lateinit var instance: App
            private set

        fun albumsJson(): String {
            return instance.assets.open("albums.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
        }

        fun parseJsonString(jsonString: String): List<Album> {
            val albumJsonArray = JSONArray(jsonString)
            val albumItems = mutableListOf<Album>()

            for (i in 0 until albumJsonArray.length()) {
                val album = albumJsonArray.getJSONObject(i)

                val title = album.getString("title")
                val userId = album.getInt("userId")
                val id = album.getInt("id")
                albumItems.add(Album(title = title, userId = userId, id = id))
            }

            return albumItems
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}