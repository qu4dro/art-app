package dev.orlovvv.art.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import dev.orlovvv.art.R
import dev.orlovvv.art.databinding.*
import dev.orlovvv.art.ui.fragments.home.HomePhotoItemUiState
import dev.orlovvv.art.utils.setBlackAndWhite

class PhotosAdapter(private val clickListener: OnItemClickListener) :
    ListAdapter<HomePhotoItemUiState, RecyclerView.ViewHolder>(PhotoDiffUtil) {

    interface OnItemClickListener {
        fun onPhotoClick(photo: HomePhotoItemUiState)
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
        fun bind(photo: HomePhotoItemUiState) {
            binding.apply {
                ivPhoto.load(photo.image_url) {
                    crossfade(true)
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
        fun bind(photo: HomePhotoItemUiState) {
            binding.apply {
                ivPhoto.load(photo.image_url) {
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.image_placeholder)
                }
                ivPhoto.setBlackAndWhite()
            }
            binding.root.setOnClickListener { clickListener.onPhotoClick(photo) }
        }
    }

    private object PhotoDiffUtil : DiffUtil.ItemCallback<HomePhotoItemUiState>() {
        override fun areItemsTheSame(
            oldItem: HomePhotoItemUiState,
            newItem: HomePhotoItemUiState
        ): Boolean {
            return oldItem.image_url == newItem.image_url
        }

        override fun areContentsTheSame(
            oldItem: HomePhotoItemUiState,
            newItem: HomePhotoItemUiState
        ): Boolean {
            return oldItem == newItem
        }

    }
}