package com.composezabanzad.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.composezabanzad.R

val bTitr = FontFamily(Font(R.font.titr_bold))

val Typography
    get() = Typography(
        h1 = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 96.sp,
        ),
        h2 = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 60.sp,
        ),
        h3 = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 48.sp,
        ),
        h4 = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 34.sp,
        ),
        h5 = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 24.sp,
        ),
        h6 = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 20.sp,
        ),
        subtitle1 = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 16.sp,
        ),
        subtitle2 = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 14.sp,
        ),
        body1 = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 16.sp,
        ),
        body2 = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 14.sp,
        ),
        button = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 14.sp,
        ),
        caption = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 12.sp,
        ),
        overline = TextStyle(
            color = Color.White,
            fontFamily = bTitr,
            fontSize = 10.sp,
        )
    )