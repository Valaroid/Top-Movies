package com.example.topmovies.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topmovies.databinding.FragmentSearchBinding
import com.example.topmovies.util.initRecyclerView
import com.example.topmovies.util.viewVisibility
import com.example.topmovies.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentSearchBinding

    //viewModel
    private val viewModel: SearchViewModel by viewModels()

    //adapter
    @Inject
    lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init Views
        binding.apply {

            //call data
            edtSearch.addTextChangedListener {

                val searchName = edtSearch.text.toString()
                if (searchName.isNotEmpty()) {
                    //call api with view Model
                    viewModel.getSearchMovie(searchName)
                }
            }

            //set data for recycler
            viewModel.searchMoviesListLive.observe(viewLifecycleOwner) {
                //init adapter
                searchAdapter.setList(it.data)

                //init Recycler
                recyclerSearch.initRecyclerView(
                    LinearLayoutManager(requireContext()),
                    searchAdapter
                )

            }

            //item not found
            viewModel.isEmpty.observe(viewLifecycleOwner) {

                if (it) {
                    emptyLaySearch.viewVisibility(true)
                    recyclerSearch.viewVisibility(false)

                } else {
                    emptyLaySearch.viewVisibility(false)
                    recyclerSearch.viewVisibility(true)

                }
            }

            //loading
            viewModel.loading.observe(viewLifecycleOwner) {

                if (it) {
                    loadingSearch.viewVisibility(true)

                } else {
                    loadingSearch.viewVisibility(false)

                }
            }

            //click
            searchAdapter.setClickListener {
                val direction = SearchFragmentDirections.actionToDetail(it.id!!.toInt())
                findNavController().navigate(direction)

            }

        }


    }


}