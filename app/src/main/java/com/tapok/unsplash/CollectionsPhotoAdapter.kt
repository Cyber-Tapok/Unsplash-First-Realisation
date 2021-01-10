package com.tapok.unsplash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tapok.unsplash.databinding.CardCollectionPhotoBinding
import com.tapok.unsplash.databinding.CardCoverCollectionBinding
import com.tapok.unsplash.databinding.TestCardBinding
import com.tapok.unsplash.model.CollectionsItem
import com.tapok.unsplash.model.CollectionsPhoto
import com.tapok.unsplash.model.UnsplashPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class CollectionsPhotoAdapter :
    PagingDataAdapter<UnsplashPhoto, CollectionsPhotoAdapter.PhotoViewHolder>(
        PHOTOS_DIFFUTIL
    ) {

    private val set = ConstraintSet()

    var clickListener: ((it: UnsplashPhoto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TestCardBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            val ratio = String.format("%d:%d", currentItem.width,currentItem.height)
            set.clone(holder.binding.parentContsraint)
            set.setDimensionRatio(holder.binding.imgSource.id, ratio)
            set.applyTo(holder.binding.parentContsraint)
            holder.bind(currentItem)
        }
    }

    inner class PhotoViewHolder(val binding: TestCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val currentItem = getItem(layoutPosition)
                currentItem?.let {
                    clickListener?.invoke(currentItem)
                }
            }
        }

        fun bind(currentItem: UnsplashPhoto) {
            binding.photo = currentItem
        }
    }

    companion object {
        private val PHOTOS_DIFFUTIL = object : DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(
                oldItem: UnsplashPhoto,
                newItem: UnsplashPhoto
            ) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UnsplashPhoto,
                newItem: UnsplashPhoto
            ) =
                oldItem == newItem
        }
    }
}