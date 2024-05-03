package mobi.foo.dokkapracticing.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mobi.foo.dokkapracticing.common.Constants
import mobi.foo.dokkapracticing.data.remote.ApiServices
import mobi.foo.dokkapracticing.data.repository.MainRepositoryImpl
import mobi.foo.dokkapracticing.domain.repository.MainRepository
import mobi.foo.dokkapracticing.domain.usecases.GetLaunchesUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor

    ): OkHttpClient {

        return OkHttpClient.Builder().apply {
            this.connectTimeout(60, TimeUnit.SECONDS)
            this.readTimeout(60, TimeUnit.SECONDS)
            this.addInterceptor(httpLoggingInterceptor)

        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAppApi(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideMainRepository(appServices: ApiServices): MainRepository =
        MainRepositoryImpl(appServices)

    @Provides
    @Singleton
    fun provideMainRepositoryImpl(
        appServices: ApiServices
    ): MainRepositoryImpl {
        return MainRepositoryImpl(appServices)
    }


    @Provides
    @Singleton
    fun provideGetLaunchesUseCase(
        mainRepository: MainRepository
    ): GetLaunchesUseCase {
        return GetLaunchesUseCase(mainRepository)
    }
}