package br.com.doghero.dhproject.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.doghero.dhproject.R
import br.com.doghero.dhproject.data.model.Hero
import br.com.doghero.dhproject.utils.ImageHelper
import br.com.doghero.dhproject.utils.toCurrency
import br.com.doghero.dhproject.utils.toast
import br.com.doghero.dhproject.utils.visible
import kotlinx.android.synthetic.main.item_my_hero.view.*
import java.util.*

class MyHeroHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(hero: Hero) {
        with(itemView) {
            ImageHelper.loadCircularImage(context, hero.user.imageUrl, R.drawable.ic_launcher_foreground, iv_hero_photo)
            if (hero.isSuperhero) iv_superhero.visible()

            tv_hero_stars.text = context.getString(R.string.myheroes_stars, Random().nextInt(50))
            tv_hero_name.text = hero.user.firstName
            tv_hero_neighborhood.text = hero.addressNeighborhood
            tv_night_price.text = hero.price.toCurrency()

            btn_chat.setOnClickListener {
                context.toast(context.getString(R.string.myheroes_toast_chat))
            }
            btn_book_again.setOnClickListener {
                context.toast(context.getString(R.string.myheroes_toast_bookagain))
            }
            btn_like.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) context.toast(context.getString(R.string.myheroes_toast_favorite_added))
            }
        }
    }
}