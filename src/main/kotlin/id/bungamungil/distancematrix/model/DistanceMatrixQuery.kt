package id.bungamungil.distancematrix.model

import com.google.gson.annotations.SerializedName

class DistanceMatrixQuery(destination: Location, origin: Location) {

    @SerializedName("origins")
    val origins: String = "${origin.latitude()},${origin.longitude()}"

    @SerializedName("destinations")
    val destinations: String = "${destination.latitude()},${destination.longitude()}"

    @SerializedName("travel_mode")
    val travelMode: String = "walking"

}