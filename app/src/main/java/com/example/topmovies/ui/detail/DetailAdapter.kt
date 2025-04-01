package com.example.topmovies.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.topmovies.databinding.ItemImagesDetailBinding
import javax.inject.Inject

class DetailAdapter @Inject constructor() : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    //binding
    private lateinit var binding:ItemImagesDetailBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){

        fun setData(item : String){

            binding.imgItemDetail.load(item){
                crossfade(true)
                crossfade(800)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        binding=ItemImagesDetailBinding.inflate(inflater,parent,false)
        return ViewHolder()

    }

    override fun getItemCount(): Int=differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }


    val diff = object: DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }

    }

    val differ = AsyncListDiffer(this,diff)

   



}