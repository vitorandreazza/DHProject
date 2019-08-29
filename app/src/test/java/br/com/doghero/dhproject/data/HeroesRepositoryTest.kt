package br.com.doghero.dhproject.data

import br.com.doghero.dhproject.data.model.Hero
import br.com.doghero.dhproject.data.model.MyHeroes
import br.com.doghero.dhproject.data.model.User
import br.com.doghero.dhproject.data.remote.HeroesApi
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HeroesRepositoryTest {

    @Mock
    private lateinit var heroesApi: HeroesApi
    private lateinit var heroesRepository: HeroesRepository
    private lateinit var myHeroes: MyHeroes
    private lateinit var testSubscriber: TestObserver<MyHeroes>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        heroesRepository = HeroesRepository(heroesApi)

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
        testSubscriber = TestObserver()
    }

    @Test
    fun `successful request heroes`() {
        given(heroesApi.getMyHeroes()).willReturn(Single.just(myHeroes))

        heroesRepository.getMyHeroes().subscribe(testSubscriber)

        then(heroesApi).should().getMyHeroes()
        testSubscriber.assertValue(myHeroes)
        testSubscriber.assertNoErrors()
    }

    @Test
    fun `fail request heroes`() {
        given(heroesApi.getMyHeroes()).willReturn(Single.error(Exception()))

        heroesRepository.getMyHeroes().subscribe(testSubscriber)

        then(heroesApi).should().getMyHeroes()
        testSubscriber.assertError(Throwable::class.java)
        testSubscriber.assertNoValues()
    }
}