package br.com.doghero.dhproject.ui

import android.support.annotation.StringRes
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.doghero.dhproject.ui.base.HolderItem
import kotlinx.android.synthetic.main.item_my_hero_header.view.*

class MyHeroHeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(header: HeroHeader) {
        itemView.tv_my_hero_header.text = itemView.context.getString(header.text)
    }
}

data class HeroHeader(@StringRes val text: Int) : HolderItem