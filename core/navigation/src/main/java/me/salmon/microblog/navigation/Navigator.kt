package me.salmon.microblog.navigation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import me.salmon.microblog.models.Author
import me.salmon.microblog.navigation.components.HomeNavigation
import me.salmon.microblog.navigation.components.ProfileNavigation
import java.lang.Exception
import javax.inject.Inject


open class Navigator @Inject constructor(val context: Context) {

    enum class Feature(var enabled: Boolean) {
        HOME(true),
        PROFILE(true),
        BLOG(true),
        COMMENT(false),
        MAP(true),
        EMAIL(true)
    }


    fun navigate(feature: Feature?, obj: Any?) {
        feature?.let {
            if (it.enabled) {
                navigateTo(it, obj)
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
                    (context as? ProfileNavigation)?.let {
                        return it.profileActivityClass()
                    }
                }
                Feature.BLOG -> {
                    TODO("Not yet implemented")
                }
                Feature.COMMENT -> {
                    TODO("Not yet implemented")
                }
                Feature.MAP -> {

                }
                Feature.EMAIL -> {

                }
            }
        }
        // return empty for testing purposes
        return ""
    }

    fun navigateTo(feature: Feature?, obj: Any?) {
        feature?.let { feature->
            when(feature) {
                Feature.EMAIL -> {
                    (obj as? String)?.let {
                        openEmail(it)
                    }
                }
                Feature.MAP -> {
                    (obj as? Author)?.let { author ->
                        openMap(author)
                    }
                }
                else -> {
                    val intent = Intent()
                        .setComponent(ComponentName(context, getNavigationClass(feature)))
                    putExtra(feature, intent, obj)
                    context.startActivity(intent)
                    (context as? AppCompatActivity)?.let { activity ->
                        activity.overridePendingTransition(0, 0)
                    }
                }
            }
        }
    }

    private fun openEmail(email: String) {
        try {
            val uri = Uri.parse("mailto:${email}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (_: Exception) {

        }

    }

    private fun putExtra(feature: Feature, intent: Intent, obj: Any?) {
        obj?.let {
            when(feature) {
                Feature.PROFILE -> {
                    intent.putExtra(Constants.authorExtra, obj as Parcelable)
                }
                else -> {
                    // do nothing
                }
            }
        }
    }

    private fun openMap(author: Author) {
        try {
            val mapUri = "geo:${author.lat},${author.long}?q=${author.lat},${author.long}(${author.name})"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUri))
            context.startActivity(intent)
        } catch (_: Exception) {
            try {
                val uri = "https://maps.google.com/maps?saddr=${author.lat},${author.long}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                context.startActivity(intent)
            } catch (_: Exception) { }
        }
    }

}