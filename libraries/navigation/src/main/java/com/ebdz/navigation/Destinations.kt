package com.ebdz.navigation

/**
 * Represents the possible destinations of App via Compose Navigation. The destinations represents
 * a flow where a backstack is required, so the tabs inside the Home destination is a simple
 * navigation.
 */
object Destinations {

    /**
     * Home destination.
     */
    const val Home = "home"

    /**
     * Settings destination.
     */
    const val Settings = "settings"

    /**
     * About destination.
     */
    const val About = "about"
}
