package com.ntl.guidelinesapp.modules.kotlin_koin_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ntl.guidelinesapp.AppUtils
import com.ntl.guidelinesapp.databinding.ActivityKoinMvvmActivityBinding
import com.ntl.guidelinesapp.modules.kotlin_koin_mvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class KoinMVVMActivity : AppCompatActivity() {
    lateinit var binding: ActivityKoinMvvmActivityBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_koin_mvvm_activity)
        AppUtils.setTitleBar(this, KoinMVVMActivity::class.java)
        binding = ActivityKoinMvvmActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btPlus.setOnClickListener {
                viewModel.increase()
            }
        }

        viewModel.count.observe(this) {
            binding.tvNumber.text = it.toString()
        }

    }
}