package com.proxym.quarantracking.common.di.module

import android.content.Context
import com.demo.data.ApiService
import com.demo.data.BuildConfig
import com.demo.data.common.CACHE_SIZE
import com.demo.mvvmcleanarchitecture.BuildConfig.BASE_URL
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesOkhttpCache(context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE.toLong())
    }
    @Provides
    fun providesOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .cache(cache)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10.toLong(), TimeUnit.SECONDS)
            .readTimeout(10.toLong(), TimeUnit.SECONDS)
            .cache(cache)



        if (BuildConfig.DEBUG)
            client.addNetworkInterceptor(StethoInterceptor())

        return client.build()
    }
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val gsonBuilder = GsonBuilder()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory((GsonConverterFactory.create(gsonBuilder.create())))
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit) : ApiService {
        return  retrofit.create(ApiService::class.java)
    }
}