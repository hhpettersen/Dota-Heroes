package com.app.dotaheroes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dotaheroes.R
import com.app.dotaheroes.other.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: HeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = HeroAdapter()
        recyclerViewHeroes.layoutManager = LinearLayoutManager(this)
        recyclerViewHeroes.adapter = adapter

        mainViewModel.res.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    progress.visibility = View.GONE
                    recyclerViewHeroes.visibility = View.VISIBLE
                    it.data.let { res->
                        res?.let { adapter.submitList(it) }
                    }
                }
                Status.LOADING -> {
                    progress.visibility = View.VISIBLE
                    recyclerViewHeroes.visibility = View.GONE
                }
                Status.ERROR -> {
                    progress.visibility = View.GONE
                    recyclerViewHeroes.visibility = View.VISIBLE
                    Snackbar.make(rootView, "Something went wrong",Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }
}