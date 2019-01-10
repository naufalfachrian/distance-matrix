package id.bungamungil.distancematrix.model

import io.reactivex.Scheduler

interface Configuration {

    fun googleMapsApiKey(): String

    fun subscriberScheduler(): Scheduler

    fun observerScheduler(): Scheduler

}