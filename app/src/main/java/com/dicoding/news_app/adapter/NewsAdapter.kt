package com.dicoding.news_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.news_app.R
import com.dicoding.news_app.api.ArticlesItem
import com.squareup.picasso.Picasso

class NewsAdapter(private val listNews: MutableList<ArticlesItem>) : RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNewsImage: ImageView = itemView.findViewById(R.id.img_list_news)
        val tvNewsTitle: TextView = itemView.findViewById(R.id.tv_title_news)
        val tvNewsDesc: TextView = itemView.findViewById(R.id.tv_desc_news)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ListViewHolder, position: Int) {
        val data = listNews[position]

        val titleNews = holder.tvNewsTitle
        val descNews = holder.tvNewsDesc
        val imgNews = holder.tvNewsImage

        titleNews.text = data.title
        descNews.text = data.description

        Picasso.get()
            .load(data.image)
            .resize(100, 100)
            .centerCrop()
            .into(imgNews)
    }

    override fun getItemCount() = listNews.size

}