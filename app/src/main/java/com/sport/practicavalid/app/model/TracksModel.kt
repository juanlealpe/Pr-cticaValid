package com.sport.practicavalid.app.model

import com.google.gson.annotations.SerializedName



data class TrackApiResponse(
    @SerializedName("tracks") val trackResponse: Track
)

data class Track(
    @SerializedName("track") val track: ArrayList<Tracks>



)

data class Image(
    @SerializedName("#text")val text:String,
    @SerializedName("size")val size:String


)

data class Tracks(
    @SerializedName("name") val name: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("listeners") val listeners: String,
    @SerializedName("mbid") val mbid: String,
    @SerializedName("url")val url: String,
    @SerializedName("image") val imageList: ArrayList<Image>
)