package br.com.doghero.dhproject.data.model

import br.com.doghero.dhproject.ui.base.HolderItem
import com.google.gson.annotations.SerializedName

data class MyHeroes(
        @SerializedName("favorites") val favorites: List<Hero>,
        @SerializedName("recents") val recents: List<Hero>
)

data class Hero(
        @SerializedName("id") val id: Int,
        @SerializedName("price") val price: Double,
        @SerializedName("is_superhero") val isSuperhero: Boolean,
        @SerializedName("address_neighborhood") val addressNeighborhood: String,
        @SerializedName("user") val user: User
) : HolderItem

data class User(
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("first_name") val firstName: String
)
