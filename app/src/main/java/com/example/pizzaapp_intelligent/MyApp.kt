package com.example.pizzaapp_intelligent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pizzaapp_intelligent.data.ChartViewModel
import com.example.pizzaapp_intelligent.data.PizzaViewModel
import com.example.pizzaapp_intelligent.screens.Caddy
import com.example.pizzaapp_intelligent.screens.DetailPizza
import com.example.pizzaapp_intelligent.screens.HelloView
import com.example.pizzaapp_intelligent.screens.PizzaMenu

@kotlinx.serialization.Serializable
object PizzaList

@kotlinx.serialization.Serializable
data class PizzaRoute(val idPizza : Int)

@kotlinx.serialization.Serializable
object PizzaHello

@kotlinx.serialization.Serializable
object PizzaChart


@Composable
fun MyApp(
    navController: NavHostController = rememberNavController(),
    chartViewModel : ChartViewModel = viewModel(),
    pizzaViewModel : PizzaViewModel =  viewModel()
){
    NavHost(
        navController = navController,
        startDestination = PizzaHello,
        modifier = Modifier.padding(0.dp)
    ){
        composable<PizzaHello>{
            HelloView(
                navController = navController,
            )
        }
        composable<PizzaList>{
            PizzaMenu(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                navController = navController,
                chartViewModel = chartViewModel,
                pizzaViewModel = pizzaViewModel
            )
        }
        composable<PizzaRoute>{ backStackEntry ->
            val pizzaRoute = backStackEntry.toRoute<PizzaRoute>()
            DetailPizza(
                pizza = pizzaViewModel.loadPizza(idPizza = pizzaRoute.idPizza),
                navHostController = navController,
                chartViewModel = chartViewModel,
            )
        }
        composable<PizzaChart>{
            Caddy(
                navController = navController,
                chartViewModel = chartViewModel
            )
        }
    }

}