package br.com.doghero.dhproject.ui

import br.com.doghero.dhproject.data.HeroesRepository
import br.com.doghero.dhproject.data.model.Hero
import br.com.doghero.dhproject.data.model.MyHeroes
import br.com.doghero.dhproject.data.model.User
import br.com.doghero.dhproject.ui.base.ImmediateSchedulerProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MyHeroesPresenterTest {

    @Mock
    private lateinit var heroesRepository: HeroesRepository
    @Mock
    private lateinit var myHeroesView: MyHeroesView
    private lateinit var presenter: MyHeroesPresenter
    private lateinit var myHeroes: MyHeroes

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MyHeroesPresenter(myHeroesView, heroesRepository, ImmediateSchedulerProvider())

        val hero1 = Hero(
                id = 0,
                price = 15.0,
                isSuperhero = true,
                addressNeighborhood = "Address1",
                user = User(imageUrl = "url1", firstName = "Name1")
        )
        val hero2 = Hero(
                id = 0,
                price = 20.0,
                isSuperhero = false,
                addressNeighborhood = "Address2",
                user = User(imageUrl = "url2", firstName = "Name2")
        )
        myHeroes = MyHeroes(recents = arrayListOf(hero1, hero2), favorites = arrayListOf(hero1, hero2))
    }

    @Test
    fun `successful load and show heroes`() {
        given(heroesRepository.getMyHeroes()).willReturn(Single.just(myHeroes))

        presenter.subscribe()

        then(myHeroesView).should().showProgress()
        then(myHeroesView).should().showHeroes(anyList())
        then(myHeroesView).should().hideProgress()
    }

    @Test
    fun `failure load and show heroes`() {
        given(heroesRepository.getMyHeroes()).willReturn(Single.error(Exception()))
        val inOrder = inOrder(myHeroesView)

        presenter.subscribe()

        then(myHeroesView).should(inOrder).showProgress()
        then(myHeroesView).should(inOrder).showMessage(anyInt())
        then(myHeroesView).should(inOrder).hideProgress()
        then(myHeroesView).should(inOrder, never()).showHeroes(anyList())
    }
}