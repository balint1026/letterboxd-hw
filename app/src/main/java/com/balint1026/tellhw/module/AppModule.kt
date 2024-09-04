package com.balint1026.tellhw.module

import com.balint1026.tellhw.repositories.FirestoreRepository
import com.balint1026.tellhw.repositories.MovieAPI
import com.balint1026.tellhw.repositories.MovieRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun providesRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply { this.level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun providesFirestoreRepository(): FirestoreRepository{
        return FirestoreRepository()
    }

    @Singleton
    @Provides
    fun providesMovieRepository(movieAPI: MovieAPI): MovieRepository{
        return MovieRepository(movieAPI)
    }


    @Singleton
    @Provides
    fun providesMovieAPI(retrofit: Retrofit): MovieAPI{
        return retrofit.create(MovieAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesAuthInterceptor(): AuthInterceptor{
        return AuthInterceptor()
    }

}