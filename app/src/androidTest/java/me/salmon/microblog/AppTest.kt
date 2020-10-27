package me.salmon.microblog

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.HiltAndroidApp
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AppTest {

    @Test
    fun checkHiltAppAnnotation() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        assertTrue(appContext is App)
        assertTrue(appContext.javaClass.isAnnotationPresent(HiltAndroidApp::class.java))
    }
}