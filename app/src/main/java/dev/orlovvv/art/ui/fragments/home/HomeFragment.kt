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
import dev.orlovvv.art.R
import dev.orlovvv.art.databinding.FragmentHomeBinding
import dev.orlovvv.art.ui.adapters.PhotosAdapter
import dev.orlovvv.art.ui.viewmodels.PhotoViewModel
import dev.orlovvv.art.utils.LoadState
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: PhotoViewModel by activityViewModels()

    private val adapter = PhotosAdapter(
        object : PhotosAdapter.OnItemClickListener {
            override fun onPhotoClick(photo: HomePhotoItemUiState) {
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
                    when (uiState.loadState) {
                        LoadState.LOADING -> setLoadingUi()
                        LoadState.ERROR -> setErrorUi()
                        LoadState.SUCCESS -> {
                            adapter.submitList(uiState.photos)
                            setSuccessUi()
                        }
                    }
                }
            }
        }
    }

    private fun setSuccessUi() {

    }

    private fun setErrorUi() {

    }

    private fun setLoadingUi() {

    }

    private fun setupUi() {
        binding.apply {
            rvPhotos.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}