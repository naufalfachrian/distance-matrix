package id.bungamungil.distancematrix.network

import id.bungamungil.distancematrix.model.DistanceMatrix
import id.bungamungil.distancematrix.model.DistanceMatrixQuery
import io.reactivex.Maybe

internal interface GoogleMapsClient {

    fun distanceMatrix(query: DistanceMatrixQuery): Maybe<DistanceMatrix>

}