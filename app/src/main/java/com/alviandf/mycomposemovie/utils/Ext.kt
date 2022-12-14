package com.alviandf.mycomposemovie.utils

fun Double?.orZero(): Double {
    return this ?: 0.0
}

fun Int?.orZero(): Int {
    return this ?: 0
}

fun Boolean?.orFalse(): Boolean {
    return this ?: false
}

fun <T> List<T?>?.orEmptyList(): List<T> {
    return this?.filterNotNull() ?: listOf()
}