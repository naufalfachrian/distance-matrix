package id.bungamungil.distancematrix.network

import com.google.gson.JsonObject
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.QueryMap

internal interface GoogleMapsInternalClient {

    @GET("distancematrix/json")
    fun distanceMatrix(@QueryMap query: Map<String, String>): Maybe<JsonObject>

}