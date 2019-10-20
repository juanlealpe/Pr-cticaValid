package com.sport.practicavalid.app.ui.track

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.sport.practicavalid.R

import com.sport.practicavalid.app.model.Tracks
import com.sport.practicavalid.databinding.ItemListTrackBinding
/**
 *
 * author: Juan Orlando Leal
 * Adactador
 */
class TrackAdapter :
    androidx.recyclerview.widget.ListAdapter<Tracks, TrackAdapter.ViewHolder>(
        DiffCallback()
    ){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list_track, parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { track ->
            with(holder) {
                itemView.tag = track
                bind(createOnClickListener(track.name,track.url,track.imageList.get(0).text), track)
            }
        }
    }

    private fun createOnClickListener(name: String,url: String, urlImage:String): View.OnClickListener {
        return View.OnClickListener {

            val direction =
                TracksFragmentDirections.toDetailTrackFragment(name,url,urlImage)
            it.findNavController().navigate(direction)

           // it.findNavController().navigate(direction)
        }
    }
    private class DiffCallback : DiffUtil.ItemCallback<Tracks>() {

        override fun areItemsTheSame(oldItem: Tracks, newItem: Tracks): Boolean {
            return oldItem.mbid == newItem.mbid
        }

        override fun areContentsTheSame(oldItem: Tracks, newItem: Tracks): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder (private val binding: ItemListTrackBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(listener: View.OnClickListener, track: Tracks){

            with(binding) {
                clickListener = listener
                viewModel = TrackAdapterViewModel(track)
                executePendingBindings()
            }
        }
    }


}
