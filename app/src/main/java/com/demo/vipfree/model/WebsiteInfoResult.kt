package com.demo.vipfree.model

import java.lang.Exception

sealed class WebsiteInfoResult{
    data class Success(val list: List<WebsiteInfo>) : WebsiteInfoResult()
    data class Error(val error: Exception) : WebsiteInfoResult()
}
