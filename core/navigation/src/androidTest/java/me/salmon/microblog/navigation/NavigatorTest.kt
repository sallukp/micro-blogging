package me.salmon.microblog.navigation

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import me.salmon.microblog.navigation.test.helpers.HomeActivity
import me.salmon.microblog.navigation.test.helpers.UnImplementedActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NavigatorTest {

    @Before
    fun setUp() {

    }

    @Test
    fun testNavigationClass() {
        launchActivity<HomeActivity>().use { scenario ->
            scenario.onActivity {
                assertEquals(Constants.homeComponent, it.navigator.getNavigationClass(Navigator.Feature.HOME))
            }
        }
        launchActivity<UnImplementedActivity>().use { scenario ->
            scenario.onActivity {
                assertNotEquals(Constants.homeComponent, it.navigator.getNavigationClass(Navigator.Feature.HOME))
            }
        }
    }
}