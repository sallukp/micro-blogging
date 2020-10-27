package me.salmon.microblog

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dagger.hilt.android.AndroidEntryPoint
import me.salmon.microblog.home.HomeActivity
import me.salmon.microblog.navigation.components.HomeNavigation
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SplashActivityTest {

    @Test
    fun testHomeActivityNavigation() {
        Intents.init()
        launchActivity<SplashActivity>()
        intended(hasComponent(HomeActivity::class.java.name))
        Intents.release()
    }

}