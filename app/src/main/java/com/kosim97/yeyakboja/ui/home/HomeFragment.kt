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
import com.kosim97.yeyakboja.ui.home.gym.GymAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var campingAdapter: CampingAdapter
    private lateinit var footballAdapter: GymAdapter
    private lateinit var soccerAdapter: GymAdapter
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
        initView()
    }

    private fun initView() {
        if (isFirst) {
            homeViewModel.getGymData("풋살장")
            homeViewModel.getGymData("축구장")
            homeViewModel.getCampingData()
            isFirst = false
        } else {
            setAllItem()
        }
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
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.soccerList.collectLatest {
                    val data = it.sortedByDescending { sort ->
                        sort.gymActiveStart.replace("-","")
                    }
                    setSoccerItem(data)
                }
            }
        }
    }

    private fun setAllItem() {
        setCampingItem(homeViewModel.campingList.value)
        setFootballItem(homeViewModel.footballList.value)
        setSoccerItem(homeViewModel.soccerList.value)
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
        footballAdapter = GymAdapter(findNavController())
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

    private fun setSoccerItem(data: List<GymDomainModel>) {
        soccerAdapter = GymAdapter(findNavController())
        homeBinding.soccerRv.also {
            it.adapter = soccerAdapter
            it.layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
        }
        soccerAdapter.submitList(data.toMutableList())
    }
}