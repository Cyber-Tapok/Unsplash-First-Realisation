package com.tapok.unsplash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tapok.unsplash.databinding.CardCollectionPhotoBinding
import com.tapok.unsplash.model.CollectionsPhoto
import com.tapok.unsplash.model.UnsplashPhoto

class CollectionsPhotoAdapter : RecyclerView.Adapter<CollectionsPhotoAdapter.PhotoViewHolder>() {
    private var dataList: CollectionsPhoto = CollectionsPhoto()
    var clickListener: ((it: UnsplashPhoto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardCollectionPhotoBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(dataList: CollectionsPhoto) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class PhotoViewHolder(val binding: CardCollectionPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                clickListener?.invoke(dataList[adapterPosition])
            }
        }

        fun bind(position: Int) {
            binding.photo = dataList[position]
        }
    }
}