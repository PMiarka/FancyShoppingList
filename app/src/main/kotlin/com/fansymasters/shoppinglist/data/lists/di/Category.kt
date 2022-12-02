package com.fansymasters.shoppinglist.data.lists.di

enum class Category(val apiKey: String) {
    VEG_AND_FRUITS("VegAndFruits"),
    WATER_DRINKS("WaterDrinks"),
    CANDY("Candy"),
    FISHES("Fishes"),
    SPICES("Spices"),
    OTHER("Other")
}

fun String.mapToCategory(): Category =
    Category.values().firstOrNull { it.apiKey.uppercase() == this } ?: Category.OTHER