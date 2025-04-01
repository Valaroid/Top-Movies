package com.example.topmovies.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.topmovies.R
import com.example.topmovies.databinding.ActivityMainBinding
import com.example.topmovies.ui.detail.DetailFragment
import com.example.topmovies.util.viewVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //binding
    private lateinit var binding: ActivityMainBinding

    //NavController
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
             val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
             insets
         }*/
        //DetailFragment()

        //Init Views
        binding.apply {

            navController = findNavController(R.id.navHost_main)
            bottomNavigationMain.setupWithNavController(navController)

            //Bottom Navigation Visibility
            navController.addOnDestinationChangedListener { _, destination, _ ->

                if (destination.id == R.id.splashFragment || destination.id == R.id.registerFragment || destination.id==R.id.detailFragment) {

                    bottomNavigationMain.viewVisibility(false)

                } else {

                    bottomNavigationMain.viewVisibility(true)

                }


            }


        }


    }


    override fun navigateUpTo(upIntent: Intent?): Boolean {
        return navController.navigateUp() || super.navigateUpTo(upIntent)

    }
}