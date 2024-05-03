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

/**
 * Dagger module responsible for providing network-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides an instance of OkHttpClient with specified configurations.
     *
     * @param httpLoggingInterceptor Interceptor for logging HTTP requests and responses.
     * @return Configured OkHttpClient instance.
     */
    @Provides
    @Singleton
    fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor

    ): OkHttpClient {
        // Build OkHttpClient instance with custom configurations
        return OkHttpClient.Builder().apply {
            // Set connection timeout to 60 seconds
            this.connectTimeout(60, TimeUnit.SECONDS)
            // Set read timeout to 60 seconds
            this.readTimeout(60, TimeUnit.SECONDS)
            // Add HTTP logging interceptor for logging requests and responses
            this.addInterceptor(httpLoggingInterceptor)
        }.build() // Build the OkHttpClient instance
    }

    /**
     * Provides a Retrofit instance with a specified OkHttpClient.
     * @param okHttpClient The OkHttpClient instance to be used with Retrofit.
     * @return Retrofit instance.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides an instance of the API service interface using a Retrofit instance.
     * @param retrofit The Retrofit instance to create the API service.
     * @return An instance of the API service.
     */
    @Provides
    @Singleton
    fun provideAppApi(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }


    /**
     * Provides an instance of HttpLoggingInterceptor with logging level set to BODY.
     * @return HttpLoggingInterceptor instance.
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Provides a MainRepository instance using the provided ApiServices.
     * @param appServices The ApiServices instance to be used by MainRepository.
     * @return MainRepository instance.
     */
    @Provides
    @Singleton
    fun provideMainRepository(appServices: ApiServices): MainRepository =
        MainRepositoryImpl(appServices)

    /**
     * Provides a singleton instance of MainRepositoryImpl.
     *
     * @param appServices The ApiServices instance used by MainRepositoryImpl.
     * @return MainRepositoryImpl instance for data operations.
     */
    @Provides
    @Singleton
    fun provideMainRepositoryImpl(
        appServices: ApiServices
    ): MainRepositoryImpl {
        return MainRepositoryImpl(appServices)
    }

    /**
     * Provides an instance of GetLaunchesUseCase using MainRepository.
     *
     * @param mainRepository The MainRepository instance to be used by GetLaunchesUseCase.
     * @return GetLaunchesUseCase instance for retrieving launches data.
     */
    @Provides
    @Singleton
    fun provideGetLaunchesUseCase(
        mainRepository: MainRepository
    ): GetLaunchesUseCase {
        return GetLaunchesUseCase(mainRepository)
    }
}