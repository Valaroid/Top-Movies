package com.example.topmovies.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.topmovies.databinding.FragmentHomeBinding
import com.example.topmovies.ui.home.adapter.GenresHomeAdapter
import com.example.topmovies.ui.home.adapter.LastMovieHomeAdapter
import com.example.topmovies.ui.home.adapter.PosterHomeAdapter
import com.example.topmovies.util.initRecyclerView
import com.example.topmovies.util.viewVisibility
import com.example.topmovies.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentHomeBinding

    //viewModel
    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var posterHomeAdapter: PosterHomeAdapter

    @Inject
    lateinit var genresHomeAdapter: GenresHomeAdapter

    @Inject
    lateinit var lastMovieHomeAdapter: LastMovieHomeAdapter

    val snapHelper:SnapHelper by lazy { PagerSnapHelper() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getPosterHome(3)
        homeViewModel.getGenresList()
        homeViewModel.getLastMovies()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initViews
        binding.apply {

            //fill Poster adapter
           homeViewModel.imgPosterLive.observe(viewLifecycleOwner){
              posterHomeAdapter.differ.submitList(it.data)

               //init recycler poster
               recyclerPosterHome.initRecyclerView(
                   LinearLayoutManager(
                       requireContext(),
                       RecyclerView.HORIZONTAL,
                       false
                   ), posterHomeAdapter
               )
               //match recycler poster with indicator
               snapHelper.attachToRecyclerView(recyclerPosterHome)
               indicatorHome.attachToRecyclerView(recyclerPosterHome,snapHelper)

           }

            //for indicator library
            posterHomeAdapter.registerAdapterDataObserver(indicatorHome.adapterDataObserver)


            //fill genres adapter
            homeViewModel.genresListLive.observe(viewLifecycleOwner){
                genresHomeAdapter.differ.submitList(it)

                //init recycler generes
                recyclerGenresHome.initRecyclerView(LinearLayoutManager(
                    requireContext(),
                    RecyclerView.HORIZONTAL,
                    false
                ), genresHomeAdapter)
            }

            //fill last movie adapter
            homeViewModel.lastMoviesLive.observe(viewLifecycleOwner){
                lastMovieHomeAdapter.setList(it.data)


                //init recycler last Movie
                recyclerLastMovieHome.initRecyclerView(LinearLayoutManager(
                    requireContext(),
                    RecyclerView.VERTICAL,
                    false
                ), lastMovieHomeAdapter)
            }

            //loading
            homeViewModel.loading.observe(viewLifecycleOwner){

                if (it){
                    loadingHome.viewVisibility(true)
                    scrollHome.viewVisibility(false)
                }else{
                    loadingHome.viewVisibility(false)
                    scrollHome.viewVisibility(true)

                }

            }

            //click item
            lastMovieHomeAdapter.setClickListener {

                val direction = HomeFragmentDirections.actionToDetail(it.id!!.toInt())
                findNavController().navigate(direction)

            }





        }

    }


}