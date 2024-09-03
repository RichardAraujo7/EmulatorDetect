package br.com.impacta.emudetector.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController, auth: FirebaseAuth) {
    val user = auth.currentUser
    val userEmail = user?.email ?: ""

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menu", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF003366)),
                navigationIcon = {
                    IconButton(onClick = {
                        auth.signOut()
                        navController.navigate("login")
                    }) {
                        Icon(Icons.Filled.ExitToApp, contentDescription = "Sair", tint = Color.White)                     }
                }
            )
        },
        containerColor = Color(0xFF003366)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(userEmail, style = MaterialTheme.typography.headlineMedium, color = Color.White)
                Spacer(modifier = Modifier.height(32.dp))
            }
            items(menuItems) { item ->
                MenuItem(item)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

data class MenuItem(
    val title: String,
    val icon: ImageVector
)

val menuItems = listOf(
    MenuItem("Meu Perfil", Icons.Filled.Person),
    MenuItem("Minhas Configurações", Icons.Filled.Settings),
    MenuItem("Central de Ajuda", Icons.Outlined.QuestionMark),
    MenuItem("Sobre o App", Icons.Filled.Info)
)

@Composable
fun MenuItem(item: MenuItem) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth(0.8f),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004080))
    ) {
        Icon(item.icon, contentDescription = item.title, modifier = Modifier.size(ButtonDefaults.IconSize), tint = Color.White)
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(item.title, fontWeight = FontWeight.Bold, color = Color.White)
    }
}