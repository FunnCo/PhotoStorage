package com.funnco.photostorage.activity.main.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.funnco.photostorage.common.model.ImageCard
import com.funnco.photostorage.databinding.ItemImageCardBinding
import com.squareup.picasso.Picasso

class ImageCardAdapter(var listOfItems: List<ImageCard?>): RecyclerView.Adapter<ImageCardAdapter.ImageCardViewHolder>() {
    class ImageCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var binding: ItemImageCardBinding

        fun bind(item: ImageCard?){
            binding = ItemImageCardBinding.bind(itemView)

            Picasso.get().load(item?.imageURI).into(binding.itemImage)
            binding.itemName.text = item?.imageName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageCardViewHolder {
        return ImageCardViewHolder(ItemImageCardBinding.inflate(LayoutInflater.from(parent.context), parent, false).root)
    }

    override fun onBindViewHolder(holder: ImageCardViewHolder, position: Int) {
        holder.bind(listOfItems[position])
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    fun addItem(item: ImageCard){
        val tempList = listOfItems.toMutableList()
        tempList.add(item)
        listOfItems = tempList
        notifyItemInserted(listOfItems.size)
    }
}