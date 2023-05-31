package com.kosim97.yeyakboja.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kosim97.domain.model.CampingDomainModel
import com.kosim97.domain.model.GymDomainModel
import com.kosim97.yeyakboja.databinding.FragmentHomeBinding
import com.kosim97.yeyakboja.ui.home.camping.CampingAdapter
import com.kosim97.yeyakboja.ui.home.football.FootballAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Collections

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var campingAdapter: CampingAdapter
    private lateinit var footballAdapter: FootballAdapter
    private var isFirst = true
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

        initObserver()
        homeViewModel.getFootballData()
        homeViewModel.getCampingData()
    }

    private fun initView() {

    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.campingList.collectLatest {
                    val data = it.sortedByDescending {sort ->
                        sort.campingActiveStart.replace("-","")
                    }
                    setCampingItem(data)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.footballList.collectLatest {
                    val data = it.sortedByDescending { sort ->
                        sort.gymActiveStart.replace("-","")
                    }
                    setFootballItem(data)
                }
            }
        }
    }

    private fun setCampingItem(data: List<CampingDomainModel>) {
        campingAdapter = CampingAdapter(findNavController())
        homeBinding.campingRv.also {
            it.adapter = campingAdapter
            it.layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
        }
        campingAdapter.submitList(data.toMutableList())
    }

    private fun setFootballItem(data: List<GymDomainModel>) {
        footballAdapter = FootballAdapter(findNavController())
        homeBinding.footballRv.also {
            it.adapter = footballAdapter
            it.layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
        }
        footballAdapter.submitList(data.toMutableList())
    }
}