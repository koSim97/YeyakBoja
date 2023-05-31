package com.kosim97.yeyakboja.ui.detail

import android.os.Bundle
import android.os.Parcel
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.kosim97.yeyakboja.R
import com.kosim97.yeyakboja.databinding.FragmentDetailBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    val calList = ArrayList<CalendarDay>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentDetailBinding.inflate(inflater).also {
            detailBinding = it
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = detailViewModel
            return it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailBinding.campingItem = args.item
        Glide.with(requireContext()).load(args.item.campingImage).into(detailBinding.campingIv)
        detailViewModel.getReserveList(args.item.campingURL)
        detailBinding.calendar.addDecorator(ReserveDayDecorator())
        initObserver()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.reserveList.collectLatest {
                    detailBinding.calendar.currentDate = CalendarDay.from(it[0].substring(0,4).toInt(),it[0].substring(5,6).toInt(),it[0].substring(6,8).toInt())
                    for (i in it.indices) {
                        detailBinding.calendar.addDecorator(NotReserveDayDecorator(CalendarDay.from(it[i].substring(0,4).toInt(),it[i].substring(5,6).toInt(),it[i].substring(6,8).toInt())))
                    }
                }
            }
        }
    }

    inner class NotReserveDayDecorator(day: CalendarDay): DayViewDecorator {
        private val mDay = day
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return day == mDay
        }

        override fun decorate(view: DayViewFacade?) {
            view?.setBackgroundDrawable(resources.getDrawable(R.drawable.ic_favorite_24))
            view?.setDaysDisabled(true)
        }
    }

    inner class ReserveDayDecorator(): DayViewDecorator {
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return true
        }

        override fun decorate(view: DayViewFacade?) {
            view?.setBackgroundDrawable(resources.getDrawable(R.drawable.ic_home_black_24dp))
            view?.setDaysDisabled(true)
        }
    }

}