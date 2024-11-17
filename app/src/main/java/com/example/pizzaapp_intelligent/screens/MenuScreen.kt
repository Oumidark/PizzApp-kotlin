package com.example.pizzaapp_intelligent.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pizzaapp_intelligent.PizzaChart
import com.example.pizzaapp_intelligent.PizzaRoute
import com.example.pizzaapp_intelligent.R
import com.example.pizzaapp_intelligent.data.ChartViewModel
import com.example.pizzaapp_intelligent.data.PizzaViewModel
import com.example.pizzaapp_intelligent.model.Pizza
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaCard(
    pizza: Pizza,
    modifier: Modifier = Modifier,
    onClickAction: () -> Unit
) {
    // Animation states
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }

    // AnimatedVisibility for entry animation
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        Card(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = onClickAction,
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White // Changer en blanc
            )
        )
        {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = pizza.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(pizza.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(230.dp)
                        .clip(RoundedCornerShape(12.dp))

                )
                Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Price: ${pizza.price}€",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp // Augmente la taille du texte
                            )
                        )

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MutableCollectionMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PizzaMenu(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    chartViewModel: ChartViewModel,
    pizzaViewModel: PizzaViewModel
) {
    val menu = pizzaViewModel.pizzas.observeAsState(emptyList())
    val choices by chartViewModel.choices.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "App Logo",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "PizzaApp",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            var scale by remember { mutableStateOf(1f) }
            FloatingActionButton(
                onClick = {
                    scale = if (scale == 1f) 1.2f else 1f
                    navController.navigate(PizzaChart)
                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(56.dp * scale),
                containerColor = MaterialTheme.colorScheme.secondary,
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Check order",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center,
            )
            {
                LazyColumn(modifier = modifier) {
                    items(menu.value) { pizza ->
                        PizzaCard(
                            pizza = pizza,
                            onClickAction = {
                                navController.navigate(PizzaRoute(menu.value.indexOf(pizza)))
                            }
                        )
                    }
                }
            }
        }
    )
}
