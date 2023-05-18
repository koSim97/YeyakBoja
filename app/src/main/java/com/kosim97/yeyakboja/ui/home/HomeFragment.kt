package com.kosim97.yeyakboja.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kosim97.yeyakboja.BuildConfig
import com.kosim97.yeyakboja.R
import com.kosim97.yeyakboja.databinding.FragmentHomeBinding
import com.naver.maps.map.LocationSource
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMapSdk
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var mapView: MapView
    private lateinit var locationSource: LocationSource
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentHomeBinding.inflate(inflater).also {
            homeBinding = it
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = homeViewModel
            return it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = homeBinding.map
        mapView.onCreate(savedInstanceState)
        homeViewModel.getGymAllData()
        locationSource = FusedLocationSource(requireActivity(), 1000)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }
}