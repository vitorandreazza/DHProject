package br.com.doghero.dhproject.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.doghero.dhproject.R
import br.com.doghero.dhproject.data.model.Hero
import br.com.doghero.dhproject.ui.base.HolderItem

class MyHeroesAdapter(private val items: List<HolderItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> LayoutInflater.from(parent.context).inflate(R.layout.item_my_hero_header, parent, false).let {
                MyHeroHeaderHolder(it)
            }
            TYPE_HERO -> LayoutInflater.from(parent.context).inflate(R.layout.item_my_hero, parent, false).let {
                MyHeroHolder(it)
            }
            else -> throw IllegalArgumentException("Invalid view holder type.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is MyHeroHeaderHolder -> holder.bind(item as HeroHeader)
            is MyHeroHolder -> holder.bind(item as Hero)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Hero -> TYPE_HERO
            is HeroHeader -> TYPE_HEADER
            else -> throw IllegalArgumentException("Invalid item instance.")
        }
    }

    companion object {

        const val TYPE_HEADER = 0
        const val TYPE_HERO = 1
    }
}