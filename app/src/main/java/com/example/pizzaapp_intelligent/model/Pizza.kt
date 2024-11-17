package com.example.pizzaapp_intelligent.model

@kotlinx.serialization.Serializable
data class Pizza(val name : String, val price : Double, val image: Int)