package com.tapok.unsplash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tapok.unsplash.databinding.CardCoverCollectionBinding
import com.tapok.unsplash.model.Collections
import com.tapok.unsplash.model.CollectionsItem

class CollectionsAdapter : RecyclerView.Adapter<CollectionsAdapter.CollectionsViewHolder>() {
    private var dataList: Collections = Collections()
    var clickListener: ((it: CollectionsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardCoverCollectionBinding.inflate(inflater, parent, false)
        return CollectionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = dataList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setData(dataList: Collections) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    inner class CollectionsViewHolder(val binding: CardCoverCollectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                clickListener?.invoke(dataList[adapterPosition])
            }
        }
        fun bind(position: Int) {
            binding.collection = dataList[position]
//            binding.coverPhoto.photo = dataList[position].cover_photo
        }
    }
}