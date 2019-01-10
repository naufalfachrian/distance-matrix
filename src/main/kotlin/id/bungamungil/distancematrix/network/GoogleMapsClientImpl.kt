package id.bungamungil.distancematrix.network

import com.google.gson.JsonObject
import id.bungamungil.distancematrix.model.Configuration
import id.bungamungil.distancematrix.model.DistanceMatrix
import id.bungamungil.distancematrix.model.DistanceMatrixQuery
import io.reactivex.Maybe
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal class GoogleMapsClientImpl(private val config: Configuration) : GoogleMapsClient {

    private val baseUrl = "https://maps.googleapis.com/maps/api/"

    private val internalOkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    private val internalRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(internalOkHttpClient)
            .build()

    private val internalGoogleMapsClient = internalRetrofit.create(GoogleMapsInternalClient::class.java)

    override fun distanceMatrix(query: DistanceMatrixQuery): Maybe<DistanceMatrix> {
        val queryMap = HashMap<String, String>()
        queryMap.put("origins", query.origins)
        queryMap.put("destinations", query.destinations)
        queryMap.put("travel_mode", query.travelMode)
        queryMap.put("key", config.googleMapsApiKey())
        return internalGoogleMapsClient.distanceMatrix(queryMap)
                .subscribeOn(config.subscriberScheduler())
                .observeOn(config.observerScheduler())
                .map { t: JsonObject ->
                    return@map object : DistanceMatrix {
                        override fun destinationAddress(): String? {
                            return t["destination_addresses"].asJsonArray[0].asString
                        }

                        override fun originAddress(): String? {
                            return t["origin_addresses"].asJsonArray[0].asString
                        }

                        override fun distanceText(): String? {
                            return t["rows"].asJsonArray[0].asJsonObject["elements"].asJsonArray[0].asJsonObject["distance"].asJsonObject["text"].asString
                        }

                        override fun distanceValue(): Double? {
                            return t["rows"].asJsonArray[0].asJsonObject["elements"].asJsonArray[0].asJsonObject["distance"].asJsonObject["value"].asDouble
                        }

                        override fun durationText(): String? {
                            return t["rows"].asJsonArray[0].asJsonObject["elements"].asJsonArray[0].asJsonObject["duration"].asJsonObject["text"].asString
                        }

                        override fun durationValue(): Double? {
                            return t["rows"].asJsonArray[0].asJsonObject["elements"].asJsonArray[0].asJsonObject["duration"].asJsonObject["value"].asDouble
                        }
                    }
                }
    }

}