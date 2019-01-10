package id.bungamungil.distancematrix.model

interface DistanceMatrix {

    /**
     * Destination address
     */
    fun destinationAddress(): String?

    /**
     * Origin address
     */
    fun originAddress(): String?

    /**
     * Distance text
     */
    fun distanceText(): String?

    /**
     * Distance value in meters
     */
    fun distanceValue(): Double?

    /**
     * Duration text
     */
    fun durationText(): String?

    /**
     * Duration value in seconds
     */
    fun durationValue(): Double?

}