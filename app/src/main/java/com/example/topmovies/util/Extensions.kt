package com.example.topmovies.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView


    fun View.viewVisibility(isVisible: Boolean) {

        if (isVisible) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }

    }

    fun RecyclerView.initRecyclerView(layoutManager: RecyclerView.LayoutManager,adapter : RecyclerView.Adapter<*>){

        this.layoutManager=layoutManager
        this.adapter=adapter

    }



