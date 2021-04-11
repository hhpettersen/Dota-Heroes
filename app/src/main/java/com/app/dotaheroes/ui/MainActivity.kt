package com.app.dotaheroes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dotaheroes.R
import com.app.dotaheroes.models.HeroStats
import com.app.dotaheroes.other.Recycler
import com.app.dotaheroes.other.Status
import com.app.dotaheroes.other.TapListener
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.hero_row.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.res.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    progress.visibility = View.GONE
                    recyclerViewHeroes.visibility = View.VISIBLE
                    it.data.let { res->
                        res?.let { heroes ->
                            renderHeros(heroes)
                        }
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

    private fun renderHeros(heroes: List<HeroStats>) {
        val heroAdapter = Recycler.Adapter(heroes)
        heroAdapter.register(
            Recycler.Type(
                HeroStats::class,
                ViewType.GUEST.ordinal,
                R.layout.hero_row,
                bind = { itemView, renderModel, pos, tapListener ->
                    HeroAdapter.render(itemView, renderModel, pos, tapListener)
                })
        )

        recyclerViewHeroes.apply {
            adapter = heroAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}