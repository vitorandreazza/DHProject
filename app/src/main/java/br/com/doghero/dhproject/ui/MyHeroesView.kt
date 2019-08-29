package br.com.doghero.dhproject.ui

import br.com.doghero.dhproject.ui.base.BaseView
import br.com.doghero.dhproject.ui.base.HolderItem

interface MyHeroesView : BaseView {

    fun showHeroes(heroesItems: List<HolderItem>)
}