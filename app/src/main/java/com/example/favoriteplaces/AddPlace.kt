package com.example.favoriteplaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.favoriteplaces.Store.PlacesStore

class AddPlace : AppCompatActivity() {

    var btnSubmit: Button? = null
    var tvTitle: TextView? = null
    var tvAddress: TextView? = null
    var flagDisableEdit:Boolean = false
    var iTitle: String? = null
    var iAddress: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place)


        btnSubmit = findViewById(R.id.btn_submit)
        tvTitle = findViewById(R.id.tv_name)
        tvAddress = findViewById(R.id.tv_address)

        flagDisableEdit = intent.getBooleanExtra("ALLOW_EDITING", false)
        iTitle = intent.getStringExtra("TITLE")
        iAddress = intent.getStringExtra("ADDRESS")

        if(!flagDisableEdit){
            btnSubmit?.setOnClickListener{
                val title = tvTitle?.text.toString()
                val address = tvAddress?.text.toString()

                if(title.isNotEmpty() && address.isNotEmpty()){
                    PlacesStore.addPlace(title, address)
                }
                else{
                    Toast.makeText(this, "Place was not saved", Toast.LENGTH_LONG).show()
                }
                finish()
            }
        } else{
            btnSubmit?.visibility = View.GONE
            tvTitle?.text = iTitle
            tvAddress?.text = iAddress
        }

    }
}