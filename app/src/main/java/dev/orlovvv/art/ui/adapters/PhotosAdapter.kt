package dev.orlovvv.art.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.orlovvv.art.R
import dev.orlovvv.art.databinding.ItemPhotoV1Binding
import dev.orlovvv.art.databinding.ItemPhotoV2Binding
import dev.orlovvv.art.databinding.ItemPhotoV3Binding
import dev.orlovvv.art.ui.fragments.home.HomePhotoItemUiState
import kotlin.random.Random

class PhotosAdapter(private val clickListener: OnItemClickListener) :
    ListAdapter<HomePhotoItemUiState, RecyclerView.ViewHolder>(PhotoDiffUtil) {

    interface OnItemClickListener {
        fun onArticleClick(photo: HomePhotoItemUiState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder1 = PhotoV1ViewHolder(
            ItemPhotoV1Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        val viewHolder2 = PhotoV2ViewHolder(
            ItemPhotoV2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        val viewHolder3 = PhotoV3ViewHolder(
            ItemPhotoV3Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return when (viewType) {
            1 -> viewHolder1
            2 -> viewHolder2
            3 -> viewHolder3
            else -> viewHolder1
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
        return if (position % 5 == 0) 1
        else if (position % 2 == 0) 2
        else 3
    }

    inner class PhotoV1ViewHolder(
        private val binding: ItemPhotoV1Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: HomePhotoItemUiState) {
            binding.apply {
                tvUsername.text = photo.user.username
                tvDescription.text = photo.description
                tvLikes.text = photo.likes
                ivPhoto.load(photo.image_url) {
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.image_placeholder)
                }
                ivProfilePic.load(photo.user.image_url_small) {
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.image_placeholder)
                }
            }
            //binding.root.setOnClickListener { clickListener.onArticleClick(photo) }
        }
    }

    inner class PhotoV2ViewHolder(
        private val binding: ItemPhotoV2Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: HomePhotoItemUiState) {
            binding.apply {
                tvDescription.text = photo.description
                tvLikes.text = photo.likes
                ivPhoto.load(photo.image_url) {
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.image_placeholder)
                }
            }
            //binding.root.setOnClickListener { clickListener.onArticleClick(photo) }
        }
    }

    inner class PhotoV3ViewHolder(
        private val binding: ItemPhotoV3Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: HomePhotoItemUiState) {
            binding.apply {
                tvDescription.text = photo.description
                tvLikes.text = photo.likes
                ivPhoto.load(photo.image_url) {
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.image_placeholder)
                }
                tvFirstname.text = photo.user.firstname
                tvLastname.text = photo.user.lastname
            }
            //binding.root.setOnClickListener { clickListener.onArticleClick(photo) }
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