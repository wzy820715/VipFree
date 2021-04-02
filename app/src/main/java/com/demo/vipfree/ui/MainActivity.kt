package com.demo.vipfree.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.vipfree.adapter.WebsiteListAdapter
import com.demo.vipfree.databinding.ActivityMainBinding
import com.demo.vipfree.model.WebsiteInfoResult

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//      Use the 'by viewModels()' by "androidx.fragment:fragment-ktx:1.2.5" artifact
    private val mWebsiteVM : WebsiteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initData()
    }

    private fun initData(){
        val mAdapter = WebsiteListAdapter()
        binding.rv.apply {
            addItemDecoration(
                DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL)
            )
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
//        val mWebsiteVM = ViewModelProvider(this).get(WebsiteViewModel::class.java)


        lifecycleScope.launchWhenCreated{
            mWebsiteVM.getDataList().observe(this@MainActivity){ result ->
                when(result){
                    is WebsiteInfoResult.Success ->{
                        mAdapter.submitList(result.list)
                    }
                    is WebsiteInfoResult.Error ->{
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                    }

                }

                binding.progressBar.visibility = View.GONE

            }
            binding.progressBar.visibility = View.VISIBLE
        }
    }

}
