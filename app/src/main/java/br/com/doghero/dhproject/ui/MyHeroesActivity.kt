package br.com.doghero.dhproject.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.doghero.dhproject.R
import br.com.doghero.dhproject.data.HeroesRepository
import br.com.doghero.dhproject.data.remote.HeroesApi
import br.com.doghero.dhproject.ui.base.HolderItem
import br.com.doghero.dhproject.ui.base.SchedulerProvider
import br.com.doghero.dhproject.utils.gone
import br.com.doghero.dhproject.utils.toast
import br.com.doghero.dhproject.utils.visible
import kotlinx.android.synthetic.main.activity_my_heroes.*
import kotlinx.android.synthetic.main.include_progressbar.*
import kotlinx.android.synthetic.main.item_my_hero.*

class MyHeroesActivity : AppCompatActivity(), MyHeroesView {

    private val presenter = MyHeroesPresenter(this, HeroesRepository(HeroesApi), SchedulerProvider)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_heroes)

        initUi()
        presenter.subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    private fun initUi() {
        supportActionBar?.title = getString(R.string.myheroes_title)
    }

    override fun showHeroes(heroesItems: List<HolderItem>) {
        rv_my_heroes.run {
            layoutManager = LinearLayoutManager(this@MyHeroesActivity)
            adapter = MyHeroesAdapter(heroesItems)
        }
    }

    override fun showProgress() {
        progress_overlay.visible()
    }

    override fun hideProgress() {
        progress_overlay.gone()
    }

    override fun showMessage(message: Int) {
        toast(getString(message))
    }
}
