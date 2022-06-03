package com.ntl.guidelinesapp.modules.coroutines.singlerequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ntl.guidelinesapp.R

class SingleFragment : Fragment() {

    private val viewModel by viewModels<SingleViewModel>()
    private lateinit var tvResult: TextView
    private lateinit var btCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_single, null, false)
        tvResult = rootView.findViewById(R.id.tv_result)
        btCancel = rootView.findViewById(R.id.bt_cancel)

        viewModel.data.observe(viewLifecycleOwner) { data ->
            tvResult.text = "Result $data"
        }

        viewModel.error.observe(viewLifecycleOwner) { message ->
            tvResult.text = message
        }

        btCancel.setOnClickListener {
            viewModel.cancel()
        }

        return rootView
    }

}