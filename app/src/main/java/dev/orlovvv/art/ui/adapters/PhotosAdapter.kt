package dev.orlovvv.art.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
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
            3 -> PhotoV3ViewHolder(
                ItemPhotoV3Binding.inflate(
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
            is PhotoV3ViewHolder -> holder.bind(photo)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 3 == 1) return 2
        if (position % 3 == 2) return 3
        return 1
    }

    inner class PhotoV1ViewHolder(
        private val binding: ItemPhotoV1Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotoUi?) {
            binding.apply {
                photo?.let { photo ->
                    ivPhoto.load(photo.image_url_large) {
                        placeholder(R.drawable.image_placeholder)
                        error(R.drawable.image_placeholder)
                    }
                    ivPhoto.setBlackAndWhite()
                    tvFullText.text = photo.full_text
                    ViewCompat.setTransitionName(binding.ivPhoto, photo.image_url_small)
                    clRoot.setOnClickListener { clickListener.onPhotoClick(photo) }
                }
            }
        }
    }

    inner class PhotoV2ViewHolder(
        private val binding: ItemPhotoV2Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotoUi?) {
            binding.apply {
                photo?.let { photo ->
                    ivPhoto.load(photo.image_url_large) {
                        placeholder(R.drawable.image_placeholder)
                        error(R.drawable.image_placeholder)
                    }
                    ivPhoto.setBlackAndWhite()
                    tvFullText.text = photo.full_text
                    clRoot.setOnClickListener {
                        clickListener.onPhotoClick(photo)
                    }
                }
            }
        }
    }

    inner class PhotoV3ViewHolder(
        private val binding: ItemPhotoV3Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotoUi?) {
            binding.apply {
                photo?.let { photo ->
                    ivPhoto.load(photo.image_url_large) {
                        placeholder(R.drawable.image_placeholder)
                        error(R.drawable.image_placeholder)
                    }
                    ivPhoto.setBlackAndWhite()
                    tvFullText.text = photo.full_text
                    tvFullText2.text = photo.full_text
                    clRoot.setOnClickListener { clickListener.onPhotoClick(photo) }
                }
            }
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