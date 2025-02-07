package com.example.harrypotterapp.auth_feature.presentation.register

//if password = confirmPassword{ RegisterEvent}

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.example.harrypotterapp.auth_feature.presentation.login.LoginEvent
import com.example.harrypotterapp.auth_feature.presentation.login.LoginState
import com.example.harrypotterapp.auth_feature.presentation.login.LoginViewModel
import com.example.harrypotterapp.ui.theme.bodyFont
import com.example.harrypotterapp.ui.theme.colorDisabled
import com.example.harrypotterapp.ui.theme.colorTextDisabled
import com.example.harrypotterapp.ui.theme.colorTitle
import com.example.harrypotterapp.ui.theme.subTitleFont
import com.example.harrypotterapp.ui.theme.titleFont

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    loginToHomeGraph: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }

    LoginScreenContent(
        onNavigateToRegister = onNavigateToRegister,
        onNavigateToForgotPassword = onNavigateToForgotPassword,
        loginEvent = loginViewModel::handleEvent,
        loginState = loginViewModel.loginState.collectAsState().value
    )

    //Mesma logica para sharedPreferences
    LaunchedEffect(Unit) {
        loginViewModel.loginState.collect { loginState ->
            if (loginState.isSuccessful) {
                loginToHomeGraph()
            } else {
                loginState.error?.let {
                    snackBarHostState.showSnackbar(it)
                }
            }
        }
    }

    SnackbarHost(hostState = snackBarHostState)
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    loginEvent: (LoginEvent) -> Unit,
    loginState: LoginState
) {
    var hidePassword by remember { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFEEEAEA), // Cor F3EEF2
                        Color(0xFFECEDF5)  // Cor EBF1F7
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                )
            )
    ) {
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
                    color = colorTitle
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
                    color = colorTitle
                )
            }

            //Email
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                value = loginState.email,
                onValueChange = {
                    if (it.length <= 50) {
                        loginEvent(LoginEvent.EmailChanged(it))
                    }
                },
                shape = RoundedCornerShape(12.dp),
                label = {
                    Text(
                        text = "Email",
                        style = bodyFont
                    )
                },
                placeholder = {
                    Text(
                        text = "example@gmail.com",
                        style = bodyFont
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { loginEvent(LoginEvent.ClearEmail) }
                    ) {
                        if (loginState.isNotEmpty()) {
                            Icon(
                                Icons.Filled.Close,
                                tint = colorDisabled,
                                modifier = modifier.size(24.dp),
                                contentDescription = null
                            )
                        }
                    }
                },
                singleLine = true,
                textStyle = TextStyle(
                    color = colorTitle,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.quicksand_bold))
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFFEFEFD),
                    unfocusedContainerColor = Color.White,
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
                    .padding(top = 16.dp),
                value = loginState.password,
                onValueChange = {
                    if (it.length <= 20) {
                        loginEvent(LoginEvent.PasswordChanged(it))
                    }
                },
                visualTransformation = if (hidePassword) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                label = {
                    Text(
                        text = "Password",
                        style = bodyFont
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
                            tint = colorDisabled,
                            modifier = modifier.size(24.dp),
                            contentDescription = null
                        )
                    }
                },
                singleLine = true,
                textStyle = TextStyle(
                    color = colorTitle,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.quicksand_bold))
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFFEFEFD),
                    unfocusedContainerColor = Color.White,
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
                        text = "Recovery password",
                        style = TextStyle(
                            color = Color(0xFF79797C),
                            fontFamily = FontFamily(Font(R.font.quicksand_bold))
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .shadow(
                        16.dp,
                        RoundedCornerShape(12.dp),
                        ambientColor = Color(0xFFCC2FC5),
                        spotColor = Color(0xFFCC2FC5)
                    ) // Sombra destacada
            ) {
                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    onClick = {
                        loginEvent(LoginEvent.Login)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFCC2FC5)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (loginState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 4.dp
                        )
                    } else {
                        Text(
                            text = "Login",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_bold))
                        )
                    }
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
                        color = Color(0xFF79797C),
                        fontFamily = FontFamily(Font(R.font.quicksand_bold))
                    ),
                    color = colorTitle
                )

                TextButton(
                    onClick = {
                        onNavigateToRegister()
                    }
                ) {
                    Text(
                        text = "Register Now",
                        style = TextStyle(
                            color = Color(0xFFCC2FC5),
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
        loginState = LoginState()
    )
}