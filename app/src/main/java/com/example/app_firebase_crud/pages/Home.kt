package com.example.app_firebase_crud.pages

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.app_firebase_crud.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

enum class HomeViewState {
    HOME,
    LISTAGEM
}

@Composable
fun HomeScreen(
    userName: String,
    onLogout: () -> Unit
) {
    var viewState by remember { mutableStateOf(HomeViewState.HOME) }
    var mostrarDialogLogout by remember { mutableStateOf(false) }
    val db = Firebase.firestore
    val banco = remember { mutableStateListOf<Map<String, Any>>() }

    val backgroundColor = Color(0xFF121212)
    val primaryColor = Color(0xFF0351C9)
    val textColor = Color.White
    val cardBackground = Color(0xFF1E1E1E)

    if (mostrarDialogLogout) {
        AlertDialog(
            onDismissRequest = { mostrarDialogLogout = false },
            title = {
                Text(
                    text = "Sair",
                    fontWeight = FontWeight.Bold,

                )
            },
            text = {
                Text(
                    text = "Tem certeza que deseja sair?",

                )
            },
            confirmButton = {
                TextButton(onClick = {
                    mostrarDialogLogout = false
                    onLogout()
                }) {
                    Text("Sim", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogLogout = false }) {
                    Text("Cancelar"
                    )
                }
            },
            containerColor = cardBackground,
            titleContentColor = primaryColor,
            textContentColor = textColor
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            when (viewState) {
                HomeViewState.HOME -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.usuario),
                            contentDescription = "Home",
                            modifier = Modifier.size(200.dp),
                        )

                        Text(
                            text = "Bem-vindo, $userName!",
                            fontSize = 32.sp,
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(top = 24.dp, bottom = 24.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "Para ver a lista de usuários, clique no menu abaixo!",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(45.dp))

                        Image(
                            painter = painterResource(id = R.drawable.face),
                            contentDescription = "Facebook",
                            modifier = Modifier
                                .size(150.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // 🔷 Botão de Sair
                        OutlinedButton(
                            onClick = { mostrarDialogLogout = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = primaryColor,
                                containerColor = Color.Transparent
                            ),
                            border = BorderStroke(1.dp, primaryColor)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.sair),
                                contentDescription = "Imagem de sair",
                                modifier = Modifier
                                    .size(20.dp) // ajuste o tamanho conforme necessário
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("SAIR", fontWeight = FontWeight.Bold)
                        }
                    }
                    }

                HomeViewState.LISTAGEM -> {
                    Image(
                        painter = painterResource(id = R.drawable.lista_user),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = "facebook",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 46.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(24.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    banco.forEachIndexed { index, registro ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(cardBackground, shape = RoundedCornerShape(8.dp))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "Registro ${index + 1}",
                                color = primaryColor,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text("Nome: ${registro["nome"]}", color = textColor)
                            Text("Sobrenome: ${registro["sobrenome"]}", color = textColor)
                            Text("Email: ${registro["email"]}", color = textColor)
                            Text("Senha: ${registro["senha"]}", color = textColor)
                            Text("Telefone: ${registro["telefone"]}", color = textColor)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // 🔻 Rodapé com dois botões (Home, Listar)
        FooterNavigation(
            currentView = viewState,
            onHomeClick = { viewState = HomeViewState.HOME },
            onUserListClick = {
                db.collection("banco")
                    .get()
                    .addOnSuccessListener { result ->
                        banco.clear()
                        result.forEach { banco.add(it.data) }
                        viewState = HomeViewState.LISTAGEM
                    }
                    .addOnFailureListener {
                        Log.e("Firestore", "Erro ao buscar registros", it)
                    }
            }
        )
    }
}

// 🔻 NOVO COMPONENTE DE RODAPÉ
@Composable
fun FooterNavigation(
    currentView: HomeViewState,
    onHomeClick: () -> Unit,
    onUserListClick: () -> Unit
) {
    val primaryColor = Color(0xFF0351C9)
    val selectedColor = primaryColor
    val unselectedColor = Color.LightGray

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        color = Color(0xFF000000),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = onHomeClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "Home",
                        modifier = Modifier.size(28.dp),
                        tint = if (currentView == HomeViewState.HOME) selectedColor else unselectedColor
                    )
                }
                Text(
                    text = "Home",
                    color = if (currentView == HomeViewState.HOME) selectedColor else unselectedColor,
                    fontSize = 12.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = onUserListClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.lista1),
                        contentDescription = "Listar",
                        modifier = Modifier.size(28.dp),
                        tint = if (currentView == HomeViewState.LISTAGEM) selectedColor else unselectedColor
                    )
                }
                Text(
                    text = "Listar",
                    color = if (currentView == HomeViewState.LISTAGEM) selectedColor else unselectedColor,
                    fontSize = 12.sp
                )
            }
        }
    }
}
