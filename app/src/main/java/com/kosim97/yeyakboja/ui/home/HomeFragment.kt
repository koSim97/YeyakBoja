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
import com.kosim97.yeyakboja.databinding.FragmentHomeBinding
import com.kosim97.yeyakboja.ui.home.camping.CampingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Connection
import org.jsoup.Jsoup

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var campingAdapter: CampingAdapter
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
        homeViewModel.getGymClassData()
        homeViewModel.getCampingData()
    }

    private fun initView() {

    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.campingList.collectLatest {
                    setCampingItem(it)
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
        val url = data[0].campingURL
        Log.d("test", url)
        //getReserveList(url)
        campingAdapter.submitList(data.toMutableList())
    }

    private fun getReserveList(url: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val dataUrl = Jsoup.connect(url).get()
            val testMap: MutableMap<String, String> = mutableMapOf()
            testMap["sysToday"] = dataUrl.select("#aform #sysToday").`val`()
            testMap["rsv_svc_id"] = dataUrl.select("#aform #rsv_svc_id").`val`()
            testMap["use_time_unit_code"] = dataUrl.select("#aform #use_time_unit_code").`val`()
            testMap["tme_ty_code"] = dataUrl.select("#aform #tme_ty_code").`val`()
            testMap["wait_posbl_co"] = dataUrl.select("#aform #wait_posbl_co").`val`()
            testMap["rsvde_stdr_rcept_daycnt"] =
                dataUrl.select("#aform #rsvde_stdr_rcept_daycnt").`val`()
            testMap["sltYear"] = dataUrl.select("#aform #sltYear").`val`()
            testMap["sltMonth"] = dataUrl.select("#aform #sltMonth").`val`()
            testMap["sltDay"] = dataUrl.select("#aform #sltDay").`val`()
            testMap["yyyymm"] = dataUrl.select("#aform #yyyymm").`val`()
            testMap["yyyy"] = dataUrl.select("#aform #yyyy").`val`()
            testMap["mm"] = dataUrl.select("#aform #mm").`val`()
            testMap["dd"] = dataUrl.select("#aform #dd").`val`()
            testMap["calCheck"] = dataUrl.select("#aform #calCheck").`val`()
            testMap["unitCheck"] = dataUrl.select("#aform #unitCheck").`val`()
            testMap["count"] = dataUrl.select("#aform #count").`val`()
            testMap["reqst_resve_unit_value"] =
                dataUrl.select("#aform #reqst_resve_unit_value").`val`()
            testMap["mumm_use_posbl_time"] = dataUrl.select("#aform #mumm_use_posbl_time").`val`()
            testMap["mxmm_use_posbl_time"] = dataUrl.select("#aform #mxmm_use_posbl_time").`val`()
            testMap["type"] = dataUrl.select("#aform #type").`val`()
            testMap["mgis_cdata"] = dataUrl.select("#aform #mgis_cdata").`val`()
            testMap["mgis_buffer"] = dataUrl.select("#aform #mgis_buffer").`val`()
            testMap["x"] = dataUrl.select("#aform #x").`val`()
            testMap["y"] = dataUrl.select("#aform #y").`val`()
            testMap["register_no"] = dataUrl.select("#aform #register_no").`val`()
            testMap["logincheck"] = dataUrl.select("#aform #logincheck").`val`()
            testMap["satisfyScore"] = dataUrl.select("#aform #satisfyScore").`val`()
            testMap["booking_end"] = dataUrl.select("#aform #booking_end").`val`()

            val docs =
                Jsoup.connect("https://yeyak.seoul.go.kr/web/reservation/selectListReservCalAjax.do")
                    .header("Content-Type", "application/x-www-form-urlencoded, charset=UTF-8")
                    .header("Host", "yeyak.seoul.go.kr")
                    .header("Origin", "https://yeyak.seoul.go.kr")
                    .header("Referer", url)
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept-Language", "ko-KR,ko;q=0.9,en;q=0.8")
                    .header("Connection", "keep-alive")
                    .header(
                        "sec-ch-ua",
                        "\"Google Chrome\";v=\"113\", \"Chromium\";v=\"113\", \";Not A Brand\";v=\"24\""
                    )
                    .header("sec-ch-ua-mobile", "?0")
                    .header("Sec-Fetch-Dest", "empty")
                    .header("Sec-Fetch-Mode", "cors")
                    .header("Sec-Fetch-Site", "same-origin")
                    .header(
                        "User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36"
                    )
                    .data(testMap)
                    .ignoreContentType(true)
                    .method(Connection.Method.POST)
                    .execute()

            val docsConvert = JSONObject(docs.body())
            val docsArray= docsConvert.get("resultListDays") as JSONArray

            for (i in 0 until docsArray.length()) {
                val docsObj = docsArray.getJSONObject(i)
                if (docsObj.get("SVC_RESVE_CODE") == "Y") {
                    Log.d("test","asd $docsObj")
                }
            }
        }
    }
}