package com.sport.practicavalid.app.ui.track.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sport.practicavalid.databinding.FragmentDetailTrackBinding
import com.sport.practicavalid.utility.DialogUtil

import com.sport.practicavalid.utility.base.SimpleBaseFragment

class DetailTrackBaseFragment: SimpleBaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDetailTrackBinding.inflate(inflater, container, false)
        loadingDialog = DialogUtil.progress(activity!!)

        val name = DetailTrackBaseFragmentArgs.fromBundle(arguments!!).name
        val url = DetailTrackBaseFragmentArgs.fromBundle(arguments!!).url
        val urlImage = DetailTrackBaseFragmentArgs.fromBundle(arguments!!).urlImage

         init(binding,name, url,urlImage)

        return binding.root
    }

    private fun init(binding: FragmentDetailTrackBinding,name: String ,url: String,urlImage:String){

        binding.name   = name
        binding.webView.loadUrl(url)
        Glide.with(this).load(urlImage).into(binding.imageTracks)

    }
}