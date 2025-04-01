package com.example.topmovies.ui.splash

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.topmovies.R
import com.example.topmovies.databinding.FragmentSplashBinding
import com.example.topmovies.util.UserDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentSplashBinding

    //DataStore
    @Inject
    lateinit var userDataStore: UserDataStore


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch{

            delay(2000)

            //check the data store
            userDataStore.getTokenFromDataStore().collect{

                if (it.isEmpty()){
                    findNavController().navigate(R.id.actionSplashToRegister)
                    
                }else{
                    findNavController().navigate(R.id.actionSplashToHome)
                }



            }


        }

    }





}