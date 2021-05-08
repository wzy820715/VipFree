package com.demo.vipfree.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.transition.ChangeBounds
import android.view.KeyEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.demo.vipfree.databinding.ActivityWebBinding
import com.demo.vipfree.model.WebsiteInfo
import com.demo.vipfree.utils.UiUtils

class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding
    private var mWebsite = "https://www.iqiyi.com/"
    private var mPrefix: MutableList<String> = ArrayList()
    private var mTargetUrl: String = ""

    companion object {
        const val PARSE_URL = "http://jx.618g.com/?url="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initData()
    }

    private fun initData(){
        binding.btnWatch.isEnabled = false
        var bundle: Bundle? = this.intent.extras
        if(bundle != null){
           val webInfo : WebsiteInfo? = bundle.getParcelable<WebsiteInfo>("info")
            if(webInfo != null){
                mWebsite = webInfo.url
                mPrefix = webInfo.prefix
            }
        }
        val bounds = ChangeBounds()
        window.sharedElementEnterTransition = bounds
        initWebView()
    }

    private fun initWebView() {
        UiUtils.setWebViewSetting(binding.wv)
        binding.wv.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url.toString()
                return if (url.startsWith("http:") || url.startsWith("https:")) {
                    view!!.loadUrl(url)
                    false
                } else {
                    true
                }

            }
            override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressBar.visibility = View.VISIBLE
                if(checkPreFix(url.toString())){
                    doAnim()
                    binding.btnWatch.isEnabled = true
                    mTargetUrl = url.toString()
                }else{
                    binding.btnWatch.isEnabled = false
                }
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                super.onReceivedError(view, request, error)
                binding.progressBar.visibility = View.GONE
            }
        }
        binding.wv.loadUrl(mWebsite)
    }

    fun checkPreFix(url: String):Boolean{
        for (prefix in mPrefix){
            if(url.startsWith(prefix))
                return true
        }
        return false
    }

    private fun doAnim() {
        val animatorX: ObjectAnimator = ObjectAnimator.ofFloat(
            binding.btnWatch, "scaleX", 1f, 1.2f, 1f)
        val animatorY: ObjectAnimator = ObjectAnimator.ofFloat(
            binding.btnWatch, "scaleY", 1f, 1.2f, 1f)
        val animatorTransY: ObjectAnimator = ObjectAnimator.ofFloat(
            binding.btnWatch, "translationY", 0f, -40f, 0f)
        val animatorSet : AnimatorSet = AnimatorSet()
        animatorSet.playTogether(animatorX, animatorY, animatorTransY)
        animatorSet.duration = 400
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.start()
    }

    fun onWatchBtnClicked(@Suppress("UNUSED_PARAMETER") view: View){
        if(mTargetUrl.isNotEmpty()){
            binding.wv.stopLoading()
            val intent: Intent = Intent()
            intent.action = "android.intent.action.VIEW"
            intent.data = Uri.parse(PARSE_URL + mTargetUrl)
            startActivity(intent)
            finish()
        }
    }

    fun onReloadBtnClicked(@Suppress("UNUSED_PARAMETER") view: View){
        binding.wv.reload()
    }

    fun onCloseBtnClicked(@Suppress("UNUSED_PARAMETER") view: View){
        this.finish()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.wv.canGoBack()) {
            binding.wv.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}