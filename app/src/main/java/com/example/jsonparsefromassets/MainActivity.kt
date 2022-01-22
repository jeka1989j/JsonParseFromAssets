package com.example.jsonparsefromassets

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonparsefromassets.databinding.ActivityMainBinding
import com.example.jsonparsefromassets.model.Avenger
import com.example.jsonparsefromassets.model.AvengersModelClass
import com.example.jsonparsefromassets.model.Contact
import com.example.jsonparsefromassets.recyclerviewAdapter.AvengerItemAdapter
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        try {
            // retrieve data from json file
            val jsonString = readJsonFile()
            // convert data from json file to string
            val avenger = Gson().fromJson(jsonString, AvengersModelClass::class.java)

            // create instance of the recyclerView
            val recyclerView = binding.avengersList
            // instantiate the AvengerItemAdapter(), call the avengers object from avengers.json file
            val adapter = AvengerItemAdapter(this, avenger.avengers)

            // set adapter instance equals to the recyclerView to inflate the items from our model
            recyclerView.adapter = adapter

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    private fun readJsonFile(): String {
        val json: String = try {
            val bufferReader = assets.open("avengers.json").bufferedReader()
            bufferReader.use {
                it.readText()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }

        return json
    }
}