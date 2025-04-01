package com.example.topmovies.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.topmovies.databinding.ItemGenresHomeBinding
import com.example.topmovies.model.home.ResponseGenresList.ResponseGenresListItem
import javax.inject.Inject

class GenresHomeAdapter @Inject constructor() : RecyclerView.Adapter<GenresHomeAdapter.ViewHolder>() {

    //binding
    private lateinit var binding : ItemGenresHomeBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        binding= ItemGenresHomeBinding.inflate(inflater,parent,false)
        return ViewHolder()
    }

    override fun getItemCount(): Int =differ.currentList.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])

    }


    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root){

        fun setData(item: ResponseGenresListItem){

            binding.txtItemGenresHome.text=item.name

        }


    }




    val diffUtil = object:DiffUtil.ItemCallback<ResponseGenresListItem>(){
        override fun areItemsTheSame(oldItem: ResponseGenresListItem, newItem: ResponseGenresListItem): Boolean {

            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseGenresListItem, newItem: ResponseGenresListItem): Boolean {

            return oldItem==newItem
        }


    }


    val differ=AsyncListDiffer(this,diffUtil)





}