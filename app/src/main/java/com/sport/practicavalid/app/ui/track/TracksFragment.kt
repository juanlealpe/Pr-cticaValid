package com.sport.practicavalid.app.ui.track


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.sport.practicavalid.R
import com.sport.practicavalid.databinding.FragmentTracksBinding
import com.sport.practicavalid.utility.Constants
import com.sport.practicavalid.utility.DialogUtil
import com.sport.practicavalid.utility.NetworkUtil
import com.sport.practicavalid.utility.ViewModelTrackUtil
import com.sport.practicavalid.utility.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 * author: Juan Orlando Leal
 */
class TracksFragment : BaseFragment(){
    private lateinit var binding: FragmentTracksBinding
    private lateinit var model: TrackViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTracksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model = ViewModelProviders.of(this, ViewModelTrackUtil.provideTrackFactory()).get(TrackViewModel::class.java)
        loadingDialog = DialogUtil.progress(activity!!)
        binding.tracksRecycler.visibility = View.GONE

        val adapter = TrackAdapter()
        init(adapter)


        val intent = activity?.intent?.data

        Log.i(Constants.LOG_I, "Data: ${intent.toString()}")

        getTracks()

        /*Navigation.findNavController(view)
            .createDeepLink()
            .setDestination(R.id.search_movie_fragment)
            .createPendingIntent()*/
    }





    private fun init(adapter: TrackAdapter){
        val factory = ViewModelTrackUtil.provideTrackFactory()
        model = ViewModelProviders.of(this, factory).get(TrackViewModel::class.java)
        binding.viewModel = model
        binding.tracksRecycler.adapter = adapter
        binding.tracksRecycler.isNestedScrollingEnabled = false

        subscribeUi(adapter)
    }

    private fun subscribeUi(adapter: TrackAdapter){
        model.isLoading.observe(viewLifecycleOwner, Observer {
            if(it){
                loadingDialog.show()
            }else{
                loadingDialog.hide()
            }
        })




        model.tracks.observe(viewLifecycleOwner, Observer {
                response ->
            binding.hasSearched = true
            val data = response
            binding.searchText.text = getString(R.string.text_results_searched, data.size)

            if (!data.isNullOrEmpty() || data.size > 0) {
                binding.tracksRecycler.visibility = View.VISIBLE
                adapter.submitList(data)
            }else{
                binding.tracksRecycler.visibility = View.GONE
                adapter.submitList(null)
            }

        })
    }

    private fun getTracks(){
        if(validate()){
            //TODO Remover esto
            model.getTracks("Spain")
        }
    }



    private fun validate(): Boolean {
        var isCorrect = true
        when {

            context?.let {
                !NetworkUtil.isOnline(it)
            } ?: false -> {
                isCorrect = false
                TODO("cambiar esto")
                //SnackbarUtil.default(binding.root, "No hay conextión a internet")
            }
        }
        return isCorrect
    }
}