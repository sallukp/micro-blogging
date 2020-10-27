package me.salmon.microblog.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import me.salmon.microblog.network.NetworkConstants.base_url
import me.salmon.microblog.network.author.AuthorsService
import me.salmon.microblog.network.comment.CommentService
import me.salmon.microblog.network.post.PostsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {


    @Singleton
    @Provides
    fun providesGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun provideAuthorsService(retrofit: Retrofit): AuthorsService = retrofit
        .create(AuthorsService::class.java)

    @Singleton
    @Provides
    fun providePostsService(retrofit: Retrofit): PostsService = retrofit
        .create(PostsService::class.java)

    @Singleton
    @Provides
    fun provideCommentsService(retrofit: Retrofit): CommentService = retrofit
        .create(CommentService::class.java)
}