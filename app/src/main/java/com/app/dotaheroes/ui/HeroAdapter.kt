package com.app.dotaheroes.ui

import android.view.View
import com.app.dotaheroes.R
import com.app.dotaheroes.models.HeroStats
import com.app.dotaheroes.other.Recycler
import com.app.dotaheroes.other.TapListener
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.hero_row.view.*

enum class ViewType {
    GUEST
}

object HeroAdapter: Recycler.Renderer<HeroStats> {
    override fun render(itemView: View, rm: HeroStats, pos: Int, tapListener: TapListener?) {
        itemView.heroName.text = rm.localized_name

        Glide.with(itemView.context)
            .load(rm.getIconImage())
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(itemView.heroIcon)
    }

}