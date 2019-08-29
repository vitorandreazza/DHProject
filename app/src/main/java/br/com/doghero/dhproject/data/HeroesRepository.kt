package br.com.doghero.dhproject.data

import br.com.doghero.dhproject.data.remote.HeroesApi

class HeroesRepository(private val heroesApi: HeroesApi) {

    fun getMyHeroes() = heroesApi.getMyHeroes()
}