package com.example.pizzaapp_intelligent.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pizzaapp_intelligent.R
import com.example.pizzaapp_intelligent.model.Pizza


class PizzaViewModel : ViewModel() {
    private val _pizzas = MutableLiveData<List<Pizza>>()
    val pizzas: LiveData<List<Pizza>> = _pizzas

    init {
          loadPizzas()
    }

    private fun loadPizzas() {
        _pizzas.value = listOf(
            Pizza("Margherita", 8.0, R.drawable.pizza1),
            Pizza("Capricciosa", 10.0, R.drawable.pizza2),
            Pizza("Diavola", 9.0, R.drawable.pizza3),
            Pizza("Quattro Stagioni", 11.0, R.drawable.pizza4),
            Pizza("Quattro Formaggi", 12.0, R.drawable.pizza5),
            Pizza("Marinara", 7.0, R.drawable.pizza6),
            Pizza("Pepperoni", 9.0, R.drawable.pizza7),
            Pizza("Prosciutto", 10.0, R.drawable.pizza8),
            Pizza("Frutti di Mare", 13.0, R.drawable.pizza9)
        )
    }

    fun loadPizza(idPizza: Int): Pizza {
        return _pizzas.value!![idPizza]
    }
}


class ChartViewModel : ViewModel() {
    private val _choices = MutableLiveData<MutableList<Pair<Pizza, Int>>?>(mutableListOf())
    val choices: LiveData<MutableList<Pair<Pizza, Int>>?> = _choices

    fun addToChart(pizzaAndCheese: Pair<Pizza, Int>) {
        val updatedChoices = _choices.value?.toMutableList() ?: mutableListOf()
        updatedChoices.add(pizzaAndCheese)
        _choices.value = updatedChoices
    }

    fun deleteFromChart(pizzaAndCheese: Pair<Pizza, Int>){
        val updatedChoices = _choices.value?.toMutableList() ?: mutableListOf()
        val index = updatedChoices.indexOf(pizzaAndCheese)
        updatedChoices.removeAt(index)
        _choices.value = updatedChoices
    }

    fun clearChart() {
       _choices.value = mutableListOf()
    }
}