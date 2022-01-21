package com.example.jsonparsefromassets

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jsonparsefromassets.model.Avenger
import com.example.jsonparsefromassets.model.Contact
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val avengerList: ArrayList<Avenger> = ArrayList()
//        val avengerContact: ArrayList<Contact> = ArrayList()

        try {
            // instantiate a obj which called the json Object
            val obj = JSONObject(readJsonFile())
            // and fetch JSONArray avengers from JSONObject
            val avengersArray = obj.getJSONArray("avengers")

            for (item in 0 until avengersArray.length()) {
                // create a JSONObject for fetching avenger's data
                val avenger = avengersArray.getJSONObject(item)
                // fetch property from avenger class and store it in a variable
                val name = avenger.getString("name")
                val email = avenger.getString("email")
                // create a JSONObject for fetching contact's data
                val contact = avenger.getJSONObject("contact")
                val mobile = contact.getString("mobile")

                // instantiate info about mobile & and it to the Contact ArrayList()
                val avengerMobile = Contact(mobile)
//                avengerContact.add(avengerMobile)
                // instantiate info about avenger & add it to the Avenger ArrayList()
                val avengerDetails = Avenger(name, email, avengerMobile)
                avengerList.add(avengerDetails)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val name = findViewById<TextView>(R.id.nameOfAvenger)
        val mobile = findViewById<TextView>(R.id.mobileOfAvenger)
        name.text = avengerList[1].name
        mobile.text = avengerList[1].contact.mobile
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