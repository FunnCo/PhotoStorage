package com.funnco.photostorage.activity.main.fragment.all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.funnco.photostorage.R
import com.funnco.photostorage.activity.main.fragment.ImageCardAdapter
import com.funnco.photostorage.common.DatabaseHandler
import com.funnco.photostorage.databinding.FragmentAllPhotoBinding

class AllPhotoFragment : Fragment() {

    lateinit var binding: FragmentAllPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllPhotoBinding.inflate(LayoutInflater.from(context), container, false)

        binding.fragmentAllphotoRecycler.adapter = ImageCardAdapter(DatabaseHandler.imagesList)


        return binding.root
    }

}