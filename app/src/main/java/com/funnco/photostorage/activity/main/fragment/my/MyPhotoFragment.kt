package com.funnco.photostorage.activity.main.fragment.my

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.funnco.photostorage.R
import com.funnco.photostorage.activity.main.fragment.ImageCardAdapter
import com.funnco.photostorage.common.DatabaseHandler
import com.funnco.photostorage.common.model.ImageCard
import com.funnco.photostorage.databinding.FragmentMyPhotoBinding
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class MyPhotoFragment : Fragment() {

    companion object{
        lateinit var currentInstanceBinding: FragmentMyPhotoBinding

        fun updateAdapter(item: ImageCard){
            (currentInstanceBinding.fragmentMyphotoRecycler.adapter as ImageCardAdapter).addItem(item)
        }
    }

    lateinit var binding: FragmentMyPhotoBinding
    val READ_CONTENT_CODE = 90002
    lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPhotoBinding.inflate(LayoutInflater.from(context), container, false)
        prefs = context?.getSharedPreferences("UUID", AppCompatActivity.MODE_PRIVATE)!!
        currentInstanceBinding = binding
        binding.fragmentMyphotoFabAdd.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, READ_CONTENT_CODE)
        }

        binding.fragmentMyphotoRecycler.adapter = ImageCardAdapter(DatabaseHandler.myImagesList)
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == READ_CONTENT_CODE && data?.data != null){
            Log.i("ImageSelection", data.data?.toString()!!)

            DatabaseHandler.uploadImage(data.data!!, "Uploaded_image_${Random.nextLong(10000000000000000, 999999999999999999)}",prefs.getString("uuid", "-1")!!)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}