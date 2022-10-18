package dev.orlovvv.art.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.orlovvv.art.R
import dev.orlovvv.art.databinding.*
import dev.orlovvv.art.ui.model.PhotoUi
import dev.orlovvv.art.utils.setBlackAndWhite

class PhotosAdapter(private val clickListener: OnItemClickListener) :
    PagingDataAdapter<PhotoUi, RecyclerView.ViewHolder>(PhotoDiffUtil) {

    interface OnItemClickListener {
        fun onPhotoClick(photo: PhotoUi?)
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
        val photo = getItem(position)
        if (photo?.advertising != null) return 2
        return 1
    }

    inner class PhotoV1ViewHolder(
        private val binding: ItemPhotoV1Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotoUi?) {
            binding.apply {
                ivPhoto.load(photo?.image_url_small) {
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
        fun bind(photo: PhotoUi?) {
            binding.apply {
//                ivPhoto.load(photo?.image_url) {
//                    placeholder(R.drawable.image_placeholder)
//                    error(R.drawable.image_placeholder)
//                }
//                ivPhoto.setBlackAndWhite()
            }
            binding.root.setOnClickListener { clickListener.onPhotoClick(photo) }
        }
    }

    private object PhotoDiffUtil : DiffUtil.ItemCallback<PhotoUi>() {
        override fun areItemsTheSame(
            oldItem: PhotoUi,
            newItem: PhotoUi
        ): Boolean {
            return oldItem.image_url_small == newItem.image_url_small
        }

        override fun areContentsTheSame(
            oldItem: PhotoUi,
            newItem: PhotoUi
        ): Boolean {
            return oldItem == newItem
        }

    }
}