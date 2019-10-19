package com.sport.practicavalid.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sport.practicavalid.app.ui.track.TrackViewModel
import com.sport.practicavalid.repository.TracksRepository

class TracksViewModelFactory(private val repository: TracksRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrackViewModel(repository) as T
    }
}