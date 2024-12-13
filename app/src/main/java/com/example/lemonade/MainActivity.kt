package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                LemonadeApp()

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    // Variables d'état pour suivre la progression
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    var requiredSqueezes by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center, // Centrer le titre
                        modifier = Modifier.fillMaxWidth() // Assurer le centrage
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFEB3B))
            )
        },
        content = { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Cadre autour de l'image
                    Box(
                        modifier = Modifier
                            .size(220.dp) // Taille du cadre
                            .background(
                                color = Color(0x80C3ECD2), // Couleur de fond (vert clair)
                                shape = RoundedCornerShape(16.dp) // Coins arrondis
                            )
                            .clickable {
                                // Logique pour les clics
                                when (currentStep) {
                                    1 -> {
                                        // Passer au citron
                                        currentStep = 2
                                        requiredSqueezes = (2..4).random()
                                        squeezeCount = 0
                                    }
                                    2 -> {
                                        // Presser le citron
                                        squeezeCount++
                                        if (squeezeCount >= requiredSqueezes) {
                                            currentStep = 3
                                        }
                                    }
                                    3 -> {
                                        // Boire la citronnade
                                        currentStep = 4
                                    }
                                    4 -> {
                                        // Recommencer
                                        currentStep = 1
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center // Centrer l'image dans le cadre
                    ) {
                        // Image cliquable
                        Image(
                            painter = painterResource(
                                id = when (currentStep) {
                                    1 -> R.drawable.lemon_tree
                                    2 -> R.drawable.lemon_squeeze
                                    3 -> R.drawable.lemon_drink
                                    else -> R.drawable.lemon_restart
                                }
                            ),
                            contentDescription = stringResource(
                                id = when (currentStep) {
                                    1 -> R.string.lemon_tree
                                    2 -> R.string.lemon
                                    3 -> R.string.glass_of_lemonade
                                    else -> R.string.empty_glass
                                }
                            ),
                            modifier = Modifier.size(200.dp) // Taille de l'image
                        )
                    }

                    // Texte descriptif sous l'image
                    Spacer(modifier = Modifier.height(16.dp)) // Espacement entre l'image et le texte
                    Text(
                        text = when (currentStep) {
                            1 -> stringResource(R.string.tap_lemon_tree)
                            2 -> stringResource(R.string.keep_tapping_lemon)
                            3 -> stringResource(R.string.tap_lemonade)
                            else -> stringResource(R.string.tap_empty_glass)
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center, // Centrer le texte
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    )
}


