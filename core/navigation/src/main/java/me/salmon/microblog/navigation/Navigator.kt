package me.salmon.microblog.navigation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import me.salmon.microblog.navigation.components.HomeNavigation
import javax.inject.Inject

open class Navigator @Inject constructor(val context: Context) {

    enum class Feature(var enabled: Boolean) {
        HOME(true),
        PROFILE(true),
        BLOG(true),
        COMMENT(false)
    }


    fun navigate(feature: Feature?) {
        feature?.let {
            if (it.enabled) {
                navigateTo(it)
            }
        }
    }


    fun getNavigationClass(feature: Feature?): String {
        feature?.let { feature
            when(feature) {
                Feature.HOME -> {
                    (context as? HomeNavigation)?.let {
                        return it.homeActivityClass()
                    }
                }
                Feature.PROFILE -> {
                    TODO("Not yet implemented")
                }
                Feature.BLOG -> {
                    TODO("Not yet implemented")
                }
                Feature.COMMENT -> {
                    TODO("Not yet implemented")
                }
            }
        }
        // return empty for testing purposes
        return ""
    }

    fun navigateTo(feature: Feature?) {
        feature?.let {
            val intent = Intent()
                .setComponent(ComponentName(context, getNavigationClass(it)))
            context.startActivity(intent)
        }
    }

}