package com.sport.practicavalid.repository

import androidx.lifecycle.LiveData

import com.sport.practicavalid.api.API
import com.sport.practicavalid.app.model.Resource
import com.sport.practicavalid.app.model.Track
import com.sport.practicavalid.app.model.TrackApiResponse
import com.sport.practicavalid.utility.SimpleNetworkBoundResource
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Response



class TracksRepository : BaseRespository() {


    fun getTopTracks(country: String): LiveData<Resource<TrackApiResponse>> {
        return object : SimpleNetworkBoundResource<TrackApiResponse>() {
            override fun notifyDisposable(disposable: Disposable) {
                addDisposable(disposable)
            }


            override fun callService(): Observable<Response<TrackApiResponse>> {
                return api.getTopTracksByCountry(API.apiKey,country)
            }
        }.asLiveData()
    }

}