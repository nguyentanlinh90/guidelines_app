package com.ntl.guidelinesapp.modules.coroutines

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ntl.guidelinesapp.AppUtils
import com.ntl.guidelinesapp.R
import com.ntl.guidelinesapp.modules.coroutines.home.HomeFragment

class CoroutinesActivity : AppCompatActivity() {
    private val TAG = CoroutinesActivity::class.java.simpleName
    private var isShowResult = false
    private var count = 0
    private lateinit var tvData: TextView
    private lateinit var pbLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        AppUtils.setTitleBar(this, CoroutinesActivity::class.java)

        if (null == savedInstanceState) {
            supportFragmentManager.beginTransaction().add(R.id.container, HomeFragment())
                .addToBackStack("home".javaClass.name).commit()
        }
    }


}