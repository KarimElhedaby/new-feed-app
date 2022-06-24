package com.newfeeds.core.utils

import android.app.Activity
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController

fun FragmentManager.replaceFragment(
    fragment: Fragment,
    @IdRes containerId: Int,
    addToBackStack: Boolean = true,
) {
    beginTransaction()

        .replace(containerId, fragment)
        .apply {
            if (addToBackStack) addToBackStack(null)
        }
        .commit()
}

fun FragmentManager.addFragment(
    fragment: Fragment,
    @IdRes containerId: Int,
    addToBackStack: Boolean = true,

) {
    beginTransaction()
        .add(containerId, fragment)
        .apply {
            if (addToBackStack) addToBackStack(null)
        }
        .commit()


    /**
     * navigate using activity navigation controller*/
    fun Activity.navigate(
        navController: NavController,
        @StringRes navId: Int
    ) {
        navController.navigate(navId)
    }
}