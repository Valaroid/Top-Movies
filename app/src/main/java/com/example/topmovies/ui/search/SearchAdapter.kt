package com.example.topmovies.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.topmovies.databinding.ItemLastMovieHomeBinding
import com.example.topmovies.model.home.ResponseLastMovieHome
import com.example.topmovies.model.home.ResponseLastMovieHome.Data
import javax.inject.Inject

class SearchAdapter @Inject constructor() : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    //binding
    private lateinit var binding : ItemLastMovieHomeBinding

    //myList
    var myList = emptyList<Data>()

    //click
    var click: ((Data) -> Unit)? = null
    fun setClickListener(listener:(Data) -> Unit){
        click=listener
    }

    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root){

        fun setItem(item : Data){

            binding.apply {
                imgItemLastMovieHome.load(item.poster){
                    crossfade(true)
                    crossfade(800)
                }
                txtTitleLastMovieHome.text=item.title
                txtRateLastMovieHome.text=item.imdb_rating
                txtCountryLastMovieHome.text=item.country
                txtYearLastMovieHome.text=item.year

                root.setOnClickListener {
                    click?.let {
                        it(item)
                    }
                }

            }

        }

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding= ItemLastMovieHomeBinding.inflate(inflater,parent,false)
        return ViewHolder()

    }


    override fun getItemCount(): Int =myList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(myList[position])
        holder.setIsRecyclable(false)

    }



    class SearchMovieDiffUtils(val oldList : List<Data>,val newList : List<Data>) : DiffUtil.Callback(){
        override fun getOldListSize(): Int {
           return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition]===newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition]===newList[newItemPosition]
        }


    }

    fun setList(addList : List<Data>){
        val diffUtil=SearchMovieDiffUtils(myList,addList)
        val differ=DiffUtil.calculateDiff(diffUtil)
        myList=addList
        differ.dispatchUpdatesTo(this)
    }




}