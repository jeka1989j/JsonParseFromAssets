package com.example.jsonparsefromassets.model

import java.util.ArrayList

data class Contact(val mobile: String)
data class Avenger(val name: String, val email: String, val contact: Contact)
data class AvengersModelClass(val avengers: ArrayList<Avenger>)