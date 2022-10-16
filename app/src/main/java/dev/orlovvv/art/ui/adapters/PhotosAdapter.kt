package dev.orlovvv.art.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.orlovvv.art.R
import dev.orlovvv.art.databinding.*
import dev.orlovvv.art.domain.model.Photo
import dev.orlovvv.art.utils.setBlackAndWhite

class PhotosAdapter(private val clickListener: OnItemClickListener) :
    PagingDataAdapter<Photo, RecyclerView.ViewHolder>(PhotoDiffUtil) {

    interface OnItemClickListener {
        fun onPhotoClick(photo: Photo?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            2 -> PhotoV2ViewHolder(
                ItemPhotoV2Binding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> PhotoV1ViewHolder(
                ItemPhotoV1Binding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val photo = getItem(position)
        when (holder) {
            is PhotoV1ViewHolder -> holder.bind(photo)
            is PhotoV2ViewHolder -> holder.bind(photo)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) 1 else if (position % 3 == 0) 1 else 2
    }

    inner class PhotoV1ViewHolder(
        private val binding: ItemPhotoV1Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo?) {
            binding.apply {
                ivPhoto.load(photo?.image_url) {
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.image_placeholder)
                }
                ivPhoto.setBlackAndWhite()
            }
            binding.root.setOnClickListener { clickListener.onPhotoClick(photo) }
        }
    }

    inner class PhotoV2ViewHolder(
        private val binding: ItemPhotoV2Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo?) {
            binding.apply {
                ivPhoto.load(photo?.image_url) {
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.image_placeholder)
                }
                ivPhoto.setBlackAndWhite()
            }
            binding.root.setOnClickListener { clickListener.onPhotoClick(photo) }
        }
    }

    private object PhotoDiffUtil : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(
            oldItem: Photo,
            newItem: Photo
        ): Boolean {
            return oldItem.image_url == newItem.image_url
        }

        override fun areContentsTheSame(
            oldItem: Photo,
            newItem: Photo
        ): Boolean {
            return oldItem == newItem
        }

    }
}