package com.demo.vipfree.utils

import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import com.demo.vipfree.ui.MainApplication

class UiUtils {

    companion object{
        fun dp2px(dp: Float): Int {
            val density: Float? =
                MainApplication.getContext()?.resources?.displayMetrics?.density
            return (density!! * dp + .5f).toInt()
        }

        /**
         * 获取系统剪贴板内容
         */
        fun getClipContent(): String? {
            val manager = MainApplication.getContext()
                ?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            if (manager.hasPrimaryClip() && manager.primaryClip!!.itemCount > 0) {
                val addedText = manager.primaryClip!!.getItemAt(0).text
                val addedTextString = addedText.toString()
                if (!TextUtils.isEmpty(addedTextString)) {
                    return addedTextString
                }
            }
            return ""
        }

        fun setWebViewSetting(webView: WebView) {
            val webSettings = webView.settings
            // webview启用javascript支持 用于访问页面中的javascript
            webSettings.javaScriptEnabled = true
            //设置WebView缓存模式 默认断网情况下不缓存
            webSettings.cacheMode = WebSettings.LOAD_DEFAULT
            //让WebView支持DOM storage API
            webSettings.domStorageEnabled = true
            //让WebView支持缩放
            webSettings.setSupportZoom(true)
            //启用WebView内置缩放功能
            webSettings.builtInZoomControls = true
            webSettings.textZoom = 100
            if (Build.VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
            }
            webSettings.loadWithOverviewMode = true;
            //让WebView支持可任意比例缩放
            webSettings.useWideViewPort = true
            //让WebView支持播放插件
//            webSettings.pluginState = WebSettings.PluginState.ON
            //设置WebView使用内置缩放机制时，是否展现在屏幕缩放控件上
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                webSettings.displayZoomControls = false
            }
            //设置在WebView内部是否允许访问文件
//            webSettings.allowFileAccess = true
            //设置WebView的访问UserAgent
//            webSettings.userAgentString =
//                "Mozilla/5.0 (Linux; Android 8.0.0; SM705 Build/SANFRANCISCO) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36 JsBridge"
            webSettings.userAgentString =
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36”"
            //设置脚本是否允许自动打开弹窗
//            webSettings.javaScriptCanOpenWindowsAutomatically = true
            // 加快HTML网页加载完成速度
            webSettings.loadsImagesAutomatically = Build.VERSION.SDK_INT >= 19
            // 开启Application H5 Caches 功能
            // webSettings.setAppCacheEnabled(true);
            // 设置编码格式
            webSettings.defaultTextEncodingName = "utf-8"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
        }
    }
}