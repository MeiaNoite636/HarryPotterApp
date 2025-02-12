package com.example.harrypotterapp.auth_feature.presentation.register


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.harrypotterapp.auth_feature.util.isValidEmail
import com.example.harrypotterapp.ui.theme.bodyFont
import com.example.harrypotterapp.ui.theme.colorDisabled
import com.example.harrypotterapp.ui.theme.colorTextDisabled
import com.example.harrypotterapp.ui.theme.colorTitle
import com.example.harrypotterapp.ui.theme.subTitleFont
import com.example.harrypotterapp.ui.theme.titleFont
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val registerState by registerViewModel.registerState.collectAsState()

    RegisterScreenContent(
        registerEvent = registerViewModel::handleEvent,
        registerState = registerState,
        snackBarHostState = snackBarHostState,
        coroutineScope = coroutineScope
    )

    SnackbarHost(hostState = snackBarHostState)
}

@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    registerEvent: (RegisterEvent) -> Unit,
    registerState: RegisterState,
    snackBarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    var hidePassword by remember { mutableStateOf(false) }
    var hideConfirmPassword by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(true) }

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
                    text = "Welcome",
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
                    text = "Let's create your account!",
                    style = subTitleFont,
                    color = colorTitle
                )
            }

            //Username
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                value = registerState.username,
                onValueChange = {
                    if (it.length <= 50) {
                        registerEvent(RegisterEvent.UserNameChanged(it))
                    }
                },
                shape = RoundedCornerShape(12.dp),
                label = {
                    Text(
                        text = "Username",
                        style = bodyFont
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { registerEvent(RegisterEvent.ClearUserName) }
                    ) {
                        if (registerState.username.isNotEmpty()) {
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

            //Email
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                value = registerState.email,
                onValueChange = {
                    if (it.length <= 50) {
                        registerEvent(RegisterEvent.EmailChanged(it))
                        isEmailValid = isValidEmail(it)
                    }
                },
                shape = RoundedCornerShape(12.dp),
                label = {
                    Text(
                        text = "Email",
                        style = bodyFont
                    )
                },
                supportingText = {
                    if (!isEmailValid) {
                        Text(
                            text = "Invalid format",
                            color = Color.Red,
                            fontFamily = FontFamily(Font(R.font.quicksand_bold))
                        )
                    }
                },
                placeholder = {
                    Text(
                        text = "example@gmail.com",
                        style = bodyFont
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { registerEvent(RegisterEvent.ClearEmail) }
                    ) {
                        if (registerState.email.isNotEmpty()) {
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
                                    tint = colorDisabled,
                                    modifier = modifier.size(24.dp),
                                    contentDescription = null
                                )
                            }
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
                    .padding(top = 8.dp),
                value = registerState.password,
                onValueChange = {
                    if (it.length <= 20) {
                        registerEvent(RegisterEvent.PasswordChanged(it))
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

            //Confirm Password
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                value = registerState.confirmPassword,
                onValueChange = {
                    if (it.length <= 20) {
                        registerEvent(RegisterEvent.ConfirmPasswordChanged(it))
                    }
                },
                visualTransformation = if (hideConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                label = {
                    Text(
                        text = "Confirm Password",
                        style = bodyFont
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { hideConfirmPassword = !hideConfirmPassword }
                    ) {
                        if (registerState.confirmPassword.isNotEmpty()) {
                            Icon(
                                if (hideConfirmPassword) painterResource(
                                    id = R.drawable.eye_opened_icon
                                ) else painterResource(
                                    id = R.drawable.eye_closed_icon
                                ),
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

            Box(
                modifier = Modifier
                    .padding(top = 64.dp)
                    .fillMaxWidth()
                    .height(58.dp)
                    .shadow(
                        16.dp,
                        RoundedCornerShape(12.dp),
                        ambientColor = Color(0xFFCC2FC5),
                        spotColor = Color(0xFFCC2FC5)
                    ) // Shadow effect
            ) {
                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    onClick = {
                        coroutineScope.launch {
                            when {
                                registerState.username.isBlank() -> snackBarHostState.showSnackbar("Username cannot be empty")
                                registerState.email.isBlank() -> snackBarHostState.showSnackbar("Email cannot be empty")
                                registerState.password.isBlank() -> snackBarHostState.showSnackbar("Password cannot be empty")
                                registerState.confirmPassword.isBlank() -> snackBarHostState.showSnackbar(
                                    "Confirm Password cannot be empty"
                                )

                                registerState.password != registerState.confirmPassword -> snackBarHostState.showSnackbar(
                                    "Passwords do not match"
                                )

                                else -> {
                                    registerEvent(RegisterEvent.Register)
                                    if (registerState.isSuccessful) {
                                        snackBarHostState.showSnackbar("Registration successful")
                                    } else {
                                        snackBarHostState.showSnackbar(registerState.error.toString())
                                    }
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFCC2FC5)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (registerState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 4.dp
                        )
                    } else {
                        Text(
                            text = "Register",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_bold))
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenContentPreview() {
    RegisterScreenContent(
        registerEvent = {},
        registerState = RegisterState(),
        snackBarHostState = SnackbarHostState(),
        coroutineScope = rememberCoroutineScope()
    )
}