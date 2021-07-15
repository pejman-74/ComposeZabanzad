package com.composezabanzad.ui

import androidx.navigation.compose.NamedNavArgument

sealed class ScreenRoute : Route() {

    object Home : ScreenRoute() {
        override fun route(): String = "Home"
    }

}

abstract class Route {

    abstract fun route(): String

    open fun arguments(): List<NamedNavArgument> = emptyList()

    fun getFullRoute(): String = buildString {
        append(route())
        arguments().forEach { arg ->
            append("/{${arg.name}}")
        }
    }

    fun getNavigableRoute(vararg argValue: Any): String = buildString {
        append(route())
        argValue.forEach { value ->
            append("/${value}")
        }
    }
}