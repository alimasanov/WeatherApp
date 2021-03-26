package com.example.weatherapp.tools

import com.google.android.gms.maps.model.LatLng
import io.nlopez.smartlocation.SmartLocation
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LocationManager @Inject constructor(
    private var smartLocation: SmartLocation
) {

    fun myCoordinate(): Maybe<LatLng> = Maybe.create<LatLng> { emitter ->
        smartLocation.location().oneFix().start { location ->
            emitter.onSuccess(LatLng(location.latitude, location.longitude))
            emitter.onComplete()
        }
    }
        .timeout(10, TimeUnit.SECONDS) { it.onComplete() }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(AndroidSchedulers.mainThread())
}