package com.funnco.photostorage.common

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.funnco.photostorage.activity.main.fragment.my.MyPhotoFragment
import com.funnco.photostorage.common.model.ImageCard
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlin.random.Random

object DatabaseHandler {

    var imagesList: List<ImageCard?> = emptyList()
    var myImagesList: List<ImageCard?> = emptyList()

    val database = Firebase.database
    val imagesRef = database.getReference("images")

    val storage = Firebase.storage("gs://photostorage-c568f.appspot.com")

    fun readAllImages(uuid: String) {
        imagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val tempList1 = mutableListOf<ImageCard?>()
                for (child in snapshot.children) {
                    tempList1.add(child.getValue(ImageCard::class.java))
                }
                imagesList = tempList1

                val tempList2 = mutableListOf<ImageCard?>()
                for (item in imagesList) {
                    if (item?.authorUUID == uuid) {
                        tempList2.add(item)
                    }
                }
                myImagesList = tempList2
                Log.i(
                    "FBDatabse",
                    "Reading complete. imageList.size = ${imagesList.size}, myImagesList.size = ${myImagesList.size}"
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FBDatabase", "Error occurred: ${error.message}")
            }

        })
    }

    fun writeExampleImages() {
        for (i in 0..10) {
            val tempCard = ImageCard(
                "-1",
                "https://d34ip4tojxno3w.cloudfront.net/app/uploads/placeholder.jpg",
                "TestCard №${i}"
            )
            imagesRef.child("image ${Random.nextLong(1000000000, 9999999999)}").setValue(tempCard)
        }
    }

    fun writeNewImage(item: ImageCard) {
        imagesRef.child("image ${Random.nextLong(1000000000, 9999999999)}").setValue(item).addOnSuccessListener {
            Log.d("AAA", "OK")
            MyPhotoFragment.updateAdapter(item)
        }
    }

    fun uploadImage(uri: Uri, filename: String, uuid: String) {
        val storageRef = storage.getReference("/images/$filename")
        storageRef.putFile(uri).addOnSuccessListener {
            Log.i("FBStorage", "File uploaded Successfully")
            storageRef.downloadUrl.addOnSuccessListener {
                writeNewImage(
                    ImageCard(
                        uuid, it.toString(), "Uploaded Image"
                    )
                )
                Log.i("FBStorage", "Download link: $it")
            }
        }
    }

}