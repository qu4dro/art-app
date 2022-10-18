package dev.orlovvv.art.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dev.orlovvv.art.R
import dev.orlovvv.art.databinding.FragmentHomeBinding
import dev.orlovvv.art.domain.model.Photo
import dev.orlovvv.art.ui.adapters.PhotosAdapter
import dev.orlovvv.art.ui.model.PhotoUi
import dev.orlovvv.art.ui.viewmodels.PhotoViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: PhotoViewModel by activityViewModels()

    private val adapter = PhotosAdapter(
        object : PhotosAdapter.OnItemClickListener {
            override fun onPhotoClick(photo: PhotoUi?) {
                findNavController().navigate(R.id.action_homeFragment_to_imageFragment)
            }
        }
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    adapter.submitData(uiState.photos)
                }
            }
        }
    }

    private fun setupUi() {
        binding.apply {
            rvPhotos.adapter = adapter
//            adapter.addLoadStateListener { loadState ->
//                val errorState = when {
//                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
//                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
//                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
//                    else -> null
//                }
//                when (val throwable = errorState?.error) {
//                    is IOException -> {}
//                    is HttpException -> {}
//                }
//            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}