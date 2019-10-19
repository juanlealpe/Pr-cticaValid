package com.sport.practicavalid.app.ui.track

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.sport.practicavalid.app.model.Resource
import com.sport.practicavalid.app.model.Track
import com.sport.practicavalid.app.model.Tracks
import com.sport.practicavalid.repository.TracksRepository
import com.sport.practicavalid.utility.Status
import com.sport.practicavalid.utility.base.BaseViewModel

class TrackViewModel(private val repository: TracksRepository): BaseViewModel() {
    private val country = String()


    private val _tracks = MediatorLiveData<ArrayList<Tracks>>()
    val tracks: LiveData<ArrayList<Tracks>> = _tracks

    init {
        _tracks.addSource(repository.getTopTracks(country)){ data ->
            _tracks.removeSource(tracks)
            processResource(data)

        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.clear()
    }

    private fun processResource(resource: Resource<Track>){
        when (resource.status) {
            Status.LOADING -> {
                setLoading(true)
            }
            Status.SUCCESS -> {
                setLoading(false)
                resource.data?.let {
                    if(it.track.isNotEmpty()){
                        _tracks.value = it.track
                    }
                }
            }
            Status.ERROR -> {
                setLoading(false)
            }
        }
    }

     fun getTracks(country:String){
        _tracks.removeSource(tracks)

        val liveData = repository.getTopTracks(country)

        _tracks.addSource(liveData){ data ->
            processResource(data)
        }
    }




}