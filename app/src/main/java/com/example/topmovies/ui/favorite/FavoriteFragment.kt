package com.example.topmovies.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topmovies.R
import com.example.topmovies.databinding.FragmentFavoriteBinding
import com.example.topmovies.util.initRecyclerView
import com.example.topmovies.util.viewVisibility
import com.example.topmovies.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentFavoriteBinding


    //viewModel
    val viewModel: FavoriteViewModel by viewModels()

    //adapter
    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //get list from db
            viewModel.getListMovie()

            //init recycler
            viewModel.myListMovieLive.observe(viewLifecycleOwner) {

                //init adapter
                favoriteAdapter.setList(it)

                //init recycler
                recyclerFavorite.initRecyclerView(
                    LinearLayoutManager(requireContext()),
                    favoriteAdapter
                )


            }

            //click
            favoriteAdapter.setClickListener {
                val direction = FavoriteFragmentDirections.actionToDetail(it.id)
                findNavController().navigate(direction)

            }


            //is empty
            viewModel.isEmpty.observe(viewLifecycleOwner){
               if (it){
                   emptyLayFavorite.viewVisibility(true)
                   recyclerFavorite.viewVisibility(false)

               }else{
                   emptyLayFavorite.viewVisibility(false)
                   recyclerFavorite.viewVisibility(true)
               }

            }



        }


    }


}