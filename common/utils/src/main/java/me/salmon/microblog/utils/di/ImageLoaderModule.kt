package me.salmon.microblog.utils.di

import android.content.Context
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped
import me.salmon.microblog.utils.GlideApp

@InstallIn(FragmentComponent::class)
@Module
object ImageLoaderModule {

    @FragmentScoped
    @Provides
    fun provideGlideRequests(@ActivityContext context: Context): RequestManager = GlideApp.with(context)
}