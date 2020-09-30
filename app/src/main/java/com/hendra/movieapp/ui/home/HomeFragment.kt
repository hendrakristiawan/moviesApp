package com.hendra.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hendra.movieapp.R
import com.hendra.movieapp.utils.ResourceState

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var adapterList: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recycler = view.findViewById(R.id.recyclerPoster)
        initObserver()
        return view
    }

    private fun initRecycler() {
        recycler.apply {
            adapter = adapterList
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
        }
    }

    private fun initObserver() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.listPosterLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status == ResourceState.SUCCESS) {
                it?.data?.let { list ->
                    adapterList = HomeAdapter(list)
                    initRecycler()
                }
            }
        })
    }
}