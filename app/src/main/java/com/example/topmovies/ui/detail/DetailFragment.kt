package com.example.topmovies.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.topmovies.R
import com.example.topmovies.databinding.FragmentDetailBinding
import com.example.topmovies.db.MovieEntity
import com.example.topmovies.util.initRecyclerView
import com.example.topmovies.util.viewVisibility
import com.example.topmovies.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {



    //binding
    private lateinit var binding: FragmentDetailBinding

    //viewModel
    val viewModel: DetailViewModel by viewModels()

    //get Id By navArgs
    val args: DetailFragmentArgs by navArgs()

    //movie entity
    @Inject
    lateinit var movieEntity : MovieEntity


    //adapter
    @Inject
    lateinit var detailAdapter: DetailAdapter
    //id
    private var idThisMovie :Int=0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

        //Test()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //id This Movie
        idThisMovie = args.id

        //call api
        viewModel.getMovieDetail(idThisMovie)

        //----------------------------------
        // check the movie exist in data base
        viewModel.checkIsExistMovieInDb(idThisMovie)


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //Init Views
        binding.apply {

            //this Movie Data
            viewModel.thisMovieDataLive.observe(viewLifecycleOwner) { response ->
                //img poster
                imgPosterDetail.load(response.poster) {
                    crossfade(true)
                    crossfade(800)
                }

                //img bg poster
                imgPosterBgDetail.load(response.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                //init txt
                txtTitleDetail.text=response.title
                txtRateDetail.text = response.imdb_rating
                txtDateDetail.text = response.released
                txtTimeDetail.text = response.runtime
                txtInfoDetail.text = response.plot
                txtActorsNameDetail.text = response.actors


                //init recycler
                detailAdapter.differ.submitList(response.images)
                recyclerMovieImageDetail.initRecyclerView(
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    ), detailAdapter
                )

                //init movie entity
                movieEntity.id=idThisMovie
                movieEntity.image= response.poster.toString()
                movieEntity.title=response.title.toString()
                movieEntity.rateImdb=response.imdb_rating.toString()
                movieEntity.year=response.year.toString()
                movieEntity.country=response.country.toString()





            }


            //is loading
            viewModel.isLoading.observe(viewLifecycleOwner){

                if (it){
                    loadingDetail.viewVisibility(true)
                    scrollDetail.viewVisibility(false)

                }else{
                    loadingDetail.viewVisibility(false)
                    scrollDetail.viewVisibility(true)

                }

            }


            //on back img click
            imgBackDetail.setOnClickListener {

                findNavController().navigateUp()
            }



            //-------------------------------------------------------------

            //is exist
            viewModel.isExistInDb.observe(viewLifecycleOwner){
                // check the movie exist in data base
                viewModel.checkIsExistMovieInDb(idThisMovie)

                if (it){

                    imgHeartDetail.setColorFilter(ContextCompat.getColor(requireContext(), R.color.scarlet), android.graphics.PorterDuff.Mode.MULTIPLY);


                }else{

                    imgHeartDetail.setColorFilter(ContextCompat.getColor(requireContext(), R.color.philippineSilver), android.graphics.PorterDuff.Mode.MULTIPLY);

                }


            }



            //on heart img click
            imgHeartDetail.setOnClickListener {

                viewModel.doWorkOnDb(movieEntity)

            }









        }


    }





}