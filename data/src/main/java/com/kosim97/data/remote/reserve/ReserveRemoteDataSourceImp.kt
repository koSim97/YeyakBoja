package com.kosim97.data.remote.reserve

import org.json.JSONObject
import org.jsoup.Connection
import org.jsoup.Jsoup
import javax.inject.Inject

class ReserveRemoteDataSourceImp @Inject constructor(): ReserveRemoteDataSource {
    override suspend fun getReserveList(url: String): JSONObject {
        val dataUrl = Jsoup.connect(url).get()
        val dataMap: MutableMap<String, String> = mutableMapOf()
        dataMap["sysToday"] = dataUrl.select("#aform #sysToday").`val`()
        dataMap["rsv_svc_id"] = dataUrl.select("#aform #rsv_svc_id").`val`()
        dataMap["use_time_unit_code"] = dataUrl.select("#aform #use_time_unit_code").`val`()
        dataMap["tme_ty_code"] = dataUrl.select("#aform #tme_ty_code").`val`()
        dataMap["wait_posbl_co"] = dataUrl.select("#aform #wait_posbl_co").`val`()
        dataMap["rsvde_stdr_rcept_daycnt"] =
            dataUrl.select("#aform #rsvde_stdr_rcept_daycnt").`val`()
        dataMap["sltYear"] = dataUrl.select("#aform #sltYear").`val`()
        dataMap["sltMonth"] = dataUrl.select("#aform #sltMonth").`val`()
        dataMap["sltDay"] = dataUrl.select("#aform #sltDay").`val`()
        dataMap["yyyymm"] = dataUrl.select("#aform #yyyymm").`val`()
        dataMap["yyyy"] = dataUrl.select("#aform #yyyy").`val`()
        dataMap["mm"] = dataUrl.select("#aform #mm").`val`()
        dataMap["dd"] = dataUrl.select("#aform #dd").`val`()
        dataMap["calCheck"] = dataUrl.select("#aform #calCheck").`val`()
        dataMap["unitCheck"] = dataUrl.select("#aform #unitCheck").`val`()
        dataMap["count"] = dataUrl.select("#aform #count").`val`()
        dataMap["reqst_resve_unit_value"] =
            dataUrl.select("#aform #reqst_resve_unit_value").`val`()
        dataMap["mumm_use_posbl_time"] = dataUrl.select("#aform #mumm_use_posbl_time").`val`()
        dataMap["mxmm_use_posbl_time"] = dataUrl.select("#aform #mxmm_use_posbl_time").`val`()
        dataMap["type"] = dataUrl.select("#aform #type").`val`()
        dataMap["mgis_cdata"] = dataUrl.select("#aform #mgis_cdata").`val`()
        dataMap["mgis_buffer"] = dataUrl.select("#aform #mgis_buffer").`val`()
        dataMap["x"] = dataUrl.select("#aform #x").`val`()
        dataMap["y"] = dataUrl.select("#aform #y").`val`()
        dataMap["register_no"] = dataUrl.select("#aform #register_no").`val`()
        dataMap["logincheck"] = dataUrl.select("#aform #logincheck").`val`()
        dataMap["satisfyScore"] = dataUrl.select("#aform #satisfyScore").`val`()
        dataMap["booking_end"] = dataUrl.select("#aform #booking_end").`val`()

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
                .data(dataMap)
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .execute()

        return JSONObject(docs.body())
    }
}