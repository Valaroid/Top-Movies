package com.example.topmovies.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.topmovies.databinding.ItemPosterHomeBinding
import com.example.topmovies.model.home.ResponseLastMovieHome.*
import javax.inject.Inject


class PosterHomeAdapter@Inject constructor() : RecyclerView.Adapter<PosterHomeAdapter.ViewHolder>() {

    //binding
    private lateinit var binding:ItemPosterHomeBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        binding=ItemPosterHomeBinding.inflate(inflater,parent,false)
        return ViewHolder()
    }

    override fun getItemCount()=if (differ.currentList.size>5) 5 else differ.currentList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }


    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root){

        fun bindData(item : Data){

            binding.apply {
                txtInfoPosterHome.text="${item.imdb_rating} | ${item.country} | ${item.year}"
                txtTitlePosterHome.text=item.title
                imgPosterItemHome.load(item.poster){
                    crossfade(true)
                    crossfade(800)
                }

            }

        }

    }


    val diffutil = object :DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem==newItem
        }


    }



    val differ=AsyncListDiffer(this,diffutil)

}