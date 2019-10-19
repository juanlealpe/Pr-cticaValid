package com.sport.practicavalid.app.ui.track

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import com.sport.practicavalid.app.model.Tracks

class TrackAdapterViewModel (track: Tracks): ViewModel() {
    private val track = checkNotNull(track)

    val titleString = ObservableField<String>(this.track.name)
}