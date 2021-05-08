package com.demo.vipfree.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.demo.vipfree.model.WebsiteInfoResult
import com.demo.vipfree.repository.JsonWebsiteInfoRepository
import kotlinx.coroutines.Dispatchers.Default

class WebsiteViewModel : ViewModel() {

//    private val viewModelJob = SupervisorJob()
//    private val uiScope = CoroutineScope(Dispatchers.Default + viewModelJob)

//    private val mDataList: MutableLiveData<WebsiteInfoList> by lazy {
//        MutableLiveData<WebsiteInfoList>().also {
//            requestData()
//        }
//    }

    private val repository: JsonWebsiteInfoRepository = JsonWebsiteInfoRepository()

    private val mData: LiveData<WebsiteInfoResult> = liveData(Default) {
        val repos = repository.getWebsiteInfo().asLiveData()
        emitSource(repos)
//        val repos = repository.getWebsiteInfo1()
//        emit(repos)
    }

    fun getDataList(): LiveData<WebsiteInfoResult> = mData

//    private fun requestData(){
//        viewModelScope.launch {
//            async(Dispatchers.IO){
//                val sb = StringBuilder()
//                try {
//                    val assetManager: AssetManager = MainApplication.getContext()!!.assets
//                    val bf = BufferedReader(
//                        InputStreamReader(
//                            assetManager.open("website_data")
//                        )
//                    )
//                    var line: String?
//                    while (bf.readLine().also { line = it } != null) {
//                        sb.append(line)
//                    }
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//                val gson = Gson()
//                val result: WebsiteInfoList = gson.fromJson(sb.toString(), WebsiteInfoList::class.java)
//                mDataList.postValue(result)
//            }
//        }
//    }

}