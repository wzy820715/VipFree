package com.demo.vipfree.model

sealed class WebsiteInfoResult{
    data class Success(val list: List<WebsiteInfo>) : WebsiteInfoResult()
    data class Error(val error: Exception) : WebsiteInfoResult()
}
