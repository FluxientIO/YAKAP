package com.example.yakap.ui.models

import androidx.annotation.DrawableRes

data class OnboardingPage(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)
