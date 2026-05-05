package ru.easycode.zerotoheroandroidtdd.core.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


interface Screen {

    fun show(supportFragmentManager: FragmentManager, containerId: Int)

    abstract class Replace(private val fragmentClass: Class<out Fragment>) : Screen {
        open fun args(): Bundle? = null

        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            val fragment = fragmentClass.getDeclaredConstructor().newInstance()
            args()?.let { fragment.arguments = it }
            supportFragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commit()
        }
    }

    abstract class Add(private val fragmentClass: Class<out Fragment>) : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction()
                .add(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                .addToBackStack(fragmentClass.name)
                .commit()
        }
    }

    object Pop : Screen {
        override fun show(
            supportFragmentManager: FragmentManager,
            containerId: Int
        ) {
            supportFragmentManager.popBackStack()
        }
    }
}
