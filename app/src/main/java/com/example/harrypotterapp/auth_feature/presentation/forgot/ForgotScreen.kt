package com.example.harrypotterapp.auth_feature.presentation.forgot

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
fun ForgotScreen(
    modifier: Modifier = Modifier,
    forgotViewModel: ForgotViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val forgotState = forgotViewModel.forgotState.collectAsState().value

    ForgotScreenContent(
        modifier = modifier,
        forgotState = forgotState,
        forgotEvent = forgotViewModel::handleEvent,
        snackBarHostState = snackBarHostState,
        coroutineScope = coroutineScope
    )

    SnackbarHost(hostState = snackBarHostState)
}

@Composable
fun ForgotScreenContent(
    modifier: Modifier = Modifier,
    forgotState: ForgotState,
    forgotEvent: (ForgotEvent) -> Unit,
    snackBarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
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
                .background(Color.White.copy(alpha = 0.09f))
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                modifier = modifier.padding(top = 40.dp),
                text = "Hello Again!",
                style = titleFont,
                color = Color(0xFFE4E2F0)
            )

            Text(
                text = "Please enter your account details for recovery",
                style = subTitleFont,
                color = Color(0xFF8B879D)
            )

            //Email
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                value = forgotState.email,
                onValueChange = {
                    if (it.length <= 50) {
                        forgotEvent(ForgotEvent.EmailChanged(it))
                        isEmailValid = isValidEmail(it)
                    }
                },
                shape = RoundedCornerShape(16.dp),
                label = {
                    Text(
                        text = "Email",
                        style = TextStyle(
                            color = Color(0xFFE4E2F0),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold))
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = "example@gmail.com",
                        style = TextStyle(
                            color = Color(0xFFE4E2F0),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold))
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
                            onClick = { forgotEvent(ForgotEvent.ClearEmail) }
                    ) {
                        if (forgotState.email.isNotEmpty()) {
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
                    color = Color(0xFFE4E2F0),
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

            Spacer(modifier = modifier.padding(top = 96.dp))

            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = {
                    coroutineScope.launch {
                        when {
                            forgotState.email.isBlank() -> snackBarHostState.showSnackbar("Email is empty")
                            else -> {
                                forgotEvent(ForgotEvent.Forgot)
                                if (forgotState.isSuccessful) {
                                    snackBarHostState.showSnackbar(message = "Email sent successfully")
                                } else {
                                    snackBarHostState.showSnackbar(message = forgotState.error.toString())
                                }
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE6764E)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                if (forgotState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 4.dp
                    )
                } else {
                    Text(
                        text = "Send Email",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.quicksand_bold))
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ForgotScreenPreview() {
    ForgotScreenContent(
        forgotState = ForgotState(),
        forgotEvent = { },
        snackBarHostState = SnackbarHostState(),
        coroutineScope = rememberCoroutineScope()
    )
}
