package br.com.improving.adjoechallenge

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapter by lazy {
        AlbumsAdapter()
    }

    private val receiver by lazy {
        registerReceiver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        registerReceiver()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    fun updateItems(albums: List<Album>) {
        activity_loading.visibility = View.GONE
        albums_recycler.visibility = View.VISIBLE
        adapter.items.clear()
        adapter.items.addAll(albums)
        adapter.notifyDataSetChanged()
    }

    private fun loadData() {
        JSONDownloader(this)
            .apply {
                execute("https://jsonplaceholder.typicode.com/albums")
            }
    }

    private fun registerReceiver(): BroadcastReceiver {
        return CustomReceiver().apply {
            this@MainActivity.registerReceiver(this, IntentFilter(Intent.ACTION_USER_PRESENT))
        }
    }


    private fun setupRecyclerView() {
        with(albums_recycler) {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(
                    this@MainActivity,
                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                    false
                )

            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.activity_vertical_margin).toInt()
                )
            )

            setHasFixedSize(true)
            adapter = this@MainActivity.adapter
        }
    }
}
