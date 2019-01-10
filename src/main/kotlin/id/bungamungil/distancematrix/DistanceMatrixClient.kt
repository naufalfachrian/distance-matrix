package id.bungamungil.distancematrix

import id.bungamungil.distancematrix.model.Configuration
import id.bungamungil.distancematrix.model.DistanceMatrix
import id.bungamungil.distancematrix.model.DistanceMatrixQuery
import id.bungamungil.distancematrix.network.GoogleMapsClient
import id.bungamungil.distancematrix.network.GoogleMapsClientImpl
import io.reactivex.Maybe

class DistanceMatrixClient(config: Configuration) : GoogleMapsClient {

    private val internalClient = GoogleMapsClientImpl(config)

    override fun distanceMatrix(query: DistanceMatrixQuery): Maybe<DistanceMatrix> {
        return internalClient.distanceMatrix(query)
    }

}