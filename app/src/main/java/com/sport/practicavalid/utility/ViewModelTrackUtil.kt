package com.sport.practicavalid.utility

import com.sport.practicavalid.app.viewmodel.TracksViewModelFactory
import com.sport.practicavalid.repository.TracksRepository

object ViewModelTrackUtil {

    fun provideTrackFactory(): TracksViewModelFactory {
        val repository = TracksRepository()
        return TracksViewModelFactory(repository)
    }
}