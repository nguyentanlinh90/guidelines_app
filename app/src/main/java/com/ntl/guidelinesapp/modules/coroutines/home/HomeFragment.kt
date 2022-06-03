package com.ntl.guidelinesapp.modules.coroutines.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ntl.guidelinesapp.modules.coroutines.multiplerequest.cocurrency.ConcurrencyFragment
import com.ntl.guidelinesapp.modules.coroutines.multiplerequest.sequential.SequentialFragment
import com.ntl.guidelinesapp.modules.coroutines.singlerequest.SingleFragment
import com.ntl.guidelinesapp.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home_coroutine, null, false)

        rootView.findViewById<Button>(R.id.btnSingle).setOnClickListener {
            openScreen(SingleFragment())
        }

        rootView.findViewById<Button>(R.id.btnSequentital).setOnClickListener {
            openScreen(SequentialFragment())
        }

        rootView.findViewById<Button>(R.id.btnConcurency).setOnClickListener {
            openScreen(ConcurrencyFragment())
        }


        return rootView
    }

    private fun openScreen(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(fragment.javaClass.name).commit()
    }

}