package br.com.doghero.dhproject.data.remote

import android.support.annotation.VisibleForTesting
import br.com.doghero.dhproject.data.model.MyHeroes
import com.google.gson.Gson
import io.reactivex.Single
import java.util.concurrent.TimeUnit

object HeroesApi {
    private const val MY_HEROES_JSON_RESPONSE = "{\"recents\":[{\"is_superhero\":true,\"user\":{\"first_name\":\"Eduardo\",\"image_url\":\"https://doghero-uploads.s3.amazonaws.com/uploads/photo/1433381/sq135_DH_24012018123600937_98895.png\"},\"address_neighborhood\":\"Parque Chacabuco\",\"price\":55},{\"id\":53012,\"is_superhero\":false,\"user\":{\"first_name\":\"Maria\",\"image_url\":\"https://doghero-uploads.s3.amazonaws.com/uploads/photo/764186/sq135_20170404094603_345722_1491353162913.jpg\"},\"address_neighborhood\":\"Aclimação\",\"price\":60}],\"favorites\":[{\"is_superhero\":false,\"user\":{\"first_name\":\"Cris\",\"image_url\":\"https://doghero-uploads.s3.amazonaws.com/uploads/photo/1088842/sq135_20170926010555_270986_1506441955458.jpg\"},\"address_neighborhood\":\"Vila Gomes Cardim\",\"price\":70},{\"is_superhero\":false,\"user\":{\"first_name\":\"Gustavo\",\"image_url\":\"https://doghero-uploads.s3.amazonaws.com/uploads/photo/242781/sq135_Cris_SA-MI.jpg\"},\"address_neighborhood\":\"Vila Mariana\",\"price\":45},{\"is_superhero\":true,\"user\":{\"first_name\":\"Marina\",\"image_url\":\"https://doghero-uploads.s3.amazonaws.com/uploads/photo/1398933/sq135_1516145372035_517808_IMG0535JPG.jpeg\"},\"address_neighborhood\":\"Vila Mariana\",\"price\":65}]}"
    private val gson = Gson()

    fun getMyHeroes(): Single<MyHeroes> = Single.fromCallable { parseMyHeroesResponse() }

    @VisibleForTesting
    fun parseMyHeroesResponse() =
            gson.fromJson<MyHeroes>(MY_HEROES_JSON_RESPONSE, MyHeroes::class.java)
}
