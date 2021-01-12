package com.example.adsapp.di.module


import android.content.Context
import com.example.adsapp.service.AdsService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val READ_TIME_OUT = 10000L
        const val WRITE_TIME_OUT = 10000L
        const val CONNECT_TIME_OUT = 10000L
        const val CACHE_SIZE = 20 * 1024 * 1024L //20 MiB
        const val BASE_URL = "https://gist.githubusercontent.com/baldermork/"
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun buildOkHttpClient(interceptor: Interceptor, cache: Cache)
            : OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .cache(cache)
            .build()

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideHttpInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()
                .newBuilder()
                .build()
            return@Interceptor it.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient, gson: Gson): AdsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(AdsService::class.java)
    }
}
