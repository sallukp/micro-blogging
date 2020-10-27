package me.salmon.microblog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import me.salmon.microblog.home.HomeActivity
import me.salmon.microblog.navigation.Navigator
import me.salmon.microblog.navigation.components.HomeNavigation
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity(), HomeNavigation {

    @Inject
    lateinit var navigator: Navigator

    public override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        navigateToHome()
    }

    fun navigateToHome() {
        navigator.navigate(Navigator.Feature.HOME, null)
        finish()
    }

    override fun homeActivityClass(): String = HomeActivity::class.java.name
}