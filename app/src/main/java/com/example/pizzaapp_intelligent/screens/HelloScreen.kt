package com.example.pizzaapp_intelligent.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pizzaapp_intelligent.PizzaList
import com.example.pizzaapp_intelligent.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloView(navController: NavHostController) {
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
                            modifier = Modifier.size(63.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "PizzaExpress",
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(Color(0xFFEA3B3B), Color(0xFF0C0A09)),
                            center = Offset(x = 500f, y = 500f),
                            radius = 1000f
                        )
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .size(400.dp)
                            .clip(CircleShape)

                            .shadow(10.dp, CircleShape)
                            .padding(2.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Welcome to PizzaExpress!",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Bouton animé
                    AnimatedOrderButton(onClick = { navController.navigate(PizzaList) })
                }
            }
        }
    )
}
@Composable
fun AnimatedOrderButton(onClick: () -> Unit) {
    // Animation de pulsation
    val infiniteTransition = rememberInfiniteTransition()

    // Animation d'échelle plus marquée
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,  // Augmentation plus marquée de la taille du bouton
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = LinearEasing), // Durée plus courte pour une animation plus rapide
            repeatMode = RepeatMode.Reverse
        )
    )

    // Animation de l'ombre plus marquée
    val shadowOffset by infiniteTransition.animateFloat(
        initialValue = 4f,
        targetValue = 16f, // Ombre plus marquée pendant l'animation
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = LinearEasing), // Durée plus courte pour une ombre dynamique plus visible
            repeatMode = RepeatMode.Reverse
        )
    )

    Button(
        onClick = onClick,
        modifier = Modifier
            .scale(scale)  // Application de l'animation de mise à l'échelle
            .clip(RoundedCornerShape(80))
            .shadow(shadowOffset.dp, RoundedCornerShape(50)),  // Ombre dynamique
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE44D26),
            contentColor = Color.White
        )
    ) {
        Text(
            "Order Now",
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}
