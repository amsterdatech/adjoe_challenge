package br.com.improving.adjoechallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumsAdapter :
    RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    var items: MutableList<Album> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.album_item,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = items[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Album) {
            itemView.album_title.text = item.title
            itemView.album_user_id.text = "User ID: ${item.userId}"
            itemView.album_id.text = "Album ID: ${item.id}"
        }
    }
}