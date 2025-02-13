package com.example.harrypotterapp.auth_feature.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.harrypotterapp.R
import com.example.harrypotterapp.auth_feature.util.isValidEmail
import com.example.harrypotterapp.ui.theme.colorTextDisabled
import com.example.harrypotterapp.ui.theme.subTitleFont
import com.example.harrypotterapp.ui.theme.titleFont
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    loginToHomeGraph: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val loginState by loginViewModel.loginState.collectAsState()

    LoginScreenContent(
        onNavigateToRegister = onNavigateToRegister,
        onNavigateToForgotPassword = onNavigateToForgotPassword,
        onNavigateToHome = loginToHomeGraph,
        loginEvent = loginViewModel::handleEvent,
        loginState = loginState,
        snackBarHostState = snackBarHostState,
        coroutineScope = coroutineScope
    )

    SnackbarHost(hostState = snackBarHostState)
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToHome: () -> Unit,
    loginEvent: (LoginEvent) -> Unit,
    loginState: LoginState,
    snackBarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    var hidePassword by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background), // Substitua pela sua imagem
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        //Glass Layer
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.1f))
        )

        Column(
            modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Row(
                modifier = modifier
                    .padding(top = 48.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hello Again!",
                    style = titleFont,
                    color = Color(0xFFE4E2F0)
                )
            }

            Row(
                modifier = modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Welcome back you've\n       been missed!",
                    style = subTitleFont,
                    color = Color(0xFF8B879D)
                )
            }

            Spacer(modifier.padding(24.dp))

            //Email
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                value = loginState.email,
                onValueChange = {
                    if (it.length <= 50) {
                        loginEvent(LoginEvent.EmailChanged(it))
                        isEmailValid = isValidEmail(it)
                    }
                },
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    Text(
                        text = "example@gmail.com",
                        style = TextStyle(
                            color = Color(0xFF8B879D),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold))
                        )
                    )
                },
                label = {
                    Text(
                        text = "Email",
                        style = TextStyle(
                            color = Color(0xFFFFFFFF),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_bold))
                        )
                    )
                },
                supportingText = {
                    if (!isEmailValid) {
                        Text(
                            text = "Invalid format",
                            color = Color.Red,
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold))
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = { loginEvent(LoginEvent.ClearEmail) }
                    ) {
                        if (loginState.email.isNotEmpty()) {
                            if (!isEmailValid) {
                                Icon(
                                    Icons.Filled.Info,
                                    tint = Color.Red,
                                    modifier = modifier.size(24.dp),
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    Icons.Filled.Close,
                                    tint = Color(0xFFE4E2F0),
                                    modifier = modifier.size(24.dp),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                },
                singleLine = true,
                textStyle = TextStyle(
                    color = Color(0xFF8B879D),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold))
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Black,
                    unfocusedContainerColor = Color.Black,
                    unfocusedPlaceholderColor = colorTextDisabled,
                    focusedPlaceholderColor = colorTextDisabled,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            //Password
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                value = loginState.password,
                onValueChange = {
                    if (it.length <= 20) {
                        loginEvent(LoginEvent.PasswordChanged(it))
                    }
                },
                visualTransformation = if (hidePassword) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(16.dp),
                label = {
                    Text(
                        text = "Password",
                        style = TextStyle(
                            color = Color(0xFFFFFFFF),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_bold))
                        )
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { hidePassword = !hidePassword }
                    ) {
                        Icon(
                            if (hidePassword) painterResource(
                                id = R.drawable.eye_opened_icon
                            ) else painterResource(
                                id = R.drawable.eye_closed_icon
                            ),
                            tint = Color(0xFFE4E2F0),
                            modifier = modifier.size(24.dp),
                            contentDescription = null
                        )
                    }
                },
                singleLine = true,
                textStyle = TextStyle(
                    color = Color(0xFF8B879D),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold))
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Black,
                    unfocusedContainerColor = Color.Black,
                    unfocusedPlaceholderColor = colorTextDisabled,
                    focusedPlaceholderColor = colorTextDisabled,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Spacer(Modifier.weight(1f))
                TextButton(
                    onClick = { onNavigateToForgotPassword() }
                ) {
                    Text(
                        text = "Forgot Password",
                        style = TextStyle(
                            color = Color(0xFF8B879D),
                            fontFamily = FontFamily(Font(R.font.quicksand_bold))
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 24.dp))

            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = {
                    coroutineScope.launch {
                        when {
                            loginState.email.isBlank() -> snackBarHostState.showSnackbar("Email is empty")
                            loginState.password.isBlank() -> snackBarHostState.showSnackbar("Password is empty")
                            else -> {
                                loginEvent(LoginEvent.Login)
                                if (loginState.isSuccessful) {
                                    onNavigateToHome()
                                } else {
                                    snackBarHostState.showSnackbar(message = loginState.error.toString())
                                }
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE6764E)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (loginState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.Black,
                        strokeWidth = 4.dp
                    )
                } else {
                    Text(
                        text = "Sign in",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.quicksand_bold))
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?",
                    style = TextStyle(
                        color = Color(0xFF8B879D),
                        fontFamily = FontFamily(Font(R.font.quicksand_bold))
                    ),
                    color = Color(0xFF8B879D)
                )

                TextButton(
                    onClick = {
                        onNavigateToRegister()
                    }
                ) {
                    Text(
                        text = "Register Now",
                        style = TextStyle(
                            color = Color(0xFFE4E2F0),
                            fontFamily = FontFamily(Font(R.font.quicksand_bold))
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenContentPreview() {
    LoginScreenContent(
        onNavigateToRegister = {},
        onNavigateToForgotPassword = {},
        loginEvent = {},
        loginState = LoginState(),
        onNavigateToHome = {},
        snackBarHostState = remember { SnackbarHostState() },
        coroutineScope = rememberCoroutineScope()
    )
}