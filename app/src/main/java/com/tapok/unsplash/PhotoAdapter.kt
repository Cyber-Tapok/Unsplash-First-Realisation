package com.tapok.unsplash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tapok.unsplash.databinding.ItemPhotoBinding
import com.tapok.unsplash.model.UnsplashPhoto
import com.tapok.unsplash.utils.calculateRatio

class PhotoAdapter :
    PagingDataAdapter<UnsplashPhoto, PhotoAdapter.PhotoViewHolder>(PHOTOS_DIFFUTIL) {

    private val set = ConstraintSet()

    var clickListener: ((it: UnsplashPhoto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(set, currentItem)
        }
    }

    inner class PhotoViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val currentItem = getItem(layoutPosition)
                currentItem?.let {
                    clickListener?.invoke(currentItem)
                }
            }
        }

        private fun setViewRatio(set: ConstraintSet, item: UnsplashPhoto) {
            val ratio = calculateRatio(item.width, item.height)
            set.apply {
                clone(binding.parentContsraint)
                setDimensionRatio(binding.imgSource.id, ratio)
                applyTo(binding.parentContsraint)
            }
        }

        fun bind(set: ConstraintSet, currentItem: UnsplashPhoto) {
            setViewRatio(set, currentItem)
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