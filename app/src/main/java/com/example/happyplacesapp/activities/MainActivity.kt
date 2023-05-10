package com.example.happyplacesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.happyplacesapp.R
import com.example.happyplacesapp.activities.AddHappyPlaceActivity
import com.example.happyplacesapp.adapters.HappyPlacesAdapter
import com.example.happyplacesapp.database.DatabaseHandler
import com.example.happyplacesapp.models.HappyPlace
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var rvRecyclerView: RecyclerView? = null
    private var tvNoRecord: TextView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvRecyclerView = findViewById(R.id.rv_main_activity)
        tvNoRecord = findViewById(R.id.tv_no_places)
        var fabAddHappyPlaceActivity: FloatingActionButton = findViewById(R.id.fabAddPlace)
        fabAddHappyPlaceActivity.setOnClickListener {
            val intent = Intent(this, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }
        getAllPlacesFromDatabase()
    }

    private fun setupHappyPlacesRecyclerView(
        happyPlaceList:
        ArrayList<HappyPlace>
    ) {
      if(happyPlaceList.size>0)  {
            tvNoRecord?.visibility =View.GONE
            rvRecyclerView?.visibility = View.VISIBLE
            rvRecyclerView?.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val happyPlacesAdapter = HappyPlacesAdapter(this, happyPlaceList)
            rvRecyclerView?.adapter = happyPlacesAdapter
        }else{
          tvNoRecord?.visibility =View.VISIBLE
          rvRecyclerView?.visibility = View.GONE
        }

    }

    private fun getAllPlacesFromDatabase() {
        val db = DatabaseHandler(this)
        val allHappyPlaces = db.getAllHappyPlaces()
        setupHappyPlacesRecyclerView(allHappyPlaces)

        }
    }
