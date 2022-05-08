package com.adyen.android.assignment.ui.nearbyPlaces

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adyen.android.assignment.databinding.NearbyPlacesFragmentBinding
import com.adyen.android.assignment.ui.nearbyPlaces.adapter.NearbyPlacesAdapter
import com.adyen.android.assignment.ui.nearbyPlaces.adapter.NearbyPlacesDiffCallback
import com.adyen.android.assignment.utils.NearbyPlacesState
import com.adyen.android.assignment.utils.hideView
import com.adyen.android.assignment.utils.showErrorDialog
import com.adyen.android.assignment.utils.showView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NearbyPlacesFragment : Fragment() {

    private var _binding: NearbyPlacesFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<NearbyPlacesViewModel>()

    private val nearbyPlacesAdapter by lazy { NearbyPlacesAdapter(NearbyPlacesDiffCallback) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NearbyPlacesFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = NearbyPlacesFragmentArgs.fromBundle(requireArguments()).location

        observeViewModel()

        viewModel.getNearbyPlaces(location = args)

        binding.nearbyPlacesList.apply {
            adapter = nearbyPlacesAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.nearbyPlacesState.collect {
                when (it) {
                    is NearbyPlacesState.Loading -> {
                        binding.loader.showView()
                    }
                    is NearbyPlacesState.Error -> {
                        binding.loader.hideView()
                        requireContext().showErrorDialog(it.message)
                    }
                    is NearbyPlacesState.Success -> {
                        binding.loader.hideView()
                        nearbyPlacesAdapter.submitList(it.data)
                        viewModel.nearbyPlacesState.value = null
                    }
                    else -> {}
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}