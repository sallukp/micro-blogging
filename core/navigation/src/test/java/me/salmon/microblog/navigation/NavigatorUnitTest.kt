package me.salmon.microblog.navigation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import org.mockito.Spy
import org.powermock.api.mockito.PowerMockito.whenNew
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Navigator::class)
class NavigatorUnitTest {

    @Mock
    private val context = mock(Context::class.java)

    @Mock
    private val navigator = spy(Navigator(context))

    @Test
    fun testNavigateToHome() {
        val feature = Navigator.Feature.HOME
        testNavigate(feature, null)
    }
    @Test
    fun testNavigateToProfile() {
        val feature = Navigator.Feature.PROFILE
        testNavigate(feature, null)
    }

    @Test
    fun testNavigateToBlog() {
        val feature = Navigator.Feature.BLOG
        testNavigate(feature, null)
    }

    @Test
    fun testNavigateToComment() {
        val feature = Navigator.Feature.COMMENT
        testNavigate(feature, null)
    }

    private fun testNavigate(feature: Navigator.Feature, obj: Any?) {
        whenever(context.packageName).thenReturn(Constants.packageName)
        whenever(navigator.getNavigationClass(any())).thenReturn(Constants.homeComponent)
        val mockIntent = mock(Intent::class.java)
        whenNew(Intent::class.java).withAnyArguments().thenReturn(mockIntent)

        val mockComponent = mock(ComponentName::class.java)
        whenever(mockComponent.className).thenReturn(Constants.homeComponent)
        whenNew(ComponentName::class.java).withArguments(context, Constants.homeComponent)
            .thenReturn(mockComponent)
        whenever(mockIntent.component).thenReturn(mockComponent)
        whenever(mockIntent.setComponent(mockComponent)).thenReturn(mockIntent)

        feature.enabled = true;
        navigator.navigate(feature, obj)

        verify(navigator, times(1)).navigateTo(feature, obj)

        // test feature disabled
        feature.enabled = false
        navigator.navigate(feature, obj)
        // verifies navigateToHome is not called, times(1) because the method was called in the
        // previous step
        verify(navigator, times(1)).navigateTo(feature, obj)
    }


    @Test
    fun testStartHomeActivity() {
        whenever(context.packageName).thenReturn(Constants.packageName)
        val navigator = spy(Navigator(context))
        val feature = Navigator.Feature.HOME
        whenever(navigator.getNavigationClass(any())).thenReturn(Constants.homeComponent)
        val mockIntent = mock(Intent::class.java)
        whenNew(Intent::class.java).withAnyArguments().thenReturn(mockIntent)

        val mockComponent = mock(ComponentName::class.java)
        whenever(mockComponent.className).thenReturn(Constants.homeComponent)
        whenNew(ComponentName::class.java).withArguments(context, Constants.homeComponent)
            .thenReturn(mockComponent)

        val expectedIntent = mock(Intent::class.java)
        whenever(expectedIntent.component).thenReturn(mockComponent)

        whenever(mockIntent.setComponent(mockComponent)).thenReturn(expectedIntent)

        navigator.navigateTo(feature, null)
        val captor = ArgumentCaptor.forClass(Intent::class.java)
        Mockito.verify(context).startActivity(captor.capture())
        Assert.assertTrue(captor.value.component!!.className.contains(Constants.homeComponent))
    }
}