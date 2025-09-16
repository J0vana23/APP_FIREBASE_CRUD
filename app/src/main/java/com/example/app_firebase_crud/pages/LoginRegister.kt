package com.example.app_firebase_crud.pages

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import com.example.app_firebase_crud.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.auth.auth



// TELA DE LOGIN
@Composable
fun LoginScreen(
    onLogin: (String) -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mostrarSenha by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val backgroundColor = Color(0xFF121212)
    val primaryColor = Color(0xFF0351C9)
    val cardBackground = Color(0xFF121212)
    val labelColor = Color.Gray

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.facebook),
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp)
        )

        Text("Login",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp, color = primaryColor,
            modifier = Modifier.padding(vertical = 24.dp))

        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp))
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email:", color = labelColor) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = cardBackground,
                unfocusedContainerColor = cardBackground,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = primaryColor,
                unfocusedIndicatorColor = Color.LightGray
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha:", color = labelColor) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = cardBackground,
                unfocusedContainerColor = cardBackground,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = primaryColor,
                unfocusedIndicatorColor = Color.LightGray
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (mostrarSenha) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { mostrarSenha = !mostrarSenha }) {
                    Icon(
                        painter = painterResource(id = if (mostrarSenha) R.drawable.olho else R.drawable.visivel),
                        contentDescription = "Toggle password visibility",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (email.isBlank() || senha.isBlank()) {
                    errorMessage = "Preencha todos os campos"
                    return@Button
                }

                val auth = Firebase.auth

                auth.signInWithEmailAndPassword(email, senha)
                    .addOnSuccessListener {
                        val user = it.user
                        val nomeUsuario = user?.email ?: "Usuário"
                        onLogin(nomeUsuario)
                    }
                    .addOnFailureListener { e ->
                        errorMessage = "Erro de login: ${e.message}"
                        Log.e("LoginScreen", "Login failed", e)
                    }

            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Entrar", fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { onRegisterClick() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = primaryColor),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, primaryColor)
        ) {
            Text("Não tem conta? Cadastre-se", fontSize = 16.sp)
        }
    }
}



//---------------------------------------------------------------------------------------------------------------------------------------------------------


//TELA DE CADASTRO
@Composable
fun RegisterScreen(
    onRegisterComplete: () -> Unit,
    onLoginClick: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val db = Firebase.firestore
    val auth = Firebase.auth

    val backgroundColor = Color(0xFF121212)
    val primaryColor = Color(0xFF0351C9)
    val White = Color.White

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("facebook",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 46.sp, color = White,
                    modifier = Modifier.padding(vertical = 5.dp))

                Image(
                    painter = painterResource(id = R.drawable.pessoa_cadastro),
                    contentDescription = "Logo",
                    modifier = Modifier.size(150.dp)
                )

                Text("Cadastro",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp, color = primaryColor,
                    modifier = Modifier.padding(vertical = 14.dp))

                if (errorMessage.isNotEmpty()) {
                    Text(errorMessage, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp))
                }

                listOf(
                    Triple("Nome:", nome, { it: String -> nome = it }),
                    Triple("Sobrenome:", sobrenome, { it: String -> sobrenome = it }),
                    Triple("Email:", email, { it: String -> email = it }),
                    Triple("Senha:", senha, { it: String -> senha = it }),
                    Triple("Telefone:", telefone, { it: String -> telefone = it }),
                ).forEach { (label, value, onChange) ->
                    StyledOutlinedTextField(
                        value = value,
                        onValueChange = onChange,
                        label = label,
                        isPassword = label == "Senha"
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (nome.isBlank() || sobrenome.isBlank() || email.isBlank() || senha.isBlank()) {
                            errorMessage = "Preencha todos os campos obrigatórios"
                            return@Button
                        }

                        auth.createUserWithEmailAndPassword(email, senha)
                            .addOnSuccessListener { authResult ->
                                val uid = authResult.user?.uid

                                val usuario = hashMapOf(
                                    "nome" to nome,
                                    "sobrenome" to sobrenome,
                                    "email" to email,
                                    "telefone" to telefone
                                )

                                if (uid != null) {
                                    db.collection("usuarios").document(uid)
                                        .set(usuario)
                                        .addOnSuccessListener {
                                            onRegisterComplete()
                                        }
                                        .addOnFailureListener { e ->
                                            errorMessage = "Erro ao salvar dados: ${e.message}"
                                            Log.e("Register", "Erro Firestore", e)
                                        }
                                }
                            }
                            .addOnFailureListener { e ->
                                errorMessage = "Erro no cadastro: ${e.message}"
                                Log.e("Register", "Erro FirebaseAuth", e)
                            }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Cadastrar", fontSize = 18.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, primaryColor)
                ) {
                    Text("Já tem uma conta? Faça login", fontSize = 16.sp, color = primaryColor)
                }

                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}


@Composable
fun StyledOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val cardBackground = Color(0xFF121212)
    val primaryColor = Color(0xFF0351C9)
    val labelColor = Color.Gray

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = labelColor) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = cardBackground,
            unfocusedContainerColor = cardBackground,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedIndicatorColor = primaryColor,
            unfocusedIndicatorColor = Color.LightGray
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text
        )
    )

}

