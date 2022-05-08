package com.adyen.android.assignment.ui.nearbyPlaces

import android.os.Bundle
import android.util.Log
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
                        Log.d("LOADING", "TRUE")
                    }
                    is NearbyPlacesState.Error -> {
                        Log.d("LOADING", it.message)
                    }
                    is NearbyPlacesState.Success -> {
                        nearbyPlacesAdapter.submitList(it.data)
                    }
                    else -> {}
                }
            }
        }
    }


}