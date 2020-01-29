package br.com.improving.adjoechallenge

import android.app.Activity
import android.os.AsyncTask
import java.io.BufferedInputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class JSONDownloader(private val activity: Activity) : AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String): String {
        return download(params[0])
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        if (!activity.isFinishing && !isCancelled) {
            when {
                result.startsWith("URL ERROR") -> {
                    val error = result

                }
                result.startsWith("CONNECT ERROR") -> {
                    val error = result
                }
                else -> {
                    (activity as MainActivity).updateItems(App.parseJsonString(result))
                }
            }
        }
    }


    private fun connect(jsonURL: String): Any {
        try {
            val url = URL(jsonURL)
            val con = url.openConnection() as HttpURLConnection

            //CON PROPS
            con.requestMethod = "GET"
            con.connectTimeout = 15000
            con.readTimeout = 15000
            con.doInput = true

            return con

        } catch (e: MalformedURLException) {
            e.printStackTrace()
            return "URL ERROR " + e.message

        } catch (e: IOException) {
            e.printStackTrace()
            return "CONNECT ERROR " + e.message
        }
    }

    private fun download(jsonURL: String): String {
        val connection = connect(jsonURL)
        if (connection.toString().startsWith("Error")) {
            return connection.toString()
        }
        try {
            val con = connection as HttpURLConnection
            return if (con.responseCode == 200) {
                val jsonString = BufferedInputStream(con.inputStream)
                    .bufferedReader()
                    .use {
                        it.readText()
                    }

                jsonString


            } else {
                "Error " + con.responseMessage
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return "Error " + e.message
        }
    }
}