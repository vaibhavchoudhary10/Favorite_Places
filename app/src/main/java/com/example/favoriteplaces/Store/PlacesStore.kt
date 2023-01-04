package com.example.favoriteplaces.Store

import com.example.favoriteplaces.model.FavPlaceModel

object PlacesStore {
    private var places: ArrayList<FavPlaceModel> = ArrayList<FavPlaceModel>()
    private var lastIndex: Int = 0;
    fun getPlaces(): ArrayList<FavPlaceModel> {
        return places
    }

    fun addPlace(title: String, address: String){
       // var lastIndex = places.elementAt((places.size - 1)).id
        val newItem = FavPlaceModel(++lastIndex, title , address)
        places.add(newItem)
    }
}