package br.com.doghero.dhproject.ui

import br.com.doghero.dhproject.R
import br.com.doghero.dhproject.data.HeroesRepository
import br.com.doghero.dhproject.ui.base.BasePresenter
import br.com.doghero.dhproject.ui.base.BaseSchedulerProvider
import br.com.doghero.dhproject.ui.base.HolderItem
import br.com.doghero.dhproject.utils.applyIoToMainSchedulers
import br.com.doghero.dhproject.utils.applyLoading

class MyHeroesPresenter(
        override val view: MyHeroesView,
        private val heroesRepository: HeroesRepository,
        private val schedulerProvider: BaseSchedulerProvider
) : BasePresenter(view) {

    override fun subscribe() {
        heroesRepository.getMyHeroes()
                .applyIoToMainSchedulers(schedulerProvider)
                .applyLoading(view)
                .subscribe({
                    val recentsHeader = HeroHeader(text = R.string.myheroes_recents_header)
                    val favoritesHeader = HeroHeader(text = R.string.myheroes_likes_header)
                    val heroItems = ArrayList<HolderItem>().apply {
                        add(recentsHeader)
                        addAll(it.recents)
                        add(favoritesHeader)
                        addAll(it.favorites)
                    }
                    view.showHeroes(heroItems)
                }, {
                    view.showMessage(R.string.error_getting_heroes)
                })
                .addToPresenterComposite()
    }

    companion object {
        private val TAG = MyHeroesPresenter::class.java.simpleName
    }
}