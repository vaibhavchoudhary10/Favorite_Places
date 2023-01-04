package com.example.favoriteplaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.favoriteplaces.Store.PlacesStore
import com.example.favoriteplaces.adapter.FavPlacesAdapter
import com.example.favoriteplaces.model.FavPlaceModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

interface CellClickListener{
    fun onClick(model: FavPlaceModel)
}

class MainActivity : AppCompatActivity(), CellClickListener {
    private var tvNoData:TextView? = null
    private var btnAddPlace:FloatingActionButton? = null
    private var rvPlacesList:RecyclerView? = null

    override fun onResume(){
        super.onResume()
        rvPlacesList?.adapter?.notifyDataSetChanged()

        if(PlacesStore.getPlaces().size > 0){
            tvNoData?.visibility = View.GONE
            rvPlacesList?.visibility = View.VISIBLE
        } else{
            rvPlacesList?.visibility = View.GONE
            tvNoData?.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvNoData = findViewById(R.id.tv_no_data)
        rvPlacesList = findViewById(R.id.rv_places_list)
        btnAddPlace = findViewById(R.id.btn_add_place)


        setAdapterForRecyclerView()
        btnAddPlace?.setOnClickListener{
            val intent = Intent(this, AddPlace::class.java)
            intent.putExtra("ALLOW_EDITING", false)
            startActivity(intent)
        }
    }

    private fun setAdapterForRecyclerView() {
        rvPlacesList?.adapter = FavPlacesAdapter(this, PlacesStore.getPlaces(), this)
        rvPlacesList?.layoutManager = LinearLayoutManager(this)
        rvPlacesList?.setHasFixedSize(true)
    }

    override fun onClick(model: FavPlaceModel) {
        val intent = Intent(this, AddPlace::class.java)
        intent.putExtra("ALLOW_EDITING", true)
        intent.putExtra("TITLE", model.title)
        intent.putExtra("ADDRESS", model.address)
        startActivity(intent)
    }
}