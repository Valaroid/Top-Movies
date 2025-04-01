package com.example.topmovies.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.topmovies.databinding.ItemLastMovieHomeBinding
import com.example.topmovies.db.MovieEntity
import javax.inject.Inject

class FavoriteAdapter @Inject constructor() : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    //binding
    private lateinit var binding: ItemLastMovieHomeBinding

    //myList
    var myList = emptyList<MovieEntity>()

    var click: ((MovieEntity) -> Unit)? = null

    fun setClickListener(listener: (MovieEntity) -> Unit) {

        click = listener

    }

    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: MovieEntity) {

            binding.apply {

                txtTitleLastMovieHome.text = item.title
                txtRateLastMovieHome.text = item.rateImdb
                txtCountryLastMovieHome.text = item.country
                txtYearLastMovieHome.text = item.year

                imgItemLastMovieHome.load(item.image) {
                    crossfade(true)
                    crossfade(800)
                }


                //click
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
        binding = ItemLastMovieHomeBinding.inflate(inflater, parent, false)
        return ViewHolder()

    }

    override fun getItemCount(): Int {
        return myList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(myList[position])
        holder.setIsRecyclable(false)
    }


    fun setList(list: List<MovieEntity>) {
        val diffUtil = FavoriteDiffUtil(myList, list)
        val diff = DiffUtil.calculateDiff(diffUtil)
        myList = list

        diff.dispatchUpdatesTo(this)

    }


    class FavoriteDiffUtil(val oldList: List<MovieEntity>, val newList: List<MovieEntity>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size

        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]

        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }


    }


}