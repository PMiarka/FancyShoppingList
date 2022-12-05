package com.fansymasters.shoppinglist.data.lists

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

fun Category.toDisplayText(): String =
    when (this) {
        Category.VEG_AND_FRUITS -> "Vegetables&Fruits"
        Category.WATER_DRINKS -> "Drinks"
        Category.CANDY -> "Candy"
        Category.FISHES -> "Fishes"
        Category.SPICES -> "Species"
        Category.OTHER -> "Other"
    }
