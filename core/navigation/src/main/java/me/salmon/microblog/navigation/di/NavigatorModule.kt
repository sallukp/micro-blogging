package me.salmon.microblog.navigation.di

import android.app.Activity
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import me.salmon.microblog.navigation.Navigator

@InstallIn(ActivityComponent::class)
@Module
object NavigatorModule {

    @Provides
    fun providesNavigator(@ActivityContext context: Context) = Navigator(context)
}