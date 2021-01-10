package com.tapok.unsplash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tapok.unsplash.databinding.CardCoverCollectionBinding
import com.tapok.unsplash.model.CollectionsItem

class CollectionsAdapter :
    PagingDataAdapter<CollectionsItem, CollectionsAdapter.CollectionsViewHolder>(
        COLLECTION_DIFFUTIL
    ) {
    var clickListener: ((it: CollectionsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardCoverCollectionBinding.inflate(inflater, parent, false)
        return CollectionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(currentItem)
        }
    }

    inner class CollectionsViewHolder(val binding: CardCoverCollectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val currentItem = getItem(layoutPosition)
                currentItem?.let {
                    clickListener?.invoke(currentItem)
                }
            }
        }

        fun bind(currentItem: CollectionsItem) {
            binding.collection = currentItem
        }
    }

    companion object {
        private val COLLECTION_DIFFUTIL = object : DiffUtil.ItemCallback<CollectionsItem>() {
            override fun areItemsTheSame(
                oldItem: CollectionsItem,
                newItem: CollectionsItem
            ) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CollectionsItem,
                newItem: CollectionsItem
            ) =
                oldItem == newItem
        }
    }
}