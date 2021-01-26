package com.example.searchrecipe.models

data class Hit(
    val bookmarked: Boolean,
    val bought: Boolean,
    val recipe: Recipe
)