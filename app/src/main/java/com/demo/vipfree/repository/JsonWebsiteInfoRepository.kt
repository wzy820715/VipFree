package com.demo.vipfree.repository

import android.content.res.AssetManager
import com.demo.vipfree.model.WebsiteInfoResult
import com.demo.vipfree.ui.MainApplication
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class JsonWebsiteInfoRepository : BaseRepository{

    private val _parseResults = MutableSharedFlow<WebsiteInfoResult>(replay = 1)
    // Expose read-only SharedFlow
    private val parseResults = _parseResults.asSharedFlow()

    suspend fun getWebsiteInfo(): Flow<WebsiteInfoResult>{
//        withContext(Dispatchers.IO) {
            val sb = StringBuilder()
            try {
                val assetManager: AssetManager = MainApplication.getContext()!!.assets
                val bf = BufferedReader(
                    InputStreamReader(
                        assetManager.open("website_data")
                    )
                )
                var line: String?
                while (bf.readLine().also { line = it } != null) {
                    sb.append(line)
                }
            } catch (e: IOException) {
                _parseResults.emit(WebsiteInfoResult.Error(e))
            }
            val result: WebsiteInfoResult.Success = Gson().fromJson(sb.toString(),
                WebsiteInfoResult.Success::class.java)
            _parseResults.emit(result)
//        }

        return parseResults
    }

//    suspend fun getWebsiteInfo1(): WebsiteInfoResult{
////        withContext(Dispatchers.IO) {
//        val sb = StringBuilder()
//        try {
//            val assetManager: AssetManager = MainApplication.getContext()!!.assets
//            val bf = BufferedReader(
//                InputStreamReader(
//                    assetManager.open("website_data")
//                )
//            )
//            var line: String?
//            while (bf.readLine().also { line = it } != null) {
//                sb.append(line)
//            }
//        } catch (e: IOException) {
//            return WebsiteInfoResult.Error(e)
//        }
//
////        }
//
//        return Gson().fromJson(sb.toString(),
//            WebsiteInfoResult.Success::class.java)
//    }

}