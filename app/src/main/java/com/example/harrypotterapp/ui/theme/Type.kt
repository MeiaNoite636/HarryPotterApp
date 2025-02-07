package com.example.harrypotterapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp
import com.example.harrypotterapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val titleFont = TextStyle(
    fontFamily = FontFamily(Font(R.font.quicksand_bold)),
    fontSize = 28.sp
)

val subTitleFont = TextStyle(
    fontFamily = FontFamily(Font(R.font.quicksand_medium)),
    fontSize = 20.sp
)



val bodyFont = TextStyle(
    fontFamily = FontFamily(Font(R.font.quicksand_bold)),
    color = Color(0xFFBDBDC1),
    fontSize = 16.sp
)

val MyTypography = Typography(
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.quicksand_regular)),
        fontSize = 14.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.quicksand_medium)),
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.quicksand_bold)),
        fontSize = 14.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.quicksand_medium)),
        fontSize = 20.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.quicksand_medium)),
        fontSize = 24.sp
    )




)