package com.sport.practicavalid.app.model

import com.google.gson.annotations.SerializedName

data class TracksSearchModel(
    @SerializedName("country") val country: String,
    @SerializedName("api_key") val api_key: String

)

data class Track(
    @SerializedName("track") val track: ArrayList<Tracks>
)

data class Tracks(
    @SerializedName("name") val name: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("listeners") val listeners: String,
    @SerializedName("mbid") val mbid: String,
    @SerializedName("url")val url: String
)