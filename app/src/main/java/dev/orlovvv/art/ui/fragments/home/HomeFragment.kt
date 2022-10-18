package dev.orlovvv.art.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import dev.orlovvv.art.R
import dev.orlovvv.art.databinding.FragmentHomeBinding
import dev.orlovvv.art.ui.adapters.PhotosAdapter
import dev.orlovvv.art.ui.model.PhotoUi
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

    private val adapter = PhotosAdapter(
        object : PhotosAdapter.OnItemClickListener {
            override fun onPhotoClick(photo: PhotoUi?) {
                photo?.let {
                    val action = HomeFragmentDirections.actionHomeFragmentToImageFragment(it)
                    findNavController().navigate(action)
                }

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
        postponeEnterTransition()
        setupUi()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    adapter.submitData(uiState.photos)
                }
            }
        }
        (view.parent as? ViewGroup)?.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z,true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z,false)
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