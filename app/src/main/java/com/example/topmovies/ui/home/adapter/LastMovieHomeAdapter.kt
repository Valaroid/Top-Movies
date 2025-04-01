package com.example.topmovies.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.topmovies.databinding.ItemLastMovieHomeBinding
import com.example.topmovies.model.home.ResponseLastMovieHome.*
import javax.inject.Inject

class LastMovieHomeAdapter @Inject constructor() : RecyclerView.Adapter<LastMovieHomeAdapter.ViewHolder>() {

    //binding
    private lateinit var binding : ItemLastMovieHomeBinding

    //list
    var myList = emptyList<Data>()

    //click
    var click : ((Data)->Unit)?=null

    fun setClickListener(listener : (Data)->Unit){

        click=listener

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemLastMovieHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(myList[position])
        holder.setIsRecyclable(false)
    }





    inner class ViewHolder(): RecyclerView.ViewHolder(binding.root){

        fun setData(item : Data){

            //initViews
            binding.apply {

                imgItemLastMovieHome.load(item.poster){
                    crossfade(true)
                    crossfade(800)
                }
                //init txt title
                txtTitleLastMovieHome.text=item.title
                //init txt rating
                txtRateLastMovieHome.text=item.imdb_rating
                //init txt country
                txtCountryLastMovieHome.text=item.country
                //init txt year
                txtYearLastMovieHome.text=item.year

                //click
                root.setOnClickListener {
                    click?.let {
                        it(item)
                    }

                }

            }

        }


    }

    class MovieDiffUtil(val oldList:List<Data>, val newList : List<Data>) : DiffUtil.Callback(){
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

    fun setList(data: List<Data>){

        val movieDiffUtil=MovieDiffUtil(myList,data)
        val diff = DiffUtil.calculateDiff(movieDiffUtil)
        myList=data
        diff.dispatchUpdatesTo(this)

    }




}