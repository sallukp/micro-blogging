package me.salmon.microblog

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.nhaarman.mockitokotlin2.whenever
import com.nhaarman.mockitokotlin2.any
import me.salmon.microblog.navigation.Navigator
import me.salmon.microblog.navigation.components.HomeNavigation
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashActivityUnitTest {

    @Spy
    private val splashActivity = spy(SplashActivity::class.java)

    @Mock
    private val context = mock(Context::class.java)
    @Spy
    private val navigator = spy(Navigator(context))

    @Before
    fun setUp() {
        splashActivity.navigator = navigator
    }

    @Test
    fun checkClassExtendedHomeNavigation() {
        assertTrue(splashActivity is HomeNavigation)
    }

    @Test
    fun testHomeNavigationImplementation() {
        assertEquals("me.salmon.microblog.home.HomeActivity",
            splashActivity.homeActivityClass())
    }

    @Test
    fun testOnCreate() {
        val delegate = mock(AppCompatDelegate::class.java)
        whenever(splashActivity.delegate).thenReturn(delegate)
        doNothing().whenever(splashActivity).navigateToHome()
        splashActivity.onPostCreate(null)
        verify(splashActivity).navigateToHome()
    }

    @Test
    fun testNavigateToHome() {
        doNothing().whenever(splashActivity).finish()
        whenever(navigator.navigate(any())).then {  }
        splashActivity.navigateToHome()
        val captor = ArgumentCaptor.forClass(Navigator.Feature::class.java)
        verify(navigator).navigate(captor.capture())
        assertEquals(Navigator.Feature.HOME, captor.value)
        assertNotEquals(Navigator.Feature.PROFILE, captor.value)
        assertNotEquals(Navigator.Feature.BLOG, captor.value)
        assertNotEquals(Navigator.Feature.COMMENT, captor.value)
        verify(splashActivity).finish()
    }
}