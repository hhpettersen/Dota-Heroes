package com.app.dotaheroes.models

import com.app.dotaheroes.other.Recycler

data class HeroStats(
    val id: Int? = 0,
    val name: String? = "",
    val localized_name: String? = ""
) : Recycler.RenderModel {
    fun getIconImage(): String {
        val parsedName = name?.replace("npc_dota_hero_", "")
        return "https://cdn.dota2.com/apps/dota2/images/heroes/${parsedName}_vert.jpg?"
    }
}