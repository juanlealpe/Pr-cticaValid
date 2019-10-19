package com.sport.practicavalid.api

import com.sport.practicavalid.app.model.Track
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("?method=geo.gettoptracks&format=json")
    fun getTopTracksByCountry(@Query("api_key") apiKey: String,
                     @Query("country") country: String) : Observable<Response<Track>>


    companion object {
        const val apiKey = "829751643419a7128b7ada50de590067"
        fun create(): API{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://ws.audioscrobbler.com/2.0/")
                .build()
            return retrofit.create(API::class.java)
        }
    }
}

